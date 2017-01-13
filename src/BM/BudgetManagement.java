package BM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class BudgetManagement {
    public static final Color PANEL_COLOR = Color.pink;
        private static BMMenuBar menu;
    
    public static void main(String[] args)  {
        JFrame mainFrame = new JFrame("Budget Management");
        mainFrame.setLocation(100, 20);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BMTablePanel table = new BMTablePanel();
        BMPeriodPanel period = new BMPeriodPanel(table);
        BMAddTransactionPanel addTransaction = new BMAddTransactionPanel(table);
        
        menu = new BMMenuBar(table);
        mainFrame.setJMenuBar(menu);
        
        JPanel mainPanel = (JPanel) mainFrame.getContentPane();  
        BorderLayout mainLayout = new BorderLayout();
        mainPanel.setLayout(mainLayout);        
        mainPanel.add(period, BorderLayout.NORTH);
        mainPanel.add(Box.createRigidArea(new Dimension(5,0)), BorderLayout.LINE_START);
        mainPanel.add(table, BorderLayout.CENTER);
        mainPanel.add(Box.createRigidArea(new Dimension(5,0)), BorderLayout.LINE_END);
        mainPanel.add(addTransaction, BorderLayout.SOUTH);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
