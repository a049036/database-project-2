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

	        bufferedReader.close();    
			
			Relation test1 = new Relation(relation);
			FdList test2 = new FdList(fds);
			
			System.out.println("Relation and Functional Dependencies:");
			System.out.println(test1.toString());
			System.out.println(test2.toString());
			System.out.println();
			
			System.out.println("BCNF Violations:");
			System.out.println(test2.BCNFViolations(test1));
			System.out.println();
			
			RF_pair test3 = new RF_pair(test1,test2);
			RF_pair_decompTree test4 = test3.BCNF_decomp();
			
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
		
		
	}
}
