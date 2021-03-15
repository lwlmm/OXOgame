package OXOExceptions;

public class InvalidIdentifierLengthException extends InvalidIdentifierException
{
    int length;

    public InvalidIdentifierLengthException(int row, int column, int lengthIn)
    {
        super(row, column);
        length = lengthIn;
    }

    @Override
    public String toString() {
        return "\nInvalid input length !\n"+
                "Should be 2 characters, one letter, one number"+
                "\nCurrent length: " + length;
    }
}
