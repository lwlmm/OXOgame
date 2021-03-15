package OXOExceptions;

public class CellDoesNotExistException extends OXOMoveException
{

    public CellDoesNotExistException(int row, int column)
    {
        super(row, column);
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
