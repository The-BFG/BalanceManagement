/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author giacomo
 */
public class BMMenuBar  extends JMenuBar implements ActionListener{
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem openArchive = new JMenuItem("Apri archivio");
    private JMenuItem saveArchive = new JMenuItem("Salva archivio");
    
    private JMenu exportMenu = new JMenu("Esporta");
    private JMenuItem exportCSV = new JMenuItem("Esporta in formato CommaSV");
    private JMenuItem exportTSV = new JMenuItem("Esporta in formato TabSV");
    private JMenuItem exportOpenDocument = new JMenuItem("Esporta in formato OpenDoument"); 
    
    
    public BMMenuBar() {
        fileMenu.add(openArchive);
        fileMenu.add(saveArchive);
        openArchive.addActionListener(this);
        saveArchive.addActionListener(this);
        
        exportMenu.add(exportCSV);
        exportMenu.add(exportTSV);
        exportMenu.add(exportOpenDocument);
        exportCSV.addActionListener(this);
        exportTSV.addActionListener(this);
        exportOpenDocument.addActionListener(this);
        
        this.add(fileMenu);
        this.add(exportMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()) {
            case "Apri archivio":
                
                break;
            case "Salva archivio":
                
                break;
            case "Esporta in formato CSV":
                
                break;
            case "Esporta in formato TSV":
                
                break;
            case "Esporta in formato ODT":
                
                break;
            default:
        }
    }
}
