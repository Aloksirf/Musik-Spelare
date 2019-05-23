
/**
 * Opens up a MusicPlayer that can:
 *  - Import .wav-files and play them.
 *  - Create/Open Playlists.
 *  - Add/Remove songs to playlists.
 *  - Sort playlist by band/songs/time.
 *  
 * @author Andreas Stadin, Nicklas Kriström, Vidar Hårding and Oliver Olsson
 */

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
	public static String getPlayList;
	public static PlayList myPlayList = new PlayList();

	/** Constructor */
	public MusicPlayer() {
		super("MusicPlayer");
		getPlayList = "Library.txt";
		setAlwaysOnTop(true);
		setSize(350, 165);
		textPlayList = new JTextField("Active Playlist: ", 30);
		textPlayList.setEditable(false);
		textPlayList.setBackground(Color.orange);
		jf = new JFrame("MusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout()); // Makes sure the buttons is separated.
		setLocation(200, 200); // Where the window starts on open.
		setResizable(false);

		// Textbox
		jfText = new JTextField("Enter music file here.", 30);
		add(jfText);
		add(textPlayList);

		// Buttons
		JButton play = new JButton("");
		JButton pause = new JButton("");
		JButton stop = new JButton("");

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

		// Makes sure you play the file based on the text in the textfield.
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter = 0;
				playSound(jfText.getText() + ".wav");
				jfText.setText(jfText.getText());
			}
		});

		// Pauses the sound if it's playing. Resumes upon clicking again.
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

		// Stops the sound if it's playing. Close the Clip upon clicking.
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

		// Used for pressing Enter to start a new Song.
		jfText.addKeyListener(this);

		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu pList = new JMenu("Playlist");
		JMenu search = new JMenu("Search");
		JMenu help = new JMenu("Help");

		JMenu sortBy = new JMenu("Sort by");

		sortBy.setIcon(new ImageIcon("Images/sort.png"));

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
		JMenuItem item12 = new JMenuItem("New Playlist", new ImageIcon("Images/newfile.png"));
		JMenuItem item13 = new JMenuItem("Songs by a Band", new ImageIcon("Images/song.png"));

		menuBar.add(file);
		menuBar.add(pList);
		menuBar.add(search).add(item13).addActionListener(this);
		menuBar.add(help).add(item3).addActionListener(this);

		file.add(item1).addActionListener(this);
		file.add(item9).addActionListener(this);
		file.add(item2).addActionListener(this);

		pList.add(item12).addActionListener(this);
		pList.add(item8).addActionListener(this);
		pList.add(item10).addActionListener(this);
		pList.add(item11).addActionListener(this);
		pList.add(sortBy);

		sortBy.add(item4).addActionListener(this);
		sortBy.add(item5).addActionListener(this);
		sortBy.add(item6).addActionListener(this);
		sortBy.add(item7).addActionListener(this);

		setJMenuBar(menuBar);
		// pack(); //Makes sure everything you add, fits the window.
	}

	/**
	 * Starting to play the sound (.wav) on your computer.
	 * 
	 * @param file The name of the file.
	 */
	private void playSound(String file) {
		try {
			if (musicFile == null || !musicFile.isRunning()) {
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
	private void openSound() {
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

	/**
	 * Opens up the current directory for you to choose a .txt-file. Printing out
	 * the continent of the chosen playlist.
	 */
	private void openPlayList() {
		JFileChooser choice = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "TXT");
		choice.setFileFilter(filter);
		choice.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = choice.showOpenDialog(jfText);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = choice.getSelectedFile();
			String listName = selectedFile.getName();
			if (listName.equals("Library.txt")) {
				JOptionPane.showMessageDialog(this, "Cannot open Library File. Please choose another playlist.",
						"Error", 0);
			} else {
				getPlayList = listName;
				listName = listName.replaceAll(".txt", "");
				System.out.println("\n------- Successfully loaded playlist: " + getPlayList + " -------");
				myPlayList.loadPlayList(listName);
				PlayList.printPlayList();
				textPlayList.setText("Active Playlist:     " + getPlayList);
				textPlayList.setBackground(Color.GREEN);
			}
		}
	}

	/**
	 * All the different things that will happen when you press anything in the
	 * menu. This is the ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Open Sound") {
			openSound();
		} else if (e.getActionCommand() == "Open") {
			openPlayList();
		} else if (e.getActionCommand() == "Band") {
			System.out.println("\n---------------- Sorted by Band ----------------");
			myPlayList.sortList("band");
			PlayList.printPlayList();
			String temp = getPlayList.replaceAll(".txt", "");
			myPlayList.savePlayList(temp);
		} else if (e.getActionCommand() == "Playtime") {
			System.out.println("\n-------------- Sorted by Playtime --------------");
			myPlayList.sortList("playtime");
			PlayList.printPlayList();
			String temp = getPlayList.replaceAll(".txt", "");
			myPlayList.savePlayList(temp);
		} else if (e.getActionCommand() == "Filecode") {
			System.out.println("\n-------------- Sorted by Filecode --------------");
			myPlayList.sortList("filecode");
			PlayList.printPlayList();
			String temp = getPlayList.replaceAll(".txt", "");
			myPlayList.savePlayList(temp);
		} else if (e.getActionCommand() == "Song") {
			System.out.println("\n---------------- Sorted by Song ----------------");
			myPlayList.sortList("song");
			PlayList.printPlayList();
			String temp = getPlayList.replaceAll(".txt", "");
			myPlayList.savePlayList(temp);
		} else if (e.getActionCommand() == "About") {
			playSound("victory.wav");
			JOptionPane.showMessageDialog(this,
					"Made by:\n\nAndreas Stadin\nOliver Olsson\nVidar Hårding\nNicklas Kriström");
		} else if (e.getActionCommand() == "Add Song") {
			String s = getPlayList.replaceAll(".txt", "");
			if (s == null || s.equals("null")) {
			} else if (s.equals("Library")) {
				JOptionPane.showMessageDialog(this, "Please open a playlist first.", "Error", 2);
			} else {
				getPlayList = s + ".txt";
				while (true) {
					String str = JOptionPane.showInputDialog(this, "Enter song to add\n\nTo exit: press 'Avbryt'.",
							"Enter song name", 1);
					if (str == null || str.equals("null")) {
						break;
					}
					boolean temp = myPlayList.addToPlayList(str, MainFile.library);
					if (temp) {
						JOptionPane.showMessageDialog(this, "Successfully added " + str + " to your playlist.");
						System.out.println("\n-------------- Playlist: " + getPlayList + " --------------");
						s = s.replaceAll(".txt", "");
						myPlayList.savePlayList(s);
						PlayList.printPlayList();
					}
				}
				s = s.replaceAll(".txt", "");
				myPlayList.savePlayList(s);
			}
		} else if (e.getActionCommand() == "Remove Song") {
			if (PlayList.songs.isEmpty()) {
				JOptionPane.showMessageDialog(this, "\nPlaylist is empty. Unable to remove songs.", "Error", 2);
			} else {
				String s = getPlayList.replaceAll(".txt", "");
				if (s == null || s.equals("null")) {
				} else if (s.equals("Library")) {
					JOptionPane.showMessageDialog(this, "Please open a playlist first.", "Error", 2);
				} else {
					boolean removed;
					while (true) {
						String str = JOptionPane.showInputDialog(this,
								"Enter song to remove\n\nTo exit: press 'Avbryt'.", "Enter song name to remove it", 1);
						if (str == null || str.equals("null")) {
							break;
						}
						removed = myPlayList.removeSong(str);
						if (removed) {
							JOptionPane.showMessageDialog(this, "Successfully removed " + str + " from your playlist.");
							System.out.println("\n-------------- Playlist: " + getPlayList + " --------------");
							myPlayList.loadPlayList(s);
							PlayList.printPlayList();
						} else {
							System.out.println("\nSong not found. Be aware of letters with Upper Case");
						}
					}
					myPlayList.savePlayList(s);
				}
			}
		} else if (e.getActionCommand() == "New Playlist") {
			String str = JOptionPane.showInputDialog(this, "Enter Playlist name: ", "Enter Playlist name", 1);
			if (str == null || str.equals("null")) {
			} else {
				myPlayList.makePlaylist(str);
				JOptionPane.showMessageDialog(this, "Successfully created " + str + ".txt as a playlist.");
				getPlayList = str + ".txt";
				textPlayList.setText("Active Playlist:     " + getPlayList);
				textPlayList.setBackground(Color.GREEN);
				System.out.println("\n-------------- Playlist: " + getPlayList + " --------------");
				myPlayList.loadPlayList(str);
				PlayList.printPlayList();
			}
		} else if (e.getActionCommand() == "Songs by a Band") {
			myPlayList.loadPlayList("Library");
			String str = JOptionPane.showInputDialog(this, "Enter a BAND to see all their songs.", "Enter band name",
					1);
			if (str == null || str.equals("null")) {
			} else if (str.equals("")) {
				JOptionPane.showMessageDialog(this, "You must Enter a Band name", "Error", 1);
			} else {
				boolean check = PlayList.checkBandExistence(str);
				if (check) {
					System.out.println("\n----------- Songs by " + str + " -----------");
					PlayList.printSongsFromBand(str);
				} else {
					System.out.println("\nCouldn't find " + str + " in library file.");
				}
			}
			String temp = getPlayList.replaceAll(".txt", "");
			myPlayList.loadPlayList(temp);
		} else if (e.getActionCommand() == "Print Library") {
			System.out.println("\n------------- Printing Library File ------------");
			myPlayList.loadPlayList("Library");
			PlayList.printPlayList();
			String temp = getPlayList.replaceAll(".txt", "");
			myPlayList.loadPlayList(temp);
		} else if (e.getActionCommand() == "Exit") {
			int result = JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "Are you sure?",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == 0) {
				System.exit(0);
			}
		}
	}

	/**
	 * Plays the sound when you hit Enter on your keyboard. This is the KeyListener.
	 */
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

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
