package BM;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXDatePicker;

public class BMPeriodPanel extends JPanel {
    private final JLabel periodLbl = new JLabel("Choose the transactions period you want to see:");
    private final JLabel fromLbl = new JLabel("From:");
    private final JLabel toLbl = new JLabel("To:");
    private JXDatePicker fromXDP, toXDP;
    private final BoxLayout panelLayout;
    private final FlowLayout topLayout, midiLayout;
    private final JPanel topPanel,midiPanel;
    
    public BMPeriodPanel() {
        //toModel.setYear((new GregorianCalendar().get(Calendar.YEAR)));
        fromXDP = new JXDatePicker();
        fromXDP.setDate(Calendar.getInstance().getTime());
        fromXDP.setFormats(BMItem.dateFormat);
        
        toXDP = new JXDatePicker();
        toXDP.setDate(Calendar.getInstance().getTime());
        toXDP.setFormats(BMItem.dateFormat);
              
        topPanel = new JPanel();
        topLayout = new FlowLayout(FlowLayout.LEFT);
        topPanel.setLayout(topLayout);
        topPanel.add(periodLbl);
        
        midiPanel = new JPanel();
        midiLayout = new FlowLayout(FlowLayout.LEFT);
        midiPanel.setLayout(midiLayout);
        midiPanel.add(fromLbl);
        midiPanel.add((JComponent)fromXDP);
        midiPanel.add(Box.createRigidArea(new Dimension(10,0)));
        midiPanel.add(toLbl);
        midiPanel.add((JComponent)toXDP);
        
        panelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(panelLayout);
        add(topPanel);
        add(midiPanel);
        add(Box.createRigidArea(new Dimension(0,10)));
    }    
}