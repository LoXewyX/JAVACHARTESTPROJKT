package use;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;

public class Simplify {
	
	public Simplify() {
		
		// Use the Windows feel
		try {
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
		} catch (ClassNotFoundException |
				InstantiationException |
				IllegalAccessException |
				UnsupportedLookAndFeelException e) {}
		
		
	}
	
	// System voids
	
	public void exit() {
		
		System.exit(0);
		
	}

	public void print(Object o) {
		
		System.out.print(o);
		
	}
	
	public void println(Object o) {
		
		System.out.println(o);
		
	}
	
	public void println() {
		
		System.out.println();
		
	}
	
	public void enterPrint() {
		
		System.out.print("\nPress ENTER to continue...");
		
        try {

        		System.in.read();
            
        } catch (IOException e) {}
		
	}
	
	public void consClean() throws IOException, InterruptedException {
		
		// Only windows
		
		Runtime rt = Runtime.getRuntime();
		new ProcessBuilder("cmd.exe", "/c", "cls").inheritIO().start().waitFor();
		rt.exec("cmd /c Powrprof.dll,SetSuspendState");
		
	}
	
	// Boolean returns
	
	public boolean isNumeric(String s) {
		
		try {
			
			Integer.parseInt(s);
			return true;
			
		} catch(Exception e) {
			
			return false;
			
		}
		
		
	}
	
	// String returns
	
	public int YES_NO = 0;
	public int ENABLED_DISABLED = 1;
	public int BIN = 2;
	public int CHECK = 3;
	
	public String boolText(boolean bool, int i) {
		
		switch(i) {
		
		case 0:
			if(bool) return "YES";
			return "NO";
		case 1:
			if(bool) return "enabled";
			return "disabled";
		case 2:
			if(bool) return "0";
			return "1";
		case 3:
			if(bool) return "successed";
			return "error";
		
		}
		
		return null;
		
	}
	
	public String[] splitEvery(String s, int num) {
		
		return s.split("(?<=\\G.{"+num+"})");
		
	}
	
	public boolean isArrayRepeated(String[] arr) {
		
		// This method ignores null and checks the bidimensional array
		// if is any variable repeated inside
		
		for (int i = 0; i < arr.length; i++) {
			
            for (int j = 0; j < arr.length; j++) {
            	
            	if (arr[i] == null || arr[i] == "");
            	else if (arr[i].equals(arr[j]) && i != j)
                    return true;
            } 
            
        }
		
		return false;
			
	}
	
	public String printArray(String pswrd[]) {
		
		String chain = "";
		
		for(int i = 0; i < pswrd.length; i++) {
			
			chain += "[" + pswrd[i] + "]";
			if(i != pswrd.length - 1)
				chain += ", ";
			
		}
		return chain;
//		return Arrays.toString(pswrd);
		
	}
	
	// Java Swing
	
	public void msg(Object o) {
		
		JOptionPane.showMessageDialog(null, o);
		
	}
	
	public void msg(Object o, Object title) {
		
		JOptionPane.showMessageDialog(null, o, String.valueOf(title), 1);
		
	}
	
	public void msg(Object o, Object title, int display) {
		
		JOptionPane.showMessageDialog(null, o, String.valueOf(title), display);
		
	}
	
	public void msgError(Object o) {
		
		JOptionPane.showMessageDialog(null, o, "Error", 0);
		
	}
	
	public void msgError(Object o, Object title) {
		
		JOptionPane.showMessageDialog(null, o, String.valueOf(title), 0);
		
	}
	
	public int msgOption(Object o, Object title, String[] options) {
		
		return JOptionPane.showOptionDialog(null, o, String.valueOf(title),
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);
		
	}
	
	public int msgYesNo(Object o, Object title) {
		
		return JOptionPane.showConfirmDialog(null, o, String.valueOf(title),
				JOptionPane.YES_NO_OPTION);
		
	}
	
	public String msgInput(Object a) {
		
		return JOptionPane.showInputDialog(null, a);
		
	}
	
	public String msgInput(Object a, Object title) {
		
		return JOptionPane.showInputDialog(null, a, String.valueOf(title), 3);
		
	}
	
	
	// File manager and routing
	
	public void createFolder(String route, String folderName) {
		
		File logs = new File(route + "\\" + folderName);
		if (!logs.exists()) logs.mkdirs();
		
	}
	
	public void createFile(String route, String name, String write) {
		
		String file = route + "\\" + name;
		try {
			new File(file);
		    FileWriter myWriter = new FileWriter(file);
			myWriter.write(write);
		    myWriter.close();
		} catch (IOException e1) {}
		
	}
	
	public void writeFile(String route, String text) {
		
		try {  
			
			File file = new File(route);
			Desktop desktop = Desktop.getDesktop();
			if(file.exists()) {
				desktop.open(file);
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(text);
				bw.close();
				
			}
			
		}  
		
		catch(Exception e) {} 
		
	}
	
	public void deleteFile(String route) {
		
		File directoryToDelete = new File(route);
		try {
			
			FileUtils.forceDelete(directoryToDelete);
			
		} catch (IOException e) {}
		
	}
	
