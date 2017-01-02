package BM;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class BMPeriodPanel extends JPanel {
    private final JLabel periodLbl = new JLabel("Choose the transactions period you want to see:");
    private final JLabel fromLbl = new JLabel("From:");
    private final JLabel toLbl = new JLabel("To:");
    private final UtilDateModel fromModel, toModel;
    private final Properties p;
    private JDatePanelImpl fromDatePanel,toDatePanel;
    private JDatePickerImpl fromDatePicker, toDatePicker;
    private final BoxLayout panelLayout;
    private final FlowLayout topLayout, midiLayout;
    private final JPanel topPanel,midiPanel;
    
    public BMPeriodPanel() {
        fromModel = new UtilDateModel();
        fromModel.setDay(1);
        fromModel.setMonth(0);
        fromModel.setYear(1970);
        toModel = new UtilDateModel();
        toModel.setDay((new GregorianCalendar().get(Calendar.DAY_OF_MONTH)));
        toModel.setMonth((new GregorianCalendar().get(Calendar.MONTH)));
        toModel.setYear((new GregorianCalendar().get(Calendar.YEAR)));
        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        fromDatePanel = new JDatePanelImpl(fromModel, p);
        toDatePanel = new JDatePanelImpl(toModel, p);
        fromDatePicker = new JDatePickerImpl(fromDatePanel, new BMDateFormatter());
        fromDatePicker.setMaximumSize(new Dimension(200,30));
        toDatePicker = new JDatePickerImpl(toDatePanel, new BMDateFormatter());
        toDatePicker.setMaximumSize(new Dimension(200,30));
        
        /**Layouts
         * 
         */        
        topPanel = new JPanel();
        topLayout = new FlowLayout(FlowLayout.LEFT);
        topPanel.setLayout(topLayout);
        topPanel.add(periodLbl);
        
        midiPanel = new JPanel();
        midiLayout = new FlowLayout(FlowLayout.LEFT);
        midiPanel.setLayout(midiLayout);
        midiPanel.add(fromLbl);
        midiPanel.add((JComponent)fromDatePicker);
        midiPanel.add(Box.createRigidArea(new Dimension(10,0)));
        midiPanel.add(toLbl);
        midiPanel.add(toDatePicker);
        
        panelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(panelLayout);
        add(topPanel);
        add(midiPanel);
        add(Box.createRigidArea(new Dimension(0,10)));
    }    
}