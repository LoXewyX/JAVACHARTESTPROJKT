package use;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Simplify {
	
	public void exit() {
		
		System.exit(0);
		
	}

	public void print(Object o) {
		
		System.out.print(o);
		
	}
	
	public void consClean() throws IOException, InterruptedException {
		
		Runtime rt = Runtime.getRuntime();
		new ProcessBuilder("cmd.exe", "/c", "cls").inheritIO().start().waitFor();
		rt.exec("cmd /c Powrprof.dll,SetSuspendState");
		
	}
	
	public void enter() {
		
		System.out.print("\nPress ENTER to continue...");
		
        try {

        		System.in.read();
            
        } catch (IOException e) {}
		
	}
	
	public String[] split(String s, int num) {
		
		return s.split("(?<=\\G.{"+num+"})");
		
	}
	
	public void msg(Object o) {
		
		JOptionPane.showMessageDialog(null, o);
		
	}
	
	public void msgError(Object o) {
		
		JOptionPane.showMessageDialog(null, o, "Error", 0);
		
	}
	
	public int msgContinue(Object o, Object title) {
		
		return JOptionPane.showConfirmDialog(null, o, String.valueOf(title), JOptionPane.OK_CANCEL_OPTION);
		
	}
	
	public String msginput(Object a, Object title) {
		
		return JOptionPane.showInputDialog(null, a, String.valueOf(title), 3);
		
	}
	
	public boolean isNumeric(String s) {
		
		try {
			
			Integer.parseInt(s);
			return true;
			
		} catch(Exception e) {
			
			return false;
			
		}
		
		
	}
	
}
