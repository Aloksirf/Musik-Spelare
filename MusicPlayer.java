import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicPlayer extends JFrame implements ActionListener, KeyListener {

	private Clip musicFile = null;
	private JFrame jf;
	private JTextField jfText;
	private int counter = 0;
	public String getSong;
	public String getPlayList;

	/** Constructor */
	public MusicPlayer() {
		super("MusicPlayer");
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
		JButton b1 = new JButton("");
		JButton b2 = new JButton("");
		JButton b3 = new JButton("");
		getContentPane().add(jfText);

		b1.setOpaque(false);
		b1.setContentAreaFilled(false);
		b1.setBorderPainted(false);

		b2.setOpaque(false);
		b2.setContentAreaFilled(false);
		b2.setBorderPainted(false);

		b3.setOpaque(false);
		b3.setContentAreaFilled(false);
		b3.setBorderPainted(false);

		b1.setIcon(new ImageIcon("Images/play.png"));
		b2.setIcon(new ImageIcon("Images/pause.png"));
		b3.setIcon(new ImageIcon("Images/stop.png"));

		// Gör så knapparna reagerar på knapptryck
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter = 0;
				playSound(jfText.getText() + ".wav");
				jfText.setText(jfText.getText());
			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter = 1;
				if (musicFile == null) {
					JOptionPane.showMessageDialog(jf, "No Audio-file loaded.");
				} else {
					if (!musicFile.isRunning()) {
						JOptionPane.showMessageDialog(jf, "No Audio running.");
					} else {
						musicFile.stop();
						musicFile = null;
					}
				}
			}
		});
		b2.addActionListener(this);

		// Används endast när vi trycker ENTER för att starta en låt.
		jfText.addKeyListener(this);

		// Lägger till knapparna i JFrame
		add(b1, BorderLayout.SOUTH);
		add(b2, BorderLayout.SOUTH);
		add(b3, BorderLayout.SOUTH);

		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu m = new JMenu("File");
		JMenu m3 = new JMenu("Playlist");
		JMenu m2 = new JMenu("Help");

		JMenu m4 = new JMenu("Sort by");

		m4.setIcon(new ImageIcon("Images/sort.png"));

		menuBar.add(m);
		menuBar.add(m3);
		menuBar.add(m2);

		JMenuItem item1 = new JMenuItem("Open Sound", new ImageIcon("Images/openfile.png"));
		JMenuItem item8 = new JMenuItem("Open", new ImageIcon("Images/openfile.png"));
		JMenuItem item4 = new JMenuItem("Band", new ImageIcon("Images/group.png"));
		JMenuItem item5 = new JMenuItem("Song", new ImageIcon("Images/song.png"));
		JMenuItem item6 = new JMenuItem("Playtime", new ImageIcon("Images/time.png"));
		JMenuItem item7 = new JMenuItem("Filecode", new ImageIcon("Images/code.png"));
		JMenuItem item2 = new JMenuItem("Exit", new ImageIcon("Images/exit.png"));
		JMenuItem item3 = new JMenuItem("About", new ImageIcon("Images/about.png"));
		JMenuItem item9 = new JMenuItem("New", new ImageIcon("Images/newfile.png"));
		JMenuItem item10 = new JMenuItem("Add Song", new ImageIcon("Images/newfile.png"));

		m.add(item1).addActionListener(this);
		m.add(item2).addActionListener(this);

		m2.add(item3).addActionListener(this);

		m3.add(item9).addActionListener(this);
		m3.add(item8).addActionListener(this);
		m3.add(item10).addActionListener(this);
		m3.add(m4);

		m4.add(item4).addActionListener(this);
		m4.add(item5).addActionListener(this);
		m4.add(item6).addActionListener(this);
		m4.add(item7).addActionListener(this);

		setJMenuBar(menuBar);
		pack(); // Allt på samma rad, ser också till att allt du lagt till kommer med
		// i rutan!!
	}

	/**
	 * Starting to play the sound on your computer.
	 * 
	 * @param file The name of the file.
	 */
	public void playSound(String file) {
		try {
			if (musicFile == null || !musicFile.isRunning()) { // Om du ändrar här, så måste du ändra på ActionPerformed
																// med STOP
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(file));
				musicFile = AudioSystem.getClip();
				musicFile.open(audio);
				musicFile.start();
			}
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

	public void openPlayList() {
		JFileChooser choice = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "TXT");
		choice.setFileFilter(filter);
		choice.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = choice.showOpenDialog(jfText);

		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				File selectedFile = choice.getSelectedFile();
				String listName = selectedFile.getName();
				getPlayList = listName;
				System.out.println();
				System.out.println();
				System.out.println("------- Successfully loaded playlist: " + listName + " -------");
				PlayList.loadPlayList(listName);
				PlayList.printPlayList();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Open Sound") {
			openSound();
		} else if (e.getActionCommand() == "Band") {
			System.out.println();
			System.out.println("------- Sorted by Band -------");
			PlayList.sortList("band");
			PlayList.printPlayList();
		} else if (e.getActionCommand() == "Open") {
			openPlayList();
		} else if (e.getActionCommand() == "Playtime") {
			System.out.println();
			System.out.println("------- Sorted by Playtime -------");
			PlayList.sortList("playtime");
			PlayList.printPlayList();
		} else if (e.getActionCommand() == "Filecode") {
			System.out.println();
			System.out.println("------- Sorted by Filecode -------");
			PlayList.sortList("filecode");
			PlayList.printPlayList();
		} else if (e.getActionCommand() == "Song") {
			System.out.println();
			System.out.println("------- Sorted by Song -------");
			PlayList.sortList("song");
			PlayList.printPlayList();
		} else if (e.getActionCommand() == "About") {
			playSound("victory.wav");
			JOptionPane.showMessageDialog(this, "MADE BY THE AMAZING TEAM!!!");
		} else if (e.getActionCommand() == "New") {
			PlayList.sortList("band");
			PlayList.printPlayList();
		} else if (e.getActionCommand() == "Add Song") {
			PlayList.sortList("band");
			PlayList.printPlayList();
		} else if (e.getActionCommand() == "Exit") {
			int option = JOptionPane.showConfirmDialog(this, "Do you really want to exit?");
			if (option == 0) { // Om option är "Ja"
				System.exit(0);
			}
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
