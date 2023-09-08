/*
 * Author: Thanos Moschou (Student)
 * Date: 12/2022
 * Description: 3rd Java Assignment. At this version i changed all the arrayList data structures to HashSets. As a result
 * the code is simplified when i have to add communications and phone numbers to the registry and the suspects
 * because i do not have to check if the value is already in the set. The check is made from the data structure.
 */

public abstract class Communication 
{
	private String firstNumber;
	private String secondNumber;
	private int day;
	private int month;
	private int year;
	
	public Communication(String aNum1, String aNum2, int aDay, int aMonth, int aYear)
	{
		this.firstNumber = aNum1;
		this.secondNumber = aNum2;
		this.day = aDay;
		this.month = aMonth;
		this.year = aYear;
	}
	
	
	public abstract void printInfo(); //write the body of this method inside every class that inherits the hyper class


	public String getFirstNumber() 
	{
		return firstNumber;
	}


	public void setFirstNumber(String firstNumber) 
	{
		this.firstNumber = firstNumber;
	}


	public String getSecondNumber() 
	{
		return secondNumber;
	}


	public void setSecondNumber(String secondNumber) 
	{
		this.secondNumber = secondNumber;
	}


	public int getDay() 
	{
		return day;
	}


	public void setDay(int day)
	{
		this.day = day;
	}


	public int getMonth() 
	{
		return month;
	}


	public void setMonth(int month) 
	{
		this.month = month;
	}


	public int getYear() 
	{
		return year;
	}


	public void setYear(int year) 
	{
		this.year = year;
	}
}
