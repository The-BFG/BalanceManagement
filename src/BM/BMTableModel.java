package BM;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 * Classe che gestisce il modello del <a href="../BM/BMTablePanel">BMTablePanel</a> estendendo un modello astratto.
 * 
 * <br>In questa classe vengono implementati tutti i metodi astratti dell' AbstractTableModel in modo tale da avere
 * un modello personalizzato e piu efficiente per la situazione.
 * @author giacomo
 */
public class BMTableModel extends AbstractTableModel implements Serializable{
    private static final long serialVersionUID = 1L;

    private ArrayList<BMItem> transactions = null;
    private final String[] colName = {"Data","Descrizione","Ammontare"};
    private ArrayList<boolean[]> editable;
    
    private BMTablePanel table;
    
    /**
     * Costruttore della classe <a href="../BM/BMTableModel">BMTableModel</a> che inizializza i due ArrayList di <a href="../BM/BMItem">BMItem</a> e di booleani.
     * 
     *<br> L'ArrayList delle transazioni è quello che viene mostrato nella JTable mentre
     * quello di booleani serve per verificare che una cella della JTable sia editabile oppure no.
     * @param table 
     */
    BMTableModel(BMTablePanel table) {
        this.table = table;
        transactions = new ArrayList<>();
        editable = new ArrayList<boolean[]>();
    }

    /**Metodo get per il numero delle colonne della tabella.
     *
     * @return Integer che contiene il numero di colonne.
     */
    @Override
    public int getColumnCount() {
        return colName.length;
    }
    
    /**
     * Metodo get per il numero di righe della tabella.
     * 
     * @return Integer che contiene il numero di righe.
     */
    @Override
    public int getRowCount() {
        return transactions.size();
    }
    
    /**
     * Metodo utilizzato dalla JTable per sapere che valore deve mettere all'interno delle celle.
     * 
     * @param row Indice di riga della JTable
     * @param col Indice di colonna della JTable
     * @return Object che dipende dalla cella che viene ritornata.
     */
    @Override
    public Object getValueAt(int row, int col) {
        BMItem item = transactions.get(row);
        switch (col) {
            case 0: return BMItem.dateFormat.format(item.getDate().getTime());
            case 1: return item.getDescription(); 
            case 2: return item.getAmount(); 
            default: return ""; 
        }
    }
    
    /**
     * Metodo get per ottenere l'intestazione di colonna della JTable.
     * 
     * @param col indice di colonna.
     * @return String contenente il titolo della colonna.
     */
    @Override
    public String getColumnName(int col) {
        return colName[col];
    }
    
    /**
     * Metodo set per modificare l'attributo editabilo o no di una cella.
     * 
     * <br>Viene utilizzato per avere l'abilitazione a modificare una cella.
     * @param edit array di booleani che dice quali colonne della riga possono essere abilitate alla modifica.
     * @param row Indice di riga.
     */
    public void setEditable(boolean[] edit, int row){
        this.editable.set(row, edit);
    }
    
    /**
     * Utilizzato dalla JTable per verificare che una cella sia editabile o no.
     * 
     * @param row Indice di riga
     * @param col Indice di colonna
     * @return Boolean che è vero nel caso in cui la cella sia editabile e falso altrimenti.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return editable.get(row)[col];
    }
    
    /**
     * Metodo piu importante del <a href="../BM/BMTableModel">BMTableModel</a> in quanto gestisce la modifica di una cella.
     * 
     * <br>In questo metodo vengono verificati tutti i parametri che vengono modificati quando la cella è abilitata alla modifica.
     * @param field Oggetto contenuto nella cella dopo la modifica
     * @param row Indice di riga.
     * @param col Indice di colonna.
     */
    @Override
    public void setValueAt(Object field, int row, int col) {
        BMItem item= transactions.get(row);
        boolean[] element = {false, false, false};
        switch(col) {
            /**Date field*/
            case 0:
                String possibleDate = (String) field;
                if(isDateValid(possibleDate)) {
                    GregorianCalendar date = new GregorianCalendar();
                    date.set( Integer.parseInt(possibleDate.substring(6, 10)), (Integer.parseInt(possibleDate.substring(3, 5)) - 1), Integer.parseInt(possibleDate.substring(0, 2)));
                    item.setDate(date);
                }
                else {
                    item.setDate(item.getDate());
                    JOptionPane.showMessageDialog(null, "La data che e' stata inserita non e' corretta.", "Errore data", JOptionPane.WARNING_MESSAGE);
                }
                break;
            /**Description field*/
            case 1:
                item.setDescription((String)field);
                break;
            /**Amount field*/
            case 2:
                try {
                item.setAmount(Double.parseDouble(String.format("%1$.2f", Double.parseDouble((String)field)).replace(',', '.')));
                }
                catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Il valore della transazione inserito non e' corretto.", "Errore ammontare", JOptionPane.WARNING_MESSAGE);
                }
                break;
            /**Default*/
            default:      
                break;
        }
        this.setEditable(element, row);
        table.refreshTotal();
        fireTableDataChanged();
    } 
    
    /**
     * Metodo per l'eliminazione di una riga all'interno dell'ArrayList di transazioni e della JTable.
     * 
     * @param row Indice di riga da eliminare
     */
    public void removeRow(int row) {
        transactions.remove(row);
        this.fireTableRowsDeleted(row, row);
    }
    
    /**
     * Metodo per pulire l'ArrayList di transazioni e la JTable collegata ad esso.
     */
    public void resetTableModel() {
        if(this.getRowCount() > 0) {
            transactions.clear();        
            this.fireTableRowsDeleted(0, 0);
        }
    }
    
    /**
     * Metodo utilizzato per aggiungere un elemento all'interno del <a href="../BM/BMTableModel">BMTableModel</a>.
     * 
     * @param item <a href="../BM/BMItem">BMItem</a> che viene passato.
     */
    public void addItem(BMItem item) {
        transactions.add(item);
        fireTableDataChanged();
        boolean[] element = {false, false, false};
        editable.add(element);
    }
    
    /**
     * Metodo get per ottenere l'ArrayList di transazioni del modello.
     * 
     * @return ArrayList contenente tutte le transazioni.
     */
    public ArrayList<BMItem> getTransactionsList() {
        return transactions;
    }
    
    /**
     * Metodo set per aggiungere alle transazioni un insieme di nuove transazioni.
     * 
     * @param transactions ArrayList di <a href="../BM/BMItem">BMItem</a>
     */
    public void setTransactionList(ArrayList<BMItem> transactions) {
        this.transactions.addAll(transactions);
        boolean[] element = {false, false, false};
        
        for(int i=0;i<this.transactions.size();i++) {
            editable.add(element);
        } 
        fireTableDataChanged();
    }
    
    /**
     * Metodo di verifica della data quando viene modifica dall'interno della JTable.
     * 
     * @param date Stringa contenente una possibile data che deve essere verificata.
     * @return Boolean vero se è una data l'elemento che gli è stato passato, falso altrimenti.
     */
    public static boolean isDateValid(String date) {
        try {
            DateFormat df = BMItem.dateFormat;
            df.setLenient(false);
            df.parse(date);
            return true;
        }
        catch (ParseException e) {
            return false;
        }
    }
}