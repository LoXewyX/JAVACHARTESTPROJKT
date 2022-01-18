package tests;

import use.List;
import use.Simplify;
import visual.Panel;

public class txtToChars {

	private static Simplify sf = new Simplify();
	
	public txtToChars() {
		
		try {
			
			String s = sf.msgInput("Introduce your text: ", List.txt[Panel.num]);
	
	        char[] ch = new char[s.length()];
	  
	        for (int i = 0; i < s.length(); i++)
	            ch[i] = s.charAt(i);
	  
	        String chain = "";
	        for (char c : ch)
	        	chain += ("\'" + c + "\' = " + (int) c + "\n");
	        sf.msg(chain);
	        
		} catch (Exception e) {}
		
	}
	
}
