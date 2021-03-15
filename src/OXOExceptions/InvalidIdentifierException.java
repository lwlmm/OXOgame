package OXOExceptions;

public class InvalidIdentifierException extends CellDoesNotExistException
{

    public InvalidIdentifierException(int row, int column)
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
