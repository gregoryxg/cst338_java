/**
 * Gregory Gonzalez
 * 2/25/2017
 * CST 338 Software Design Final
 * GameWords.java
 * Used to read in a file of words, and covert it to a String []
 *
 */
import java.io.*;

public class GameWords 
{
   private String textFileName, textFileWords;
   private String [] wordsList;
   private int numLetters, numWords;
   
   public GameWords(int letters)
   {
      this.initGameWords(letters);            
   }
   public void initGameWords(int letters)
   {
      if (this.checkLetters(letters))
      {
         this.numLetters = letters;
      }
      else
      {
         this.numLetters = 3;
      }
      this.setFileName();
      this.setFileWords();
      this.setWordList();
      
   }
   public String getFileWords()
   {
      return this.textFileWords;
   }
   public String getFileName()
   {
      return this.textFileName;
   }
   public int getNumLetters()
   {
      return this.numLetters;
   }
   public int getNumWords()
   {
      return this.numWords;
   }   
   public String [] getWordList()
   {
      return this.wordsList;
   }
   public void setWordList()
   {
      this.wordsList = new String[this.numWords];
      String currWord = "";
      char currChar = '0';
      int lastPos = 0;
      for (int i = 0; i < this.numWords; i++)
      {
         while (currChar != ' ')
         {
            currChar = this.textFileWords.charAt(lastPos);
            if (currChar != ' ')
            {
               currWord += currChar;
            }            
            lastPos++;
         }
         this.wordsList[i] = currWord.toLowerCase();    
         currWord = "";
         currChar = '0';
      }
   }   
   public void setFileWords()
   {
      this.textFileWords = "";
      this.numWords = 0;
      try
      {
         FileReader fileReader = new FileReader(this.textFileName);
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         String line = bufferedReader.readLine();
         if (line != null)
         {
            this.textFileWords += line;
            while (line != null)
            {
               this.numWords++;
               this.textFileWords += " ";
               line = bufferedReader.readLine();
               this.textFileWords += line;               
            } 
         } 
         bufferedReader.close();
      }
      catch(FileNotFoundException e)
      {
         System.out.println("Unable to open " + this.textFileName);
      }
      catch (IOException e)
      {
         System.out.println("Error reading file " + this.textFileName);
      }
   }
   public void setFileName()
   {
      switch (this.numLetters)
      {
         case 3:
         {
            this.textFileName = "WordLists/threeLetterWords.txt";
            break;
         }
         case 4:
         {
            this.textFileName = "WordLists/fourLetterWords.txt";
            break;
         }
         case 5:
         {
            this.textFileName = "WordLists/fiveLetterWords.txt";
            break;
         }
         default:
         {
            this.textFileName = "WordLists/threeLetterWords.txt";
            break;
         }         
      }
   }
   public boolean checkLetters(int letters)
   {
      if (letters >= 3 && letters <= 5)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

}
