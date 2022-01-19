package use;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

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
	
	public void enterPrint() {
		
		System.out.print("\nPress ENTER to continue...");
		
        try {

        		System.in.read();
            
        } catch (IOException e) {}
		
	}
	
	public int YES_NO = 0;
	public int ENABLED_DISABLED = 1;
	
	public String boolText(boolean bool, int i) {
		
		switch(i) {
		
		case 0:
			if(bool) return "YES";
			return "NO";
		case 1:
			if(bool) return "enabled";
			return "disabled";
		
		}
		
		return null;
		
	}
	
	public String[] stringSplit(String s, int num) {
		
		return s.split("(?<=\\G.{"+num+"})");
		
	}
	
	public void msg(Object o) {
		
		JOptionPane.showMessageDialog(null, o);
		
	}
	
	public void msg(Object o, Object title) {
		
		JOptionPane.showMessageDialog(null, o, String.valueOf(title), 1);
		
	}
	
	public void msgError(Object o) {
		
		JOptionPane.showMessageDialog(null, o, "Error", 0);
		
	}
	
	public int msgOption(Object o, Object title, String[] options) {
		
		return JOptionPane.showOptionDialog(null, o, String.valueOf(title),
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);
		
	}
	
	public String msgInput(Object a, Object title) {
		
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
	
	
	// For files
	
	public void createFolder(String route, String folderName) {
		
		File logs = new File(route + "\\" + folderName);
		if (!logs.exists()) logs.mkdirs();
		
	}
	
	public void createFile(String route, String name, String ext, String write) {
		
		String file = route + "\\" + name + ext;
		try {
			new File(file);
		    FileWriter myWriter = new FileWriter(file);
			myWriter.write(write);
		    myWriter.close();
		} catch (IOException e1) {}
		
	}
	
	public String readFile(String route, String ext) {
		
		try {
			
			try (BufferedReader br = new BufferedReader(new FileReader(route + ext))) {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
				    sb.append(line);
				    sb.append(System.lineSeparator());
				    line = br.readLine();
				}
				
				return sb.toString();
			}
		    
		} catch(Exception e) {}
		
		return null;
		
	}
	
	public void openFile(String route, String ext) {
		
		try {  
			
			File file = new File(route + ext);    
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists())
				desktop.open(file);
		}  
		
		catch(Exception e) {}  
		
	}
	
	public String desktopRoute() {
		
		File desk = FileSystemView.getFileSystemView().getHomeDirectory();
		return desk.getAbsolutePath();
		
	}
	
	public String projectLocation() {
		
		File file = new File("");
		return file.getAbsolutePath();
		
	}
	
	public String getTimeByPattern(String pattern) {
		
		return new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime());
		
	}
	
	public String getUserIP() {
		
		URL ipRead;
		BufferedReader br = null;
		try {
			
			ipRead = new URL("http://checkip.amazonaws.com");
			br = new BufferedReader(
					new InputStreamReader(
							ipRead.openStream()
	                )
			);
			
		} catch (Exception e2) {}
		
		try { 
			return br.readLine();
		} catch (IOException e) {}
		
		return null;
		
	}
	
	public Inet4Address getIPv4(InetAddress[] IPs) {
		
	    for (InetAddress IP : IPs)
	    	if (IP instanceof Inet4Address)
	        	return (Inet4Address) IP;
	    return null;
	}
	
	public Inet6Address getIPv6(InetAddress[] IPs) {
		
	    for (InetAddress IP : IPs)
	        if (IP instanceof Inet6Address)
	        	return (Inet6Address) IP;
	    return null;
	}
	
}
