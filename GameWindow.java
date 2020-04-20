/**
 * Gregory Gonzalez
 * 2/25/2017
 * CST 338 Software Design Final
 * GameWindow.java
 * Custom JFrame specific to WordSearch game
 *
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class GameWindow  extends JFrame
{
   public JPanel directions, wordLengthSelectors, giveUp, playArea, playInfo, selectArea;
   private TitledBorder [] titledBorder;
   private Border blackLine;
   
   public GameWindow(String title, int numLetters)
   {
      super(title);
      this.initPanels(numLetters);     
   }   
   public void initPanels(int numLetters) //Sets up GameWindow for WordSearch
   {
      this.directions = new JPanel();
      this.wordLengthSelectors = new JPanel();
      this.giveUp = new JPanel();
      this.playArea = new JPanel();
      this.playInfo = new JPanel();      
      this.selectArea = new JPanel();
      
      this.titledBorder = new TitledBorder[6];
      this.blackLine = BorderFactory.createLineBorder(Color.BLACK);
      this.titledBorder[0] = BorderFactory.createTitledBorder(blackLine, "Directions");
      this.titledBorder[0].setTitleJustification(TitledBorder.CENTER);
      this.titledBorder[1] = BorderFactory.createTitledBorder(blackLine, "Menu Options");
      this.titledBorder[1].setTitleJustification(TitledBorder.CENTER);
      this.titledBorder[2] = BorderFactory.createTitledBorder(blackLine);
      this.titledBorder[2].setTitleJustification(TitledBorder.CENTER);
      this.titledBorder[3] = BorderFactory.createTitledBorder(blackLine, "Play Area");
      this.titledBorder[3].setTitleJustification(TitledBorder.CENTER);     
      this.titledBorder[4] = BorderFactory.createTitledBorder(blackLine, "Selection Area");
      this.titledBorder[4].setTitleJustification(TitledBorder.CENTER);
      this.titledBorder[5] = BorderFactory.createTitledBorder(blackLine, "Time Remaining"
            + "                |                Current Score");
      this.titledBorder[5].setTitleJustification(TitledBorder.CENTER);
      
      
      
      this.setLayout(new GridLayout(6, 1));
      
      this.directions.setLayout(new GridLayout(1, 1));
      this.wordLengthSelectors.setLayout(new GridLayout(1, numLetters));
      this.giveUp.setLayout(new GridLayout(1,1));
      this.playArea.setLayout(new GridLayout(1, numLetters));
      this.playInfo.setLayout(new GridLayout(1, 2));
      this.selectArea.setLayout(new GridLayout(1, numLetters));
      
      this.directions.setBorder(titledBorder[0]);
      this.wordLengthSelectors.setBorder(titledBorder[1]);
      this.giveUp.setBorder(titledBorder[2]);
      this.playArea.setBorder(titledBorder[3]);
      this.selectArea.setBorder(titledBorder[4]);
      this.playInfo.setBorder(titledBorder[5]);
      
      this.add(this.directions);
      this.add(this.wordLengthSelectors);
      this.add(this.giveUp);
      this.add(this.playArea);
      this.add(this.playInfo);
      this.add(this.selectArea);      
      this.setSize(500,500);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }   
   public void resetGameWindow()
   {
      this.directions.removeAll();
      this.wordLengthSelectors.removeAll();
      this.giveUp.removeAll();
      this.playArea.removeAll();
      this.playInfo.removeAll();
      this.selectArea.removeAll();
      this.setEnabled(true);

   }
}
