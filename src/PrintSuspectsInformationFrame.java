/*
 * Author: Thanos Moschou (Student)
 * Date: 12/2022
 * Description: 3rd Java Assignment. At this version i changed all the arrayList data structures to HashSets. As a result
 * the code is simplified when i have to add communications and phone numbers to the registry and the suspects
 * because i do not have to check if the value is already in the set. The check is made from the data structure.
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class PrintSuspectsInformationFrame extends JFrame implements ActionListener
{
	private Registry myRegistry = null;
	private HashSet<String> suspectPhones = null;
	
	//my panel...there i will place the other panels
	private JPanel myMainPanel = new JPanel(); 
	
	//other panels where i will place all the components and then i will add these panels to my main panel
	private JPanel suspectCredPanel = new JPanel();
	private JPanel smsInfoPanel = new JPanel();
	private JPanel partnersPanel = new JPanel();
	private JPanel suggestedPartnPanel = new JPanel();
	private JPanel suspFromSameCountryPanel = new JPanel();
	private JPanel retToSearchScreenButtonPanel = new JPanel();
	
	//all the components
	
	private JLabel partnersLabel = new JLabel("Partners");
	private JLabel suggestedPartnersLabel = new JLabel("Suggested Partners ----->");
	
	private JTextField suspectsNameField = new JTextField();
	private JTextField suspectsCodeNameField = new JTextField();
	private JTextArea suspectsPhoneField = new JTextArea();
	
	private JTextField searchSMSViaPhoneField = new JTextField();
	private JTextArea allSMSViaPhoneField = new JTextArea(10, 20);
	private JButton findSMSButton = new JButton("Find SMS");
	
	private JTextArea partnersNameField = new JTextArea(10, 20); //4 lines of 20 characters length each
	private JTextArea suggestedPartnersNameField = new JTextArea(6, 30);
	
	private JTextArea suspectsFromACountryField = new JTextArea(5, 38);
	
	private JButton returnToSearchPageButton = new JButton("Return to Search Screen");
	
	
	public PrintSuspectsInformationFrame(Suspect aSuspect, Registry aRegistry)
	{
		
		this.myRegistry = aRegistry;
		this.suspectPhones = aSuspect.getPhoneNumbers();
		
		//set the data to the components
		suspectsNameField.setText(aSuspect.getName());
		suspectsCodeNameField.setText(aSuspect.getCodeName());
		
		//take the phones from the hashset in order to put them to the text field
		String phones = "";
		for(String phone : aSuspect.getPhoneNumbers())
			phones += (phone + "\n");
		
		suspectsPhoneField.setText(phones);
		
		//---------------------------------------------
		Iterator<String> iter = aSuspect.getPhoneNumbers().iterator();
		searchSMSViaPhoneField.setText(iter.next()); //it will place a value of the set. But because it is a set i do not have indexes
		
		//take the text from the searchSMSViaPhoneField and use this as a phone number to search for sms that
		//contain certain keywords and then place those sms to the text area...if there are not 
		//any sms from this number with certain keywords then this text area will have a default message
		allSMSViaPhoneField.setText(myRegistry.getMessagesOfPhone(searchSMSViaPhoneField.getText(), this.suspectPhones));
		
		
		//take the names of the partners. Partners are in an hashset
		String suspec = "";
		for(Suspect s : aSuspect.getPartners())
			suspec += (s.getName() + "\n");
		
		partnersNameField.setText(suspec);
		
		
		//the same for the suggested partners
		HashSet<Suspect> possible = Suspect.findPossibleSuspectCollaboration(aSuspect);
		
		suspec = "";
		for(Suspect s : possible)
			suspec += (s.getName() + "\n");
		
		suggestedPartnersNameField.setText(suspec);
		
		//--------------------------------------
		suspectsFromACountryField.setText(Registry.getSuspectsFromCountry(aSuspect.getOriginCountry(), myRegistry));
		
		//add the listeners to the buttons. Note that the current window works also as a listener for the buttons
		findSMSButton.addActionListener(this);
		returnToSearchPageButton.addActionListener(this);
		
		//add the components to the appropriate panel and then add these panels to the main panel
		suspectCredPanel.add(suspectsNameField);
		suspectCredPanel.add(suspectsCodeNameField);
		suspectCredPanel.add(suspectsPhoneField);
		smsInfoPanel.add(searchSMSViaPhoneField);
		smsInfoPanel.add(allSMSViaPhoneField);
		smsInfoPanel.add(findSMSButton);
		partnersPanel.add(partnersLabel);
		partnersPanel.add(partnersNameField);
		suggestedPartnPanel.add(suggestedPartnersLabel);
		suggestedPartnPanel.add(suggestedPartnersNameField);
		suspFromSameCountryPanel.add(suspectsFromACountryField);
		retToSearchScreenButtonPanel.add(returnToSearchPageButton);
		
		//add borders to the panels
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		suspectCredPanel.setBorder(blackline);
		smsInfoPanel.setBorder(blackline);
		partnersPanel.setBorder(blackline);
		suggestedPartnPanel.setBorder(blackline);
		suspFromSameCountryPanel.setBorder(blackline);
		
		//set a bigger size to the panel that contains the return to search screen button because i 
		//want to have it down under the suspects from same country panel
		//returnToSearchPageButton.setSize(400, 200);
		
		myMainPanel.add(suspectCredPanel);
		myMainPanel.add(smsInfoPanel);
		myMainPanel.add(partnersPanel);
		myMainPanel.add(suggestedPartnPanel);
		myMainPanel.add(suspFromSameCountryPanel);
		myMainPanel.add(retToSearchScreenButtonPanel);
		
		
		//add the panel to the window
		this.setContentPane(myMainPanel);
		
		this.setVisible(true);
		this.setSize(600, 800);
		this.setTitle("Suspect Page");
		
		//When pressing Closing button the whole program terminates. If you want to return to search page use the 
		//"Return to Search Screen" button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("Find SMS"))
		{
			//take the text from the searchSMSViaPhoneField and use this as a phone number to search for sms that
			//contain certain keywords and then place those sms to the text area...if there are not 
			//any sms from this number with certain keywords then this text area will have a default message
			allSMSViaPhoneField.setText(myRegistry.getMessagesOfPhone(searchSMSViaPhoneField.getText(), this.suspectPhones));
		}
		else
			this.dispose();
			
	}
}
