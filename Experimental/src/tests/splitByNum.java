package tests;

import use.List;
import use.Simplify;
import visual.Panel;

public class splitByNum {
	
	private  Simplify sf = new Simplify();
	private int i = 0, range = 0;
	private String s, srange;
	
	public splitByNum() {
		
		start();
		
	}
	
	private void start() {
		
		s = sf.msgInput("\nIntroduce your number sequence: ", List.txt[Panel.num]);
		if(isNumeric(s, 1)) range();
		
	}
	
	private void range() {
		
		srange = sf.msgInput("\nIntroduce your split range: ", List.txt[Panel.num]);
		if(!srange.equals(null) && isNumeric(srange, 2)) {
			
			i = Integer.parseInt(s);
			range = Integer.parseInt(srange);
			
			String[] array = sf.splitEvery(String.valueOf(i), range);
			
	        char c;
	        String sr = "";
	        
	        for(int z = 0; z < array.length - 1; z++) {
	        	
	        	c = (char) Integer.parseInt(array[z]);
	        	sr += "\'" +  Character.toString(c) + "\', ";
	        	
	        }	
	        
	        c = (char) Integer.parseInt(array[array.length - 1]);
	    	sr += "\'" +  Character.toString(c) + "\'";
	        
	        sf.msg(i + " = " + sr);	
			
		}
		
	}
	
	private boolean isNumeric(String s, int i) {
		
		try {
			
			if(s.isBlank()) {} 	// Will call the function NullPointer
			Integer.parseInt(s);
			return true;
			
		} catch(NumberFormatException e) {
			
			sf.msgError("\n\t" + s + " is not a number!\n\n");
			if(i == 1) start();
			else range();
			
		} catch(NullPointerException e2) {}
		
		return false;
		
	}

}
