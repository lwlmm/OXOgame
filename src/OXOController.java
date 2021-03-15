import OXOExceptions.*;

import java.util.Locale;

class OXOController
{
    OXOModel gameModel;
    static int moveCount;

    public OXOController(OXOModel model)
    {
        gameModel = model;
        moveCount = 0;
    }

    public int getComboNumber(OXOPlayer player, int row, int column, int rowDirection, int columnDirection, int cnt)
    {
        if( row < 0 || row >= gameModel.getNumberOfRows() ||
                column < 0 || column >= gameModel.getNumberOfColumns()){
            return cnt;
        }
        if(gameModel.getCellOwner(row, column) != player){
            return cnt;
        }
        cnt++;
        return getComboNumber(player, row+rowDirection, column+columnDirection,
                                rowDirection, columnDirection, cnt);
    }



    public void handleIncomingCommand(String command) throws OXOMoveException
    {
        //Initialize the first Player
        if(moveCount == 0){
            gameModel.setCurrentPlayer(gameModel.players[0]);
        }

        //Check if the command length is right
        if(command.length() != 2){
            throw new InvalidIdentifierLengthException(0, 0, command.length());
        }

        //Character parser
        command = command.toLowerCase();
        if(command.charAt(0) < 'a' || command.charAt(0) >= 'z'){
            throw new InvalidIdentifierCharacterException(0, 0, command.charAt(0), RowOrColumn.ROW);
        }
        if(command.charAt(1) < '1' || command.charAt(1) >= '9'){
            throw new InvalidIdentifierCharacterException(0, 0, command.charAt(1), RowOrColumn.COLUMN);
        }

        int []coordinate = new int[2];
        coordinate[0] = (command.charAt(0) - 'a');
        coordinate[1] = (command.charAt(1) - '0' -1);

        //Outside range checker
        if (coordinate[0] >= gameModel.getNumberOfRows() ||
            coordinate[1] >= gameModel.getNumberOfColumns()){
            throw new OutsideCellRangeException(coordinate[0], coordinate[1]);
        }

        //Check if cell is already taken
        if(gameModel.getCellOwner(coordinate[0], coordinate[1]) == gameModel.players[1] ||
                gameModel.getCellOwner(coordinate[0], coordinate[1]) == gameModel.players[0]){
            throw new CellAlreadyTakenException(coordinate[0], coordinate[1]);
        }

        gameModel.setCellOwner(coordinate[0], coordinate[1], gameModel.getCurrentPlayer());

        //Set the next player
        moveCount++;
        int turnCnt = (moveCount % gameModel.getNumberOfPlayers());
        gameModel.setCurrentPlayer(gameModel.players[turnCnt]);

        //Check and set the winner
        for(int i = 0; i < gameModel.getNumberOfRows(); ++i){
            for(int j = 0; j < gameModel.getNumberOfColumns(); ++j){
                for(int k = 0; k < gameModel.getNumberOfPlayers(); ++k){
                    if(getComboNumber(gameModel.players[k], i, j, 1, 1, 0)
                            == gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.players[k]);
                    }
                    if(getComboNumber(gameModel.players[k], i, j, 1, 0, 0)
                            == gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.players[k]);
                    }
                    if(getComboNumber(gameModel.players[k], i, j, 1, -1, 0)
                            == gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.players[k]);
                    }
                    if(getComboNumber(gameModel.players[k], i, j, 0, -1, 0)
                            == gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.players[k]);
                    }
                    if(getComboNumber(gameModel.players[k], i, j, -1, -1, 0)
                            == gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.players[k]);
                    }
                    if(getComboNumber(gameModel.players[k], i, j, -1, 0, 0)
                            == gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.players[k]);
                    }
                    if(getComboNumber(gameModel.players[k], i, j, -1, 1, 0)
                            == gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.players[k]);
                    }
                    if(getComboNumber(gameModel.players[k], i, j, 0, 1, 0)
                            == gameModel.getWinThreshold()){
                        gameModel.setWinner(gameModel.players[k]);
                    }
                }
            }
        }

        //Check and set drawn
        OXOPlayer checker = new OXOPlayer(' ');
        int cellCount = 0;
        for(int i = 0; i < gameModel.getNumberOfRows(); ++i){
            for(int j = 0; j < gameModel.getNumberOfColumns(); ++j){
                for(int k = 0; k < gameModel.getNumberOfPlayers(); ++k){
                    if(gameModel.getCellOwner(i, j) == gameModel.players[k]){
                        cellCount++;
                    }
                }
            }
        }
        if(cellCount == gameModel.getNumberOfColumns()* gameModel.getNumberOfRows()){
            gameModel.setGameDrawn();
        }
    }

}

