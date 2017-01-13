package BM;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class BMTableModel extends AbstractTableModel implements java.io.Serializable{
    private ArrayList<BMItem> transactions = null;
    private final String[] colName = {"Date","Description","Amount","Edit"};
    private List<boolean[]> editable;
    
    BMTableModel() {
        transactions = new ArrayList<>();
        editable = new ArrayList<boolean[]>();
    }
    
    BMTableModel(ArrayList<BMItem> transactions) {
        this.transactions = transactions;//new ArrayList<>(transactions);
        editable = new ArrayList<boolean[]>();
        boolean[] element = {false, false, false};
        
        for(int i=0;i<this.transactions.size();i++) {
            editable.add(element);
        }        
    }

    /**Get method fo the number of column
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return colName.length;
    }
    @Override
    public int getRowCount() {
        return transactions.size();
    }
    @Override
    public Object getValueAt(int row, int col) {
        BMItem item = transactions.get(row);
        switch (col) {
            case 0: return BMItem.dateFormat.format(item.getDate().getTime());
            case 1: return item.getDescription(); 
            case 2: return item.getAmount(); 
            default: return ""; 
        }
    }
    
    @Override
    public String getColumnName(int col) {
        return colName[col];
    }
    
    public void setEditable(boolean[] edit, int row){
        this.editable.set(row, edit);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return editable.get(row)[col];
    }
    
    @Override
    public void setValueAt(Object field, int row, int col) {
        BMItem item= transactions.get(row);
        switch(col) {
            /**Date field*/
            case 0:
                item.setDate((GregorianCalendar)field);
                break;
            /**Description field*/
            case 1:
                item.setDescription((String)field);
                break;
            /**Amount field*/
            case 2:
                item.setAmount((Double)field);
                break;
            /**Default*/
            default:      
                break;
        }
        fireTableDataChanged();
    } 
    
    public void removeRow(int row) {
        transactions.remove(row);
        this.fireTableRowsDeleted(row, row);
    }
    
    public void resetTableModel() {
        if(this.getRowCount() > 0) {
            transactions.clear();        
            this.fireTableRowsDeleted(0, 0);
        }
    }
    
    public void addItem(BMItem item) {
        transactions.add(item);
        fireTableDataChanged();
        boolean[] element = {false, false, false};
        editable.add(element);
    }
    
    public ArrayList<BMItem> getTransactionsList() {
        return transactions;
    }
    
    public void setTransactionList(ArrayList<BMItem> transactions) {
        this.transactions.addAll(transactions);
        fireTableDataChanged();
    }
}