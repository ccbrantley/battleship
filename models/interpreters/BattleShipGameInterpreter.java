package battleship.models.interpreters;

/* @author Area 51 Block Party:
 * Christopher Brantley, Andrew Braswell
 * Last Updated: 12/03/2019
 */

import battleship.models.BattleShipFleet;
import battleship.models.BattleShipGame;
import battleship.models.BattleShipPlayer;
import battleship.models.Coordinate;
import battleship.tools.events.FireAwayEvent;
import battleship.tools.events.*;
import battleship.tools.Listener;
import battleship.tools.ViewAssets;


public class BattleShipGameInterpreter implements Listener {

    private BattleShipGame battleShipGame;
    private boolean gameOver = false;

    public BattleShipGameInterpreter (BattleShipGame _battleShipGame) {
        this.battleShipGame = _battleShipGame;
    }

    @Override
    public void catchEvent (Object _event) {
        if (_event instanceof FireAwayEvent) {
            FireAwayEvent event = (FireAwayEvent)_event;
            BattleShipPlayer attackingPlayer;
            BattleShipPlayer receivingPlayer;
            String gameMessage;
            int destination = event.getDestination();
            if (destination == BattleShipPlayer.AWAY) {
                attackingPlayer = this.battleShipGame.getPlayer1();
                receivingPlayer = this.battleShipGame.getPlayer2();
                gameMessage = "Player 1's target, ";
            }
            else {
                attackingPlayer = this.battleShipGame.getPlayer2();
                receivingPlayer = this.battleShipGame.getPlayer1();
                gameMessage = "Player 2's target, ";
            }
            if (!attackingPlayer.isTurn()) {
                return;
            }
            Coordinate attackedCoordinate = attackingPlayer.getCurrentTarget();
            GameMessageEvent windMessageEvent;
            int shotAlterStatus = attackingPlayer.getBattleShipFleet().calculateShotProbability(attackedCoordinate);
            if (shotAlterStatus == BattleShipFleet.SHOTUNCHANGED) {
                windMessageEvent = new GameMessageEvent("The wind is calm.");
            }
            else if (shotAlterStatus == BattleShipFleet.SHOTCHANGED) {
                windMessageEvent = new GameMessageEvent("The wind begins to blow!");
            }
            else {
                windMessageEvent = new GameMessageEvent("The wind is unmatched!");
                BattleShipGame.getEventBus().throwEvent(windMessageEvent);
                return;
            }
            BattleShipGame.getEventBus().throwEvent(windMessageEvent);
            int row = attackedCoordinate.getRow();
            int column = attackedCoordinate.getColumn();
            gameMessage += "(" + (row + 1) + ", " + (column + 1) + "), ";
            GameMessageEvent messageEvent;
            UpdatePinEvent updatePinEvent;
            if (receivingPlayer.getBattleShipFleet().receiveFire(attackedCoordinate)) {
                gameMessage += "was successful.";
                BattleShipGame.getEventBus().throwEvent(new ShipHitEvent(row,column, destination));
                updatePinEvent = new UpdatePinEvent(row, column, attackingPlayer.getPlayerTeam(), ViewAssets.REDLED);
                if (receivingPlayer.getBattleShipFleet().getLiveShipCount() == 0) {
                    gameMessage = "Player " + (attackingPlayer.getPlayerTeam() + 1) + " wins!";
                    this.gameOver = true;
                }
            }
            else {
                gameMessage += "was not successful.";
                updatePinEvent = new UpdatePinEvent(row, column, attackingPlayer.getPlayerTeam(), ViewAssets.YELLOWLED);
            }
            attackingPlayer.setTurn(false);
            receivingPlayer.setTurn(true);
            BattleShipGame.getEventBus().throwEvent(updatePinEvent);
            BattleShipGame.getEventBus().throwEvent(new GameMessageEvent(gameMessage));
            if (this.gameOver) {
                BattleShipGame.getEventBus().resetListeners();
            }
        }
    }

}