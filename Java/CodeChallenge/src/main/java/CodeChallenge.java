import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;

public class CodeChallenge {

	public static void main(String[] args) {
		
		FileInputStream fis; 
		BufferedReader br = null;
		try {
			
			//reading input ccds1.csv
			fis = new FileInputStream("src/main/resources/ccds1.csv");
			br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			
			//creating a hashmap to store the ccds1.csv file
			Map<Integer,String> dataset1=new HashMap<Integer,String>();  
			while((line= br.readLine()) != null) {
				
				Integer id= new Integer(line.split(",")[0].replaceAll("^\"|\"$", ""));
				String UA= new String(line.split(",",2)[1]).replaceAll("^\"|\"$", "");
				dataset1.put(id, UA);
							
			 }
			
			//reading input ccds2.tsv
			fis = new FileInputStream("src/main/resources/ccds2.tsv");
			br = new BufferedReader(new InputStreamReader(fis));
			line = null;
			
			//creating a hashmap to store the ccds2.tsv
			Map<Integer,String> dataset2=new HashMap<Integer,String>();  
			while((line= br.readLine()) != null) {
												
				Integer id= new Integer(line.split("\t")[0]);
				String d2v= new String(line.split("\t",2)[1]);
				dataset2.put(id,d2v);			
				
			 }
			
			//creating a set to store the key which are common in both data sets
			Set<Integer> commonset = new HashSet<Integer>(dataset1.keySet());
			
			//creating a set to store the key of non EU countries
			Set<Integer> filteredset = new HashSet<Integer>();
			
			//filtering EU countries
			commonset.retainAll(dataset2.keySet());
			for(Integer i: commonset) {
				if( dataset2.get(i).contains("AT") || dataset2.get(i).contains("BE") || dataset2.get(i).contains("BG") 
						|| dataset2.get(i).contains("HR") || dataset2.get(i).contains("CY") || dataset2.get(i).contains("CZ")
						|| dataset2.get(i).contains("DK")|| dataset2.get(i).contains("EE")|| dataset2.get(i).contains("FI")
						|| dataset2.get(i).contains("FR")|| dataset2.get(i).contains("DE")|| dataset2.get(i).contains("EL")
						|| dataset2.get(i).contains("HU")|| dataset2.get(i).contains("IE")|| dataset2.get(i).contains("IT")
						|| dataset2.get(i).contains("LV")|| dataset2.get(i).contains("LT")|| dataset2.get(i).contains("LU")
						|| dataset2.get(i).contains("MT")|| dataset2.get(i).contains("NL")|| dataset2.get(i).contains("PL")
						|| dataset2.get(i).contains("PT")|| dataset2.get(i).contains("RO")|| dataset2.get(i).contains("SK")
						|| dataset2.get(i).contains("SI")|| dataset2.get(i).contains("ES")|| dataset2.get(i).contains("SE")
						|| dataset2.get(i).contains("UK")){
					
					// data set which has EU countries
				}
				
				else {
					//key of all NON EU countries stored in set
					filteredset.add(i);
				}
			}
			//writing into output csv file
			FileWriter wr = null;
			wr = new FileWriter("target/filteredData.csv"); 
			String val[];
			
			//iterating over the set and accessing hash map values accordingly 
			for (Integer i: filteredset ) {
				
				val = dataset2.get(i).split("\t",4);
				wr.append(i.toString());
				wr.append(",");
				wr.append(val[0]);
				wr.append(",");
				wr.append(dataset1.get(i));
				wr.append(",");
				wr.append(val[1]);
				wr.append(",");
				wr.append(val[2]);
				wr.append(",");
				wr.append(val[3]);
				wr.append("\n"); 
				
		
				
			}
			System.out.println("Please check the target dir for output file");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		 
		  

	}

}
