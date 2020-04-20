/**
 * Gregory Gonzalez
 * 2/25/2017
 * CST 338 Software Design Final
 * Timer.java
 * Used to create and modify a timer for the user to guess the word within
 *
 */
import javax.swing.*;
import java.text.*;

public class Timer extends Thread
{
   JLabel timer;
   DecimalFormat secondsPattern;   
   int minutes, seconds;
   boolean running, currState;
   String currTime;
   GameController gameController;
   
   public Timer(GameController gameController)
   {
      this.timer = new JLabel();     
      this.secondsPattern = new DecimalFormat("00");     
      this.seconds = 30;
      this.running = true;
      this.currState = false;
      this.currTime = "";
      this.gameController = gameController;
      setTime();       
   }
   
   void setTime() //Used to update current time
   {
      this.currTime = secondsPattern.format(seconds);
      this.timer = new JLabel(currTime, JLabel.CENTER);      
   }
   
   public void resetClock(int seconds) //Used to reset clock to 0:00
   {
      this.seconds = seconds;
   }
   
   public JLabel getTime() //Used to reset time JLabel
   {
      return this.timer;
   }  
   
   void decrementTime() //Used to increment seconds and minutes
   {      
      if (this.seconds > 0)
      {         
         this.seconds--;
      }
   }

   public void run() //Default run action
   {
      while (running)
      {         
         if (currState) //Calls appropriate methods to decrement time with a 1 second delay between calls
         {            
            decrementTime();           
            setTime();
            this.gameController.updateTime();
            timeBetweenSeconds();
            if (this.seconds == 0) //When the timer reaches 0, gameLose() in gameController is called
            {            
               this.gameController.gameLose();
            }
         }                        
      }      
   }
   
   public void timeBetweenSeconds() //Sets 1 second intervals for run()
   {
      try
      {
         Thread.sleep(1000); //1000 ms in 1 second
      }
      catch (InterruptedException e)
      {
         System.out.println("Unexpected Interrupt");
         System.exit(0);
      }
   }
   
   public boolean getCurrState() //Returns currState
   {
      return this.currState;
   }
   
   public void setCurrState(boolean val) //Sets the currState to true or false
   {      
      this.currState = val;     
   }
}


