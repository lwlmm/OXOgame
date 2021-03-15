package OXOExceptions;

public class CellAlreadyTakenException extends OXOMoveException
{

    public CellAlreadyTakenException(int row, int column)
    {
        super (row, column);
    }

    @Override
    public String toString() {
        String out = "The (" + getRow() + "," +
                getColumn() + ") cell has been taken already!";
        return out;
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
