package odev1;


import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	 static String line;
	static int counter;
	static ArrayList<String> alphabet= new ArrayList <String>();
	static ArrayList<String> states= new ArrayList <String>();
	static String start;
	static ArrayList<String> goal= new ArrayList <String>();
	static ArrayList<String> transitions= new ArrayList <String>();
	
	public static void main(String[] args) throws IOException {
		
		
		 BufferedReader reader;
			
			
				reader = new BufferedReader(new FileReader(
						"C:\\Users\\taner\\Downloads\\NFA1.txt"));
				
			
				System.out.println("This is the NFA entered");
				 while(line!=null) {
					System.out.println(line);
					 line=reader.readLine();
				//	 if(line == "ALPHABET") {
					//	 while(line!= "STATES") {
						//	line= reader.readLine();
						//	line=reader.readLine();
						//	System.out.println(line);
						//	alphabet.add(line);
						// } 
						 }
					//if(line == "STATES") {
					//	while(line!="START") {
						//	line=reader.readLine();
						//	System.out.println(line);
						//	states.add(line);
						
					//	}
					
				//	}
					//if(line=="START") {
					
						//	line=reader.readLine();
						//	System.out.println(line);
						//	start=line;
						//}
					
				 //if(line=="FINAL") {
					// while(line!="TRANSITIONS") {
					//	 line=reader.readLine();
					//	 System.out.println(line);
					//	 goal.add(line);
				//	 }
				 
				// }
					// if(line=="TRANSITIONS") {
					//	 while(line!="END") {
					//		 line=reader.readLine();
					//		 System.out.println(line);
				//			 transitions.add(line);
					// }
				//	 }
					 
					 
			 reader.close();
		}
//}	
	//for(int i=0;i<alphabet.size();i++) {
	//	System.out.println(alphabet.get(i)+"");
	}
	//for(int i=0;i<states.size();i++) {
	//	System.out.println(states.get(i)+"");
	
	//System.out.print(start);
	
	//for(int i=0;i<goal.size();i++) {
	//	System.out.println(goal.get(i)+"");
	//}
	//for(int i=0;i<transitions.size();i++) {
	//	System.out.println(transitions.get(i)+"");
	//}
	
	