package BM;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BMTableModel extends AbstractTableModel {
    private ArrayList<BMItem> transactions = null;
    private String[] colName = {"Date","Description","Amount","Remove","Modify"};
    private boolean editable;
    
    public BMTableModel(ArrayList transactions) {
        this.transactions = transactions;
    }

    BMTableModel() {
        transactions = new ArrayList<BMItem>();
    }
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
        BMItem item = (BMItem)transactions.get(row);
        switch (col) {
            case 0: return item.getDate().toString(); 
            case 1: return item.getDescription();
            case 2: return item.getAmount();
            default: return "";
        }
    }
    
    @Override
    public String getColumnName(int col) {
        return colName[col];
    }
    
    public boolean getEditable() {
        return editable;
    }
    
    public void setEditable(boolean editable){
        this.editable = editable;
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return editable;
    }
}