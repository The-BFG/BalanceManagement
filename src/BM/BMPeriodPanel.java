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
    private final JRadioButton day, month, year;
    private final ButtonGroup periodGrp;
    private JXDatePicker fromXDP, toXDP;
    private final BoxLayout panelLayout;
    private final FlowLayout topLayout, midiLayout;
    private final JPanel topPanel,midiPanel;
    private final BMTablePanel tablePanel;
    
    public BMPeriodPanel(BMTablePanel tablePanel) {
        //toModel.setYear((new GregorianCalendar().get(Calendar.YEAR)));
        this.tablePanel = tablePanel;
        day = new JRadioButton("Giorno");
        month = new JRadioButton("Mese");
        year = new JRadioButton("Anno");
        year.setSelected(true);
        periodGrp = new ButtonGroup();
        periodGrp.add(day); 
        periodGrp.add(month); 
        periodGrp.add(year);
        day.addActionListener(this); 
        month.addActionListener(this);
        year.addActionListener(this);
              
        topPanel = new JPanel();
        topLayout = new FlowLayout(FlowLayout.LEFT);
        topPanel.setLayout(topLayout);
        topPanel.add(periodLbl);
        topPanel.add(day);
        topPanel.add(month);
        topPanel.add(year);

        fromXDP = new JXDatePicker();
        fromXDP.setDate(Calendar.getInstance().getTime());
        fromXDP.setFormats(BMItem.dateFormat);        
        toXDP = new JXDatePicker();
        toXDP.setDate(Calendar.getInstance().getTime());
        toXDP.setFormats(BMItem.dateFormat);
        
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

    @Override
    public void actionPerformed(ActionEvent e) {
        GregorianCalendar date = new GregorianCalendar();
        /*Calendar today = new GregorianCalendar();
        System.out.println(e.getActionCommand());*/
        String pattern = null;
        switch(e.getActionCommand()) {
            case "Giorno":
                pattern = "(?i)^0?"+ date.get(Calendar.DAY_OF_MONTH)+"/{1}0?"+ (date.get(Calendar.MONTH)+1)+"/{1}"+date.get(Calendar.YEAR)+"$";
                
                break;
            case "Mese":
                pattern = "(?i)^0?[1-9]+[0-9]*/{1}0?"+ (date.get(Calendar.MONTH)+1)+"/{1}"+date.get(Calendar.YEAR)+"$";
                break;
            case "Anno":
                pattern = "(?i)^0?[1-9]+[0-9]*/{1}0?/{1}"+date.get(Calendar.YEAR)+"$";
                break;
            case "Visualizza periodo":
                break;
            default:
                //startDate = Calendar.getInstance().getTime();
                //endDate = Calendar.getInstance().getTime();
        }
        try {            
            /*filters.add( RowFilter.dateFilter(ComparisonType.AFTER, startDate.getTime(), 0));
            filters.add( RowFilter.dateFilter(ComparisonType.BEFORE, endDate.getTime() ,1) );*/
            RowFilter rf = RowFilter.regexFilter(pattern);//RowFilter.andFilter(filters);
            tablePanel.setPeriodFilter(rf);
            System.out.println(date.getTime() +" Pattern: "+ pattern);
        }
        catch (NullPointerException nullPointer) {
            tablePanel.setPeriodFilter(null);
            System.out.println("sono nel catch dio nigga");
        }
    }
}