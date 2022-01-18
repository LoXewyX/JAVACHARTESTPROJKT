package tests;

import use.List;
import use.Simplify;
import visual.Panel;

public class listForChar {

	private static Simplify sf = new Simplify();
	private String chain;
	private final int i = 24;
	private int x = 0, y = i;
	
	public listForChar() {
		
		start();
		
	}
	
	private void start() {
		
		chain = "";
		String options[] = {"Back", "Next"};
		
			while(++x < y)
				chain += String.format("%03d - %1c%n", x, (char) x);
		chain += String.format("%03d - %1c%n", x, (char) x);
		int result = sf.msgOption(chain, List.txt[Panel.num], options);
		if (result == 1) {
			
			y += i;
			start();
			
		}
		if(result == 0) {
			
			if(x - i > 0) {

				y -= i;
				x -= i + i;
				
			} else {
				
				x = 0;
				y = i;
				
			}
			
			start();
			
		}
		
	}
	
}
