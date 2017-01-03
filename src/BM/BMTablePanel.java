package BM;

import java.util.GregorianCalendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BMTablePanel extends JScrollPane {
    private BMTableModel tableModel; 
    private JTable table;
    
    
    public BMTablePanel() {
        
        BMItem trans1 = new BMItem(new GregorianCalendar(), "Prova1", 100.1);
        BMItem trans2 = new BMItem(new GregorianCalendar(), "Prova2", 20.1);

        tableModel = new BMTableModel();
        tableModel.fireTableDataChanged();
        tableModel.addItem(trans1);
        tableModel.addItem(trans2);
        table = new JTable(tableModel);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);//table.getColumnModel().getColumn(1).getPreferredWidth()+100
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        
        this.getViewport().add(table);        
    }
    
    public JTable getTable() {
        return table;
    }
}