package BM;

import java.awt.BorderLayout;
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
    private static final long serialVersionUID = 1L;
    private JScrollPane scrollPane;
    private BorderLayout tablePanelLayout;
    private BMTableModel tableModel; 
    private JTable table;
    private TableRowSorter<BMTableModel> tableSorter;
    
    private JPanel totalPanel;
    private BoxLayout totalLayout;
    private final JLabel totalLbl = new JLabel("Totale delle transazioni: ");
    private JTextField totalTxt;
    
    public BMTablePanel() {
        scrollPane = new JScrollPane();
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        tableModel = new BMTableModel();
        tableSorter = new TableRowSorter<BMTableModel>(tableModel);
        
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        
        table.setRowSorter(tableSorter);  
        tableSorter.setSortable(0, false); 
        tableSorter.setSortable(1, false); 
        tableSorter.setSortable(2, false); 
        tableSorter.setSortable(3, false);
        
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        
        table.getColumnModel().getColumn(0).setMinWidth(90);
        table.getColumnModel().getColumn(0).setMaxWidth(90);        
        //table.getColumnModel().getColumn(1).setPreferredWidth(500);//table.getColumnModel().getColumn(1).getPreferredWidth()+100
        table.getColumnModel().getColumn(2).setMinWidth(160);
        table.getColumnModel().getColumn(2).setMaxWidth(160);
        table.getColumnModel().getColumn(3).setMinWidth(50);
        table.getColumnModel().getColumn(3).setMaxWidth(50);
        
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
              
        tablePanelLayout = new BorderLayout();
        this.setLayout(tablePanelLayout);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(totalPanel, BorderLayout.SOUTH);       
    }
    
    public JTable getTable() {
        return table;
    }
    public void setPeriodFilter(RowFilter<BMTableModel,Object> rf) {
        tableSorter.setRowFilter(rf);
    }
    private Double getTotal() {
        Double totalTransaction = new Double(0);
        for(int i=0; i<table.getRowCount(); i++)
            totalTransaction += Double.parseDouble((table.getValueAt(i, 2)).toString());
        return totalTransaction;
    }  
    public final void refreshTotal() {
        totalTxt.setText(String.format("%.2f", getTotal()));
    }
}