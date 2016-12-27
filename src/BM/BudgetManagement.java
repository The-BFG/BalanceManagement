package BM;

import java.awt.GridLayout;
import javax.swing.*;

public class BudgetManagement {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Budget Management");
        mainFrame.setLocation(100, 20);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BMPeriodPanel period = new BMPeriodPanel();
        BMTablePanel table = new BMTablePanel();
        
        //JPanel mainPanel = new JPanel();//(JPanel) mainFrame.getContentPane();//come se fosse un puntatore al panel predefinito del JFrame
        
        //GridLayout mainLayout = new GridLayout(2,1);
        //mainLayout.addLayoutComponent("period", period);
        //mainLayout.addLayoutComponent("table", table);
        //mainPanel.setLayout(mainLayout);
        mainFrame.add(period);
        mainFrame.add(table);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
