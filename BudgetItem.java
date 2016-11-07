/**Classe che implementa le voci di bilancio
 * Questa classe viene utilizzata dal programma
 * per creare un oggetto riga da inserire nella tabella.
 * @author Giacomo Guerzoni
 */

import java.util.GregorianCalendar;

public class BudgetItem{
    private GregorianCalendar date;
    
    public BudgetItem(){
        this.date = new GregorianCalendar();
    }
    public BudgetItem(GregorianCalendar date){
        this.date = date;
    }
}