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


/**
 * Classe che permette di cambiare il periodo di visualizzazione del bilancio.
 * 
 * <br>In questa classe viene creata l'nterfaccia e i metodi per la corretta gestione di visualizzazione
 * di periodi predefini di transazioni ma anche di periodi arbitrari.
 */
public class BMPeriodPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
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
    
    /**
     * Costruttore della classe <a href="../BM/BMPeriodPanel">BMPeriodPanel</a>.
     * 
     * Questo costruttore si occupa di gestire l'inizializzazione di tutte le componenti grafiche e non 
     * di questo pannello.
     * @param tablePanel riferimento al <a href="../BM/BMTablePanel">BMTablePanel</a>
     */
    public BMPeriodPanel(BMTablePanel tablePanel) {
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

    /**
     * Metodo che gestisce la creazione e il settaggio di un RowFilter all'interno del <a href="../BM/BMTableModel">BMTableModel</a>
     * 
     * <br>In questa parte viene creato un pattern a seconda del JRadioButton che abbiamo selezionato che è prestabilito.
     * <br>E' anche presente la creazione di un pattern per visualizzare in tabella un periodo arbitrario attraverso
     * i due menu a tendina che permetto di selezionare un range arbitrario.
     * @param e permette di capire che ha fatto partire l'evento.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e.getActionCommand());
        tablePanel.getTable().clearSelection();
        tablePanel.disableButton();
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
            RowFilter<BMTableModel, Object> rf = RowFilter.regexFilter(pattern);
            tablePanel.setPeriodFilter(rf);
            //System.out.println(date.getTime() +" Pattern: "+ pattern);
        }
        catch (NullPointerException nullPointer) {
            tablePanel.setPeriodFilter(null);
        }
        for(int i=0; i<tablePanel.getTable().getModel().getRowCount(); i++) {
            BMItem item = ((BMTableModel)tablePanel.getTable().getModel()).getTransactionsList().get(i);
            //System.out.println(i + " " + (BMItem.dateFormat.format(item.getDate().getTime()) + item.getDescription() + item.getAmount() + "\n"));
        }
        tablePanel.refreshTotal();
    }
    
    /**
     * Funzione brutale ma funzionale per la creazione di un pattern di un periodo arbitrario.
     * 
     * Questa funzione genera una regex composta di tutte le date che fanno parte del range e verifica
     * in modo tale da far sapere al <a href="../BM/BMTableModel">BMTableModel</a> quali transazioni deve mostrare.
     * @param fromXDP Periodo di partenza di cui si vogliono visualizzare le transazioni
     * @param toXDP Periodo finale di cui si vogliono visualizzare le transazioni
     * @return Stringa contenete una REGEX solitamente lunga che verra usata come RowFilter.
     */
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
                //System.out.println(pattern.substring((pattern.length()-10)) + "--->" + toDate);
        }        
        return pattern+"$";
    }
}