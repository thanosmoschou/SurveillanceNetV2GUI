/*
 * Author: Thanos Moschou (Student)
 * Date: 12/2022
 * Description: 3rd Java Assignment. At this version i changed all the arrayList data structures to HashSets. As a result
 * the code is simplified when i have to add communications and phone numbers to the registry and the suspects
 * because i do not have to check if the value is already in the set. The check is made from the data structure.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindSuspectFrame extends JFrame implements ActionListener
{
	
	//every window has it's own list of suspects and a registry object
	private HashSet<Suspect> suspects = null;
	private Registry myRegistry = null;
	
	//make the panel for finding a suspect
	private JPanel myPanel = new JPanel();
	
	//make the textfield and the button for the panel
	private JTextField searchField = new JTextField("Please enter suspect\'s name", 18);
	private JButton searchButton = new JButton("Find");
	
	
	public FindSuspectFrame(HashSet<Suspect> someSuspects, Registry aRegistry)
	{
		this.suspects = someSuspects;
		this.myRegistry = aRegistry;
		
		//add the components to the panel
		myPanel.add(searchField);
		myPanel.add(searchButton);
		
		
		//add the panel to the window
		this.setContentPane(myPanel);
		
		
		//the listener for this button is the current window
		searchButton.addActionListener(this);
		
		this.setVisible(true);
		this.setTitle("Find Suspect");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		boolean found = false;
		Suspect foundSuspect = null;
		String suspectsFindName = searchField.getText(); //take the name to search for this suspect
		
		//scan the suspects list
		for(Suspect aSusp : suspects)
		{
			if(aSusp.getName().equalsIgnoreCase(suspectsFindName))
			{
				found = true;
				foundSuspect = aSusp;
				break;
			}
		}
		
			
		if(found)
		{
			//print all the details of this suspect
			new PrintSuspectsInformationFrame(foundSuspect, myRegistry);
		}
		else
		{
			//make the new not found window in case that suspect is not contained in the list
			//i do not create a FindSuspectsFrame object because everytime this will create a new search window
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Suspect " + suspectsFindName + " Not Found");
		}	
	}		
}
