/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Export;

/**
 *
 * @author giacomo
 */
public class ExportTSV extends AbstractExport{
    
    public ExportTSV(){
        super.separator = "\t";
    }
    
    /*@Override
    public void setSeparator() {
        super.separator = "\t";      
    }*/

    @Override
    public String getSeparator() {
        return "\t";
    }    

    @Override
    public String recordToString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
