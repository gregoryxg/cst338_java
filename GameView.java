/**
 * Gregory Gonzalez
 * 2/25/2017
 * CST 338 Software Design Final
 * GameView.java
 * Responsible for updating the game window display
 *
 */

import javax.swing.*;

public class GameView
{
   GameWindow gameWindow;
   GameModel gameModel;
   
   public GameView(GameModel gameModel)
   {
      this.initView(gameModel);
   }   
   public void initView(GameModel gameModel)
   {
      this.gameModel = gameModel;
      this.gameWindow = new GameWindow(gameModel.gameName, gameModel.numLetters);
   }
   public void updateDisplay(JLabel currTime) //Used to update the game window with current values
   {
      gameWindow.resetGameWindow();
      gameWindow.directions.add(gameModel.directions);
      
      for (int i = 0; i < gameModel.directionAreaButtons.length; i++)
      {
         gameWindow.wordLengthSelectors.add(gameModel.directionAreaButtons[i]);
      }
      
      gameWindow.giveUp.add(gameModel.giveUpButton);
      
      for (int i = 0; i < gameModel.playAreaLetters.length; i++)
      {
         gameWindow.playArea.add(gameModel.playAreaLetters[i]);
      }
      
      gameWindow.playInfo.add(new JLabel("" + gameModel.score, JLabel.CENTER));
      gameWindow.playInfo.add(currTime, JLabel.CENTER);
      
      for (int i = 0; i < gameModel.selectAreaLetters.length; i++)
      {
        
         gameWindow.selectArea.add(gameModel.selectAreaLetters[i]);
              
      }
      gameWindow.setVisible(true);      
   }
}
