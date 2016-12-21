import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BMTableModel extends AbstractTableModel {
    List transactions = null;
    String[] colName = {"Date","Description","Amount","Remove","Modify"};
    
    public BMTableModel(List transactions) {
        this.transactions = transactions;
    }
    public int getColumnCount() {
        return colName.length;
    }
    public int getRowCount() {
        return transactions.size();
    }
    public Object getValueAt(int row, int col) {
        BMItem item = (BMItem)transactions.get(row);
        
    }
    
    public String getColumnName(int col) {
        
    }
    
    public boolean idCellEditable(int row, int col) {
        
    }
}