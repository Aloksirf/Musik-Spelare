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
	private JTextField jfText, textPlayList;
	private int counter = 0;
	public String getSong;
	public static String getPlayList;
	public static PlayList myPlayList = new PlayList();

	/** Constructor */
	public MusicPlayer() {
		super("MusicPlayer");
		getPlayList = "Library.txt";
		setAlwaysOnTop(true);
		setSize(300, 165);
		textPlayList = new JTextField("Active Playlist: ", 30);
		textPlayList.setEditable(false);
		textPlayList.setBackground(Color.orange);
		jf = new JFrame("MusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout()); // SÃ¥ knapparna inte hamnar pÃ¥ varandra! Annan Layout!
		setLocation(200, 200); // Var fÃ¶nstret ska bÃ¶rja (x,y)
		setResizable(false);

		// Textbox, den kan bara ladda .wav-filer
		// Man behÃ¶ver bara skriva "test", den lÃ¤gger till .wav sjÃ¤lv.
		jfText = new JTextField("Enter the name of your music file here.", 30);

		// Knappar
		JButton play = new JButton("");
		JButton pause = new JButton("");
		JButton stop = new JButton("");
		add(jfText);
		add(textPlayList);

		// LÃ¤gger till knapparna i JFrame
		add(play, BorderLayout.SOUTH);
		add(pause, BorderLayout.NORTH);
		add(stop, BorderLayout.SOUTH);

		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);

		pause.setOpaque(false);
		pause.setContentAreaFilled(false);
		pause.setBorderPainted(false);

		stop.setOpaque(false);
		stop.setContentAreaFilled(false);
		stop.setBorderPainted(false);

		play.setIcon(new ImageIcon("Images/play.png"));
		pause.setIcon(new ImageIcon("Images/pause.png"));
		stop.setIcon(new ImageIcon("Images/stop.png"));

		// GÃ¶r sÃ¥ knapparna reagerar pÃ¥ knapptryck
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter = 0;
				playSound(jfText.getText() + ".wav");
				jfText.setText(jfText.getText());
			}
		});

		pause.addActionListener(new ActionListener() {
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

		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter = 1;
				if (musicFile == null) {
					JOptionPane.showMessageDialog(jf, "No Audio-file loaded.");
				} else {
					if (!musicFile.isOpen()) {
						JOptionPane.showMessageDialog(jf, "No Audio running.");
					} else {
						musicFile.stop();
						musicFile = null;
					}
				}
			}
		});

		// AnvÃ¤nds endast nÃ¤r vi trycker ENTER fÃ¶r att starta en lÃ¥t.
		jfText.addKeyListener(this);

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
		JMenuItem item2 = new JMenuItem("Exit", new ImageIcon("Images/exit.png"));
		JMenuItem item3 = new JMenuItem("About", new ImageIcon("Images/about.png"));
		JMenuItem item4 = new JMenuItem("Band", new ImageIcon("Images/group.png"));
		JMenuItem item5 = new JMenuItem("Song", new ImageIcon("Images/song.png"));
		JMenuItem item6 = new JMenuItem("Playtime", new ImageIcon("Images/time.png"));
		JMenuItem item7 = new JMenuItem("Filecode", new ImageIcon("Images/code.png"));
		JMenuItem item8 = new JMenuItem("Open", new ImageIcon("Images/openfile.png"));
		JMenuItem item9 = new JMenuItem("Print Library", new ImageIcon("Images/print.png"));
		JMenuItem item10 = new JMenuItem("Add Song", new ImageIcon("Images/add.png"));
		JMenuItem item11 = new JMenuItem("Remove Song", new ImageIcon("Images/remove.png"));

		m.add(item1).addActionListener(this);
		m.add(item9).addActionListener(this);
		m.add(item2).addActionListener(this);

		m2.add(item3).addActionListener(this);

		m3.add(item8).addActionListener(this);
		m3.add(item10).addActionListener(this);
		m3.add(item11).addActionListener(this);
		m3.add(m4);

		m4.add(item4).addActionListener(this);
		m4.add(item5).addActionListener(this);
		m4.add(item6).addActionListener(this);
		m4.add(item7).addActionListener(this);

		setJMenuBar(menuBar);
		// pack(); // Allt pÃ¥ samma rad, ser ocksÃ¥ till att allt du lagt till kommer
		// med i rutan!!
	}

	/**
	 * Starting to play the sound on your computer.
	 * 
	 * @param file The name of the file.
	 */
	public void playSound(String file) {
		try {
			if (musicFile == null || !musicFile.isRunning()) { // Om du Ã¤ndrar hÃ¤r, sÃ¥ mÃ¥ste du Ã¤ndra pÃ¥
																// ActionPerformed med STOP
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
				if (listName.equals("Library.txt")) {
					System.out.println();
					System.out.println("Cannot open Library File. Please choose another playlist.");
				} else {
					getPlayList = listName;
					listName = listName.replaceAll(".txt", "");
					System.out.println();
					System.out.println("------- Successfully loaded playlist: " + getPlayList + " -------");
					myPlayList.loadPlayList(listName);
					textPlayList.setText("Active Playlist:     " + getPlayList);
					textPlayList.setBackground(Color.GREEN);
				}
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
			if (getPlayList != null) {
				System.out.println();
				System.out.println("------- Sorted by Band -------");
				myPlayList.sortList("band");
				myPlayList.printPlayList();
				String temp = getPlayList.replaceAll(".txt", "");
				myPlayList.savePlayList(temp);
			} else {
				JOptionPane.showMessageDialog(this, "You need to open or make a new playlist first!", "", 2);
			}
		} else if (e.getActionCommand() == "Open") {
			openPlayList();
		} else if (e.getActionCommand() == "Playtime") {
			if (getPlayList != null) {
				System.out.println();
				System.out.println("------- Sorted by Playtime -------");
				myPlayList.sortList("playtime");
				myPlayList.printPlayList();
				String temp = getPlayList.replaceAll(".txt", "");
				myPlayList.savePlayList(temp);
			} else {
				JOptionPane.showMessageDialog(this, "You need to open or make a new playlist first!", "", 2);
			}
		} else if (e.getActionCommand() == "Filecode") {
			if (getPlayList != null) {
				System.out.println();
				System.out.println("------- Sorted by Filecode -------");
				myPlayList.sortList("filecode");
				myPlayList.printPlayList();
				String temp = getPlayList.replaceAll(".txt", "");
				myPlayList.savePlayList(temp);
			} else {
				JOptionPane.showMessageDialog(this, "You need to open or make a new playlist first!", "", 2);
			}
		} else if (e.getActionCommand() == "Song") {
			if (getPlayList != null) {
				System.out.println();
				System.out.println("------- Sorted by Song -------");
				myPlayList.sortList("song");
				myPlayList.printPlayList();
				String temp = getPlayList.replaceAll(".txt", "");
				myPlayList.savePlayList(temp);
			} else {
				JOptionPane.showMessageDialog(this, "You need to open or make a new playlist first!", "", 2);
			}
		} else if (e.getActionCommand() == "About") {
			playSound("victory.wav");
			JOptionPane.showMessageDialog(this, "MADE BY THE AMAZING TEAM!!!");
		} else if (e.getActionCommand() == "Add Song") {
			String s = getPlayList.replaceAll(".txt", "");
			if (s == null) {
			} else if (s.equals("Library")) {
				JOptionPane.showMessageDialog(this, "Please open a playlist first.", "Error", 2);
			} else {
				getPlayList = s + ".txt";
				System.out.println();
				System.out.println("------- Successfully loaded playlist: " + getPlayList + " -------");
				myPlayList.loadPlayList(s);
				while (true) {
					String str = JOptionPane.showInputDialog(this, "Enter song to add\n\nTo exit: press 'Avbryt'.",
							"Enter song name", 1);
					if (str == null || str.equals("null")) {
						break;
					}
					System.out.println();
					System.out.println("------- Successfully loaded playlist: " + getPlayList + " -------");
					myPlayList.addToPlayList(str, MainFile.library);
					JOptionPane.showMessageDialog(this, "Successfully added " + str + " to your playlist.");
				}
				s = s.replaceAll(".txt", "");
				myPlayList.savePlayList(s);
			}

		} else if (e.getActionCommand() == "Remove Song") {
			String s = getPlayList.replaceAll(".txt", "");
			if (s == null) {
			} else if (s.equals("Library")) {
				JOptionPane.showMessageDialog(this, "Please open a playlist first.", "Error", 2);
			} else {
				boolean removed;
				System.out.println();
				System.out.println("------- Successfully loaded playlist: " + getPlayList + " -------");
				myPlayList.loadPlayList(s);
				while (true) {
					String str = JOptionPane.showInputDialog(this, "Enter song to remove\n\nTo exit: press 'Avbryt'.",
							"Enter song name to remove it", 1);
					if (str == null || str.equals("null")) {
						break;
					}
					removed = myPlayList.removeSong(str);
					if (removed) {
						JOptionPane.showMessageDialog(this, "Successfully removed " + str + " from your playlist.");
						System.out.println();
						System.out.println("------- Successfully loaded playlist: " + getPlayList + " -------");
						myPlayList.loadPlayList(s);
					} else {
						System.out.println();
						System.out.println("Song not found.");
					}
				}
				myPlayList.savePlayList(s);
			}

		} else if (e.getActionCommand() == "Print Library") {
			System.out.println();
			getPlayList = "Library.txt";
			System.out.println();
			System.out.println("------------- Printing Library File -------------");
			myPlayList.loadPlayList("Library");
		} else if (e.getActionCommand() == "Exit") {
			int option = JOptionPane.showConfirmDialog(this, "Do you really want to exit?");
			if (option == 0) {
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
