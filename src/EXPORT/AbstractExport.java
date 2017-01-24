package EXPORT;

import BM.BMItem;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *Classe astratta che attraverso il polimorfismo delle classi che implementano i suoi metodi astratti permette di esportare 
 * il bilancio in vari formati.
 * 
 * <br>I formati dipenderanno dal tipo di separatore che viene utilizzato dalla classe che implementa tutti i metodi astratti.
 * <br>In questo progetto i due esempi principali che implementano e utilizzano questa classe sono:
 * <br>{@link EXPORT.ExportCSV};
 * <br>{@link EXPORT.ExportTSV};
 * @author giacomo
 */
public abstract class AbstractExport {
    private String separator;
    private ArrayList<BMItem> transactions;
    
    /**
     * Costruttore della classe astratta che puo solo essere utilizzato dalle classi figlie.
     * 
     * Si occupa di inizializzare l'ArrayList delle transazioni con L'arrayList che gli viene passato e che verra poi esportato.
     * @param transactions elenco di transazioni che serve per inizializzare l'ArrayList di questa classe.
     */
    protected AbstractExport(ArrayList<BMItem> transactions) {
        this.transactions = transactions;
    }   
    
    public abstract String getSeparator();
    public abstract String getExtension();
    protected abstract String getDefaultPath();
    
    /**
     * Metodo che serve per settare il separatore della classe.
     * 
     * @param separator Stringa che viene utilizzata per separare i campi durante l'esportazione.
     */
    protected void setSeparator(String separator) {
        this.separator = separator;
    }
    
    /**
     * metodo get per ottenere l'ArrayList che si vuole esportare.
     * @return ArraList di {@link BM.BMItem}.
     */
    protected ArrayList<BMItem> getTransactions() {
        return transactions;
    }
    
    /**
     * Metodo utilizzato per generare la stringa dai dati ottenuti dal record della tabella.
     * 
     * @param item Oggetto che deve essere letto e convertito in stringa attraverso i sui metodi get.
     * @return String contenente tutti i campti dell'oggetto  {@link BM.BMItem}.
     */
    public String recordToString(BMItem item){
        return (BMItem.dateFormat.format(item.getDate().getTime()) + getSeparator() + item.getDescription() + getSeparator() + item.getAmount() + "\n");
    }
    
    /**
     * Metodo che sfrutta il polimorfismo e attraverso altri metodi implementati nelle classi figlie esporta un'ArrayList.
     */
    public void exportData() {
        if(transactions.size() > 0) {
            //System.out.println(getDefaultPath());
            JFileChooser save = new JFileChooser(getDefaultPath());
            save.setMultiSelectionEnabled(false);
            save.setFileSelectionMode(JFileChooser.FILES_ONLY);
            save.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
            save.setSelectedFile(new File("MioArchivio"));
            save.addChoosableFileFilter(new FileNameExtensionFilter("*" + getExtension(), getExtension()));
            save.setFileFilter(save.getChoosableFileFilters()[1]);

            int returnVal = save.showSaveDialog(save);

            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String path = save.getSelectedFile().getAbsolutePath() + getExtension();

                FileWriter fout;
                String record;
                try { 
                    fout = new FileWriter(path); 
                    for(int i=0; i < transactions.size(); i++) {
                        record = recordToString(transactions.get(i));
                        try{
                            fout.write(record);
                        }
                        catch(IOException e) {
                            System.out.println("Scrittura sul file fallita\n" + e);
                        }
                    }
                    fout.close();
                }
                catch(IOException e) {
                    System.out.println("Apertura del file fallita\n" + e);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Esportazione annullata perche non e' presente alcuna transazione.", "Esportazione annullata", JOptionPane.WARNING_MESSAGE);
        }
    }
}
