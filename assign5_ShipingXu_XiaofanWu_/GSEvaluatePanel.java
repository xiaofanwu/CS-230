/* GSEvaluatePanel.java
 * CS230 Assignment5 Task3
 * Written by: Shiping Xu & Xiaofan Wu
 * 10/25/2015
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class GSEvaluatePanel extends JPanel {
  // instance variables
  private GradSchools schoolList;
  private JPanel controls, infoPanel;
  private JSlider academicsSlider, researchSlider, pubsSlider;
  private JLabel academicsLabel, researchLabel, pubsLabel, infoLabel;

  public GSEvaluatePanel(GradSchools gs) {
    // set the lay out to be a box Layout, vertically from top to bottom
    setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
    
    this.schoolList = gs; 
    
    // create a academics slider 
    academicsSlider = new JSlider (JSlider.HORIZONTAL, 0, 5, 0);
    academicsSlider.setMajorTickSpacing (5);
    academicsSlider.setMinorTickSpacing (1);
    academicsSlider.setPaintTicks (true);      
    academicsSlider.setPaintLabels (true);

    // create a research slider 
    researchSlider = new JSlider (JSlider.HORIZONTAL, 0, 5, 0);
    researchSlider.setMajorTickSpacing (5);
    researchSlider.setMinorTickSpacing (1);    
    researchSlider.setPaintTicks (true);
    researchSlider.setPaintLabels (true);

    // create a publications slider
    pubsSlider = new JSlider (JSlider.HORIZONTAL, 0, 5, 0);
    pubsSlider.setMajorTickSpacing (5);
    pubsSlider.setMinorTickSpacing (1);
    pubsSlider.setPaintTicks (true);
    pubsSlider.setPaintLabels (true);

    // 3 evaluation labels, with the corresponding weights next to them
    academicsLabel = new JLabel ("Academics: 0");
    researchLabel = new JLabel ("Research: 0");
    pubsLabel = new JLabel ("Publications: 0");
    
    // creates a control JPanel, set the layout to be box layout, add everything on to the control panel
    controls = new JPanel();
    BoxLayout layout = new BoxLayout (controls, BoxLayout.X_AXIS);
    controls.setLayout (layout);
    controls.add (academicsLabel);
    controls.add (academicsSlider);
    controls.add (Box.createRigidArea (new Dimension (0, 20))); // creates a space between sliders
    controls.add (researchLabel);
    controls.add (researchSlider);
    controls.add (Box.createRigidArea (new Dimension (0, 20)));
    controls.add (pubsLabel);     
    controls.add (pubsSlider);
    
    // creates an info panel
    infoPanel = new JPanel();
    // an info label that prompts the user to use sliders to set weights
    infoLabel = new JLabel("Please use the sliders to assign weights in each category.");
    infoPanel.add(infoLabel); // add label to the panel
    
    // add 2 panels to the GSEvaluate panel
    add(controls); 
    add(infoPanel);   
    
    // creates a new Sliderlistener for the sliders
    SliderListener listener = new SliderListener();
    academicsSlider.addChangeListener(listener); 
    researchSlider.addChangeListener(listener);
    pubsSlider.addChangeListener(listener);
  }
  
  // SliderListener is a private class for responding to sliding events
  private class SliderListener implements ChangeListener {
    // instance variables, three weights
    private int weightA, weightR, weightP;    
    public void stateChanged (ChangeEvent event) {
      // get the value from the sliders to set weights
      weightA = academicsSlider.getValue();
      weightR = researchSlider.getValue();
      weightP = pubsSlider.getValue();
      
      // change the numbers on the labels
      academicsLabel.setText("Academics: " + weightA);
      researchLabel.setText("Research: " + weightR);
      pubsLabel.setText("Publications: " + weightP);
      
      // compute each school's overall score and rank the school in the overall category
      schoolList.computeRatings(weightA, weightR, weightP);
      schoolList.rankSchool("Overall");
      
      // sets the text in the infolabel
      infoLabel.setText(schoolList.toString());
      infoLabel.setFont(new Font("Arial", Font.ITALIC, 16));
      infoLabel.setForeground(Color.magenta);
    }
  }
  
    
}