package odev1;


import java.util.*;
import java.lang.*;

import java.io.*;  

public class Main{
    
	
	
	public static void main(String args[])  {

        // java JDK 19
        
        ArrayList<String> fileTotal = new ArrayList<String>();
        try  
        {  
        File file=new File("G2.txt");    //creates a new file instance  
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
        int nonTerminalIndex = fileTotal.indexOf("NON-TERMINAL");
        int terminalIndex = fileTotal.indexOf("TERMINAL");
        int rulesIndex = fileTotal.indexOf("RULES");
        int startIndex = fileTotal.indexOf("START");
        
        // variables in list format
        List<String> nonTerminal1 = fileTotal.subList(nonTerminalIndex + 1, terminalIndex); //nonTerminal
        List<String> terminal1 = fileTotal.subList(terminalIndex + 1, rulesIndex);          //terminal
        List<String> rules1 = fileTotal.subList(rulesIndex + 1, startIndex);                //rules
        List<String> start1 = fileTotal.subList(startIndex + 1, fileTotal.size());          //rules
        
       
        List<String> nonTerminal = new ArrayList<String>();
        List<String> terminal = new ArrayList<String>();
        List<String> rules = new ArrayList<String>();
        List<String> start = new ArrayList<String>();
        
        nonTerminal.addAll(nonTerminal1);
        terminal.addAll(terminal1);
        rules.addAll(rules1);
        start.addAll(start1);
        
     //   List<String> FirstLetter=new ArrayList<String>();
    //	List<String> elements=new ArrayList<String>();
        
        
        //print to test
       
        
        
        System.out.println(nonTerminal); 
        System.out.println(terminal); 
        System.out.println(rules); 
        System.out.println(start);    
        
        //List<String> nonTerminal1= new ArrayList<String>();
       // nonTerminal1.add("S1");
        
        
       // for(int b=0;b<nonTerminal.size();b++) {
        //	nonTerminal1.add(nonTerminal.get(b));
      //  }
      //  System.out.println(nonTerminal1);
        
      
        
        
       
      
     //  System.out.println(start);
     //  System.out.println(nonTerminal);
        
        newstartstate(rules,start,nonTerminal);
        
        
      //  System.out.println(rules);
        
	    nullable(rules);
	    
	    
	    removeDuplicates(rules);
	  //  System.out.println(rules);
	
	    unitproduction(rules,nonTerminal,terminal,start);
	  
	   // System.out.println("unit production");
	   //System.out.println(rules);
	  
	  // System.out.println(terminal);
	  // System.out.println(nonTerminal);
	    
	    
	    convert_to_Chomsky(rules,nonTerminal,terminal,start);
	  removeDuplicates(rules);
	   // System.out.println(rules);
	   // System.out.println(nonTerminal);
	   // unitproduction(rules,nonTerminal,terminal,start);
	  //  removeDuplicates(rules);
	    
	    //System.out.println(rules);
	  
	  System.out.println(nonTerminal); 
      System.out.println(terminal); 
      System.out.println(rules); 
      System.out.println(start); 
	  
	
	}  


public static List<String> nullable(List<String>rules) {
String nullable_non_terminal;
String[]checker;
String[]checker1;
String[]checker2;
String[]new_rules;
String new_rule;
String new_rule1;
String delimiter="";
String delimiter1=":";

for(int i=0;i<rules.size();i++) {
	checker=rules.get(i).split(":");
	if(checker[1].contains("e")) {
		rules.remove(i);
		
		nullable_non_terminal=checker[0];
		
		for(int x=0;x<rules.size();x++) {
			checker2=rules.get(x).split(":");
			if(checker2[1]==nullable_non_terminal) {
				checker2[1]="e";
				new_rule1=String.join(delimiter1, checker2);
			    rules.add(new_rule1);
			}
			
		}
		
		
		
		for(int y=0;y<rules.size();y++) {
			checker1=rules.get(y).split(":");
			if(checker1[1].contains(nullable_non_terminal)) {
				for(int z=0;z<3;z++) {
					new_rules=checker1[1].split(nullable_non_terminal, z);
					checker1[1]=String.join( delimiter,new_rules);
				new_rule=String.join(delimiter1, checker1);
				rules.add(new_rule);
				}
			}
		}
	}
	
	}
	
	
	







return rules;

}



public static  List<String> removeDuplicates(List<String> rules)
{

  Set<String> set=new HashSet<>(rules);
  rules.clear();
  rules.addAll(set);
  
  return rules;
  
  
}






public static List<String> newstartstate( List<String> rules,List<String> start,List<String>nonTerminal){
	String[] checker;
	for(int i=0; i<rules.size();i++) {
		checker=rules.get(i).split(":");
		
		if(checker[1].contains("S")) {
			
			rules.add(0,"X:S");
		    start.set(0,"X");
	        nonTerminal.add(0, "X");
			break;
		}
	}
	
	return rules;
}

public static List<String> unitproduction(List<String> rules,List<String> nonTerminal,List<String> Terminal, List<String>start ){
	String[] checker;
	String[] checker1;
	
	
	
	int count;
	String new_rule;
	
	
	
	String delimiter=":";

	

	
	
	for(int i=0;i<rules.size();i++) {
		
		checker=rules.get(i).split(":");
		//System.out.println("first for loop");
		//System.out.println(checker[0]);
		//System.out.println(checker[1]);
		
		for(int y=0;y<rules.size();y++) {
			
			checker1=rules.get(y).split(":");
			//System.out.println("Second for loop");
			//System.out.println(checker1[0]);
			//System.out.println(checker1[1]);
			
			if(checker[0].equals(checker1[1])) {
				checker1[1]=checker[1];
				//System.out.println("if statement");
				//System.out.println(checker1[0]);
				//System.out.println(checker1[1]);
				
				
				
				new_rule=String.join(delimiter,checker1);
				
				
				//System.out.println("Final String");
				//System.out.println(new_rule);
				rules.add(new_rule);
				
			}
			
			
			
		}
		
		
	
	}
	
	
	for(int z=0;z<rules.size();z++) {
		
		checker=rules.get(z).split(":");
		
		count=checker[1].length();
				
		
			
			for(int x=0;x<nonTerminal.size();x++) {
				
				
				if(checker[1].equals(nonTerminal.get(x))) {
					
					rules.remove(z);
					
				}
			}
			
			
		}
		
		
		
	
	
	return rules;
}
	
	
	
public static List<String> convert_to_Chomsky(List<String> rules,List<String> nonTerminal,List<String> Terminal, List<String>start ){
	
	String[] checker;
	String[] checker1;
	String[] checker2;
	List<String> FirstLetter=new ArrayList<String>();
	List<String> elements=new ArrayList<String>();
	
	
	
	int bugfix=0;
	
	int index;
	String first;
	String[] divider;
	
	
	
	int count=0;
	String new_rule;
	String new_rule1;
	String U="U";
	
	String delimiter=":";
	String delimiter1="";
	
	String [] variables;
	String random_Uppercase_Letter;
	String random_Uppercase_Letter1;
	
	String Ustate;
	
	
	System.out.println(rules);
	for(int o=0; o<rules.size();o++) {
		
		
		
		
		
		checker=rules.get(o).split(":");
		
		
		if(Terminal.contains(checker[1])) {
			
			FirstLetter.add(checker[0]);
			
			elements.add(checker[1]);
			
			
		}
	
		
		
	}
	
	//System.out.println("FirstLetter");
//	System.out.println(FirstLetter);
	//System.out.println("elements");
	
	//System.out.println(elements);
	
	
	for(int z=0;z<rules.size();z++){
		checker2=rules.get(z).split(":");
		
		
		variables=checker2[1].split("");
		
	//	System.out.println(checker2[0]);
//	System.out.println(checker2[1]);
	//	System.out.println(variables.length);
	//	System.out.println("variable array");
	//	System.out.println(Arrays.toString(variables));
		
		
		//System.out.println("rules");
		//System.out.println(rules);
	
		
	//	System.out.println("z");
	//	System.out.println(z);
		//System.out.println("rule at z");
		//System.out.println(rules.get(z));
		
		
		
		
		
		
		
		
		for(int h=0;h<variables.length-1;h++) {
			
			
			
			if(variables[h].equals("U") ) {
				
				Ustate=variables[h]+variables[h+1];
				variables[h]=Ustate;
				List<String>variables1=new ArrayList<String>(Arrays.asList(variables));
				
				variables1.remove(h+1);
				
				variables=variables1.toArray(new String[0]);
				
				h=0;
				
			}
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		if(variables.length>2) {
			
			
			rules.remove(z)	;
			//System.out.println(rules);
			
			
			
			
			
			for(int x=0;x<variables.length-1;x++) {
				
				
				
				
			//System.out.println(variables[x]);
			//	System.out.println(variables[x+1]);
				
				if(Terminal.contains(variables[x]) && Terminal.contains(variables[x+1])) {
					
				
						
						
						
							//System.out.println("two lowercases if successful");
					if(variables[x].equals(variables[x+1])) {
					
					//	System.out.println("two equal lowercases if");
						
						if(elements.contains(variables[x])) {
							
							
							
							 index=elements.indexOf(variables[x]);
							
							 first=FirstLetter.get(index);
							
							
							
							variables[x]=first;
							variables[x+1]=first;
						
						
						
						}
						
						
						
							
					
						
						else {
						
						
						String UpperLetter=U+Integer.toString(count);
						
					// System.out.println(UpperLetter);
						nonTerminal.add(UpperLetter);
						FirstLetter.add(UpperLetter);
						new_rule=UpperLetter+":"+variables[x];
						elements.add(variables[x]);
						rules.add(new_rule);
						variables[x]=UpperLetter;
						variables[x+1]=UpperLetter;
						
						
						}
						
						
						checker2[1]=String.join(delimiter1, variables);
						//	System.out.println(checker2[1]);
							new_rule=String.join(delimiter, checker2);
					
							//System.out.println(new_rule);
					rules.add(new_rule);
					
					
					
					
					
					
					
					z=0;
					
					count++;
					
				//	System.out.println("z");
				//	System.out.println(z);
					
					}
					
						
					else{
						
					//	System.out.println("two lowercase different if");
						
						
						
						if(elements.contains(variables[x])) {
							

							 index=elements.indexOf(variables[x]);
							
							 first=FirstLetter.get(index);
							
							
							
							variables[x]=first;
							
							
						}
						
						if(elements.contains(variables[x+1])) {
							
							 index=elements.indexOf(variables[x+1]);
								
							 first=FirstLetter.get(index);
							
							
							
							variables[x+1]=first;
							
							
						}
						
						
						
						
						else {
						
						String UpperLetter=U+Integer.toString(count);
						
						
						
						count++;
						String UpperLetter1=U+Integer.toString(count);
						
						count++;
						
						
					
						
						nonTerminal.add(UpperLetter);
						FirstLetter.add(UpperLetter);
						elements.add(variables[0]);
						
						nonTerminal.add(UpperLetter1);
						FirstLetter.add(UpperLetter1);
						elements.add(variables[1]);
						
						new_rule=UpperLetter+":"+variables[x];
						rules.add(new_rule);
						new_rule=UpperLetter1+":"+variables[x+1];
						
						rules.add(new_rule);
						variables[x]=UpperLetter;
						variables[x+1]=UpperLetter1;
						}
						checker2[1]=String.join(delimiter1, variables);
						
						//System.out.println(checker2[1]);
						
						new_rule=String.join(delimiter, checker2);
						
						//System.out.println(new_rule);
						
						rules.add(new_rule);
						z=0;
					
					}
					
					
				}
					
				
				else	if(nonTerminal.contains(variables[x])&& Terminal.contains(variables[x+1])) {
					
					if(elements.contains(variables[x+1])) {
						
						
						 index=elements.indexOf(variables[x+1]);
							
						 first=FirstLetter.get(index);
						
						
						
						variables[x+1]=first;
						
						
						
						
						
					}
					
					else {
						
						String UpperLetter=U+Integer.toString(count);
						
						// System.out.println(UpperLetter);
							nonTerminal.add(UpperLetter);
							FirstLetter.add(UpperLetter);
							new_rule=UpperLetter+":"+variables[x+1];
							elements.add(variables[x+1]);
							rules.add(new_rule);
						
							variables[x+1]=UpperLetter;
						
						count++;
						
					}
					
					
					checker2[1]=String.join(delimiter1, variables);
					
					//System.out.println(checker2[1]);
					
					new_rule=String.join(delimiter, checker2);
					
					//System.out.println(new_rule);
					
					rules.add(new_rule);
					z=0;
                    
					
					
					
				}
				
				
				
				
				else	if(Terminal.contains(variables[x]) && nonTerminal.contains(variables[x+1])) {
				
					if(elements.contains(variables[x])) {
						
						
						 index=elements.indexOf(variables[x]);
							
						 first=FirstLetter.get(index);
						
						
						
						variables[x]=first;
						
						
						
						
						
					}
				
				
                     else {
						
						String UpperLetter=U+Integer.toString(count);
						
						// System.out.println(UpperLetter);
							nonTerminal.add(UpperLetter);
							FirstLetter.add(UpperLetter);
							new_rule=UpperLetter+":"+variables[x];
							elements.add(variables[x]);
							rules.add(new_rule);
						
							variables[x]=UpperLetter;
						
						count++;
						
					}
					
					
                checker2[1]=String.join(delimiter1, variables);
					
					//System.out.println(checker2[1]);
					
					new_rule=String.join(delimiter, checker2);
					
					//System.out.println(new_rule);
					
					rules.add(new_rule);
					z=0;
				    
				}
				
				
				
				
				
				
				
				
					
				
			
				else if(nonTerminal.contains(variables[x]) && nonTerminal.contains(variables[x+1])) {
			
				
				
				
				if(elements.contains(variables[x]+variables[x+1])) {
				
					
					 index=elements.indexOf(variables[x]+variables[x+1]);
						
					 first=FirstLetter.get(index);
					
					
					variables[x]="";
					variables[x+1]=first;
					
					List<String>variables1=new ArrayList<String>(Arrays.asList(variables));
					
					variables1.remove(x);
					
					variables=variables1.toArray(new String[0]);
					
				}
				
				
				
				
				
				
				else {
				
					String UpperLetter=U+Integer.toString(count);
					count++;
				
				
				//System.out.println(UpperLetter);
				
				nonTerminal.add(UpperLetter);
				FirstLetter.add(UpperLetter);
				elements.add(variables[x]+variables[x+1]);
				
				new_rule=UpperLetter+":"+variables[x]+variables[x+1];
				
				
				rules.add(new_rule);
				
				//System.out.println(variables[x]);
				//System.out.println(variables[x+1]);
				variables[x]="";
				variables[x+1]=UpperLetter;
				
				List<String>variables1=new ArrayList<String>(Arrays.asList(variables));
				
				variables1.remove(x);
				
				variables=variables1.toArray(new String[0]);
				
				
				
				}
				
				checker2[1]=String.join(delimiter1,variables);
				
			//System.out.println(checker2[1]);
				
				new_rule=String.join(delimiter, checker2);
				
			//System.out.println(new_rule);
				
				
				rules.add(new_rule);
				
				z=0;
				
				
			}
			}
			}
			
			
			if(variables.length==2) {
				
if(nonTerminal.contains(variables[0])&& Terminal.contains(variables[1])) {
					
					rules.remove(z);
	if(elements.contains(variables[1])) {
						
						
						 index=elements.indexOf(variables[1]);
							
						 first=FirstLetter.get(index);
						
						
						
						variables[1]=first;
						
						
						
						
						
					}
					
					else {
						
						String UpperLetter=U+Integer.toString(count);
						
						// System.out.println(UpperLetter);
							nonTerminal.add(UpperLetter);
							FirstLetter.add(UpperLetter);
							new_rule=UpperLetter+":"+variables[1];
							elements.add(variables[1]);
							rules.add(new_rule);
						
							variables[1]=UpperLetter;
						
						count++;
						
					}
					
					
					checker2[1]=String.join(delimiter1, variables);
					
					//System.out.println(checker2[1]);
					
					new_rule=String.join(delimiter, checker2);
					
					//System.out.println(new_rule);
					
					rules.add(new_rule);
					z=0;

					
					
					
				}
				
				
				
				
else if(Terminal.contains(variables[0]) && nonTerminal.contains(variables[1])) {
				
					rules.remove(z);
					if(elements.contains(variables[0])) {
						
						
						 index=elements.indexOf(variables[0]);
							
						 first=FirstLetter.get(index);
						
						
						
						variables[0]=first;
						
						
						
						
						
					}
				
				
                     else {
						
						String UpperLetter=U+Integer.toString(count);
						
						// System.out.println(UpperLetter);
							nonTerminal.add(UpperLetter);
							FirstLetter.add(UpperLetter);
							new_rule=UpperLetter+":"+variables[0];
							elements.add(variables[0]);
							rules.add(new_rule);
						
							variables[0]=UpperLetter;
						
						count++;
						
					}
					
					
                checker2[1]=String.join(delimiter1, variables);
					
					//System.out.println(checker2[1]);
					
					new_rule=String.join(delimiter, checker2);
					
					//System.out.println(new_rule);
					
					rules.add(new_rule);
					z=0;
				}
				
				
else if(Terminal.contains(variables[0]) && Terminal.contains(variables[1])) {
					
					
					
				//	System.out.println("It works");
				//	System.out.println(rules.get(z));
					rules.remove(z);
					
					if(variables[0].equals(variables[1])) {
						//System.out.println("two equal lowercases if");
					
						//System.out.println("It works");
						if(elements.contains(variables[0])) {
							
						
							index=elements.indexOf(variables[0]);
							
							 first=FirstLetter.get(index);
							
							
							
							variables[0]=first;
							variables[1]=first;
						
						
						
						
						
						}
						
						
						
						
						
						
						else {
						String UpperLetter=U+Integer.toString(count);
						
					// System.out.println(UpperLetter);
						nonTerminal.add(UpperLetter);
						FirstLetter.add(UpperLetter);
						new_rule=UpperLetter+":"+variables[0];
						elements.add(variables[0]);
						rules.add(new_rule);
						variables[0]=UpperLetter;
						variables[1]=UpperLetter;
						
						
						
						}
						
							checker2[1]=String.join(delimiter1, variables);
						//	System.out.println(checker2[0]);
							new_rule=String.join(delimiter, checker2);
					      //   System.out.println("It works");
						//	System.out.println(new_rule);
					rules.add(new_rule);
					z=0;
					count++;
					}
					else{
						
						//	System.out.println("two lowercase different if");
							
						if(elements.contains(variables[0])) {
							

							 index=elements.indexOf(variables[0]);
							
							 first=FirstLetter.get(index);
							
							
							
							variables[0]=first;
							
							
						}
						
						if(elements.contains(variables[1])) {
							
							 index=elements.indexOf(variables[1]);
								
							 first=FirstLetter.get(index);
							
							
							
							variables[1]=first;
							
							
						}
							
							
						
						
						
						
						
							
							
							
					else {
							
							String UpperLetter=U+Integer.toString(count);
							
							
							
							count++;
							String UpperLetter1=U+Integer.toString(count);
							
							count++;
							
							
						
							
							nonTerminal.add(UpperLetter);
							FirstLetter.add(UpperLetter);
							
							
							nonTerminal.add(UpperLetter1);
							
							FirstLetter.add(UpperLetter1);
							
							new_rule=UpperLetter+":"+variables[0];
							
							elements.add(variables[0]);
							rules.add(new_rule);
							
							
							new_rule=UpperLetter1+":"+variables[1];
							elements.add(variables[1]);
							rules.add(new_rule);
							variables[0]=UpperLetter;
							variables[1]=UpperLetter1;
							
					}
							
							
							checker2[1]=String.join(delimiter1, variables);
							
							//System.out.println(checker2[1]);
							
							new_rule=String.join(delimiter, checker2);
							
							//System.out.println(new_rule);
							
							rules.add(new_rule);
							z=0;
						}
					
					
				}
					
					
					
					
				
			}
				
				
				
			
		
	
	
	
			
	
	
	
			
			
			

	
	
	
	
	
	
	
	
	
	
	
	

}

	//System.out.println("elements");
	//System.out.println(elements);
	//System.out.println("Rules test");
	//System.out.println(rules);
	//System.out.println(rules.get(1));
	rules.remove(0);

return rules;

}
	}























			
	