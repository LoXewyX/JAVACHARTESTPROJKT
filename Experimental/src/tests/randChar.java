package tests;

import use.List;
import use.Simplify;
import visual.Panel;

public class randChar {
	
	private Simplify sf = new Simplify();
	
	public randChar() {
		
		int rand = (int) (Math.random() * 100001);
		sf.msg(rand + " = \'" + (char) rand + "\'", List.txt[Panel.num]);
		
	}

}
