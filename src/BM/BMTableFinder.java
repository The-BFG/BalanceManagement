/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author giacomo
 */
public class BMTableFinder extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JTable table;
    private BMTextField searchTxt;
    private JButton searchBtn;
    private Image img = null;
    
    private BorderLayout glueLayout;
    private FlowLayout searchLayout;
    
    public BMTableFinder(BMTablePanel tablePanel) {
        this.table = tablePanel.getTable();
        searchTxt = new BMTextField("Cerca...", 20);
        searchBtn = new JButton();       
        try {
            this.img = ( ImageIO
                    .read(new FileInputStream(((System.getProperty("user.dir").endsWith("class")) ? "../icon/search.png" : "./icon/search.png"))))
                    .getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
            System.out.println("Impossibile caricare l'icona del searchBtn\n" + ex);
        }
        searchBtn.setIcon(new ImageIcon(img));
        searchBtn.setBackground(null);
        searchBtn.setBorder(null);
        searchBtn.setContentAreaFilled(false);
        searchBtn.addActionListener(this);
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
