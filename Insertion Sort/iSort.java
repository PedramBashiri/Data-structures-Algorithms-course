//Author: Pedram Bashiri



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class iSort {

	// Data structure to store the sequence of numbers
	static ArrayList<Integer> numbers = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileAddress = "";
		for (String s: args) {
			fileAddress+=s;
        }
		
		readFileAtPath(fileAddress);
		Sort();

	}
	
	public static void readFileAtPath(String filename) {
		
		
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
			return;
		}
		String filePath = System.getProperty("user.dir") + "/" + filename;
		BufferedReader inputStream = null;
		
		// To handle any potential IO errors
		try {
			try {
				inputStream = new BufferedReader(new FileReader(filePath));
				String lineContent = null;
				
				// Loop will iterate over each line within the file.
				// It will stop when no new lines are found.
				while ((lineContent = inputStream.readLine()) != null) {
					
					// Let's remove the ";" at the end of text
					if(lineContent.endsWith(";"))
						lineContent = lineContent.substring(0, lineContent.length()-1);
					
					String[] elements = lineContent.split(";");
					for(int i = 0; i<elements.length;i++){
						numbers.add(Integer.parseInt(elements[i]));
					}
					
				}
								
			}
			
			// Make sure we close the buffered reader.
			finally {
				if (inputStream != null)
					inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Sort(){
		
		int i,j,temp;
		String output = "";
		
		Integer[] arrayedNumbers = new Integer[numbers.size()];
		arrayedNumbers = numbers.toArray(arrayedNumbers);
		
		for (j = 1; j < arrayedNumbers.length; j++)    
	    {
	           temp = arrayedNumbers[ j ];
	           for(i = j - 1; (i >= 0) && (arrayedNumbers[ i ] > temp); i--)   // Greater values are moving up
	          {
	        	   arrayedNumbers[ i+1 ] = arrayedNumbers[ i ];
	          }
	           arrayedNumbers[ i+1 ] = temp;    
	     }
		
		for(i=0;i<arrayedNumbers.length;i++){
			output+=arrayedNumbers[i] + ";";
		}
		
		output = output.substring(0, output.length()-1);
		
		try {
			 

			File file = new File("answers.txt");
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(output);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
