/**
 * Gregory Gonzalez
 * 2/25/2017
 * CST 338 Software Design Final
 * GameController.java
 * Responsible for all actions the user takes, as well as managing the timer and win/lose conditions
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GameController
{
   GameModel gameModel;
   GameView gameView;
   Timer timer;
   
   public GameController(GameModel gameModel, GameView gameView)
   {      
      this.timer = new Timer(this);
      this.initController(gameModel, gameView);
   }   
   public void initController(GameModel gameModel, GameView gameView)
   {
      this.gameModel = gameModel;
      this.gameView = gameView;
      this.timer.start();
      this.resetGame();
   }
   
   public void resetGame() //Used to reset the game after winning, losing or changing the word length
   {
      gameModel.giveUpButton = new JButton("Give Up");
      gameModel.giveUpButton.addActionListener(new giveUp());
      gameModel.directionAreaButtons[0] = new JButton(gameModel.letterSelector[0]);
      gameModel.directionAreaButtons[0].addActionListener(new letterSelector(3));
      gameModel.directionAreaButtons[1] = new JButton(gameModel.letterSelector[1]);
      gameModel.directionAreaButtons[1].addActionListener(new letterSelector(4));
      gameModel.directionAreaButtons[2] = new JButton(gameModel.letterSelector[2]);
      gameModel.directionAreaButtons[2].addActionListener(new letterSelector(5));
      this.gameModel.selectedWord = this.getRandomWord();
      this.gameModel.playedWord = "";
      this.gameModel.currentWord = this.gameModel.selectedWord.toCharArray(); 
      this.gameModel.scrambledWord = this.scrambleWord().toCharArray();
      this.initJButtonArrays();
      
      this.timer.resetClock(getSeconds()); //Used to reset the timer to the value returned by getSeconds()
      this.gameView.updateDisplay(this.timer.getTime());
      if (!this.timer.getCurrState())
      {
         this.timer.setCurrState(true);
      } 
   }
   public int getSeconds() //Used to set the timer based on the current word length
   {
      switch(gameModel.numLetters)
      {
         case 3:
         {
            return 30;
         }
         case 4:
         {
            return 45;
         }
         case 5:
         {
            return 60;
         }
         default:
         {
            return 30;
         }         
      }
   }

   public void initJButtonArrays() //Initializes the JButton arrays playAreaLetters and selectAreaLetters
   {
      for (int i = 0; i < gameModel.playAreaLetters.length; i++)
      {
         this.gameModel.playAreaLetters[i] = new JButton("_");         
      }
      for (int i = 0; i < gameModel.selectAreaLetters.length; i++)
      {
         this.gameModel.selectAreaLetters[i] = new JButton(Character.toString(gameModel.scrambledWord[i]));
         this.gameModel.selectAreaLetters[i].addActionListener(new SelectLetter(i));
      }
   }
   
   public void updateTime() //Called from Timer to update the time remaining between user actions
   {      
      this.gameView.updateDisplay(this.timer.getTime());
   }
   
   public void update() //Used to update the game window and check win conditions
   {
      gameView.updateDisplay(this.timer.getTime());
      checkWin();
   }
   
   public void checkWin() //Checks if the played word matches the selected word to unscramble, and calls gameWin() if there is a match
   {
      if (gameModel.playedWord.equals(gameModel.selectedWord))
      {
         gameWin();
      }
   }
   
   public void gameWin() //Shows a pop-up stating player has won, current score, and continue or quit options
   {
      timer.setCurrState(false);
      gameModel.score++;
      gameView.gameWindow.setEnabled(false);
      JFrame gameWon = new JFrame();
      gameWon.setSize(200, 120);
      gameWon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gameWon.setLocationRelativeTo(null);
      
      gameWon.setLayout(new BorderLayout());
      JPanel top = new JPanel();
      JPanel bot = new JPanel();
      top.setLayout(new GridLayout(3, 1));
      bot.setLayout(new GridLayout(1,2));
      JLabel message = new JLabel("" + gameModel.selectedWord + " is the correct word!", JLabel.CENTER);
      JLabel message2 = new JLabel("You win!", JLabel.CENTER);
      JLabel message3 = new JLabel("Current Score: " + gameModel.score, JLabel.CENTER);
      JButton playAgain = new JButton("Continue");      
      JButton quit = new JButton("Quit");
      playAgain.addActionListener(new playAgain(gameWon));
      quit.addActionListener(new quitGame());
      
      top.add(message);
      top.add(message2);
      top.add(message3);
      bot.add(playAgain);
      bot.add(quit);
      gameWon.add(top, BorderLayout.NORTH);
      gameWon.add(bot, BorderLayout.SOUTH);
      gameWon.setVisible(true);
   }
   
   public void gameLose() //Shows a pop-up stating player has lost, current score, and continue or quit options
   {
      timer.setCurrState(false);
      gameView.gameWindow.setEnabled(false);
      JFrame gameLose = new JFrame();
      gameLose.setSize(200, 120);
      gameLose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gameLose.setLocationRelativeTo(null);
      
      gameLose.setLayout(new BorderLayout());
      JPanel top = new JPanel();
      JPanel bot = new JPanel();
      top.setLayout(new GridLayout(2, 1));
      bot.setLayout(new GridLayout(1,2));
      JLabel message = new JLabel("" + gameModel.selectedWord + " was the correct word!", JLabel.CENTER);
      JLabel message2 = new JLabel("Current Score: " + gameModel.score, JLabel.CENTER);
      JButton playAgain = new JButton("Continue");      
      JButton quit = new JButton("Quit");
      playAgain.addActionListener(new playAgain(gameLose));
      quit.addActionListener(new quitGame());
      
      top.add(message);
      top.add(message2);
      bot.add(playAgain);
      bot.add(quit);
      gameLose.add(top, BorderLayout.NORTH);
      gameLose.add(bot, BorderLayout.SOUTH);
      gameLose.setVisible(true);
   }
      
   public String getRandomWord() //Returns a random word from a list of 500 words
   {
      int rndNum = (int)(Math.random() * (gameModel.numWords));
      this.gameModel.selectedWord = gameModel.wordList[rndNum];
      return this.gameModel.selectedWord;
   }
   public String scrambleWord() //Returns a scrambled version of the current word
   { 
      Random randNum = new Random();
      char scrambled[] = gameModel.selectedWord.toCharArray();

      // Scramble the letters  
      for( int i = 0; i < scrambled.length - 1; i++)
      {
          int j = randNum.nextInt(scrambled.length - 1);
          // Swap letters
          char temp = scrambled[i]; 
          scrambled[i] = scrambled[j];  
          scrambled[j] = temp;
      }
      
      String scrambledWord = new String(scrambled);

      if (scrambledWord.equals(gameModel.selectedWord)) //If scrambleWord equals current word, scrambleWord() is returned
      {
         return scrambleWord();
      }
      else
      {
         return scrambledWord;
      }           
   }
   
   class SelectLetter implements ActionListener //Action when user selects a letter to play
   {
      int position;
      char selectedChar;
            
      public SelectLetter(int position)
      {
         this.position = position;
         this.selectedChar = gameModel.selectAreaLetters[this.position].getText().charAt(0);
      }
      
      public void actionPerformed(ActionEvent e)
      {         
         //Add selected letter to playAreaLetters        
         for (int i = 0; i < gameModel.playAreaLetters.length; i++)
         {
            if (gameModel.playAreaLetters[i].getText() == "_")
            {
               gameModel.playAreaLetters[i] = new JButton(Character.toString(this.selectedChar));
               gameModel.playAreaLetters[i].addActionListener(new RemoveLetter(i));
               break;
            }
         }
         //Add selected letter to playedWord
         gameModel.playedWord += this.selectedChar;
         
         //Change selected button to blank tile
         gameModel.selectAreaLetters[this.position] = new JButton("_");
         update();
      }
   }
   class RemoveLetter implements ActionListener //Action when user removes a letter from the board
   {
      int position;
      char selectedChar;
      
      public RemoveLetter(int position)
      {
         this.position = position;
         this.selectedChar = gameModel.playAreaLetters[this.position].getText().charAt(0);
      }
      
      public void actionPerformed(ActionEvent e)
      {
         //Removes selected letter to selectAreaLetters
         for (int i = 0; i < gameModel.selectAreaLetters.length; i++)
         {
            if (gameModel.selectAreaLetters[i].getText() == "_")
            {
               gameModel.selectAreaLetters[i] = new JButton(Character.toString(this.selectedChar));
               gameModel.selectAreaLetters[i].addActionListener(new SelectLetter(i));
               break;
            }
         }         
         
         //Sets selected letter to blank tile
         gameModel.playAreaLetters[this.position] = new JButton("_");
         
         //Removes selected letter from playWord
         String tempString = "";
         for (int i = 0; i < gameModel.playAreaLetters.length - 1; i++)
         {
            if (gameModel.playAreaLetters[i].getText() != "_")
            {
               tempString += gameModel.playAreaLetters[i].getText();
            }            
         }
         gameModel.playedWord = tempString;
         
         update();
      }
   }
   
   class playAgain implements ActionListener //Action when user selects continue upon winning or losing
   {
      JFrame won;
      int score;
      
      public playAgain(JFrame won)
      {
         this.won = won;
         this.score = gameModel.score;
      }
      
      public void actionPerformed(ActionEvent e)
      {
         this.won.setVisible(false);
         gameView.gameWindow.setVisible(false);
         resetGame();
      }
   }
   
   class quitGame implements ActionListener //Action when user selects quit after winning or losing
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
   }
   
   class giveUp implements ActionListener //Action when user selects the "Give Up" buttons
   {      
      public void actionPerformed(ActionEvent e)
      {
         gameLose();      
      }
   }
   
   class letterSelector implements ActionListener //Action when user selects the word length selector buttons
   {
      int score = gameModel.score;
      int numLetters;
      public letterSelector(int numLetters)
      {
         this.numLetters = numLetters;
      }
      public void actionPerformed(ActionEvent e)
      {
         gameModel.initModel(this.numLetters);
         gameModel.score = this.score;
         resetGame();
      }
   }   
}