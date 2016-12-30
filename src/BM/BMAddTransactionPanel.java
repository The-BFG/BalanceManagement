package BM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
import org.jdesktop.swingx.JXDatePicker;

public class BMAddTransactionPanel extends JPanel implements ActionListener {
    private final JLabel addLbl = new JLabel("Insert details of a new transaction:");
    private final BorderLayout panelLayout;
    private final FlowLayout topLayout, centerLayout;
    private final JPanel topPanel, centerPanel;
    
    private final JButton addBtn;
    private final JTextField descTxt, amountTxt;
    private final UtilDateModel insertModel;
    private final JDatePanelImpl insertDatePanel;
    private final JDatePickerImpl insertDatePicker;
    private final JXDatePicker insertXDP;
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
        insertModel.setSelected(true);
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        insertDatePanel = new JDatePanelImpl(insertModel, p);
        insertDatePanel.setLocation(insertDatePanel.getLocation().x,(insertDatePanel.getLocation().y-100));
        insertDatePicker = new JDatePickerImpl(insertDatePanel, new BMDateFormatter());
        insertDatePicker.setMaximumSize(new Dimension(150,15));
        insertDatePicker.setLocation(insertDatePicker.getLocation().x,(insertDatePicker.getLocation().y-100));
        descTxt = new JTextField("Transaction description",50);
        amountTxt = new JTextField("Amount",10);
        
         insertXDP = new JXDatePicker();
        insertXDP.setDate(Calendar.getInstance().getTime());
        insertXDP.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        
        centerLayout = new FlowLayout(FlowLayout.LEFT);
        centerPanel = new JPanel();
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( !descTxt.getText().equals("") && !amountTxt.getText().equals("") && !(insertXDP.getDate() == null)) {
            try {
                Integer.parseInt(amountTxt.getText());
            }
            catch (NumberFormatException numberException){
                System.out.println("Insert a number");
            }
            
        }
    }
    
}