package BM;

//import java.awt.*;
import javax.swing.*;
import javax.swing.JTable;

public class BudgetManagement {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Budget Management");
        mainFrame.setLocation(100, 20);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lTest = new JLabel("porca troietta");

        BMTableModel tableModel = new BMTableModel();
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);//table.getColumnModel().getColumn(1).getPreferredWidth()+100
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(110);
        table.getColumnModel().getColumn(4).setPreferredWidth(110);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel mainPanel = (JPanel) mainFrame.getContentPane();//come se fosse un puntatore al panel predefinito del JFrame
        mainPanel.add(scrollPane); 
        //mainFrame.add(mainPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
