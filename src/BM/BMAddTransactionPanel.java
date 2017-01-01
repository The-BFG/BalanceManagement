package BM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class BMAddTransactionPanel extends JPanel{
    private final JLabel addLbl = new JLabel("Insert details of a new transaction:");
    private final BorderLayout panelLayout;
    private final FlowLayout topLayout, centerLayout;
    private final JPanel topPanel, centerPanel;
    
    private final JButton addBtn;
    private final JTextField descTxt, amountTxt;
    private final UtilDateModel insertModel;
    private final JDatePanelImpl insertDatePanel;
    private final JDatePickerImpl insertDatePicker;
    private final Properties p;

    public BMAddTransactionPanel() {
        topLayout = new FlowLayout(FlowLayout.LEFT);
        topPanel = new JPanel();
        topPanel.setLayout(topLayout);
        topPanel.add(addLbl);
        
        insertModel = new UtilDateModel();
        insertModel.setDay((new GregorianCalendar().get(Calendar.DAY_OF_MONTH)));
        insertModel.setMonth((new GregorianCalendar().get(Calendar.MONTH)));
        insertModel.setYear((new GregorianCalendar().get(Calendar.YEAR)));
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        insertDatePanel = new JDatePanelImpl(insertModel, p);
        insertDatePicker = new JDatePickerImpl(insertDatePanel, new BMDateFormatter());
        insertDatePicker.setMaximumSize(new Dimension(150,15));
        
        descTxt = new JTextField("Transaction description", 30);
        //descTxt.setFont(new Font("Bigger",));
        amountTxt = new JTextField("Amount", 10);
        centerLayout = new FlowLayout(FlowLayout.LEFT);
        centerPanel = new JPanel();
        centerPanel.setLayout(centerLayout);
        centerPanel.add(insertDatePicker);
        centerPanel.add(descTxt);
        centerPanel.add(amountTxt);
        
        addBtn = new JButton("Add Transaction");
        
        panelLayout = new BorderLayout();
        setLayout(panelLayout);

        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(addBtn, BorderLayout.LINE_END);
    }
    
}