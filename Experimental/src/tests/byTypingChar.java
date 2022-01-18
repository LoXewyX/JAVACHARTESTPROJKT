package tests;

import use.List;
import use.Simplify;
import visual.Panel;

public class byTypingChar {

	private static Simplify sf = new Simplify();
	
	public byTypingChar() {
		
		start();
		
	}
	
	private void start() {
		
		char c;
		String s;
		
		try {
			
			do s = sf.msgInput("Introduce your character", List.txt[Panel.num]);
			while (s.length() == 0);
			
			c = s.charAt(0);
			
			s = String.valueOf(c); // redundant but cool expression
			sf.msg("\'" + s + "\' = " + (int) c);
			
		} catch(Exception e) {}

	}
	
}