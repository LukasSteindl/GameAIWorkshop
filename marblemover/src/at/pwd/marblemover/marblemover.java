package at.pwd.marblemover;

import java.util.List;
import at.pwd.boardgame.game.base.WinState;
import at.pwd.boardgame.game.mancala.MancalaGame;
import at.pwd.boardgame.game.mancala.MancalaBoard;
import at.pwd.boardgame.game.mancala.agent.MancalaAgent;
import at.pwd.boardgame.game.mancala.agent.MancalaAgentAction;

public class marblemover implements MancalaAgent {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public String BestMove = "";

    @Override
    public MancalaAgentAction doTurn(int computationTime, MancalaGame mancalaGame) {

        // wer bin ich?
        int currentPlayer = mancalaGame.getState().getCurrentPlayer();
        // System.out.println(ANSI_GREEN + currentPlayer + ANSI_RESET);
        // String ownDepot = mancalaGame.getBoard().getDepotOfPlayer(currentPlayer);
        // int moveValue = mancalaGame.getState().stonesIn(ownDepot);

        // ermittle den besten score ausgehend von der aktuellen position:
        // der beste move für uns als maximierenden Spieler wurde dann in der globalen
        // Varibale BestMove hinterlegt!
        BestMove = mancalaGame.getSelectableSlots().get(0);

        int bewertung = ScoreFromPosition(currentPlayer, mancalaGame, 4);
        System.out.println(
                "maximaler score den der bot aus dieser position machen kann:" + bewertung + " Bester Move" + BestMove);

        // System.out.println("looking 4 steps ahead gives" + TotalNumberOfLeaveNodes +
        // " possible outcomes!");
        // get a list of all currently selectable slots
        List<String> slots = mancalaGame.getSelectableSlots();
        for (String possiblemoves : slots)
            System.out.println(possiblemoves);
        // since this list will never be empty (otherwise the game would be over), we
        // dont need a additional check
        // Slot IDs are unique strings strings

        // String selectedSlot = slots.get(BestMove);
        // now we pack the selected slot in an agent action and return it
        // the Mancala Boardgame Engine will then apply this action onto the slot
        return new MancalaAgentAction(BestMove);
    }

    public int ScoreFromPosition(int currentPlayer, MancalaGame m, int depth) {
        depth = depth - 1;
        // init best move to leftmost move

        int maximumScoreSoFar = -1000000;
        int minimumScoreSoFar = 1000000;

        // wenn man auf der Blattebene angekommen ist weil die tiefe erschöpft ist oder
        // ein spieler gewonnen hat.
        if (depth == 0 || m.checkIfPlayerWins().getState() != WinState.States.NOBODY) {
            // hier schauen welcher Spieler wieviele Punkte hätte!
            MancalaBoard BoardInNMoves = m.getBoard();
            String myDepot = BoardInNMoves.getDepotOfPlayer(currentPlayer);
            String enemyDepot = BoardInNMoves.getDepotOfPlayer(1 - currentPlayer);
            int myPoints = m.getState().stonesIn(myDepot);
            int enemyPoints = m.getState().stonesIn(enemyDepot);
            System.out.println("Outlook of 4 steps ends in - myPoints:" + myPoints + "enemyPoints:" + enemyPoints);
            return myPoints - enemyPoints;
        }

        else {

            boolean maximizing = false;
            // color each own move green and each enemy move red.
            if (m.getState().getCurrentPlayer() == currentPlayer)// {
                // maximizing
                maximizing = true;

            for (String s : m.getSelectableSlots()) {
                // gib den aktuellen move aus und führe diesen in einer kopie des spiels aus:

                MancalaGame gamecopy = new MancalaGame(m);

                // aus dem alphabeta-agent abgeschaut um jeweils den currentplayer in der game
                // instanz zu setzen
                boolean moveAgain = gamecopy.selectSlot(s);
                if (!moveAgain)
                    gamecopy.nextPlayer();

                // stell dir vor der nächste schritt ist der letzte... dann erhält man aus der
                // funktion einen positiven wert wenn der aktuelle bot mehr punkte als der
                // gegner hat!
                int currentScore = ScoreFromPosition(currentPlayer, gamecopy, depth);

                if (maximizing) {
                    System.out.println(ANSI_GREEN + new String(new char[(depth - 3) * -1]).replace("\0", "-") + s
                            + "Score" + currentScore + ANSI_RESET);
                } else {

                    System.out.println(ANSI_RED + new String(new char[(depth - 3) * -1]).replace("\0", "-") + s
                            + "Score" + currentScore + ANSI_RESET);
                }

                // take maximum score from position as current return value:
                // wenn man maximierender spieler ist, wählt man den move der den höchsten
                // positiven wert liefert!

                if (currentScore > maximumScoreSoFar) {
                    maximumScoreSoFar = currentScore;
                    if (depth == 3)// bestmove darf nur ganz oben gesetzt werden!
                        BestMove = s;
                }

                if (currentScore < minimumScoreSoFar) {
                    minimumScoreSoFar = currentScore;
                }

                // rufe nun rekursiv die selbe methode aus
                // numberOfLeaves = numberOfLeaves + ScoreFromPosition(currentPlayer, gamecopy,
                // depth, numberOfLeaves);
            }
            if (maximizing)
                return maximumScoreSoFar;
            else
                return minimumScoreSoFar;

        }

    }

    @Override
    public String toString() {
        return "MarbleMover";
    }
}