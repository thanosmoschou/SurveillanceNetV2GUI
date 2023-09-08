/*
 * Author: Thanos Moschou (Student)
 * Date: 12/2022
 * Description: 3rd Java Assignment. At this version i changed all the arrayList data structures to HashSets. As a result
 * the code is simplified when i have to add communications and phone numbers to the registry and the suspects
 * because i do not have to check if the value is already in the set. The check is made from the data structure.
 */

import java.util.HashSet;

public class Suspect
{
	private String name;
	private String codeName;
	private String originCountry;
	private String city;
	private HashSet<Suspect> partners = new HashSet<Suspect>();
	private HashSet<String> phoneNumbers = new HashSet<String>();
	
	
	public Suspect(String aName, String aCodeName, String anOriginCountry, String aCity)
	{
		this.name = aName;
		this.codeName = aCodeName;
		this.originCountry = anOriginCountry;
		this.city = aCity;
	}
	
	
	public void addNumber(String aNumber)
	{
		phoneNumbers.add(aNumber);		
	}
	
	
	public void addPartner(Suspect aSuspect)
	{
		partners.add(aSuspect);	
	}
	
	
	public boolean isConnectedTo(Suspect aSuspect)
	{
		for(Suspect aPartner : partners)
		{
			if(aPartner == aSuspect)
				return true;
		}
		
		return false; //if we go to this point the for each loop ended without getting to the if part
	}
	
	
	public HashSet<Suspect> getCommonPartners(Suspect aSuspect)
	{
		HashSet<Suspect> myCommonList = new HashSet<Suspect>();
		HashSet<Suspect> aSuspectList = aSuspect.getPartners(); //get the partners of aSuspect
		
		//we take every value of this.partners and we search to the aSuspectList if this value exists. If we find it
		//then we break the nested for and we are going to search if the next value of this.partners exists if the
		//aSuspect list and so on...this implementation is similar having 2 tables with one dimension each and 
		//want to compare their values. Because of the 2 tables we would have a nested for.
		for(Suspect partner1 : this.partners)
		{
			for(Suspect partner2 : aSuspectList)
			{
				if(partner1 == partner2) //compare address values because Suspect is an object so we compare memory addresses. If partner1 and partner2 are the same memory address value then they are the same object
				{
					myCommonList.add(partner1);
					break; //Let's go to search the next partner of this.partners
				}
			}
		}
		
		return myCommonList;
	}
	
	
	public void printPossiblePartners()
	{
		for(Suspect sus : partners)
		{
			//print the name and the code name of each partner. If the 2 partners are from the same
			//country then after the name of the partner is printed an asterisk...otherwise nothing is printed
			System.out.println("Name: " + sus.getName() + "Code Name: " + sus.getCodeName() +
					((this.getOriginCountry().equalsIgnoreCase(sus.getOriginCountry())) ? "*" : ""));
		}
	}
	
	
	public static HashSet<Suspect> findPossibleSuspectCollaboration(Suspect aSuspect)
	{
		HashSet<Suspect> partnersOfASuspect = aSuspect.getPartners(); //partners of aSuspect...take every partner included in this list and find his partners
		HashSet<Suspect> returnSuspect = new HashSet<>();
		
		/* The methodology is the following. If we call the method for A and if A has partners B and D and 
		 * B has partners E the possible collaboration for A is with E. If we call the method for E
		 * then the possible collaboration is with A and D.
		 * 
		 * So when i call the method for A: Take the set of partners for A(let's call it 1st set) and from there
		 * take for every element its partners(let's call it 2nd set). If an element of the 2nd set is not
		 * in the 1st, i will add it to another set(call it final set) and when the loops are over i will return this set.
		 * NOTE: When i call the method for A i note that partners are B and D. When i take the B and D partners
		 * set i notice that they have A as a partner. From the methodology above due to the fact that
		 * A is not in the partner's list of A i have to put it to the returned set. But it does not make any sense A to be partner of A. As a result
		 * A is not included in the partners list of A. So i have to make sure not to insert A to the return set
		 */
		
		for(Suspect sus : partnersOfASuspect)
		{
			for(Suspect part : sus.getPartners())
			{
				if(!partnersOfASuspect.contains(part) && part != aSuspect)
					returnSuspect.add(part);
			}
		}
		
		return returnSuspect;
		
	}
	
	
	public String getName() 
	{
		return name;
	}


	public void setName(String name) 
	{
		this.name = name;
	}


	public String getCodeName()
	{
		return codeName;
	}


	public void setCodeName(String codeName)
	{
		this.codeName = codeName;
	}


	public String getOriginCountry() 
	{
		return originCountry;
	}


	public void setOriginCountry(String originCountry) 
	{
		this.originCountry = originCountry;
	}


	public String getCity()
	{
		return city;
	}


	public void setCity(String city) 
	{
		this.city = city;
	}


	public HashSet<Suspect> getPartners() 
	{
		return partners;
	}


	/*
	public void setPartners(ArrayList<Suspect> partners)
	{
		this.partners = partners;
	}
	*/


	public HashSet<String> getPhoneNumbers()
	{
		return phoneNumbers;
	}


	/*
	public void setPhoneNumbers(ArrayList<String> phoneNumbers) 
	{
		this.phoneNumbers = phoneNumbers;
	}
	*/
	
	
	public int getNumberOfPartners()
	{
		return partners.size();
	}
}
