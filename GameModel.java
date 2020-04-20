/**
 * Gregory Gonzalez
 * 2/25/2017
 * CST 338 Software Design Final
 * GameModel.java
 * Majority of game variables are stored here
 *
 */

import javax.swing.*;

public class GameModel
{
   GameWords gameWords;
   int numWords, numLetters, score;
   String selectedWord, playedWord, gameName;
   String [] wordList, letterSelector;
   char [] currentWord, scrambledWord;
   JLabel directions;
   JButton [] directionAreaButtons, playAreaLetters, selectAreaLetters;
   JButton giveUpButton;
      
   public GameModel()
   {
      this.initModel(3); //The game starts with a three letter word to unscramble
   }
   public void initModel(int numLetters)
   {
      this.gameWords = new GameWords(numLetters); //Creates a new GameWords objects and passes to it the selected number of letters for the word
      this.numWords = gameWords.getNumWords(); //Gets the number of words generated from GameWords
      this.numLetters = gameWords.getNumLetters(); //Gets the length of the words from GameWords
      this.score = 0; //Initializes score to 0
      this.selectedWord = "";
      this.playedWord = "";
      this.gameName = "Word Search"; //Title for the game window
      this.wordList = gameWords.getWordList(); //Gets the WordList array from GameWords
      this.letterSelector = new String[3]; //Uses to store the names for the word length selector buttons
      this.letterSelector[0] = "Three Letters";
      this.letterSelector[1] = "Four Letters";
      this.letterSelector[2] = "Five Letters";
      this.directions = new JLabel("Unscramble the correct " + numLetters + " letter word before the time runs out!", JLabel.CENTER); //Directions
      this.currentWord = new char[numLetters]; //Used to store the selected word as a char array
      this.scrambledWord = new char[numLetters]; //Used to store a scrambled version of selected word as a char array
      this.directionAreaButtons = new JButton[3]; //JButtons for the word length selectors
      this.playAreaLetters = new JButton[numLetters]; //JButtons for the letters on the board
      this.selectAreaLetters = new JButton[numLetters]; //JButtons for the letters the user can play
      this.giveUpButton = new JButton(); //JButton for "Give Up"
   }
}
