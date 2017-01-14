/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BM;

import Export.AbstractExport;
import Export.ExportCSV;
import Export.ExportTSV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.model.OpenDocument;
import org.jopendocument.panel.ODSViewerPanel;

/**
 *
 * @author giacomo
 */
public class BMMenuBar  extends JMenuBar implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem openArchive = new JMenuItem("Apri archivio");
    private JMenuItem saveArchive = new JMenuItem("Salva archivio");
    private JMenuItem exitBM = new JMenuItem("Esci");
    
    private JMenu exportMenu = new JMenu("Esporta");
    private JMenuItem exportCSV = new JMenuItem("Esporta in formato CommaSV");
    private JMenuItem exportTSV = new JMenuItem("Esporta in formato TabSV");
    private JMenuItem exportOpenDocument = new JMenuItem("Esporta in formato OpenDocument"); 
    
    private BMTablePanel table;    
    
    public BMMenuBar(BMTablePanel table) {
        this.table = table;
        
        fileMenu.add(openArchive);
        fileMenu.add(saveArchive);
        fileMenu.addSeparator();
        fileMenu.add(exitBM);
        openArchive.addActionListener(this);
        openArchive.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveArchive.addActionListener(this);
        saveArchive.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        exitBM.addActionListener(this);        
        exitBM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        
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
                try {
                    openArchive();
                }
                catch (FileNotFoundException fnf) {
                    System.out.println("Impossibile trovare il file.\n" + fnf);
                }
                catch (IOException ioe) {
                    System.out.println("Errore di I/O.\n" + ioe);
                } 
                catch (ClassNotFoundException cnf) { 
                    System.out.println("Classe non trovata.\n" + cnf);
                }    
                catch (ArrayIndexOutOfBoundsException aio) {
                    System.out.println("Errore apertura\n" + aio);
                }
                break;
            case "Salva archivio":
                try {
                    saveArchive();
                }
                catch (FileNotFoundException fnf) {
                    System.out.println("Impossibile trovare il file.\n" + fnf);
                }
                catch (IOException ioe) {
                    System.out.println("Errore di I/O.\n" + ioe);
                }
                break;
            case "Esci":
                System.exit(0);
                break;
            case "Esporta in formato CommaSV":
                AbstractExport exportCsv = new ExportCSV(((BMTableModel)table.getTable().getModel()).getTransactionsList());
                exportCsv.exportData();
                break;                
            case "Esporta in formato TabSV":
                AbstractExport exportTsv = new ExportTSV(((BMTableModel)table.getTable().getModel()).getTransactionsList());
                exportTsv.exportData();
                break;
            case "Esporta in formato OpenDocument":
                String date = BMItem.completeDate.format(Calendar.getInstance().getTime());
                final File file = new File("./archive/ods/Archivio("+date+")");
                try {
                    SpreadSheet.createEmpty((BMTableModel)table.getTable().getModel()).saveAs(file);
                    OOUtils.open(new File(file.getAbsolutePath() +".ods"));               
                }
                catch(FileNotFoundException fnf) {
                    System.out.println("File non trovato.\n" + fnf);
                } 
                catch (IOException ex) {
                    System.out.println("Errore di Input/Output.\n" + ex);   
                }    
                break;
            default:
        }
    }
    private void openArchive() throws ClassNotFoundException, FileNotFoundException, IOException {      
        JFileChooser open = new JFileChooser("./archive/bin");
        open.setMultiSelectionEnabled(false);
        open.setFileSelectionMode(JFileChooser.FILES_ONLY);
        open.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
        int returnVal = open.showOpenDialog(this);
        
        ArrayList<BMItem> transactions = new ArrayList<BMItem>();
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            
            ((BMTableModel)table.getTable().getModel()).resetTableModel();
            
            String filePath = open.getSelectedFile().getAbsolutePath();
            try (FileInputStream fin = new FileInputStream(filePath); ObjectInputStream ois = new ObjectInputStream(fin)) {
                Object obj = ois.readObject();
                if(obj instanceof ArrayList<?> ) {
                    for(Object e : (ArrayList<?>) obj){
                        if(e != null && e instanceof BMItem) {
                            transactions.add((BMItem)e);
                        }
                    }
                }
            }
            
            ((BMTableModel) table.getTable().getModel()).setTransactionList(transactions);
            table.refreshTotal();
        }
    }
    
    private void saveArchive() throws FileNotFoundException, IOException {
        String date="";
        String filePath; 

        JFileChooser save = new JFileChooser("./archive/bin");
        save.setMultiSelectionEnabled(false);
        save.setFileSelectionMode(JFileChooser.FILES_ONLY);
        save.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
        save.setSelectedFile(new File("MioArchivio"));
        int returnVal = save.showSaveDialog(save);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            if(save.getSelectedFile().exists()) {
                int optVal =JOptionPane.showConfirmDialog(  
                        null,
                        "Vuoi davvero sovrascrivere il file: "+ save.getSelectedFile().getName() +" ?",
                        "Sovrascrivere", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
                if(optVal == JOptionPane.YES_OPTION)
                    save.getSelectedFile().delete();
                else {
                    date = "(";
                    date = date.concat(BMItem.completeDate.format(Calendar.getInstance().getTime()) + ")");
                    //System.out.println(date);
                }
            }
            filePath = save.getSelectedFile().getAbsolutePath() + date;
            //System.out.println(filePath);
            try (FileOutputStream fout = new FileOutputStream(filePath); ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                oos.writeObject(((BMTableModel)table.getTable().getModel()).getTransactionsList());
            }
        }
    }    
}