package thai.bcnf_decomposition;

import java.io.*;

public class Test {
	public static void main (String [] args) {
		String fileName = "input.txt";
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String relation = "";
			String fds = "";
			
			if ((line = bufferedReader.readLine()) != null) {
				relation = line;
			}
	
			while((line = bufferedReader.readLine()) != null) {
	            fds += line + ", ";
	        }

	        // Always close files.
	        bufferedReader.close();    
			
			Relation test1 = new Relation(relation);
			FdList test2 = new FdList(fds);
			
			System.out.println(test1.toString());
			System.out.println(test2.toString());
			
			
			
			
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println(
	            "Unable to open file '" + 
	            fileName + "'");                
	    }
	    catch(IOException ex) {
	        System.out.println("Error reading file '" + fileName + "'");                  
	        // Or we could just do this: 
	        // ex.printStackTrace();
	    }
		
		
	}
}
