/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BM;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *Classe che estende una JTextField in modo tale da aggiungere alcune funzionalita.
 * 
 * <br>In particolare vengono aggiunti i metodi che implementano il FocusListener per gestire quando si clicca
 * sulla JTextField quando si perde il focus da essa.
 * @author giacomo
 */
public class BMTextField extends JTextField implements FocusListener  {
    private static final long serialVersionUID = 1L;
    private String firstTxt;
    
    /**
     * Costruttore della classe <a href="../BM/BMTextField">BMTextField</a>.
     * 
     * Inizializza il testo iniziale vuoto.
     */
    public BMTextField (){
        super();
        firstTxt = "";
        addFocusListener(this);
    }
    /**
     * Costruttore della classe <a href="../BM/BMTextField">BMTextField</a>.
     * 
     * Inizializza il testo iniziale con la stringa che gli viene passata come parametro.
     * @param text testo iniziale della {@link BM.BMTextField}.
     */
    public BMTextField (String text){
        super(text);
        firstTxt = text;
        addFocusListener(this);
    }
    
    /**
     * Costruttore della classe <a href="../BM/BMTextField">BMTextField</a>.
     * 
     * Inizializza il testo iniziale vuoto e setta il numero di lettere visibili.
     * @param nChar numero di lettere che è possibile visualizzare dalla {@link BM.BMTextField}.
     */
    public BMTextField (int nChar){
        super(nChar);
        firstTxt = "";
        addFocusListener(this);
    }
    
    /**
     * Costruttore della classe <a href="../BM/BMTextField">BMTextField</a>.
     * 
     * <br>Inizializza il testo iniziale con la stringa che gli viene passata come parametro e settare il numero di lettere
     * visibili.
     * @param text testo iniziale della {@link BM.BMTextField}.
     * @param nChar numero di lettere che è possibile visualizzare dalla {@link BM.BMTextField}.
     */
    public BMTextField (String text, int nChar){
        super(text, nChar);
        firstTxt = text;
        addFocusListener(this);
    }
    /**
     * Metodo get per ottenere la stringa inziale presente all'interno della {@link BM.BMTextField}.
     * 
     * @return String contenente la stringa di default della {@link BM.BMTextField}.
     */
    public String getDefaultText() {
        return firstTxt;
    }

    /**
     * Metodo che viene eseguito quando si prende il focus della {@link BM.BMTextField} e si coccupa di eliminarne il contenuto.
     */
    @Override
    public void focusGained(FocusEvent e) {
        setText(""); // Empty the text field when it receives focus
    }
    @Override
    
    /**
     * Metodo che si occupa di rimettere il testo di default quando la {@link BM.BMTextField} perde il focus.
     */
    public void focusLost(FocusEvent e) {      
        if(getText().equals("")) {
            setText(firstTxt);
        }
    }    
}