package tests;

import use.List;
import use.Simplify;
import visual.Panel;

public class splitBy {
	
	private static Simplify sf = new Simplify();
	
	public splitBy() {
		
		start();
		
	}
	
	private void start() {
		
		int i = 0, range = 0;
		String s, srange;
		
		try {
			s = sf.msginput("\nIntroduce your number sequence: ", List.txt[Panel.num]);
			if(!s.equals(null)) {
				
				 srange = sf.msginput("\nIntroduce your split range: ", List.txt[Panel.num]);
				if(!srange.equals(null)) {
					
					try {
						
						i = Integer.parseInt(s);
						range = Integer.parseInt(srange);
						
						String[] array = sf.split(String.valueOf(i), range);
						
				        char c;
				        String sr = "";
				        
				        for(int z = 0; z < array.length - 1; z++) {
				        	
				        	c = (char) Integer.parseInt(array[z]);
				        	sr += "\'" +  Character.toString(c) + "\', ";
				        	
				        }	
				        
				        c = (char) Integer.parseInt(array[array.length - 1]);
				    	sr += "\'" +  Character.toString(c) + "\'";
				        
				        sf.msg(i + " = " + sr);
						
					} catch (NumberFormatException e) {
						
							sf.msgError("\n\tBad input!\n\n");
							start();
						
					}
					
				}
				
			}
		
		} catch(Exception e) {}
		
	}

}
