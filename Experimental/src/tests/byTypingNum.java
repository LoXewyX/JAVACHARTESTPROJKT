package tests;

import visual.Panel;

import use.List;

import use.Simplify;

public class byTypingNum {

	private static Simplify sf = new Simplify();
	
	public byTypingNum() {
		
		start();
		
	}
	
	private void start() {
		
		char c = 0;
		String s;
		
		try {
			
			do s = sf.msginput("Introduce your number", List.txt[Panel.num]);
			while (s.length() == 0);
			
			if(!sf.isNumeric(s)) {
				
				sf.msgError(s + " is not a number");
				start();
				
			}
			
			c = (char) Integer.parseInt(s);
			
			s = String.valueOf(c); // redundant but cool expression
			sf.msg((int) s.charAt(0) + " = \'" + c + "\'"
			/* or it can be done with  + (int) c       */);
			
		} catch(Exception e) {}
		
	}
	
}
