package BM;

import java.util.GregorianCalendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class BMTablePanel extends JScrollPane {
    private BMTableModel tableModel; 
    private JTable table;
    private TableRowSorter<BMTableModel> tableSorter;
    
    public BMTablePanel() {
        
        BMItem trans1 = new BMItem(new GregorianCalendar(), "Prova1", 100.1);
        BMItem trans2 = new BMItem(new GregorianCalendar(), "Prova2", 20.1);
        BMItem trans3 = new BMItem(new GregorianCalendar(2010, 12, 1), "Prova3", 20.1);
        BMItem trans4 = new BMItem(new GregorianCalendar(2017, 1, 10), "Prova4", 20.1);
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
        
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);//table.getColumnModel().getColumn(1).getPreferredWidth()+100
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        
        this.getViewport().add(table);        
    }
    
    public JTable getTable() {
        return table;
    }
    public void setPeriodFilter(RowFilter rf) {
        tableSorter.setRowFilter(rf);
    }
    public Double getTotal() {
        Double total=0.0;
        for(int i=0; i<table.getRowCount(); i++)
            total += Double.parseDouble((table.getValueAt(i, 2)).toString());
        return total;
    }
}