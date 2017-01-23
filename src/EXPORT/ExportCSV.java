package EXPORT;

import BM.BMItem;
import java.util.ArrayList;

/**
 *
 * @author giacomo
 */
public class ExportCSV extends AbstractExport {
    
    public ExportCSV(ArrayList<BMItem> transactions) {        
        super(transactions);
        super.setSeparator(",");
    }

    @Override
    public String getSeparator() {
        return ",";
    }  
    
    @Override
    public String getExtension() {
        return ".csv";
    }

    @Override
    protected String getDefaultPath() {
        return (System.getProperty("user.dir").endsWith("class")) ? "../archive/csv" : "./archive/csv";
    }
}
