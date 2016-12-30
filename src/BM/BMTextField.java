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
    public BMTextField (){
        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent event) {
        
    }

    @Override
    public void focusLost(FocusEvent event) {

    }
}