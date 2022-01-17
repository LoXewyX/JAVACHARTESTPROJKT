package tests;

import use.Simplify;

public class listForChar {

	private static Simplify sf = new Simplify();
	private String chain = "";
	private int k = 24, i, x = k;
	
	public listForChar() {
		
		start();
		
	}
	
	private void start() {
		
		for(; i <= x; ++i)
			chain += String.format("%03d - %1c%n", i, (char) i);
		x += k;
		int result = sf.msgContinue(chain, "Would you like to continue?");
		if (result == 0) {
			
			chain = "";
			start();
			
		}
		
	}
	
}
