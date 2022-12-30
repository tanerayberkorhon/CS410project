package odev3;



import java.util.*;

import java.lang.*;

import java.io.*;  
public class Main {

	
	
	public static void main(String args[])  {

        // java JDK 19
        
		
		
		
        ArrayList<String> fileTotal = new ArrayList<String>();
        try  
        {  
        File file=new File("testing1.txt");    //creates a new file instance  
        FileReader fr=new FileReader(file);   //reads the file  
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
        StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
        String line;  
        while((line=br.readLine())!=null)  
        {  
            // add the input file to an arraylist
        fileTotal.add(line);
        }  
        fr.close();    //closes the stream and release the resources  
        System.out.println(fileTotal);    
        }  
        catch(IOException e)  
        {  
        e.printStackTrace();  
        }
        
        // find the indexes of labels to slice the arraylist
        int inputIndex = fileTotal.indexOf("INPUT");
        int tapeIndex = fileTotal.indexOf("TAPE");
        int transitionIndex = fileTotal.indexOf("TRANSITION");
        int startIndex = fileTotal.indexOf("START");
        int acceptIndex= fileTotal.indexOf("ACCEPT");
        int rejectIndex=fileTotal.indexOf("REJECT");
        int statesIndex=fileTotal.indexOf("STATES");
        int testIndex=fileTotal.indexOf("TEST");
        
        
        
      //  System.out.println(inputIndex);
     //   System.out.println(tapeIndex);
    //    System.out.println(transitionIndex);
     //   System.out.println(startIndex);
      //  System.out.println(acceptIndex);
     //   System.out.println(rejectIndex);
     //   System.out.print(statesIndex);
     //   System.out.println(testIndex);
        
        
        // variables in list format
        List<String> input1 = fileTotal.subList(inputIndex + 1, tapeIndex); //nonTerminal
        List<String> tape1 = fileTotal.subList(tapeIndex + 1, transitionIndex);          //terminal
        List<String> transition1 = fileTotal.subList(transitionIndex + 1, startIndex);                //rules
        List<String> start1 = fileTotal.subList(startIndex + 1, acceptIndex);          //rules
        List<String> accept1 = fileTotal.subList(acceptIndex + 1, rejectIndex); 
        List<String> reject1 = fileTotal.subList(rejectIndex + 1, statesIndex); 
        List<String> states1 = fileTotal.subList(statesIndex + 1, testIndex); 
        List<String> test1 = fileTotal.subList(testIndex + 1, fileTotal.size()); 
       
        List<String> input = new ArrayList<String>();
        List<String> tape = new ArrayList<String>();
        List<String> transition = new ArrayList<String>();
        List<String> start = new ArrayList<String>();
        List<String> accept = new ArrayList<String>();
        List<String> reject = new ArrayList<String>();
        List<String> states= new ArrayList<String>();
        List<String> test = new ArrayList<String>();
        List<String> route=new ArrayList<String>();
        
        
        
        input.addAll(input1);
        tape.addAll(tape1);
        transition.addAll(transition1);
        start.addAll(start1);
        accept.addAll(accept1);
        reject.addAll(reject1);
        states.addAll(states1);
        test.addAll(test1);
        
        
        
        
     //   List<String> FirstLetter=new ArrayList<String>();
    //	List<String> elements=new ArrayList<String>();
        
        
        //print to test
       
        
        
        System.out.println(input); 
        System.out.println(tape); 
        System.out.println(transition); 
        System.out.println(start);    
        System.out.println(accept); 
        System.out.println(reject); 
        System.out.println(states); 
        System.out.println(test); 



Turingmachine(input,tape,transition,start,accept,reject,states,test,route);










































	}


	
	
	
	public static void Turingmachine(List<String>input,List<String>tape,List<String>transition,List<String>start,List<String>accept,List<String>reject,List<String>states,List<String>test,List<String>route) {
	
	String state_position=start.get(0);
	String tape_position=test.get(0);
	String[]checker;
	String[]checker1;
	
	String state_checker;
	String inputvariable;
	String converttape="";
	String direction;
	String nextState;
	int loopchecker=0;
	route.add(state_position);
	
	//System.out.println("state");
	int tapeindex1 =0;
	while(!accept.contains(state_position) && !reject.contains(state_position)) {
		//System.out.println(test);
		for(int i=0;i<transition.size();i++) {
			
			checker=transition.get(i).split(":");
			state_checker=checker[0];
			checker1=checker[1].split("");
			
		//	System.out.println(checker1.length);
		//	System.out.println(tapeindex1);
			
			if(checker1.length==4) {
				
				inputvariable=checker1[0];
				converttape=checker1[1];
				direction=checker1[2];
				nextState=checker1[3];
			//	System.out.println(converttape);
			}
			
			else {
				
				inputvariable=checker1[0];
				direction=checker1[1];
				nextState=checker1[2];
				
				
			}
			
			
			if(state_position.equals(state_checker) && tape_position.equals(inputvariable) && checker1.length==4) {
				
				test.set(tapeindex1, converttape);
				
				
				
				state_position=nextState;
				
				route.add(state_position);
				
				
				
				if(direction.equals("R")) {
					
					
					if(tapeindex1< test.size()-1) {
						tapeindex1++;
						tape_position=test.get(tapeindex1);
						
					break;
					
					}
					
					else {
						
						test.add("b");
						tapeindex1++;
						tape_position=test.get(tapeindex1);
						
						break;
					}
					
					
				}
				
				if(direction.equals("L")) {
					
					if(tapeindex1-1>= 0) {
						tapeindex1--;
						tape_position=test.get(tapeindex1);
						
					
					break;
					}
					
					else {
						
						break;
						
						
						
						
					}
					
					
					
					
				}
				
				
				
				
				
			}
			
			
			if(state_position.equals(state_checker) && tape_position.equals(inputvariable) && checker1.length==3) {
				
				
				state_position=nextState;
				route.add(state_position);
				if(direction.equals("R")) {
					
					
					if(tapeindex1< test.size()-1) {
						tapeindex1++;
						tape_position=test.get(tapeindex1);
						
					break;
					
					}
					
					else {
						
						test.add("b");
						tapeindex1++;
						tape_position=test.get(tapeindex1);
						break;
						
					}
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				if(direction.equals("L")) {
					
					if(tapeindex1-1>= 0) {
						tapeindex1--;
						tape_position=test.get(tapeindex1);
						
					break;
					
					}
					
					else {
						
						break;
						
						
					}
					
					
					
					
				}
				
				
				
				
				
			
				
				
				
			}
			
			
			
			
		
		
			
		}
		
		
		
		if(accept.contains(state_position)) {
			
			System.out.println("ROUT:"+route);
			System.out.println("RESULT:String accepted");
		
		
		
		}
		
		
		if(reject.contains(state_position)) {
			
			System.out.println("ROUT:"+route);
			System.out.println("RESULT:String rejected");
		}
		
		
		
		
		loopchecker++;
		
		
		if(loopchecker>=1500) {
			System.out.println("RESULT:LOOP");
			break;
			
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//System.out.println(test);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	

}
