package BM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter; 

/**
 * Pannello principale che ha il compito di mostrare tutto cio che contiene l'archivio.
 * 
 * <br>Il JPanel piu esterno è separato in due sezioni una contiene un JScrollPanel dove all'interno c'e una <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html">JTable</a>
 * e il rispettivo modello: <a href="../BM/BMTableModel">BMTableModel</a>.<br>
 * La seconda sezione del pannello è composta da una JTextField contenente il totale di tutte le transasazioni visualizzate 
 * nella <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html">JTable</a> e al lato opposto troviamo i bottoni per la modifica e per l'eliminazione di una riga della <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html">JTable</a>.<br>
 * @see javax.swing.JPanel, java.awt.event.ActionListener, java.awt.event.ListSelectionListener
 * @author giacomo
 */
public class BMTablePanel extends JPanel implements ActionListener, ListSelectionListener{
    private static final long serialVersionUID = 1L;
    private JScrollPane scrollPane;
    private BorderLayout tablePanelLayout;
    private BMTableModel tableModel; 
    private JTable table;
    private TableRowSorter<BMTableModel> tableSorter;
    
    private JPanel bottomPanel;
    private BoxLayout bottomLayout;
    private final JLabel totalLbl = new JLabel("Totale delle transazioni: ");
    private JTextField totalTxt;
    private JButton editBtn, deleteBtn;
    private JLabel riga = new JLabel("");
    
