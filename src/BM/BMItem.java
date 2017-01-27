package BM;

/**Classe che implementa le voci di bilancio
 * Questa classe viene utilizzata dal programma
 * per creare un oggetto riga da inserire nella tabella.
 * @author Giacomo Guerzoni
 */
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.io.Serializable;

/**
 * Classe che serve per istanziare l'oggetto base di questo gestore di bilancio.
 * 
 * <br>L'oggetto che verra creato memorizza al suo interno la data della transazione, la sua descrizione e il rispettivo ammontare
 * della speasa/incasso.
 * @author giacomo
 */
public class BMItem implements Serializable{
    private static final long serialVersionUID = 1L;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat completeDate = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
    private GregorianCalendar date;
    private String description;
    private Double amount;

    /**
     * Costruttore di default che inizializza gli attributi della classe.
     */
    public BMItem() {
        this.date = new GregorianCalendar();
        this.description = "";
        this.amount = Double.parseDouble("0");
    }
    
    /**
     * Costruttore con tutti i paramenti in input.
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
    
    /**
     * Ritorna la data della transazione.
     * 
     * @return GregorianCalendar che corrisponda alla data della transazione.
     */
    public GregorianCalendar getDate() {return date;}
    
    /**
     * Metodo per settare una particolare data a una transazione.
     * 
     * @param date Si aspetta una data di tipo GregorianCalendar.
     */
    public void setDate(GregorianCalendar date) {this.date = date;}
    
    /**
     * Metodo get per ottenere la descrizione della transazione.
     * 
     * @return Stringa contenenta la descrizione.
     */
    public String getDescription() {return description;}
    
    /**
     * Metodo per settare una particolare descrizione a una transazione.
     * 
     * @param description stringa contenente la descrizione della transazione.
     */
    public void setDescription(String description) {this.description = description;}
    
    /**
     * Metodo get per ottenere l'emmontare della transazione.
     * 
     * @return Integer che corrisponde all'ammontare della transazione. 
     */
    public Double getAmount() {return amount;}
    
    /**
     * Metodo per settare l'ammontare di una transazione.
     * @param amount Nuovo ammontare da settare per una transazione.
     */
    public void setAmount(Double amount) {this.amount = amount;}

}