//import java.awt.*;
import javax.swing.*;
import javax.swing.JTable;
import BM.BMTableModel;
//import javax.swing.JFrame;

public class BudgetManagement {
	public static void main(String[] args) {
            JFrame mainFrame = new JFrame("Budget Management");
            mainFrame.setLocation(100, 20);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
            JLabel lTest = new JLabel("porca troietta");
            
            BMTableModel tableModel = new BMTableModel();
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            
            JPanel mainPanel = (JPanel) mainFrame.getContentPane();//come se fosse un puntatore al panel predefinito del JFrame
            mainPanel.add(scrollPane); 
            //mainFrame.add(mainPanel);
            
            mainFrame.pack();
            mainFrame.setVisible(true);
	}
}
