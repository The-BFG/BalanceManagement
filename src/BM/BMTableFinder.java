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
 *
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
    
    public BMTableFinder(BMTablePanel tablePanel) {
        this.table = tablePanel.getTable();
        searchTxt = new BMTextField("Cerca...", 20);
        searchTxt.addKeyListener(this);
        
        searchBtn = new JButton();       
        try {
            this.img = ( ImageIO
                    .read(new FileInputStream(((System.getProperty("user.dir").endsWith("class")) ? "../icon/next.png" : "./icon/next.png"))))
                    .getScaledInstance(25, 25, Image.SCALE_DEFAULT);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("button" + counter);
        find();  
        System.out.println("button" + counter);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }    

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() != KeyEvent.VK_ENTER) {
            counter = 0;
            System.out.println("released" + e.getKeyChar());
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            find();
    }
    
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
