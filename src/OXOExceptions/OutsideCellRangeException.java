package OXOExceptions;

public class OutsideCellRangeException extends CellDoesNotExistException
{
    int position;
    RowOrColumn type;

    public OutsideCellRangeException(int row, int column)
    {
        super(row, column);
        position = Math.max(getRow(), getColumn());
        if(getRow() > getColumn()){
            type = RowOrColumn.ROW;
        }
        else{
            type = RowOrColumn.COLUMN;
        }
    }

    @Override
    public String toString() {
        return "OutsideCellRangeException{" + "type: " + type +
                ", position: " + position + '}';
    }

    public int getRow()
    {
        return super.getRow();
    }

    public int getColumn()
    {
        return super.getColumn();
    }
}
