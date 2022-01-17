package tests;

import use.List;
import use.Simplify;
import visual.Panel;

public class charSum {

	private static Simplify sf = new Simplify();
	
	public charSum() {
		
		try {
			
			String s = sf.msginput("Introduce your text: ", List.txt[Panel.num]);
			int i = 0;
			
	        char[] ch = new char[s.length()];
	        
	        for (int e = 0; e < s.length(); e++) {
	        	
	        	ch[e] = s.charAt(e);
	        	i += (int) ch[e];
	        	
	        }
	        
	        sf.msg("\'" + s + "\' = " + i + "\n");
			
		} catch(Exception e) {}
		
	}
	
}
