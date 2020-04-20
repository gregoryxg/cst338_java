/**
 * Gregory Gonzalez
 * 2/25/2017
 * CST 338 Software Design Final
 * WordSearch.java
 * Creates GameModel, GameView and GameController objects to start the game
 *
 */

public class WordSearch 
{
   public static void main(String [] args)
   {
      GameModel gameModel = new GameModel();
      GameView gameView = new GameView(gameModel);
      GameController gameController = new GameController(gameModel, gameView);
   }   
}