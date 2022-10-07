package com.hossain.etc;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;//used in compareTo method


public class Date212 {
    private String fullDate; //string stored in fullDate;
    public static int count = 0; //To keep track of how many Dates are made.
    private static Date212 tempDates[] = new Date212[50]; //Used to store dates
    public static int add = 0; //Used for AddArray method.

    
//Constructor   
public Date212 (String string){
        fullDate = string;
       
        if (!validDate(string)) throw new IllegalArgumentException("Invalid Date Format!"); 

        //The text file will have to be writen as "yyddmm" so for example "20170201" having the length of 8 of the ayau
        int year = Integer.parseInt(string.substring(0,3)); //pulls out from index 0 to 3
        int month = Integer.parseInt(string.substring(4,5)); //pulls out from index 4 to 5
        int day = Integer.parseInt(string.substring(6,7)); // pulls out from index 6-7
         
        addArray(this); //addArray function is called with 'this instance of date212'
     
        count++;
    }

  //addArray method takes the Date212 object from the constructor
    public void addArray(Date212 object){
    	
        tempDates[add++] = object; //Add the object passed in to the array using the 'i'.
    }

  
    public Date212[] getNumbDates(int a){ //a is the number that signfies the dates passed in.
  
        Date212 myDates[] = new Date212[a]; //initalize it from the a.

        for(int d = 0; d< tempDates.length; d++){ //that "myTempDates" array which holds the dates is used to copy it into the specified array.
            if(tempDates[d] != null){ //if next index is not null then copy.
                myDates[d] = tempDates[d];
            }
        }
        return myDates; //return the array to method calling it!
    }

    /**
     * Returns the array and requires the numberofDates Param; DateGUI calls it.
     */
   

//getter method for date
public String getDate() { 
	if(fullDate==null) {
     return null;
	}
	return fullDate;
    }

//setters
public void setString (String s) { 
    fullDate = s;
    if(!validDate(fullDate)) throw new IllegalArgumentException("Invalid Date Format!");

        int year = Integer.parseInt(fullDate.substring(0,3));
        int month = Integer.parseInt(fullDate.substring(4,5));
        int day = Integer.parseInt(fullDate.substring(6,7));

    
}

/*
 * string passed in to check for whitespaces. Its then removed then length checked.
 */

public boolean validDate(String val) {
        String valDateWhiteSpace = val.replaceAll("\\s", "");
        //Checks if date string is good yyyymmdd is length of 8
        if(valDateWhiteSpace.length()==8) {
    return valDateWhiteSpace.length() == 8;
    }
        else return false;
}


 //returns date to string format
public String toString() { //toString method
   
	String startDateString2= "";
	
    try{
    	
        Date date1 = new SimpleDateFormat("yyyyMMdd").parse(fullDate);
            
        DateFormat df2 = new SimpleDateFormat("MMM dd, yyyy");
            
        startDateString2 = df2.format(date1);
           
        return startDateString2;
    }
    catch(ParseException e){
        e.printStackTrace();
    }
            return startDateString2;
        
}


//compareTo method will compare the dates two at a time and see which one comes earlier
//it will return a value depending on which one comes first (1 means date 1 is after date 2, -1 means date1 is before date2, 0 means both are the same)
public int compareTo(Date212 other) {
    int result = 0; //intialize int return value.
    Date date1 = new Date(), date2 = new Date();

SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//will call the object being passed into the class
        try {
            date1 = sdf.parse(fullDate);  //The object calling this method.
            date2 = sdf.parse(other.fullDate); //Other date212 object being passed in.

        } 
        //Try-catch block for parsing & formatting dates.
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        
        /*
        	 * Calendar library is used here
        	 */

        Calendar cal1 = Calendar.getInstance(); 
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        	
        //date 1 is after date 2
        if (cal1.after(cal2)) { 
           
            result = 1;
        }
        //date 1 is before date 2
        if (cal1.before(cal2)) {
            // System.out.println("Date1 is before Date2");
            result = -1;
        }
        //both dates are the same
        if (cal1.equals(cal2)) {
        
            result = 0;
        }
        return result; 
    }


}

