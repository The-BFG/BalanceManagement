package BM;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

public class BMTableModel extends AbstractTableModel implements Serializable{
    private static final long serialVersionUID = 1L;

    private ArrayList<BMItem> transactions = null;
    private final String[] colName = {"Data","Descrizione","Ammontare"};
    private ArrayList<boolean[]> editable;
    
    private BMTablePanel table;
    
    BMTableModel(BMTablePanel table) {
        this.table = table;
        transactions = new ArrayList<>();
        editable = new ArrayList<boolean[]>();
    }

    /**Get method for the number of column
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return colName.length;
    }
    @Override
    public int getRowCount() {
        return transactions.size();
    }
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
    
    @Override
    public String getColumnName(int col) {
        return colName[col];
    }
    
    public void setEditable(boolean[] edit, int row){
        this.editable.set(row, edit);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return editable.get(row)[col];
    }
    
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
                    JOptionPane.showMessageDialog(null, "La data che è stata inserita non è corretta.", "Errore data", JOptionPane.WARNING_MESSAGE);
                }
                break;
            /**Description field*/
            case 1:
                item.setDescription((String)field);
                break;
            /**Amount field*/
            case 2:
                try {
                item.setAmount(Double.parseDouble(String.format("%1$.2f",Double.parseDouble(((String)field).replace(',', '.')))));
                }
                catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Il valore della transazione inserito non è corretto.", "Errore ammontare", JOptionPane.WARNING_MESSAGE);
                }
                break;
            /**Default*/
            default:      
                break;
        }
        this.setEditable(element, row);
        fireTableDataChanged();
    } 
    
    public void removeRow(int row) {
        transactions.remove(row);
        this.fireTableRowsDeleted(row, row);
    }
    
    public void resetTableModel() {
        if(this.getRowCount() > 0) {
            transactions.clear();        
            this.fireTableRowsDeleted(0, 0);
        }
    }
    
    public void addItem(BMItem item) {
        transactions.add(item);
        fireTableDataChanged();
        boolean[] element = {false, false, false};
        editable.add(element);
    }
    
    public ArrayList<BMItem> getTransactionsList() {
        return transactions;
    }
    
    public void setTransactionList(ArrayList<BMItem> transactions) {
        this.transactions.addAll(transactions);
        boolean[] element = {false, false, false};
        
        for(int i=0;i<this.transactions.size();i++) {
            editable.add(element);
        } 
        fireTableDataChanged();
    }
    
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