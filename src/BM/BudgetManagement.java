package BM;

import javax.swing.*;

public class BudgetManagement {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Budget Management");
        mainFrame.setLocation(100, 20);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BMPeriodPanel period = new BMPeriodPanel();
        BMTablePanel table = new BMTablePanel();
        
        JPanel mainPanel = (JPanel) mainFrame.getContentPane();//come se fosse un puntatore al panel predefinito del JFrame
        
        BoxLayout mainLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(mainLayout);        
        mainPanel.add(period);
        mainPanel.add(table);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
