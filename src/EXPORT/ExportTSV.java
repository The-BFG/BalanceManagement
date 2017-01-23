/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXPORT;

import BM.BMItem;
import java.util.ArrayList;

/**
 *
 * @author giacomo
 */
public class ExportTSV extends AbstractExport{
    
    public ExportTSV(ArrayList<BMItem> transactions){
        super(transactions);
        super.setSeparator("\t");
    }

    @Override
    public String getSeparator() {
        return "\t";
    }
    
    @Override
    public String getExtension() {
        return ".tsv";
    }

    @Override
    protected String getDefaultPath() {
        return (System.getProperty("user.dir").endsWith("class")) ? "../archive/tsv" : "./archive/tsv";
    }
    
}