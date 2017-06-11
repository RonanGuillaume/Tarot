package view.tarotView;

import model.Participant;
import model.Trick;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public class TarotTableModel extends AbstractTableModel{
    private List<Trick> tricks;
    private List<Participant> header;

    public TarotTableModel(List<Participant> players) {
        super();
        this.tricks = new ArrayList<>();
        this.header = players;
    }

    @Override
    public int getRowCount() {
        return tricks.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return header.size() + 1;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0){
            return "Tour";
        }
        return header.get(column - 1).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex == tricks.size()){
            if (columnIndex == 0){
                return "Total:";
            }
            return Integer.toString(calculateSum(columnIndex));
        }
        switch (getColumnName(columnIndex)){
            case "Tour" :
                return Integer.toString(tricks.size() - 1);
            default:
                return Integer.toString(tricks.get(rowIndex).getScores().get(header.get(columnIndex - 1)));
        }
    }

    void setTricks(List<Trick> tricks) {
        this.tricks = tricks;
        fireTableDataChanged();
    }

    private int calculateSum(int columnIndex) {
        int sum = 0;
        for (Trick trick : tricks) {
            sum += trick.getScores().get(header.get(columnIndex - 1));
        }
        return sum;
    }
}
