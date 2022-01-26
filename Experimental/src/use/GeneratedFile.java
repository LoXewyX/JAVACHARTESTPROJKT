package use;

public class GeneratedFile {

	private Simplify sf = new Simplify();
	
	public GeneratedFile(String ipBlock, String dir, String captureTime, String timer, String[] pswrd) {
		
		
		String txt = "Don\'t panic, this is only a test\n"
		+ "Nothing from here is gathered\n"
		+ "Last activity at " + captureTime
		+ "\nUp time captured: " + timer
		+ "\nPasswords: " + passwordRange(pswrd)
		+ "\nNet IP: " + ipBlock
		+ "\nLocal IPv4: " + sf.getLocalUserIPv4() + "/" + sf.getIPv4Prefix()
		+ "\nLocal IPv6: " + sf.getLocalUserIPv6() + "/" + sf.getIPv6Prefix()
		+ "\nGateway: " + sf.getGateway()
		+ "\nMAC Address: " + sf.getMACAddress()
		+ "\nDHCP " + sf.boolText(sf.isDHCPEnabled(), sf.ENABLED_DISABLED);
		
		String fileName = "log.txt";
		
		sf.createFolder(dir, "logs");
		sf.createFile(dir + "\\logs", fileName, txt);
		sf.openFile(dir + "\\logs\\" + fileName);
		
	}
	
	private String passwordRange(String pswrd[]) {
		
		String s = "";
		
		for(int i = pswrd.length; i > 0; i--) {
			
			if(i != pswrd.length) s +=  " - ";
			s += "\"" + pswrd[i - 1] + "\"";
			
		}
		
		return s;
		
	}
	
}
