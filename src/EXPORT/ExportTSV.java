/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXPORT;

import BM.BMItem;
import java.util.ArrayList;

/**
 *Classe che implementa i metodi astratti ereditati da {@link EXPORT.AbstractExport}.
 * 
 * <br>Qui viene settato come separatore di default il carattere di tabulazione.
 * @author giacomo
 */
public class ExportTSV extends AbstractExport{
    
    /**
     * Costruttore che chiama il costruttore del padre {@link BM.BMTextField} ed inizializza il separatore di default.
     * @param transactions ArrayList contenente tutte le transazioni che devono essere esportate.
     */
    public ExportTSV(ArrayList<BMItem> transactions){
        super(transactions);
        super.setSeparator("\t");
    }

    /**
     * Metodo che ritorna il separatore che viene utilizzato per l'esportazione
     * @return Stringa contenente il carattere per separare i campi.
     */
    @Override
    public String getSeparator() {
        return "\t";
    }
    
    /**
     * Metodo per ottenere l'estenzione del file che verra generato.
     * @return String contenente l'estensione del file di esportazione
     */
    @Override
    public String getExtension() {
        return ".tsv";
    }

    /**
     * Metodo che ritorna il percorso di default in cui bisognerebbe salvare il file.
     * In questo caso sarebbe la cartella archive/tsv.
     * @return stringa contenente il percorso in cui effettuare i salvataggi.
     */
    @Override
    protected String getDefaultPath() {
        return ((System.getProperty("user.dir").endsWith("class")) ? "../archive/tsv" : "./archive/tsv");
    }
    
}