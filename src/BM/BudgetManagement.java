package BM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.BoxLayout;

/**
 * Classe principale che contiene il main al suo interno e crea e inizializza tutti i pannelli principali.
 * 
 * @author giacomo
 */
public class BudgetManagement {
    private static BMMenuBar menu;
    
    /**
     * Main method che inizializza tutta l'interfaccia grafica e i JFrame che contiene tutti i pannelli principali.
     * 
     * <br>I principali pannelli che vengono creati sono:
     * <br>-{@link BM.BMMenuBar};
     * <br>-{@link BM.BMPeriodPanel};
     * <br>-{@link BM.BMTableFinder};
     * <br>-{@link BM.BMTablePanel};
     * <br>-{@link BM.BMAddTransactionPanel}.
     * @param args 
     */
    public static void main(String[] args)  {
        JFrame mainFrame = new JFrame("Budget Management");
        mainFrame.setLocation(100, 20);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BMTablePanel table = new BMTablePanel();
        BMPeriodPanel period = new BMPeriodPanel(table);
        BMTableFinder searchBar = new BMTableFinder(table);
        BMAddTransactionPanel addTransaction = new BMAddTransactionPanel(table);
        
        menu = new BMMenuBar(table);
        mainFrame.setJMenuBar(menu);
        
        JPanel topPanel = new JPanel();        
        BoxLayout topLayout = new BoxLayout(topPanel, BoxLayout.X_AXIS);
        topPanel.setLayout(topLayout);
        topPanel.add(period);
        topPanel.add(searchBar);
        
        JPanel mainPanel = (JPanel) mainFrame.getContentPane();  
        BorderLayout mainLayout = new BorderLayout();
        mainPanel.setLayout(mainLayout);        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(Box.createRigidArea(new Dimension(5,0)), BorderLayout.LINE_START);
        mainPanel.add(table, BorderLayout.CENTER);
        mainPanel.add(Box.createRigidArea(new Dimension(5,0)), BorderLayout.LINE_END);
        mainPanel.add(addTransaction, BorderLayout.SOUTH);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
