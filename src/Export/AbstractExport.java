package Export;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author giacomo
 */
public abstract class AbstractExport {
    public String separator;
    
    protected AbstractExport() {
        
    }
    
    //public abstract void setSeparator();
    public abstract String getSeparator();
    public abstract String recordToString();
    
    public static boolean haveExtension(String fileName) {
        Pattern pattern = Pattern.compile("^[a-zA-Z_]*[.-.]{1}[a-zA-Z]{1,5}+$");
        Matcher match = pattern.matcher(fileName);
        return match.find();
    }
}
