package BM;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
    private JDatePickerImpl fromDatePicker, toDatePicker;
    private BoxLayout panelLayout, midiLayout;
    private JPanel midiPanel;
    
    public BMPeriodPanel() {
        periodLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        model = new UtilDateModel();
        model.setDay((new GregorianCalendar().get(Calendar.DAY_OF_MONTH)));
        model.setMonth((new GregorianCalendar().get(Calendar.MONTH)));
        model.setYear((new GregorianCalendar().get(Calendar.YEAR)));
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        fromDatePicker = new JDatePickerImpl(datePanel, new BMDateFormatter());
        toDatePicker = new JDatePickerImpl(datePanel, new BMDateFormatter());

        
        panelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(panelLayout);
        
        midiPanel = new JPanel();
        midiLayout = new BoxLayout(midiPanel, BoxLayout.X_AXIS);
        midiPanel.setLayout(midiLayout);
        midiPanel.add(fromLbl);
        midiPanel.add(fromDatePicker);
        midiPanel.add(Box.createRigidArea(new Dimension(10,0)));
        midiPanel.add(toLbl);
        midiPanel.add(toDatePicker);
        midiPanel.add(Box.createRigidArea(new Dimension(300,0)));

        
        add(periodLbl);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(midiPanel);
    }
    
}