package thai.bcnf_decomposition;

import java.io.*;

public class Test {
	public String BCNFDecomp (Relation r, FdList fds) {
		String s = "";
		
		
		
		return s;
	}
	
	
	public static void main (String [] args) {
		String fileName = "input.txt";
		String line = null;
		try { // Read from the file.
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

	        bufferedReader.close();    
			
	        // Initialize two objects.
			Relation test1 = new Relation(relation);
			FdList test2 = new FdList(fds);
			
			// Print out the two objects created above.
			System.out.println("Relation and Functional Dependencies:");
			System.out.println(test1.toString());
			System.out.println(test2.toString());
			System.out.println();
			
			// Print out all BCNF violations.
			System.out.println("BCNF violations:");
			System.out.println(test2.BCNFViolations(test1));
			System.out.println();
			
			// Initialize two auxiliary objects.
			RF_pair test3 = new RF_pair(test1,test2);
			RF_pair_decompTree test4 = test3.BCNF_decomp();
			
			// Print out BCNF decomposition.
			System.out.println("BCNF decomposition: (Relation: Functional Dependencies)");
			test4.printLeaves();
			
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println(
	            "Unable to open file '" + 
	            fileName + "'");                
	    }
	    catch(IOException ex) {
	        System.out.println("Error reading file '" + fileName + "'");
	    }
		
	
		
		
		//Relation test1 = new Relation("A B C D E");
		//Relation test2 = new Relation("A B C D");
		//System.out.println(test1.equals(test2));
	}
}
