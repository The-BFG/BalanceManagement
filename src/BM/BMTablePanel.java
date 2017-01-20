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
    
    public BMTablePanel() {
        scrollPane = new JScrollPane();
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        tableModel = new BMTableModel();
        tableSorter = new TableRowSorter<BMTableModel>(tableModel);
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        //table.addFocusListener(WHEN_FOCUSED);
        
        table.setRowSorter(tableSorter);  
        tableSorter.setSortable(0, false); 
        tableSorter.setSortable(1, false); 
        tableSorter.setSortable(2, false); 
        tableSorter.setSortable(3, false);
        
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        
        table.getColumnModel().getColumn(0).setMinWidth(90);
        table.getColumnModel().getColumn(0).setMaxWidth(90);        
        table.getColumnModel().getColumn(2).setMinWidth(160);
        table.getColumnModel().getColumn(2).setMaxWidth(160);
        table.getColumnModel().getColumn(3).setMinWidth(50);
        table.getColumnModel().getColumn(3).setMaxWidth(50);
        
        scrollPane.getViewport().add(table);  
        
        totalTxt = new JTextField(20);
        totalTxt.setEditable(false);
        JPanel totalPanel = new JPanel();
        FlowLayout totalLayout = new FlowLayout(FlowLayout.LEFT);
        totalPanel.setLayout(totalLayout);
        totalPanel.add(totalLbl);
        totalPanel.add(totalTxt);
        
        editBtn = new JButton();
        editBtn = loadIcon(editBtn,"edit.png");
        deleteBtn = new JButton();
        deleteBtn = loadIcon(deleteBtn,"trash.png");
        JPanel editPanel = new JPanel();
        BoxLayout editLayout = new BoxLayout(editPanel, BoxLayout.X_AXIS);
        editPanel.setLayout(editLayout);
        editPanel.add(editBtn);
        editPanel.add(Box.createRigidArea(new Dimension(5,0)));
        editPanel.add(deleteBtn);
        
        bottomPanel = new JPanel();
        bottomLayout = new BoxLayout(bottomPanel,BoxLayout.X_AXIS);
        bottomPanel.setLayout(bottomLayout);
        bottomPanel.add(totalPanel);
        bottomPanel.add(Box.createHorizontalGlue());//Box.createRigidArea(new Dimension(300,0)));
        bottomPanel.add(editPanel);
        this.refreshTotal();
              
        tablePanelLayout = new BorderLayout();
        this.setLayout(tablePanelLayout);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);       
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

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
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
        //btn.setBorder(null);
        btn.setContentAreaFilled(true);
        btn.addActionListener(this);
        btn.setEnabled(false);
        return btn;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
      
    }
}