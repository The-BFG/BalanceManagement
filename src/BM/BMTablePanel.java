package BM;

import java.awt.BorderLayout;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter; 

public class BMTablePanel extends JPanel {
    private JScrollPane scrollPane;
    private BorderLayout tablePanelLayout;
    private BMTableModel tableModel; 
    private JTable table;
    private TableRowSorter<BMTableModel> tableSorter;
    
    private JPanel totalPanel;
    private BoxLayout totalLayout;
    private final JLabel totalLbl = new JLabel("Totale delle transazioni mostrate: ");
    private JTextField totalTxt;
    
    public BMTablePanel() {
        scrollPane = new JScrollPane();
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        BMItem trans1 = new BMItem(new GregorianCalendar(), "Prova1", 100.1);
        BMItem trans2 = new BMItem(new GregorianCalendar(), "Prova2", 20.1);
        BMItem trans3 = new BMItem(new GregorianCalendar(2012, 12, 1), "Prova3", 100.31);
        BMItem trans4 = new BMItem(new GregorianCalendar(2017, 1, 10), "Prova4", 20.3);
        BMItem trans5 = new BMItem(new GregorianCalendar(2017, 2, 24), "Prova5", 20.1);

        tableModel = new BMTableModel();
        tableSorter = new TableRowSorter<BMTableModel>(tableModel);
        tableModel.addItem(trans1);
        tableModel.addItem(trans2);
        tableModel.addItem(trans3); 
        tableModel.addItem(trans4);
        tableModel.addItem(trans5);
        table = new JTable(tableModel);
        table.setRowSorter(tableSorter);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);//table.getColumnModel().getColumn(1).getPreferredWidth()+100
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        
        scrollPane.getViewport().add(table);  
        
        totalTxt = new JTextField(30);
        totalTxt.setEditable(false);
        totalPanel = new JPanel();
        totalLayout = new BoxLayout(totalPanel,BoxLayout.X_AXIS);
        totalPanel.setLayout(totalLayout);
        //totalPanel.add(Box.createRigidArea(new Dimension((table.getColumnModel().getColumn(0).getWidth() + (table.getColumnModel().getColumn(1).getWidth()/2)),0)));
        totalPanel.add(totalLbl);
        totalPanel.add(totalTxt);
        this.refreshTotal();
        /*totalModel = new DefaultTableModel(1,2);
        totalModel.setValueAt(" Totale", 0, 0);
        totalModel.setValueAt(this.getTotal(), 0, 1);
        
        total = new JTable(totalModel) { 
            @Override
            public boolean isCellEditable(int row, int column) {                
                return false; 
            }
        };  
        total.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        total.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        total.setCellSelectionEnabled(false);*/        
        tablePanelLayout = new BorderLayout();
        this.setLayout(tablePanelLayout);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(totalPanel, BorderLayout.SOUTH);       
    }
    
    public JTable getTable() {
        return table;
    }
    public void setPeriodFilter(RowFilter rf) {
        tableSorter.setRowFilter(rf);
    }
    private Double getTotal() {
        Double totalTransaction=0.0;
        for(int i=0; i<table.getRowCount(); i++)
            totalTransaction += Double.parseDouble((table.getValueAt(i, 2)).toString());
        return totalTransaction;
    }
    
    public final void refreshTotal() {
        totalTxt.setText(getTotal().toString());
    }
}