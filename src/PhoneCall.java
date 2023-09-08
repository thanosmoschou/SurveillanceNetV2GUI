/*
 * Author: Thanos Moschou (Student)
 * Date: 12/2022
 * Description: 3rd Java Assignment. At this version i changed all the arrayList data structures to HashSets. As a result
 * the code is simplified when i have to add communications and phone numbers to the registry and the suspects
 * because i do not have to check if the value is already in the set. The check is made from the data structure.
 */


public class PhoneCall extends Communication
{
	private int duration;
	
	public PhoneCall(String aNum1, String aNum2, int aDay, int aMonth, int aYear, int aDuration)
	{
		super(aNum1, aNum2, aDay, aMonth, aYear);
		duration = aDuration;
	}
	
	
	public void printInfo()
	{
		System.out.println("This phone call has the following info\n"
				+ "Between " + this.getFirstNumber() + " --- " + this.getSecondNumber() + "\n"
				+ "on " + this.getYear() + "/" + this.getMonth() + "/" + this.getDay() + "\n"
				+ "Duration: " + duration);
	}


	
	public int getDuration() 
	{
		return duration;
	}


	/*
	public void setDuration(int duration)
	{
		this.duration = duration;
	}	
	*/
}
