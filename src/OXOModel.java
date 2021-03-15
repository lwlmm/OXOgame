import java.util.ArrayList;

class OXOModel<cells>
{
    //private OXOPlayer cells[][];
    private ArrayList<OXOPlayer> cells;
    public OXOPlayer players[];
    private OXOPlayer currentPlayer;
    private OXOPlayer winner;
    private boolean gameDrawn;
    private int winThreshold;
    public int wid;
    public int hei;

    public OXOModel  (int numberOfRows, int numberOfColumns, int winThresh)
    {
        winThreshold = winThresh;
        cells = new ArrayList<OXOPlayer>();
        wid = numberOfRows;
        hei = numberOfColumns;
        players = new OXOPlayer[2];
    }

    public int getNumberOfPlayers()
    {
        return players.length;
    }

    public void addPlayer(OXOPlayer player)
    {
        for(int i=0; i<players.length ;i++) {
            if(players[i] == null) {
                players[i] = player;
                return;
            }
        }
    }

    public OXOPlayer getPlayerByNumber(int number)
    {
        return players[number];
    }

    public OXOPlayer getWinner()
    {
        return winner;
    }

    public void setWinner(OXOPlayer player)
    {
        winner = player;
    }

    public OXOPlayer getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setCurrentPlayer(OXOPlayer player)
    {
        currentPlayer = player;
    }

    public int getNumberOfRows()
    {
        return wid;
    }

    public int getNumberOfColumns()
    {
        return hei;
    }

    public OXOPlayer getCellOwner(int rowNumber, int colNumber)
    {
        if(cells.isEmpty()){
            return new OXOPlayer(' ');
        }
        return cells.get(rowNumber* wid + colNumber);
    }

    public void setCellOwner(int rowNumber, int colNumber, OXOPlayer player)
    {
        if(cells.isEmpty()){
            cells.ensureCapacity(wid * hei);
            OXOPlayer p1 = new OXOPlayer(' ');
            for(int i = 0; i < wid * hei; i++){
                cells.add(p1);
            }
        }
        cells.set(rowNumber * wid + colNumber, player);
    }

    public void setWinThreshold(int winThresh)
    {
        winThreshold = winThresh;
    }

    public int getWinThreshold()
    {
        return winThreshold;
    }

    public void setGameDrawn()
    {
        gameDrawn = true;
    }

    public boolean isGameDrawn()
    {
        return gameDrawn;
    }
}
