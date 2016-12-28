package BM;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class BMPeriodPanel extends JPanel {
    private final JLabel periodLbl = new JLabel("Choose the transactions period you want to see:");
    private final JLabel fromLbl = new JLabel("From:");
    private final JLabel toLbl = new JLabel("To:");
    private final UtilDateModel model;
    private final Properties p;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    
    public BMPeriodPanel() {
        
        model = new UtilDateModel();
        model.setDay((new GregorianCalendar().get(Calendar.DAY_OF_MONTH)));
        model.setMonth((new GregorianCalendar().get(Calendar.MONTH)));
        model.setYear((new GregorianCalendar().get(Calendar.YEAR)));
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new BMDateFormatter());
        
        add(periodLbl);
        add(datePicker);
    }
    
}