package BM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXDatePicker;

public class BMAddTransactionPanel extends JPanel implements ActionListener {
    private final JLabel addLbl = new JLabel("  Inserisci i dettagli della nuova transazione da aggiungere al bilancio:");
    private final BorderLayout panelLayout;
    private final BoxLayout centerLayout, topLayout;
    private final JPanel topPanel, centerPanel;
    
    private final JButton addBtn;
    private final BMTextField descTxt, amountTxt;
    private final JXDatePicker insertXDP;
    
    private BMTableModel tableModel;

    public BMAddTransactionPanel(BMTableModel tableModel) {
        this.tableModel = tableModel;
        
        topPanel = new JPanel();
        topLayout = new BoxLayout(topPanel, BoxLayout.Y_AXIS);
        topPanel.setLayout(topLayout);
        topPanel.add(Box.createRigidArea(new Dimension(0,5)));
        topPanel.add(addLbl);
        topPanel.add(Box.createRigidArea(new Dimension(0,5)));

        insertXDP = new JXDatePicker();
        insertXDP.setDate(Calendar.getInstance().getTime());
        insertXDP.setFormats(BMItem.dateFormat);
        
        descTxt = new BMTextField("Descrizione della transazione effettuata",50);
        amountTxt = new BMTextField("Ammontare",10);
        
        centerPanel = new JPanel();
        centerLayout = new BoxLayout(centerPanel, BoxLayout.X_AXIS);
        centerPanel.setLayout(centerLayout);
        centerPanel.add(Box.createRigidArea(new Dimension(7,0)));
        centerPanel.add(insertXDP);
        centerPanel.add(descTxt);
        centerPanel.add(amountTxt);
        
        addBtn = new JButton("Aggiungi transazione");
        addBtn.addActionListener(this);
        
        panelLayout = new BorderLayout();
        setLayout(panelLayout);

        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(addBtn, BorderLayout.LINE_END);
        add(Box.createRigidArea(new Dimension(0,10)), BorderLayout.PAGE_END);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if( !descTxt.getText().equals("") && !amountTxt.getText().equals("") && !(insertXDP.getDate() == null)) {
            Double amount;
            try {
                amount = Double.parseDouble(amountTxt.getText());
                GregorianCalendar calendar = new GregorianCalendar();
                String date = BMItem.dateFormat.format(insertXDP.getDate());
                calendar.set( Integer.parseInt(date.substring(6, 10)), (Integer.parseInt(date.substring(3, 5)) - 1), Integer.parseInt(date.substring(0, 2)));
                //calendar.set(2045,11,1);
                
                BMItem item = new BMItem(calendar, descTxt.getText(), amount);
                tableModel.addItem(item);
                
                descTxt.setText("Transaction description");
                amountTxt.setText("Amount");
                /*for(int i=0; i<tableModel.getRowCount();i++) {
                    for(int j=0; j<tableModel.getColumnCount();j++) {
                        System.out.println(String.valueOf(i) + tableModel.getValueAt(i, j).toString());
                    }
                }*/
            }
            catch (NumberFormatException numberException){
                JOptionPane.showMessageDialog(this, "You have to insert a number in the amount field.", "Insertion warning", JOptionPane.WARNING_MESSAGE);
            }            
        }
    }
}