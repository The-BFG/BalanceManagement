package Export;

/**
 *
 * @author giacomo
 */
public class ExportCSV extends AbstractExport {
   
    
    public ExportCSV() {
        super.separator = ",";
    }

    /*@Override
    public void setSeparator() {
        super.separator = ",";
    }*/

    @Override
    public String getSeparator() {
        return ",";
    }
    
    @Override
    public String recordToString() {
        String record = "";
        
        return record;
    }
    
}
