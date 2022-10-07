package com.hossain.etc;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import java.awt.TextArea;
import java.util.StringTokenizer;

public class DateGUI{
    public static StringTokenizer myTokens;
    public static StringTokenizer numbDates;
    public static String line;
    public static TextInputFile inFile;
    public static TextInputFile preProgram;
    public static TextArea original = new TextArea();
    public static TextArea Dates = new TextArea();
    public String fileName = "dates.txt";
    public static JFrame myGui;
    public static Container cPane;
    private static Date212 date;
    private Date212[] array;
    private Date212 temp;
    private int count;

//launches the gui with setted settings
public void run() {

    myGui= new JFrame();
    myGui.setLocationRelativeTo(null);
    myGui.setSize(540,410);
    myGui.setTitle("Dates List"); 
    myGui.setBackground(new Color(220,200,200));
    myGui.setLayout(new GridLayout(1, 2));
    
    myGui.setVisible(true);
        cPane = myGui.getContentPane();
        cPane.add(original);
        cPane.add(Dates);
        readNumbersFromFile(fileName);
        selectionSort();
    myGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}//intialize

/**
 * When Project2 kicks off "initilaize, this is what is run, initialize!"
 */

    private void selectionSort() {
    
      for ( int i = 0; i < array.length - 1; i++ ) { 
         int index = i;  //Holds the current i value.
         
         //compareTo is used here were the if-statement rules said in Date212 is used
         for ( int j = i + 1; j < array.length; j++ ) 
            if ( array[j].compareTo(array[index]) == -1) 
               index = j;
            if(!(array[index]).equals(array[i])){ //if the index that holds smallest is not equal to the current value of i then... USING '.equals' method on DATE212
	            temp = array[index]; //Date212 temp holds the smallest value.
	            array[index] = array[i];  //values shifted.
	            array[i] = temp; //current i holds lowest value. 
         }  
      } 
      //For loop to append or apply it to the GUI.
        for (int k = 0; k < array.length ; k++) {
            Dates.append(String.valueOf(array[k])); //dates are appended
            Dates.append("\n"); //new line after each index.
        }   
    }
    public void readNumbersFromFile(String fileName) {

        
        inFile = new TextInputFile(fileName);
        line = inFile.readLine(); //Starts the process of reading the file.

        //While a line exist which does not equal to null.
        while (line != null) {
           

            myTokens = new StringTokenizer(line, ",");
            // System.out.println("There are " + myTokens.countTokens() + " tokens in the line.");
           
            
            while (myTokens.hasMoreTokens()) { //While there is more tokens on the line.
                
                String[] tokensArray = new String [myTokens.countTokens()];
                
                for(int i =0; i< tokensArray.length; i++){
                    count++; //Everytime the index increases this keeps count!
                   
                    tokensArray[i]= myTokens.nextToken(); //hi array stores the value.
                    date= new Date212(tokensArray[i]); //Calls the date method on the current array index.
                
                    if(i>0){ //If there is more than one token, then read and append new line.
                        original.append("\n"); //Append new line
                        original.append(tokensArray[i]); //Append the current index.
                       
                    }
                    else if(i == 0){ //If there is only one token.
                    original.append(tokensArray[i]);
                    }
                }

            }
            original.append("\n"); //New line after every array index finishes.

            line = inFile.readLine(); //Read next line.
           
        }
           
      
             System.out.println(count+ " Amount of dates were passed"); //Shows how many dates were passed in in console
            array = date.getNumbDates(count); // "array" points to the array provided by the method getNumbDates with a parameter of countDates.
            //countDates holds the number of dates passed in.
        

        myGui.setVisible(true); 
    }


}
