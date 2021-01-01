import java.io.*;
import java.util.HashMap;
import java.util.*;
import org.json.*;

class File1{
	File1(){
	}
	File1(String s){
	}
}
public class Freshworks_Nithin{
	public static void main(String args[]){
		Scanner s =new Scanner(System.in);
		System.out.println("Enter path of file");
		String filepath = new String(s.nextLine());
		if(filepath.equals("")){
			filepath = "C://temp//test1.txt";
			//FileOutputStream out = new FileOutputStream(filepath);
			HashMap<String, Object> keyvaluestore = new HashMap<String, Object>();
			writedatatofile(filepath,keyvaluestore);
		}
		else{
			//FileOutputStream out = new FileOutputStream(filepath);
			HashMap<String, Object> keyvaluestore = new HashMap<String, Object>();
			writedatatofile(filepath,keyvaluestore);
		}	
		/*
		System.out.println("Enter key value pairs to add to file");
		int flag = 1;
		while(flag == 1){
			System.out.println("Enter key String");
			String keyvalue = new String(s.nextLine());
			System.out.println("Enter JSON value String");
			JSONObject jsonvalue = (JSONObject) parser.parse(s.nextLine());  
			keyvaluestore.put(keyvalue,jsonvalue);
			System.out.println("Do you want to create more? If yes press 1 else press 0");
			flag = s.nextInt();
			s.nextLine();
		}*/
		int flag2 =1;
		while(flag2==1){
			System.out.println("Enter the operation that you want to perform");
			System.out.println("1. Create");
			System.out.println("2. Read");
			System.out.println("3. Delete");
			System.out.println("4. Exit");
			int choice = s.nextInt();
			if(choice == 1){
				createdata(filepath);
			}
			else if (choice == 2){
				readdata(filepath);
			}
			else if (choice == 3){
				deletedata(filepath);
			}
			else{
				flag2 = 0;
			}
			
		}
	
	}
	
	public static Map<String, Object> HashMapFromTextFile(String filepath) 
	{ 
		Scanner s =new Scanner(System.in);
		Map<String, Object> map 
			= new HashMap<String, Object>(); 
		BufferedReader br = null; 

		try { 

			// create file object 
			File file = new File(filepath); 

			// create BufferedReader object from the File 
			br = new BufferedReader(new FileReader(file)); 

			String line = null; 

			// read file line by line 
			while ((line = br.readLine()) != null) { 

				// split the line by : 
				String[] parts = line.split(":"); 

				// first part is name, second is number 
				String name = parts[0].trim(); 
				JSONObject jsonvalue = new JSONObject(parts[1].trim()); 

				// put name, number in HashMap if they are 
				// not empty 
				if (!name.equals("") && !jsonvalue.equals("")) 
					map.put(name, jsonvalue); 
			} 
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
		finally { 

			// Always close the BufferedReader 
			if (br != null) { 
				try { 
					br.close(); 
				} 
				catch (Exception e) { 
				}; 
			} 
		} 

		return map; 
	} 
	public static void writedatatofile(String filepath,Map<String,Object> map){
		Scanner s =new Scanner(System.in);
		File file = new File(filepath); 

		BufferedWriter bf = null; 

		try { 

			// create new BufferedWriter for the output file 
			bf = new BufferedWriter(new FileWriter(file)); 

			// iterate map entries 
			for (Map.Entry<String, Object> entry : 
				map.entrySet()) { 

				// put key and value separated by a colon 
				bf.write(entry.getKey() + ":"
						+ entry.getValue()); 

				// new line 
				bf.newLine(); 
			} 

			bf.flush(); 
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
		finally { 

			try { 

				// always close the writer 
				bf.close(); 
			} 
			catch (Exception e) { 
			} 
		} 
	} 
	
	public static void createdata(String filepath){
		Scanner s =new Scanner(System.in);
		Map<String, Object> mapFromFile 
			= HashMapFromTextFile(filepath); 
		System.out.println("Enter key String");
			String keyvalue = new String(s.nextLine());
			System.out.println("Enter JSON value String");
			JSONObject jsonvalue = new JSONObject(s.nextLine());  
			mapFromFile.put(keyvalue,jsonvalue);	
			System.out.println("Data entered successfully");
			writedatatofile( filepath, mapFromFile);

	}
	public static void readdata(String filepath){
		Scanner s =new Scanner(System.in);
		System.out.println("Enter the key to read the data");
		String keyvalue = new String(s.nextLine());
		Map<String, Object> mapFromFile 
			= HashMapFromTextFile(filepath);
		JSONObject jsonvalue = new JSONObject(mapFromFile.get(keyvalue));
		System.out.println(jsonvalue.toString());
	}
	public static void deletedata(String filepath){
		// iterate over HashMap entries 
		Scanner s =new Scanner(System.in);
		System.out.println("Enter the key to delete the data");
		String keyvalue = new String(s.nextLine());
		Map<String, Object> mapFromFile = HashMapFromTextFile(filepath);
		mapFromFile.remove(keyvalue);
		writedatatofile(filepath, mapFromFile);
		System.out.println("Data deleted successfully");
	}
}