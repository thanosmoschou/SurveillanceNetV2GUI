/*
 * Author: Thanos Moschou (Student)
 * Date: 12/2022
 * Description: 3rd Java Assignment. At this version i changed all the arrayList data structures to HashSets. As a result
 * the code is simplified when i have to add communications and phone numbers to the registry and the suspects
 * because i do not have to check if the value is already in the set. The check is made from the data structure.
 */


public class SMS extends Communication
{
	private String text;
	
	public SMS(String aNum1, String aNum2, int aDay, int aMonth, int aYear, String aText)
	{
		super(aNum1, aNum2, aDay, aMonth, aYear);
		text = aText;
	}
	
	
	public void printInfo()
	{
		System.out.println("This SMS has the following info\n"
				+ "Between " + this.getFirstNumber() + " --- " + this.getSecondNumber() + "\n"
				+ "on " + this.getYear() + "/" + this.getMonth() + "/" + this.getDay() + "\n"
				+ "Text: " + text);
	}


	public String getText() 
	{
		if(text.length() > 0)
			return text;
		return "";
	}


	/*
	public void setText(String context)
	{
		this.text = context;
	}
	*/
}
