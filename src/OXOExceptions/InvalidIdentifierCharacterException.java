package OXOExceptions;

public class InvalidIdentifierCharacterException extends InvalidIdentifierException
{
    char character;
    RowOrColumn type;

    public InvalidIdentifierCharacterException(int row, int column, char characterIn, RowOrColumn typeIn) {
        super(row, column);
        character = characterIn;
        type = typeIn;
    }

    @Override
    public String toString()
    {
        return character + " is a wrong character of " + type + "!";
    }
}
