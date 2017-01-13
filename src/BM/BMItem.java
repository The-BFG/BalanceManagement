package BM;

/**Classe che implementa le voci di bilancio
 * Questa classe viene utilizzata dal programma
 * per creare un oggetto riga da inserire nella tabella.
 * @author Giacomo Guerzoni
 */
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.io.Serializable;

public class BMItem implements Serializable{
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat completeDate = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
    private GregorianCalendar date;
    private String description;
    private Double amount;

    /**Costruttore di default
     *
     */
    public BMItem() {
        this.date = new GregorianCalendar();
        this.description = "";
        this.amount = Double.parseDouble("0");
    }
    
    /**Costruttore con tutti i paramenti in input.
     *
     * @param date data della transazione
     * @param description descrizione della transazione
     * @param amount ammontare della transazione
     */
    public BMItem(GregorianCalendar date, String description, Double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }
    
    /**Ritorna la data della transazione.
     * @return
     */
    public GregorianCalendar getDate() {return date;}
    
    public void setDate(GregorianCalendar date) {this.date = date;}
    
    public String getDescription() {return description;}
    
    public void setDescription(String description) {this.description = description;}
    
    public Double getAmount() {return amount;}
    
    public void setAmount(Double amount) {this.amount = amount;}
}