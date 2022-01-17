package visual;

import use.List;
import use.Menu;
import use.Simplify;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Panel {
	
	public static int num;
	public int page = 0, pagemax = 1;

	private Simplify sf = new Simplify();
	
	String ipBlock = null;
	private String[] txt = new String[List.txt.length];
	private final String password = "password";
	private final int waitTime = 120;
	@SuppressWarnings("unused") private int realSec = 0;
	private int attempts = 3, min, sec, newsec, h,
		newRealSec = 0, oldSec = 0, newoldSec;
	private String timer, reg, revtimer;
    private long timeFlow = System.currentTimeMillis();
	
	private boolean isAdmin = false, devArgs = false,
			colorFrame = false, isBlocked = false;
	private JFrame jf = new JFrame();
	private JPanel jp_text = new JPanel(),
		jp_buttons = new JPanel(),
		jp_nxt = new JPanel(),
		jp_number = new JPanel(),
		jp_password = new JPanel();
	private JLayeredPane jlpane = new JLayeredPane();
	private JButton[] button = new JButton[List.txt.length];
	private JButton buttonBack, buttonNext, bpass;
	private JLabel jlab_title = new JLabel(),
		jlab_page = new JLabel("Page " + String.valueOf(page) + " / " + pagemax),
		jlab_dev_args = new JLabel();
	private JTextField jtf_pass = new JTextField(null, 18);
	
	public Panel() {
		
		txt = List.txt;
		
	}
	
	public void start() {
		
		jf.addKeyListener(keyListen());
		jtf_pass.addActionListener(checkPassword());
		jf.addMouseListener(mouseListen());
		jf.requestFocusInWindow();
		
		// Frame load
		
	    jf.setTitle("Experimental by Luis Ruiz");
	    jf.setSize(750, 500);
	    jf.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                titleAlign(jf);
            }

        });
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		jp_text.setVisible(true);
		jp_text.add(jlab_title);
		
		jp_buttons.setBounds(50, 100, 625, 300);
		
		bMenu();
		
		bNext();
		
		jp_nxt.setBounds(0, 410, 750, 100);
		jp_nxt.setVisible(true);
		
		jp_number.setBounds(500, 417, 200, 25);
		jp_number.setVisible(true);
		jp_number.add(jlab_page);
		
		jp_nxt.add(buttonBack);
		jp_nxt.add(buttonNext);
		
		jtf_pass.setSize(50, 0);
		jtf_pass.setVisible(true);
		bpass.setVisible(true);
		bpass.setFocusable(false);
		
		jp_password.setBounds(50, 382, 250, 60);
		jp_password.setVisible(false);
		jp_password.add(jtf_pass);
		jp_password.add(bpass);
		
		jlab_dev_args.setBounds(550, 0, 150, 100);
		jlab_dev_args.setVisible(true);
		
		jlpane.add(jp_text);
		jlpane.add(jp_buttons);
		jlpane.add(jp_nxt);
		jlpane.add(jp_number, 1, 0);
		jlpane.add(jp_password, 1, 0);
		jlpane.add(jlab_dev_args, 2, 0);
		
		pageUpdate();
		loop();
		
	}

	private void testWindow(int num) {
        
        // start program
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
		
		for (int i = 0; i < txt.length; i++) {
			button[i] = new JButton(txt[i]);
			button[i].setBackground(Color.WHITE);
			button[i].setForeground(Color.GRAY);
			button[i].setActionCommand(String.valueOf(i));
			button[i].setFocusable(false);
			button[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
					num = Integer.parseInt(e.getActionCommand());
//					sf.msg(num);
					testWindow(num);
					
				}
			});
			jp_buttons.add(button[i]);
		}
		
	}
	
	private void bNext() {
		
		buttonBack = new JButton("Back");
		buttonBack.setBackground(Color.WHITE);
		buttonBack.setForeground(Color.BLACK);
		buttonBack.setFocusable(false);
		buttonBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				page -= 1;
				pageUpdate();
				
			}
		});
		
		buttonNext = new JButton("Next");
		buttonNext.setBackground(Color.WHITE);
		buttonNext.setForeground(Color.BLACK);
		buttonNext.setFocusable(false);
		buttonNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				page += 1;
				pageUpdate();
				
			}
		});
		
		bpass = new JButton("Check");
		bpass.setBackground(Color.WHITE);
		bpass.setForeground(Color.BLACK);
		bpass.addActionListener(checkPassword());
		
	}
	
	private void update() {
		
		if(colorFrame) {
			
			jf.getContentPane().setBackground(Color.darkGray);
			jp_text.setBackground(Color.RED);
			jp_buttons.setBackground(Color.GREEN);
			jp_nxt.setBackground(Color.BLUE);
			jp_number.setBackground(Color.ORANGE);
			jp_password.setBackground(Color.ORANGE);
			
		} else {
			
			jf.getContentPane().setBackground(null);
			jp_text.setBackground(null);
			jp_buttons.setBackground(null);
			jp_nxt.setBackground(null);
			jp_number.setBackground(null);
			jp_password.setBackground(null);
			
		}
		
		if(isAdmin) {
			
			if(reg != null)	sf.msg(reg);
			bpass.setText("Exit");
			jtf_pass.setEnabled(false);
			jtf_pass.setText(null);
			reg = null;
			attempts = 3;
			
		}
		
		else {
			
			if(isBlocked) {
				
				bpass.setEnabled(false);
				jtf_pass.setEnabled(false);
				jtf_pass.setText(null);
				attempts = 3;
				
			} else {
				
				bpass.setText("Check");
				jtf_pass.setEnabled(true);
				bpass.setEnabled(true);
				
			}
			
		}
		
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
			
		}
		
	}
	
	private KeyListener keyListen() {
		
		return new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_F3) {
					
					if(colorFrame)
						colorFrame = false;
						
					else
						colorFrame = true;

					update();
					
				}
				
				if (e.getKeyCode() == KeyEvent.VK_F4) {
					
					if(devArgs)
						devArgs = false;
						
					else
						devArgs = true;
					update();
					
				}
				
				if (e.getKeyCode() == KeyEvent.VK_F12) {
					
					if(jp_password.isVisible()) {
						
						isAdmin = false;
						jp_password.setVisible(false);
						
					} else {
						
						jp_password.setVisible(true);
					}
					
				}
				
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					
					page -= 1;
					pageUpdate();
					
				}
				
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					
					page += 1;
					pageUpdate();
					
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {}
		};
	
	}
	
	private MouseListener mouseListen() {
		
		return new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e) {
					
				jf.requestFocus();
				
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

        };
		
	}
	
	private ActionListener checkPassword() {
		
		return new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				String s = jtf_pass.getText();
				if(isAdmin) isAdmin = false;
				else {
					if(s.equals(password)) {
						
						isAdmin = true;
						sf.msg("Admin mode enabled");
						update();
						
					} else {
						
						URL ipRead;
						BufferedReader br;
						try {
							
							ipRead = new URL("http://checkip.amazonaws.com");
							br = new BufferedReader(
									new InputStreamReader(
											ipRead.openStream()
					                )
							);
							ipBlock = br.readLine();
							
						} catch (Exception e2) {}
						
						reg = "Last attempted to access was at\n" + timer
							+ "With the password \n\""+ s + "\"\n"
							+ "IP: " + ipBlock;
						attempts -= 1;
						if(attempts <= 0)
							isBlocked = true;
						else
							sf.msgError("Password does not match! You have " +
									attempts + " attempts left");
						
					}
					
				}
				update();
			}
		};
		
	}
	
	private void loop() {
		
		try {
			
			if(devArgs) {
				
				jlab_dev_args.setText("<html>"
						+ "Up time: " + timer
						+ "<br/>(F3) colorFrame = " + colorFrame
						+ "<br/>(F4) devArgs"
						+ "<br/>(F12) isAdmin = " + isAdmin
						+ "<br/>isBlocked = " + isBlocked
						+ "</html>");
			} else
				jlab_dev_args.setText(null);
			
		} catch(Exception e) {}
		
		try {
			
			TimeUnit.MILLISECONDS.sleep(10);
			
		} catch (InterruptedException e) {}
		
		sec = (int)((System.currentTimeMillis() - timeFlow) / 1000);
		if(oldSec != sec) realSec++;
		oldSec = sec;
		
		if (sec == 60) {
			
			if(sec % 60 == 0) min++;
		    sec = 0;
		    timeFlow = System.currentTimeMillis();
		    
		}
		
		if(min == 60) {
			
			if(min % 60 == 0) h++;
			sec = 0;
			
		}

		timer = String.format("%02d:%02d:%02d\n", h, min, sec);
		
		if(isBlocked) bannedTime();
		
		loop();
		
	}
	
	private void bannedTime() {
		
		newsec = (int)((System.currentTimeMillis() - timeFlow) / 1000);
		if (newoldSec != newsec) newRealSec++;
		newoldSec = newsec;
		
		int m = 0, s;
		s = waitTime - newRealSec;
		if (s >= 60) {
			
			m += s / 60;
			s %= 60;
			
		}
		
		revtimer = String.format("%02d:%02d\n",  m, s);
		bpass.setText(revtimer);
		
		if(newRealSec == waitTime) {
			
			isBlocked = false;
			
		}
		
		update();
		
	}
	
}
