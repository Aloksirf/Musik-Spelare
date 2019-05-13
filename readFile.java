
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class musicPlayer extends JFrame implements ActionListener, KeyListener {

	private Clip musicFile = null;
	private JFrame jf;
	private JTextField jfText;
	private int counter = 0;

	/**
	 * Constructor
	 */
	public musicPlayer() {
		super("musicPlayer");
		setSize(500, 122);
		setAlwaysOnTop(true);
		jf = new JFrame("MusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout()); // Så knapparna inte hamnar på varandra! Annan Layout!
		setLocation(200, 200); // Var fönstret ska börja (x,y)
		setResizable(false);

		// Textbox, den kan bara ladda .wav-filer
		// Man behöver bara skriva "test", den lägger till .wav själv.
		jfText = new JTextField("Enter the name of your music file here.", 30);

		// Knappar
		JButton b1 = new JButton("Play");
		JButton b2 = new JButton("Pause/Unpause");
		JButton b3 = new JButton("Stop");
		getContentPane().add(jfText);

		// Gör så knapparna reagerar på knapptryck
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		// Används endast när vi trycker ENTER för att starta en låt.
		jfText.addKeyListener(this);

		// Lägger till knapparna i JFrame
		add(b1, BorderLayout.SOUTH);
		add(b2, BorderLayout.SOUTH);
		add(b3, BorderLayout.SOUTH);

		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu m = new JMenu("File");
		JMenu m2 = new JMenu("Help");
		menuBar.add(m);
		menuBar.add(m2);
		JMenuItem item1 = new JMenuItem("Open Sound");
		JMenuItem item2 = new JMenuItem("Exit");
		JMenuItem item3 = new JMenuItem("About");
		m.add(item1).addActionListener(this);
		m.add(item2).addActionListener(this);
		m2.add(item3).addActionListener(this);
		setJMenuBar(menuBar);
		//pack();   //Allt på samma rad, ser också till att allt du lagt till kommer med i rutan!!
	}

	/**
	 * Starting to play the sound on your computer.
	 * 
	 * @param file The name of the file.
	 */
	public void playSound(String file) {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(file));
			musicFile = AudioSystem.getClip();
			musicFile.open(audio);
			musicFile.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Opens up the current directory for you to choose a .wav-file. Start playing
	 * the sound once open.
	 */
	public void openSound() {
		JFileChooser choice = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("wav", "WAV");
		choice.setFileFilter(filter);
		choice.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = choice.showOpenDialog(jfText);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = choice.getSelectedFile();
			playSound(selectedFile.getName());
			String fName = selectedFile.getName().replace(".wav", "");
			jfText.setText(fName);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Play") {
			counter = 0;
			playSound(jfText.getText() + ".wav");
			jfText.setText(jfText.getText());
		} else if (e.getActionCommand() == "Stop") {
			counter = 1;
			if (musicFile == null) {
				JOptionPane.showMessageDialog(jf, "No Audio-file loaded.");
			} else {
				if (!musicFile.isRunning()) {
					JOptionPane.showMessageDialog(jf, "No Audio running.");
				} else {
					musicFile.stop();
					musicFile = null;	// ELLER använd musicFile.close() , detta ändrar hur vi använder oss av Pause/Unpause. (Om en stoppad fil ska kunna startas igen eller ej!)
					
				}
			}
		} else if (e.getActionCommand() == "Open Sound") {
			openSound();
		} else if (e.getActionCommand() == "Pause/Unpause") {
			if (musicFile == null) {
				JOptionPane.showMessageDialog(jf, "No Audio-file loaded.");
			} else {
				counter++;
				long saveClipTime = musicFile.getMicrosecondPosition();
				if (counter % 2 == 0) {
					musicFile.setMicrosecondPosition(saveClipTime);
					musicFile.start();
				} else {
					musicFile.stop();
				}
			}

		} else if (e.getActionCommand() == "About") {
			playSound("victory.wav");
			JOptionPane.showMessageDialog(jf, "MADE BY THE AMAZING TEAM!!!");
		} else if (e.getActionCommand() == "Exit") {
			System.exit(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			counter = 0;
			playSound(jfText.getText() + ".wav");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
