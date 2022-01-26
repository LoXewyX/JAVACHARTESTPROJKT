package use;

import tests.*;

public class Menu {

	private int num;
	
	public void start(int n) {
		
		num = n;
		
		switch(num) {
		
			case 0: new byTypingNum(); 		break;
			case 1: new byTypingChar(); 	break;
			case 2: new listForChar();		break;
			case 3: new splitByNum(); 		break;
			case 4: new txtToChars();		break;
			case 5: new charSum(); 			break;
			case 6: new randChar();			break;
		
		}
		
	}
	
}
