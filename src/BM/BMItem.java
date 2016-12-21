package BM;

/**Classe che implementa le voci di bilancio
 * Questa classe viene utilizzata dal programma
 * per creare un oggetto riga da inserire nella tabella.
 * @author Giacomo Guerzoni
 */
import java.util.GregorianCalendar;

public class BMItem{
    private GregorianCalendar date;
    private String description;
    private Double amount;

    public BMItem() {
        this.date = new GregorianCalendar(10,10,10);
    }
    
    /**Ritorna la data della transazione.
     * @return
     */
    public GregorianCalendar getDate() {
        return date;
    }
    
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}