    /**
     * Costruttore della classe <a href="../BM/BMTablePanel">BMTabelPanel</a> che inizializza tutti gli oggetti.
     * 
     * <br>La tabella che viene creata inizialmente è vuota e vengono disabilitati gli ordinamenti automatici 
     * delle intestazioni delle colonne, come anche i bottoni di cancellazine e modifica che sono disabilitati
     * fino a quando non viene insierita e selezionata una riga.
     */
    public BMTablePanel() {
        scrollPane = new JScrollPane();
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        tableModel = new BMTableModel(this);
        tableSorter = new TableRowSorter<BMTableModel>(tableModel);
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getSelectionModel().addListSelectionListener(this);
        
        table.setRowSorter(tableSorter);  
        tableSorter.setSortable(0, false); 
        tableSorter.setSortable(1, false); 
        tableSorter.setSortable(2, false);
        
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        
        table.getColumnModel().getColumn(0).setMinWidth(90);
        table.getColumnModel().getColumn(0).setMaxWidth(90);        
        table.getColumnModel().getColumn(2).setMinWidth(160);
        table.getColumnModel().getColumn(2).setMaxWidth(160);
        
        scrollPane.getViewport().add(table);  
        
        totalTxt = new JTextField(20);
        totalTxt.setEditable(false);
        JPanel totalPanel = new JPanel();
        FlowLayout totalLayout = new FlowLayout(FlowLayout.LEFT);
        totalPanel.setLayout(totalLayout);
        totalPanel.add(totalLbl);
        totalPanel.add(totalTxt);
        
        editBtn = new JButton();
        editBtn.setActionCommand("edit");
        editBtn = loadIcon(editBtn,"edit.png");
        deleteBtn = new JButton();
        deleteBtn.setActionCommand("delete");
        deleteBtn = loadIcon(deleteBtn,"trash.png");
        JPanel editPanel = new JPanel();
        BoxLayout editLayout = new BoxLayout(editPanel, BoxLayout.X_AXIS);
        editPanel.setLayout(editLayout);
        editPanel.add(riga);
        editPanel.add(Box.createRigidArea(new Dimension(10,0)));
        editPanel.add(editBtn);
        editPanel.add(Box.createRigidArea(new Dimension(5,0)));
        editPanel.add(deleteBtn);
        
        bottomPanel = new JPanel();
        bottomLayout = new BoxLayout(bottomPanel,BoxLayout.X_AXIS);
        bottomPanel.setLayout(bottomLayout);
        bottomPanel.add(totalPanel);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(editPanel);
        this.refreshTotal();
              
        tablePanelLayout = new BorderLayout();
        this.setLayout(tablePanelLayout);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);       
    }
    
    /**
     * Metodo get() per ottenere la tabella di questo pannello.
     * 
     * @return table intera tabella gestita da questo pannello.
     */
    public JTable getTable() {
        return table;
    }
    
    /**
     * Metodo per settare il filtro del <a href="../BM/BMTableModel">BMTableModel</a> in base alla data.
     * 
     * @param rf Filtro per visualizzare solo alcuni elementi all'interno della <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html">JTable</a>.
     * @see javax.swing.RowFilter.
     */
    public void setPeriodFilter(RowFilter<BMTableModel,Object> rf) {
        tableSorter.setRowFilter(rf);
    }
    
    /**
     * Metodo per leggere il valore totale di tutte le transazioni che sono mostrate nel <a href="../BM/BMTablePanel">BMTablePanel</a>
     * 
     * @return somma valore con precisione Double
     */
    private Double getTotal() {
        Double totalTransaction = new Double(0);
        for(int i=0; i<table.getRowCount(); i++)
            totalTransaction += Double.parseDouble((table.getValueAt(i, 2)).toString());
        return totalTransaction;
    }  
    
    /**
     * Aggiornamento del valore contenuto nella JTextField contenente il totale delle transazioni visualizzate.
     */
    public final void refreshTotal() {
        totalTxt.setText(String.format("%.2f", getTotal()));
    }
    
    /**
     * Metodo per la gestione delle azioni che vengono abilitate attraverso il click dei bottoni di modifica e cancellazione.
     * 
     * <br>Il bottone di modifica abilita la cella al doppio click e alla modifica mentre
     * quello di cancellazione elimina la riga selezionata.
     * @param e ActionEvent per catturare l'evento del click dei bottoni di modifica e cancellazione.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "edit":
                if(table.getSelectedRow() != -1) {
                    boolean [] item = {true, true, true};
                    tableModel.setEditable(item, table.getSelectedRow());
                }
                break;
            case "delete":
                if(table.getSelectedRow() != -1) {
                    tableModel.removeRow(table.getSelectedRow());
                }
                break;
            default:
        }
        this.disableButton();
        this.refreshTotal();
    }

    /**
     * Metodo che cattura l'evento di selezione di una riga nella <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html">JTable</a> e abilita i bottoni di modifica e cancellazione.
     * @param e evento per gestire la selezione di una riga.
     * @see java.awt.event.ListSelectionListener.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(table.getSelectedRow() == -1)
            return;
        riga.setText("Riga selezionata: " + (table.getSelectedRow()+1));
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
    }   
    
    /**
     * Metodo che gestisce il caricamento dell'icona all'interno del bottone passato in input.
     * 
     * <br>Vengono inoltre cambiati alcuni attributi per migliorare la visualizzazione.
     * 
     * @param btn riferimento al bottone standard creato precedentemente.
     * @param imageName nome dell'immagine che deve essere caricata all'interno del bottone come icona.
     * @return JButton con attributi standard modificati.
     */
    private JButton loadIcon(JButton btn, String imageName) {
        Image img = null;
        try {
                img =   ( ImageIO
                        .read(new FileInputStream(((System.getProperty("user.dir").endsWith("class")) ? "../icon/"+imageName : "./icon/"+imageName))))
                        .getScaledInstance(25, 25, Image.SCALE_DEFAULT);
            } 
        catch (IOException ex) {
            System.out.println("Impossibile caricare l'icona del searchBtn\n" + ex);
        }
        btn.setIcon(new ImageIcon(img));
        btn.setBackground(null);
        btn.setContentAreaFilled(true);
        btn.addActionListener(this);
        btn.setEnabled(false);
        return btn;
    }
    
    /**
     * Disabilita i bottoni del <a href="../BM/BMTablePanel">BMTablePanel</a>.
     * 
     * <br>Viene utilizzata per dissabilitare i bottoni di modifica e cancellazione quando nessuna riga è
     * selezionata all'interno del <a href="#table">table</a>.
     */
    public void disableButton() {
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
}