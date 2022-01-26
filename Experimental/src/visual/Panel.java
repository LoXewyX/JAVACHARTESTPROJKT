package visual;

import use.GeneratedFile;
import use.List;
import use.Menu;
import use.Simplify;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Panel {
	
	public static int num;
	public int page = 0, pagemax = 1;

	private Simplify sf = new Simplify();
	
	String ipBlock = null;
	private String[] txt = new String[List.txt.length];
	private final String password = "admin";
	private String currentVersion;
	private final String creator = "LoXewyX", pswrd[] = new String[3];
	@SuppressWarnings("unused") private int realSec = 0;
	private final int waitTime = 5;
	private int FILE_KEY = KeyEvent.VK_F1,
		UNLOCK_KEY = KeyEvent.VK_F2,
		COLOR_KEY = KeyEvent.VK_F3,
		DEV_KEY = KeyEvent.VK_F4,
		LEFT_KEY = KeyEvent.VK_LEFT,
		RIGHT_KEY = KeyEvent.VK_RIGHT,
		ESCAPE_KEY = KeyEvent.VK_ESCAPE,
		attempts = 3,
		min, m,
		sec, s = waitTime,
		hour;
	private long ms =  System.currentTimeMillis();
	private String timer = String.format("%02d:%02d:%02d", hour, min, sec),
		captureTime,
		dir = sf.getTempDir();
	private boolean isAdmin = false,
		devArgs = false,
		colorFrame = false,
		isBlocked = false,
		isPswrdHidden = true,
		//in dev for 1.2.4
		left = false,
	   	up = false,
	    down = false,
	    right = false;
	
	private JFrame jf = new JFrame();
	private JPanel jp_text = new JPanel(),
		jp_buttons = new JPanel(),
		jp_nxt = new JPanel(),
		jp_number = new JPanel(),
		jp_password = new JPanel();
	private JLayeredPane jlpane = new JLayeredPane();
	private JButton[] button = new JButton[List.txt.length];
	private JButton buttonBack,
		buttonNext,
		bpass,
		bshow;
	private JLabel jlab_title = new JLabel(),
		jlab_page = new JLabel("Page " + String.valueOf(page) + " / " + pagemax),
		jlab_dev_args = new JLabel(),
		jlab_dev_args_ext = new JLabel(), 
		jlab_dev = new JLabel("<html>by " + creator + "<br/>2022 - "+ sf.getYear() + "</html>"),
		jlab_shadow = new JLabel(),
		jlab_shadow_ext = new JLabel();
	private JPasswordField jtf_pass = new JPasswordField(null, 24);
	
	public void load() {
		
		currentVersion = "v" + sf.readFile(sf.getProjectLocation() + "\\src\\files\\version");
		
		jf.addKeyListener(keyListen());
		jtf_pass.addActionListener(checkPassword());
		jf.addMouseListener(mouseListen());
		jf.requestFocusInWindow();
		
		// Frame load
		
	    jf.setTitle("Experimental " + currentVersion);
	    
	    jf.setSize(750, 500);
	    jf.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                titleAlign(jf);
            }

        });
	    jf.addWindowListener(new WindowListener() { 
	        @Override public void windowOpened(WindowEvent e) {} 
	        @Override public void windowClosing(WindowEvent e) {
	        	
	        	sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Interference.wav");
	    		sf.deleteFile(dir + "\\logs\\");
	        } 
	        @Override public void windowClosed(WindowEvent e) {} 
	        @Override public void windowIconified(WindowEvent e) {} 
	        @Override public void windowDeiconified(WindowEvent e) {} 
	        @Override public void windowActivated(WindowEvent e) {} 
	        @Override public void windowDeactivated(WindowEvent e) {} 
	    }); 
	    jf.setIconImage(
	    		Toolkit.getDefaultToolkit().
	    		getImage(Panel.class.getResource("/img/favicon.png"))
	    ); 
	    jf.setResizable(false);
	    jf.setVisible(true);
	    jf.setLocationRelativeTo(null);
	    
	    jf.add(jlpane);
	    
	    // Panel load
	    
	    jlpane.setBounds(0, 0, 750, 500);
		
	    jlab_title.setText("Examples with char");
	    
		jp_text.setBounds(0, 50, 750, 50);
		jp_text.setFont(jp_text.getFont().deriveFont(64.0f));
		jp_text.setVisible(true);
		jp_text.add(jlab_title);
		
		jp_buttons.setBounds(50, 100, 625, 300);
		
		bMenu(); bNext();
		
		jp_nxt.setBounds(0, 410, 750, 100);
		jp_nxt.setVisible(true);
		
		jp_number.setBounds(475, 417, 100, 25);
		jp_number.setVisible(true);
		jp_number.add(jlab_page);
		
		jp_nxt.add(buttonBack);
		jp_nxt.add(buttonNext);
		
		jtf_pass.setSize(50, 0);
		jtf_pass.setEchoChar('\u25CF');
		jtf_pass.setVisible(true);
		jtf_pass.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Select.wav");
				
			}
			@Override
			public void focusLost(FocusEvent e) {}
	        
	    });
		bshow.setVisible(true);
		bshow.setFocusable(false);
		bpass.setVisible(true);
		bpass.setFocusable(false);
		
		jp_password.setBounds(0, 400, 250, 60);
		jp_password.setVisible(false);
		jp_password.add(jtf_pass);
		jp_password.add(bshow);
		jp_password.add(bpass);
		
		jlab_dev.setBounds(650, 390, 150, 75);
		
		jlab_dev_args.setBounds(5, 5, 150, 90);
		jlab_shadow.setBounds(6, 6, 150, 90);
		jlab_shadow.setForeground(Color.GREEN);
		
		jlab_dev_args_ext.setBounds(500, 5, 250, 100);
		jlab_shadow_ext.setBounds(501, 6, 250, 100);
		jlab_shadow_ext.setForeground(Color.GREEN);
		
		jlpane.add(jp_text);
		jlpane.add(jp_buttons);
		jlpane.add(jp_nxt);
		jlpane.add(jp_number, 1, 0);
		jlpane.add(jp_password, 1, 0);
		jlpane.add(jlab_dev_args, 3, 0);
		jlpane.add(jlab_dev_args_ext, 3, 0);
		jlpane.add(jlab_dev, 3, 0);
		jlpane.add(jlab_shadow, 2, 0);
		jlpane.add(jlab_shadow_ext, 2, 0);
			
		timeStart();
		
	}

	private void testWindow(int num) {
        
        Menu m = new Menu();
        m.start(num);
		
	}
	
	private void titleAlign(JFrame jf) {

        Font font = jf.getFont();

        String currentTitle = jf.getTitle().trim();
        FontMetrics fm = jf.getFontMetrics(font);
        int frameWidth = jf.getWidth();
        int titleWidth = fm.stringWidth(currentTitle);
        int spaceWidth = fm.stringWidth(" ");
        int centerPos = (frameWidth / 2) - (titleWidth / 2);
        int spaceCount = centerPos / spaceWidth;
        String pad = "";
        pad = String.format("%" + (spaceCount - 14) + "s", pad);
        jf.setTitle(pad + currentTitle);

    }
	
	private void bMenu() {
		
		this.txt = List.txt;
		
		for (int i = 0; i < txt.length; i++) {
			button[i] = new JButton(txt[i]);
			buttonProps(button[i]);
			button[i].setActionCommand(String.valueOf(i));
			button[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
					sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Confirm.wav");
					num = Integer.parseInt(e.getActionCommand());
					testWindow(num);
					
				}
			});
			jp_buttons.add(button[i]);
		}
		
	}
	
	private void buttonProps(JButton jb) {
		
		jb.setBackground(Color.WHITE);
		jb.setForeground(Color.darkGray);
		jb.setFocusable(false);
		jb.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	if(jb.isEnabled())
		    		sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Select.wav");
		    }
		});
		
	}
	
	private void bNext() {
		
		buttonBack = new JButton("Back");
		buttonProps(buttonBack);
		buttonBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
		    	sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Confirm.wav");
				page -= 1;
				pageUpdate();
				
			}
		});
		
		buttonNext = new JButton("Next");
		buttonProps(buttonNext);
		buttonNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Confirm.wav");
				page += 1;
				pageUpdate();
				
			}
		});
		
		bpass = new JButton("Check");
		buttonProps(bpass);
		bpass.addActionListener(checkPassword());
		
		bshow = new JButton("Reveal");
		buttonProps(bshow);
		bshow.addActionListener(passwordReveal());
		
	}
	
	private void pageUpdate() {
		
		if(page < 0) 
			page = 0; /*in case of errors*/
		else if(page == 0)
			buttonBack.setEnabled(false);
		else
			buttonBack.setEnabled(true);
		if(page > pagemax)
			page = pagemax; /*in case of errors*/
		else if(page == pagemax)
			buttonNext.setEnabled(false);
		else
			buttonNext.setEnabled(true);
		
		jlab_page.setText("Page " + String.valueOf(page) + " / " + pagemax);
		
		if(page == 0) {
			
			jp_buttons.setVisible(true);
			jlab_title.setText("Examples with char");
			
		}
			
		else {
			
			jp_buttons.setVisible(false);
			jlab_title.setText("Other example");
			jf.addKeyListener(new KeyListener() {
	            @Override
	            public void keyTyped(KeyEvent e) {}
	            @Override
	            public void keyReleased(KeyEvent e) {
	                
	            }
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
	                if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
	                if (e.getKeyCode() == KeyEvent.VK_UP) up = true;
	                if (e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
	            }
	        });
			
		}
		
		if(colorFrame && devArgs) {
			
			jf.getContentPane().setBackground(Color.darkGray);
			jp_text.setBackground(Color.RED);
			jp_buttons.setBackground(Color.GREEN);
			jp_nxt.setBackground(Color.BLUE);
			jp_number.setBackground(Color.ORANGE);
			jp_password.setBackground(Color.ORANGE);
			
			jlab_dev_args.setForeground(Color.ORANGE);
			jlab_dev_args_ext.setForeground(Color.ORANGE);
			jlab_shadow.setForeground(null);
			jlab_shadow_ext.setForeground(null);
			jlab_dev.setForeground(Color.ORANGE);
			
		} else {
			
			jf.getContentPane().setBackground(null);
			jp_text.setBackground(null);
			jp_buttons.setBackground(null);
			jp_nxt.setBackground(null);
			jp_number.setBackground(null);
			jp_password.setBackground(null);
			
			jlab_dev_args.setForeground(Color.GREEN);
			jlab_dev_args_ext.setForeground(Color.GREEN);
			jlab_shadow.setForeground(null);
			jlab_shadow_ext.setForeground(null);
			jlab_dev.setForeground(null);
			
		}
		
		if(isAdmin && devArgs) {
			
			bpass.setText("Exit");
			jtf_pass.setEnabled(false);
			jtf_pass.setText(null);
			bshow.setVisible(false);
			attempts = 3;
			
		} else {
			
			bshow.setVisible(true);
			if(isBlocked) {
				
				bpass.setEnabled(false);
				bshow.setEnabled(false);
				jtf_pass.setEnabled(false);
				jtf_pass.setText(null);
				
			} else {
				
				bpass.setText("Check");
				jtf_pass.setEnabled(true);
				bshow.setEnabled(true);
				bpass.setEnabled(true);
				
			}
			
		}
		
		String s = 	"<html>"
				+ "[ESC] To exit the program"
				+ "<br/>[F1] Select save folder"
				+ "<br/>[F2] Reset penalty time"
				+ "<br/>[F3] Colorise frames"
				+ "<br/>[F4] Developer arguments"
				+ "<br/>[Ctrl] + [KEY] + [NEW_KEY]"
				+ "</html>",
				sExt = "<html>"
				+ "Up time: " + timer
				+ "<br/>Admin is " + sf.boolText(isAdmin, sf.ENABLED_DISABLED)
				+ "<br/>Attempts left: " + attempts
				+ "<br/>You are blocked " + sf.boolText(isBlocked, sf.YES_NO)
				+ "<br/>Is colorized " + sf.boolText(colorFrame, sf.YES_NO)
				+ "<br/>Current save folder:"
				+ "<br/>" + dir
				+ "</html>";
		
		if(devArgs) {
			
			jlab_dev_args.setVisible(true);
			
			jlab_dev.setVisible(true);
			jlab_dev_args.setText(s);
			jlab_dev_args_ext.setText(sExt);
			jlab_dev_args_ext.setVisible(true);
			jlab_shadow.setText(s);
			jlab_shadow_ext.setText(sExt);
			jlab_shadow_ext.setVisible(true);
			jlab_shadow.setVisible(true);
			
		} else {
			
			jlab_dev.setVisible(false);
			jlab_dev_args.setVisible(false);
			jlab_dev_args_ext.setVisible(false);
			jlab_shadow.setVisible(false);
			jlab_shadow_ext.setVisible(false);
			
		}
		
	}
	
	private KeyListener keyListen() {
		
		return new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
									
				if (e.getKeyCode() == FILE_KEY && devArgs) {
					
					String oldDir = dir;
					dir = sf.selectFolder(dir);
					if(oldDir != dir)
						sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Enable.wav");
					
				}
				
				if (e.getKeyCode() == UNLOCK_KEY && devArgs) {
					
					if(isBlocked) {
						
						sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Enable.wav");
						isBlocked = false;
						pageUpdate();
						
					}
					
					attempts = 3;
					
				}
				
				if (e.getKeyCode() == COLOR_KEY && devArgs) {
					
					if(colorFrame)
						colorFrame = false;
						
					else {
						
						colorFrame = true;
						sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Enable.wav");
						
					}
						

					pageUpdate();
					
				}
				
				if (e.getKeyCode() == DEV_KEY) {
					
					if(devArgs) {
						
						devArgs = false;
						jp_password.setVisible(false);
						
					} else {
						
						sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Enable.wav");
						devArgs = true;
						jp_password.setVisible(true);
						
					}
					
					pageUpdate();
				}
				
				if (e.getKeyCode() == LEFT_KEY) {
					
					page -= 1;
					pageUpdate();
					
				}
				
				if (e.getKeyCode() == RIGHT_KEY) {
					
					page += 1;
					pageUpdate();
					
				}
				
				if (e.getKeyCode() == ESCAPE_KEY) {
					
						try {
							sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Interference.wav");
							jf.setVisible(false);
							Thread.sleep(1000);
						} catch (InterruptedException ex) {}
						
					System.exit(0);
					
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				if(page == 1) {
					
					if (e.getKeyCode() == KeyEvent.VK_LEFT) 	left = false;
	                if (e.getKeyCode() == KeyEvent.VK_RIGHT) 	right = false;
	                if (e.getKeyCode() == KeyEvent.VK_UP) 		up = false;
	                if (e.getKeyCode() == KeyEvent.VK_DOWN) 	down = false;
					
				}
				
			}
		};
	
	}
	
	private MouseListener mouseListen() {
		
		return new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e) {
					
				jf.requestFocus();
				
			}
			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseReleased(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}

        };
		
	}
	
	// Start with password diagnose
	private ActionListener checkPassword() {
		
		return new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				String s = String.valueOf(jtf_pass.getPassword());
				if(isAdmin) {
					
					sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Select-Drop.wav");
					isAdmin = false;
					
				}
				else {
					if(s.equals(password)) {
						
						isAdmin = true;
						sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Get.wav");
						sf.msg("Admin mode enabled");
						pageUpdate();
						
					} else if(s.isBlank()) {
						
						sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Error.wav");
						sf.msgError("You must enter a password before submit");
						
					} else {
						
						if(attempts <= 0 && !pswrd[0].equals(s)) {
							
							sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Alert.wav");
							isBlocked = true;
							ipBlock = sf.getNetworkIP();
							captureTime = sf.getTimeByPattern("HH:mm:ss");
							new GeneratedFile(ipBlock, dir, captureTime, timer, pswrd);
							Arrays.fill(pswrd, null);
							
						} else {
							
							try {
								
								if(pswrd[attempts].equals(s)) {
									
									sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Error.wav");
									sf.msgError("You had written this password before. Try another new one");
									
								} else {
									sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Error.wav");
									sf.msgError("Password does not match! You have " +
											attempts-- + " attempts left");
									pswrd[attempts] = s;
									
								}
								
							} catch(Exception ex) {
								
								sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Error.wav");
								sf.msgError("Password does not match! You have " +
										attempts + " attempts left");
								pswrd[--attempts] = s;
								
							};
							
						}
//						sf.println(sf.printArray(pswrd));
					}
					
				}
				
				pageUpdate();
				
			}
		};
		
	}
	
	private ActionListener passwordReveal() {
		
		return new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				sf.addSound(sf.getProjectLocation() + "\\src\\sounds\\Confirm.wav");
				
				if(isPswrdHidden) {
					
					jtf_pass.setEchoChar('\u0000');
					isPswrdHidden = false;
					
				} else {
					
					jtf_pass.setEchoChar('\u25CF');
					isPswrdHidden = true;
					
				}
				
				if(isPswrdHidden)  bshow.setText("Reveal");
				else bshow.setText("Hide");
				
			}
				
		};
		
	}
	
	private void timeStart() {

		// Spaghetti but functional
		if(ms > 1000) sec++;
		if(sec >= 60){
			min++;
			sec = 0;
			if(min >= 60){
				hour++;
				min = 0;
			}
		}
		try { Thread.sleep(1000); }
		catch (InterruptedException e) {}
		pageUpdate();
		timer = String.format("%02d:%02d:%02d", hour, min, sec);
		if(isBlocked) {
			
			if(ms > 1000) s--;
			if (s >= 60) {
				m += s / 60;
				s %= 60;
			}
			bpass.setText(String.format("%02d:%02d\n",  m, s));
			
			if(s <= 0) {
				
				s = waitTime;
				attempts = 3;
				isBlocked = false;
				
			}
			
		}
		timeStart();
	}
	
}
