package BM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXDatePicker;

public class BMAddTransactionPanel extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private JLabel addLbl = new JLabel("  Inserisci i dettagli della nuova transazione da aggiungere al bilancio:");
    private BorderLayout panelLayout;
    private BoxLayout centerLayout, topLayout;
    private JPanel topPanel, centerPanel;
    
    private JButton addBtn;
    private BMTextField descTxt, amountTxt;
    private JXDatePicker insertXDP;
    
    private BMTablePanel table;
    private BMTableModel tableModel;

    public BMAddTransactionPanel(BMTablePanel table) {
        this.table = table;
        this.tableModel = (BMTableModel) table.getTable().getModel();
        
        topPanel = new JPanel();
        topLayout = new BoxLayout(topPanel, BoxLayout.Y_AXIS);
        topPanel.setLayout(topLayout);
        topPanel.add(Box.createRigidArea(new Dimension(0,15)));
        topPanel.add(addLbl);
        topPanel.add(Box.createRigidArea(new Dimension(0,5)));

        insertXDP = new JXDatePicker();
        insertXDP.setDate(Calendar.getInstance().getTime());
        insertXDP.setFormats(BMItem.dateFormat);
        
        descTxt = new BMTextField("Descrizione della transazione effettuata",50);
        amountTxt = new BMTextField("Ammontare",10);
        amountTxt.addKeyListener(this);
        
        centerPanel = new JPanel();
        centerLayout = new BoxLayout(centerPanel, BoxLayout.X_AXIS);
        centerPanel.setLayout(centerLayout);
        centerPanel.add(Box.createRigidArea(new Dimension(7,0)));
        centerPanel.add(insertXDP);
        centerPanel.add(descTxt);
        centerPanel.add(amountTxt);
        
        addBtn = new JButton("Aggiungi transazione");
        addBtn.addActionListener(this);
        addBtn.addKeyListener(this);
        
        panelLayout = new BorderLayout();
        setLayout(panelLayout);

        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(addBtn, BorderLayout.LINE_END);
        add(Box.createRigidArea(new Dimension(0,10)), BorderLayout.PAGE_END);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        addTransaction();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            addTransaction();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    private void addTransaction() {
        if( !descTxt.getText().equals("") && !amountTxt.getText().equals("") && !(insertXDP.getDate() == null)) {
            Double amount;
            try {
                //System.out.println(String.format("%1$.2f", Double.parseDouble(amountTxt.getText())));
                amount = Double.parseDouble(String.format("%1$.2f", Double.parseDouble(amountTxt.getText())).replace(',', '.'));
                GregorianCalendar calendar = new GregorianCalendar();
                String date = BMItem.dateFormat.format(insertXDP.getDate());
                calendar.set( Integer.parseInt(date.substring(6, 10)), (Integer.parseInt(date.substring(3, 5)) - 1), Integer.parseInt(date.substring(0, 2)));
                
                BMItem item = new BMItem(calendar, descTxt.getText(), amount);
                tableModel.addItem(item);
                
                descTxt.setText("Descrizione della transazione effettuata");
                amountTxt.setText("Ammontare");
                
                table.refreshTotal();
                /*for(int i=0; i<tableModel.getRowCount();i++) {
                    for(int j=0; j<tableModel.getColumnCount();j++) {
                        System.out.println(String.valueOf(i) + tableModel.getValueAt(i, j).toString());
                    }
                }*/
            }
            catch (NumberFormatException numberException){
                JOptionPane.showMessageDialog(this, "Devi inserire un numero in questo campo", "Avviso di inserimento", JOptionPane.WARNING_MESSAGE);
            }            
        }
    }
}