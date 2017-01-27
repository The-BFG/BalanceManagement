/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *Classe che implementa la ricerca di una stringa all'interno del campo descrizione del <a href="../BM/BMTablePanel">BMTablePanel</a>.
 *
 * <br>In questa classe sono implementati i metodi e l'interfaccia per poter effettuare ricerche tra le vari transazioni che sono 
 * contenute nel bilancio.
 * @author giacomo
 */
public class BMTableFinder extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private BMTextField searchTxt;
    private JButton searchBtn;
    private Image img = null;
    private Integer counter = 0;
    
    private BorderLayout glueLayout;
    private FlowLayout searchLayout;
    
    /**
     * Costruttore che si occupa di implementare la parte grafica del JPanel.
     * 
     * @param tablePanel Riferimento all'oggetto di tipo <a href="../BM/BMTablePanel">BMTablePanel</a>.
     */
    public BMTableFinder(BMTablePanel tablePanel) {
        this.table = tablePanel.getTable();
        searchTxt = new BMTextField("Cerca...", 20);
        searchTxt.addKeyListener(this);
        
        searchBtn = new JButton();       
        try {
            this.img = ( ImageIO
                    .read(new FileInputStream(((System.getProperty("user.dir").endsWith("class")) ? "../icon/next.png" : "./icon/next.png"))))
                    .getScaledInstance(26, 26, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
            System.out.println("Impossibile caricare l'icona del searchBtn\n" + ex);
        }
        searchBtn.setIcon(new ImageIcon(img));
        searchBtn.setBackground(null);
        searchBtn.setBorder(null);
        searchBtn.setContentAreaFilled(false);
        searchBtn.addActionListener(this);
        searchBtn.addKeyListener(this);
        
        JPanel searchPanel = new JPanel();
        searchLayout = new FlowLayout(FlowLayout.RIGHT);
        searchPanel.setLayout(searchLayout);
        searchPanel.add(Box.createHorizontalGlue());
        searchPanel.add(searchTxt);
        searchPanel.add(searchBtn);
        
        glueLayout = new BorderLayout();
        this.setLayout(glueLayout);
        //this.add(Box.createVerticalGlue(), BorderLayout.CENTER);
        this.add(searchPanel, BorderLayout.SOUTH);
    }

    /**
     * Metodo che gestisce la pressione del bottone di ricerca e fa partire la ricerca all'interno del <a href="../BM/BMTablePanel">BMTablePanel</a>.
     * @param e evento che ha fatto partire l'esecuzione della ricerca.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("button" + counter);
        find();  
        System.out.println("button" + counter);
    }

    /**
     * @deprecated Metodo non utilizzato in quando non utile ai fini della ricerca.
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }    

    /**
     * Metodo che verifica il tipo di pulsante che è stato rilasciato.
     * 
     * <br>Se è stato premuto invio non fa niente poiche verra eseguito il metodo {@link #keyPressed(KeyEvent)}.
     * <br> In caso contrario viene resettato il contatore della ricerca che tiene conto della riga selezionata in cui 
     * è arrivato a cercare.
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() != KeyEvent.VK_ENTER) {
            counter = 0;
            System.out.println("released" + e.getKeyChar());
        }
    }
    
    /**
     * Metodo che viene azionato quando viene premuto invio da dentro la <a href="../BM/BMTextField">BMTextField</a>.
     * 
     * <br>Se premuto invio fa partire la ricerca all'interno della JTable contenuta nel <a href="../BM/BMTablePanel">BMTablePanel</a>
     * attraverso la chiamata del metodo {@link #find()}.
     * Qualsiasi altro tasto viene premuto viene ingnorato da questo metodo.
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            find();
    }
    
    /**
     * Metodo che seleziona la riga della JTable contenuta nel <a href="../BM/BMTablePanel">BMTablePanel</a> quando trova 
     * la stringa che si sta cercando.
     * 
     * <br>Viene letta l'ArrayList di tutte le transazioni e viene selezionata una riga solo quando viene trovata.
     * <br>In caso contrario non viene selezionata alcuna riga.
     */
    private void find() {
        ArrayList<BMItem> list = ((BMTableModel)table.getModel()).getTransactionsList();
        while (counter < list.size()) {
            if(list.get(counter).getDescription().contains(searchTxt.getText())){
                table.getSelectionModel().setSelectionInterval(counter, counter);
                counter++;
                return;
            }
            else 
                counter++;
        }
        counter = 0;
        
    }    
}
