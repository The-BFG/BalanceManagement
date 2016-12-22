package BM;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import 

public class BudgetManagement {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Budget Management");
        mainFrame.setLocation(100, 20);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel periodLbl = new JLabel("Choose the transactions period you want to see:");
        JLabel fromLbl = new JLabel("From:");
        JLabel toLbl = new JLabel("To:");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        frame.add(datePicker);
        
        ArrayList<BMItem> list = new ArrayList<>();
        BMItem trans1 = new BMItem(new GregorianCalendar(), "Prova1", 100.1);
        BMItem trans2 = new BMItem(new GregorianCalendar(), "Prova2", 20.1);
        list.add(trans1);
        list.add(trans2);

        TableModel tableModel = new BMTableModel(list);
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);//table.getColumnModel().getColumn(1).getPreferredWidth()+100
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel mainPanel = (JPanel) mainFrame.getContentPane();//come se fosse un puntatore al panel predefinito del JFrame
        mainPanel.add(scrollPane);
        
        GridLayout mainLayout = new GridLayout(3,1);
        mainLayout.addLayoutComponent("", new JLabel("dio"));
        mainLayout.addLayoutComponent("", mainPanel);
        mainFrame.setLayout(mainLayout);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
