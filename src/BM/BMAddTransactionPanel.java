package BM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

public class BMAddTransactionPanel extends JPanel implements ActionListener {
    private final JLabel addLbl = new JLabel("Insert details of a new transaction:");
    private final BorderLayout panelLayout;
    private final BoxLayout centerLayout;
    private final FlowLayout topLayout;
    private final JPanel topPanel, centerPanel;
    
    private final JButton addBtn;
    private final JTextField descTxt, amountTxt;
    private final JXDatePicker insertXDP;
    
    private BMTableModel tableModel;

    public BMAddTransactionPanel(BMTableModel tableModel) {
        this.tableModel = tableModel;
        
        topLayout = new FlowLayout(FlowLayout.LEFT);
        topPanel = new JPanel();
        topPanel.setLayout(topLayout);
        topPanel.add(addLbl);
        
        insertXDP = new JXDatePicker();
        insertXDP.setDate(Calendar.getInstance().getTime());
        insertXDP.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        
        descTxt = new JTextField("Transaction description",50);
        //descTxt.setPreferredSize(new Dimension(300, 25));
        amountTxt = new JTextField("Amount",10);
        
        centerPanel = new JPanel();
         centerLayout = new BoxLayout(centerPanel, BoxLayout.X_AXIS);
        centerPanel.setLayout(centerLayout);
        centerPanel.add(insertXDP);
        centerPanel.add(descTxt);
        centerPanel.add(amountTxt);
        
        addBtn = new JButton("Add Transaction");
        addBtn.addActionListener(this);
        
        panelLayout = new BorderLayout();
        setLayout(panelLayout);

        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(addBtn, BorderLayout.LINE_END);
        add(Box.createRigidArea(new Dimension(0,10)), BorderLayout.PAGE_END);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if( !descTxt.getText().equals("") && !amountTxt.getText().equals("") && !(insertXDP.getDate() == null)) {
            Double amount;
            try {
                amount = Double.parseDouble(amountTxt.getText());
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.set(ERROR, WIDTH, WIDTH);
                insertXDP.getDate().getTime();
                BMItem item = new BMItem(calendar, descTxt.getText(), amount);
                tableModel.addItem(item);
            }
            catch (NumberFormatException numberException){
                JOptionPane.showMessageDialog(this, "You have to insert a number in the amount field.", "Insertion warning", JOptionPane.WARNING_MESSAGE);
            }
            
        }
    }
}