	public String readFile(String route) {
		
		try {
			
			try (BufferedReader br = new BufferedReader(new FileReader(route))) {
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
	
	public void openFile(String route) {
		
		try {  
			
			File file = new File(route);
			Desktop desktop = Desktop.getDesktop();
			if(file.exists())
				desktop.open(file);
		}  
		
		catch(Exception e) {}  
		
	}
	
	public void openProcess(String route) {
		
        try {
        	ProcessBuilder builder = new ProcessBuilder(route);
			builder.start();
		} catch (IOException e) {}
	
	}
	
	public void openProcessByTime(String route, int ms) {
		
        try {
        	
            ProcessBuilder builder = new ProcessBuilder(route);
            Process pro = builder.start();
			Thread.sleep(ms);
			pro.destroy();
	        
		} catch (InterruptedException | IOException e) {}
		
	}
	
	public String selectFile(String route) {
		
		JFileChooser fileChooser = new JFileChooser(route);
		int i = fileChooser.showOpenDialog(null);
		if(i == JFileChooser.APPROVE_OPTION) {
			
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			return file.getPath();
			
		}
		
		return null;
		
	}
	
	public String selectFile(String route, String ext) {
		
		JFileChooser fileChooser = new JFileChooser(route);
		int i = fileChooser.showOpenDialog(null);
		if(i == JFileChooser.APPROVE_OPTION && !fileChooser.getName().endsWith(ext)) {
			
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			return file.getPath();
			
		}
		
		return null;
		
	}
	
	public String selectFolder(String route) {
		

		UIManager.put("FileChooser.saveButtonText","Accept");

		JFileChooser f = new JFileChooser(route);
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);
        if(f.getSelectedFile() != null) {
			
        	 return f.getSelectedFile().getAbsolutePath();
			
		}

        return route;
		
	}
	
	public String getDesktopRoute() {
		
		File desk = FileSystemView.getFileSystemView().getHomeDirectory();
		return desk.getAbsolutePath();
		
	}
	
	public String getProjectLocation() {
		
		File file = new File("");
		return file.getAbsolutePath();
		
	}
	
	public String getTempDir() {
		
		return System.getProperty("java.io.tmpdir");
		
	}
	
	public void webBrowse(String link) {
		
		try {
			
			Desktop.getDesktop().browse(new URI(link));
			
		} catch (IOException | URISyntaxException e) {}
		
	}
	
	public void addSound(String route) {
		
		try {
			File file = new File(route);
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			print(e);
		}
		
	}
	
	// Get Time
	
	public String getMiliseconds(int lim) {
		
		if(lim > 3) lim = 3;
		String[] sArr = splitEvery(new SimpleDateFormat("SSS").format(Calendar.getInstance().getTime()), 1);
		String s = sArr[0];
		for(int i = 1; i < lim; i++)
		 s += sArr[i];
		return s;	
		
	}
	
	public String getMiliseconds() {
		
		return new SimpleDateFormat("SSS").format(Calendar.getInstance().getTime());
		
	}
	
	public String getSeconds() {
		
		return new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
		
	}
	
	public String getMinutes() {
		
		return new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
		
	}
	
	public String getHours() {
		
		return new SimpleDateFormat("hh").format(Calendar.getInstance().getTime());
		
	}
	
	public String getMonth() {
		
		return new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
		
	}
	
	public String getShortYear() {
		
		return new SimpleDateFormat("YY").format(Calendar.getInstance().getTime());
		
	}
	
	public String getYear() {
		
		return new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime());
		
	}
	
	public String getTimeByPattern(String pattern) {
		
		return new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime());
		
	}
	
	// Get Network Address
	
	public String getNetworkIP() {
		
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
	
	public String getMACAddress() {
		
		return callCMD("ipconfig /all", "Physical Address");
		
	}
	
	public String getLocalUserIPv4() {
		
		String s = callCMD("ipconfig /all", "IPv4 Address");
		return s.replace("(Preferred)", "");
		
	}
	
	public String getLocalUserIPv6() {
		
		String s = callCMD("ipconfig /all", "Link-local IPv6 Address");
		return s.replace("(Preferred)", "");
		
	}
	
	public String getGateway() {
		
		return callCMD("ipconfig /all", "Default Gateway");
		
	}
	
	public boolean isDHCPEnabled() {
		
		String s = callCMD("ipconfig /all", "DHCP Enabled");
		if (s.equals("Yes")) return true;
		return false;
		
	}
	
	
	private String callCMD(String cmd, String arg) {
		
		if (Desktop.isDesktopSupported()) {
			
            try {
            	
                Process process = Runtime.getRuntime().exec(cmd);

                try (BufferedReader bufferedReader = new BufferedReader(
                		
                        new InputStreamReader(process.getInputStream()))) {

                    String line;
                    
                    while ((line = bufferedReader.readLine()) != null) {
                    	
                        if (line.trim().startsWith(arg)) 
                            return line.substring(line.indexOf(":") + 1).trim();

                    }
                    
                }
                
            } catch (Exception e) {}
            
        }
		
		return null;
		
	}
	
	public String getIPv4Prefix() {
		
		return  getIPPrefix(0);
		
	}
	
	public String getIPv6Prefix() {
		
		return getIPPrefix(1);
		
	}
	
	private String getIPPrefix(int i) {
		
		InetAddress localHost;
		try {
			
			localHost = Inet4Address.getLocalHost();
			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
			return String.valueOf(networkInterface.getInterfaceAddresses().get(i).getNetworkPrefixLength());
			
		} catch (UnknownHostException e) {
			
		} catch (SocketException e) {}
		
		return null;
		
	}
	
	public Inet4Address getIPv4From(InetAddress[] IPs) {
		
	    for (InetAddress IP : IPs)
	    	if (IP instanceof Inet4Address)
	        	return (Inet4Address) IP;
	    return null;
	}
	
	public Inet6Address getIPv6From(InetAddress[] IPs) {
		
	    for (InetAddress IP : IPs)
	        if (IP instanceof Inet6Address)
	        	return (Inet6Address) IP;
	    return null;
	}
	
	// Security
	
	public String encryptBase64(String input) {

        return Base64.getEncoder().encodeToString(input.getBytes());
        
    }

    public String decryptBase64(String input) {
        
    	byte[] bArray = Base64.getDecoder().decode(input);
    	return new String(bArray);
    	
    }
	
}
