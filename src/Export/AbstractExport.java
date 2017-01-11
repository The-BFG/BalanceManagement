package Export;

import BM.BMItem;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author giacomo
 */
public abstract class AbstractExport {
    private String separator;
    private ArrayList<BMItem> transactions;
    
    protected AbstractExport(ArrayList<BMItem> transactions) {
        this.transactions = transactions;
    }   
    
    public abstract String getSeparator();
    public abstract String getExtension();
    protected abstract String getDefaultPath();
    
    protected void setSeparator(String separator) {
        this.separator = separator;
    }
    
    protected ArrayList<BMItem> getTransactions() {
        return transactions;
    }
    
    public String recordToString(BMItem item){
        return (BMItem.dateFormat.format(item.getDate().getTime()) + getSeparator() + item.getDescription() + getSeparator() + item.getAmount() + "\n");
    }
    
    public void exportData() {
        if(transactions.size() > 0) {
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
            JOptionPane.showMessageDialog(null, "Esportazione annullata perche non è presente alcuna transazione.", "Esportazione annullata", JOptionPane.WARNING_MESSAGE);
        }
    }
    /*public static boolean haveExtension(String fileName) {
        Pattern pattern = Pattern.compile("^[a-zA-Z_]*[.-.]{1}[a-zA-Z]{1,5}+$");
        Matcher match = pattern.matcher(fileName);
        return match.find();
    }*/
}
