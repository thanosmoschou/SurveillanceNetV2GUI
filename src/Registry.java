/*
 * Author: Thanos Moschou (Student)
 * Date: 12/2022
 * Description: 3rd Java Assignment. At this version i changed all the arrayList data structures to HashSets. As a result
 * the code is simplified when i have to add communications and phone numbers to the registry and the suspects
 * because i do not have to check if the value is already in the set. The check is made from the data structure.
 */

import java.util.HashSet;

public class Registry
{
	private HashSet<Communication> communications = new HashSet<Communication>();
	private HashSet<Suspect> listOfSuspects = new HashSet<Suspect>();
	
	
	public void addSuspect(Suspect aSuspect)
	{
		listOfSuspects.add(aSuspect);
	}
	
	
	public void addCommunication(Communication aCommunication)
	{
		boolean stop1 = false, stop2 = false;
		communications.add(aCommunication);
		Suspect tempSus1 = null, tempSus2 = null;
		
		/* We take the list of the suspects. For each suspect we take the list of his phone numbers.
		 * Then we search if the first number of this communication matches one of his phones. If yes
		 * i save the value of sus(assume it is the first partner)in order to update the partner list of 
		 * the other suspect(assume it is the second partner).
		 * After the first check i check if the second communication number matches one of his phones.
		 * If yes then i store the sus value again(assume it is the second partner)
		 * in order to update the partners list of the other object(assume it is the first partner).
		 * Every time we try to find if a number from the communication matches a number of every suspect's 
		 * phone list. If yes then we find the one of two partners.
		 */
		
		for(Suspect sus : listOfSuspects)
		{
			for(String phone : sus.getPhoneNumbers())
			{
				if(aCommunication.getFirstNumber().equalsIgnoreCase(phone))
				{
					tempSus1 = sus; //save the value in order to update the list of the second partner
					stop1 = true; //found the first partner
					break; //break because it is impossible for a number to be both the first and second number of this communication
				}
				
				if(aCommunication.getSecondNumber().equalsIgnoreCase(phone))
				{
					tempSus2 = sus; //save the value in order to update the list of the first partner
					stop2 = true; //found the second partner
					break; //break because it is impossible for a number to be both the first and second number of this communication
				}		
			}
			
			if(stop1 && stop2) //we found the two partners so it is pointless to keep searching for partners
				break;
		}
		
		//update the partner's list of the two suspects
		if(tempSus1 != null && tempSus2 != null)
		{
			tempSus1.addPartner(tempSus2);
			tempSus2.addPartner(tempSus1);	
		}
	}
	
	
	public Suspect getSuspectWithMostPartners()
	{
		int max = 0, currentNumberOfPartners;
		Suspect maxSus = null;
		
		for(Suspect s : listOfSuspects)
		{
			currentNumberOfPartners = s.getNumberOfPartners();
			if(currentNumberOfPartners >= max)
			{
				max = currentNumberOfPartners;
				maxSus = s;
			}
		}
		
		return maxSus;
	}
	
	
	public PhoneCall getLongestPhoneCallBetween(String number1, String number2)
	{
		HashSet<PhoneCall> tempCommun = new HashSet<PhoneCall>();
		PhoneCall maxCom = null;
		int maxDuration = 0;
		
		/*Search the arraylist of communications until number1 matches the 1st number of a communication
		 * and simultaneously the numeber2 matches the 2nd number of a communication and the object is for sure a PhoneCall and not an sms. If i find them
		 * i add the communication to an arraylist in order to check the duration and return the appropriate object
		 * Maybe there are more than one communications between these two numbers
		 */
		
		for(Communication com : communications)
		{
			if(com.getFirstNumber().equalsIgnoreCase(number1) && com.getSecondNumber().equalsIgnoreCase(number2) && com instanceof PhoneCall)
				tempCommun.add((PhoneCall) com); //cast for the compiler...i know it is a phonecall but i cannot add a hyper class object to an arraylist of a sub class
		}
		
		
		//in the communication arraylist i have phonecall objects
		
		int tempDur;
		for(PhoneCall com : tempCommun)
		{
			tempDur = com.getDuration();
			if(tempDur > maxDuration)
			{
				maxDuration = tempDur;
				maxCom = com;
			}
		}
		
		return maxCom; 
	}
	
	
	
	public String getMessagesOfPhone(String number1, HashSet<String> suspPhones)
	{
		boolean found = false; //this is to know if there are sms with certain keywords. If there are not i will return a message
		HashSet<SMS> tempCommun = new HashSet<SMS>(); //all the sms communications
		String returnCommun = ""; //the sms that contain certain keywords

		String[] keywords = {"Bomb", "Attack", "Explosives", "Gun"};
		
		/* Scan the Hashset of communications for communications that are sms and numbers match one of the 
		 * following conditions:
		 * 1) The first number of sms communication equals the number1 and the 2nd number of communication matches a number of the
		 * numbers in hashset that contains all the suspect's phone numbers.
		 * 2) The second number of the sms communication matches the number1 and the first number matches
		 * one of the numbers in my suspect's hashset. 
		 * If these conditions are ok add this object to a list. From 
		 * there scan the new arrayList to find text that contains certain keywords. If a message contains at least
		 * one keyword, add it to the return list
		 */
		
		
		for(String num : suspPhones)
		{
			for(Communication com : communications)
			{
				if(((com.getFirstNumber().equalsIgnoreCase(number1) && com.getSecondNumber().equalsIgnoreCase(num)) 
					|| (com.getFirstNumber().equalsIgnoreCase(num) && com.getSecondNumber().equalsIgnoreCase(number1)))
					&& com instanceof SMS)
				{
					tempCommun.add((SMS) com); //cast for the compiler...i know it is a sms but i cannot add a hyper class object to an arraylist of a sub class
				}
			}
		}
		
		
		//in the communication arraylist i have sms objects. Scan the communications for wanted keywords
		
		String tempText;
		
		for(SMS com : tempCommun)
		{
			tempText = com.getText();
			for(String key : keywords)
			{
				if(tempText.contains(key))
				{
					returnCommun += (com.getText() + "\n");
					found = true;
					break;
				}
			}
		}
		
		if(!found)
			return "There are not any SMS with the specified keywords.\n";
		
		return returnCommun; 
	}
	
	
	public static String getSuspectsFromCountry(String aCountry, Registry aReg)
	{
		String message = "";
		
		message += ("Suspects coming from " + aCountry + "\n");
		for(Suspect sus : aReg.listOfSuspects)
		{
			if(sus.getOriginCountry().equalsIgnoreCase(aCountry))
				message += (sus.getName() + "\n");
		}
		
		return message;
	}


	//maybe destroy the privacy attribute but i know where i call it...i am trying not to change the instances state
	public HashSet<Suspect> getListOfSuspects() 
	{
		return listOfSuspects;
	}	
	
}
