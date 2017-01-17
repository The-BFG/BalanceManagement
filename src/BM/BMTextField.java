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
 *
 * @author giacomo
 */
public class BMTextField extends JTextField implements FocusListener  {
    private static final long serialVersionUID = 1L;
    private String firstTxt;
    
    public BMTextField (){
        super();
        firstTxt = "";
        addFocusListener(this);
    }
    public BMTextField (String text){
        super(text);
        firstTxt = text;
        addFocusListener(this);
    }
    public BMTextField (int nChar){
        super(nChar);
        firstTxt = "";
        addFocusListener(this);
    }
    public BMTextField (String text, int nChar){
        super(text, nChar);
        firstTxt = text;
        addFocusListener(this);
    }
    
    public String getDefaultText() {
        return firstTxt;
    }

    @Override
    public void focusGained(FocusEvent e) {
        setText(""); // Empty the text field when it receives focus
    }
    @Override
    public void focusLost(FocusEvent e) {      
        if(getText().equals("")) {
            setText(firstTxt);
        }
    }    
}