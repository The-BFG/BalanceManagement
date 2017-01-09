package BM;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.RowFilter;
import org.jdesktop.swingx.JXDatePicker;

public class BMPeriodPanel extends JPanel implements ActionListener {
    private final JLabel periodLbl = new JLabel("Mostra le transazioni del:");
    private final JLabel fromLbl = new JLabel("Da:");
    private final JLabel toLbl = new JLabel("A:");
    private JRadioButton day, month, year, all;
    private ButtonGroup periodGrp;
    private JXDatePicker fromXDP, toXDP;
    private JButton setPeriod;
    private BoxLayout panelLayout;
    private FlowLayout topLayout, midiLayout;
    private JPanel topPanel,midiPanel;
    private BMTablePanel tablePanel;
    
    public BMPeriodPanel(BMTablePanel tablePanel) {
        //toModel.setYear((new GregorianCalendar().get(Calendar.YEAR)));
        this.tablePanel = tablePanel;
        
        day = new JRadioButton("Giorno");
        month = new JRadioButton("Mese");
        year = new JRadioButton("Anno");
        all = new JRadioButton("Tutte le transazioni");
        all.setSelected(true);
        periodGrp = new ButtonGroup();
        periodGrp.add(day); 
        periodGrp.add(month); 
        periodGrp.add(year);
        periodGrp.add(all);
        day.addActionListener(this); 
        month.addActionListener(this);
        year.addActionListener(this);
        all.addActionListener(this);
              
        topPanel = new JPanel();
        topLayout = new FlowLayout(FlowLayout.LEFT);
        topPanel.setLayout(topLayout);
        topPanel.add(periodLbl);
        topPanel.add(day);
        topPanel.add(month);
        topPanel.add(year);
        topPanel.add(all);

        fromXDP = new JXDatePicker();
        fromXDP.setDate(Calendar.getInstance().getTime());
        fromXDP.setFormats(BMItem.dateFormat); 
        toXDP = new JXDatePicker();
        toXDP.setDate(Calendar.getInstance().getTime());
        toXDP.setFormats(BMItem.dateFormat);
        setPeriod = new JButton("Visualizza periodo");
        setPeriod.addActionListener(this);
        
        midiPanel = new JPanel();
        midiLayout = new FlowLayout(FlowLayout.LEFT);
        midiPanel.setLayout(midiLayout);
        midiPanel.add(fromLbl);
        midiPanel.add((JComponent)fromXDP);
        midiPanel.add(Box.createRigidArea(new Dimension(10,0)));
        midiPanel.add(toLbl);
        midiPanel.add((JComponent)toXDP);
        midiPanel.add(setPeriod);
        
        panelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(panelLayout);
        add(topPanel);
        add(midiPanel);
        add(Box.createRigidArea(new Dimension(0,7)));
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e.getActionCommand());
        GregorianCalendar date = new GregorianCalendar();
        String pattern = null;
        switch(e.getActionCommand()) {
            case "Giorno":
                pattern = "(?i)^0?"+ date.get(Calendar.DAY_OF_MONTH)+"/{1}0?"+ (date.get(Calendar.MONTH)+1)+"/{1}"+date.get(Calendar.YEAR)+"$";                
                break;
            case "Mese":
                pattern = "(?i)^0?[1-9]+[0-9]*/{1}0?"+ (date.get(Calendar.MONTH)+1)+"/{1}"+date.get(Calendar.YEAR)+"$";
                break;
            case "Anno":
                pattern = "(?i)^0?[1-9]+[0-9]*/{1}0?1?[0-9]+/{1}"+date.get(Calendar.YEAR)+"$";
                break;
            case "Tutte le transazioni":
                pattern = null;
                break;
            case "Visualizza periodo":
                
                if(BMItem.yearFormat.format(fromXDP.getDate()).compareTo(BMItem.yearFormat.format(toXDP.getDate())) <= 0)
                    pattern = createPattern(fromXDP, toXDP);
                else
                    pattern =  createPattern(toXDP, fromXDP);
                break;
            default:
        }
        try {            
            RowFilter rf = RowFilter.regexFilter(pattern);
            tablePanel.setPeriodFilter(rf);
            System.out.println(date.getTime() +" Pattern: "+ pattern);
        }
        catch (NullPointerException nullPointer) {
            tablePanel.setPeriodFilter(null);
        }
        tablePanel.refreshTotal();
    }
    
    private String createPattern(JXDatePicker fromXDP, JXDatePicker toXDP) {
        String fromDate = BMItem.dateFormat.format(fromXDP.getDate());
        String toDate = BMItem.dateFormat.format(toXDP.getDate());
        Calendar currentDate= Calendar.getInstance();
        currentDate.setTime(fromXDP.getDate());
        String pattern = "(?i)^" + fromDate;
        //System.out.println(pattern.substring((pattern.length()-9)));
        while(!pattern.substring((pattern.length()-10), pattern.length()).equals(toDate)) {
                currentDate.add(Calendar.DATE, 1);
                pattern = pattern.concat("|" + BMItem.dateFormat.format(currentDate.getTime()));
                System.out.println(pattern.substring((pattern.length()-10)) + "--->" + toDate);
        }        
        return pattern+"$";
    }
}