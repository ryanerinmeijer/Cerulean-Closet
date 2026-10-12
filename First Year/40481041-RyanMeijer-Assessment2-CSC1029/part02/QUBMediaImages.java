package part02;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import console.Console;

public class QUBMediaImages {
	// Console Declaration
	static Console con = new Console(true);
	// Declaration For Menu
	static String title = "QUBImages";
	static String options[] = { "Add Image", "Search", "Display All", "Quit" };
	static final int QUIT = options.length;
	static Menu QUBMenu = new Menu(title, options, con);
	// Search Menu
	static String searchTitle = "QUBImages Search";
	static String searchOptions[] = { "Search ID", "Search Title", "Search Description", "Search Genre",
			"searchDates" };
	static Menu searchMenu = new Menu(searchTitle, searchOptions, con);
	// Declaration of ImageManager
	static ImageManager QUBImages = new ImageManager();
	// Declaration of Fonts
	static Font titleFont = new Font("Courier", Font.BOLD, 30);
	static Font normalFont = new Font("Courier", Font.PLAIN, 15);

	public static void main(String[] args) {
		// Initialisation of Console
		con.setSize(850, 550);
		con.setVisible(true);
		// Declaration of initial images
		addImage("Andromeda Galaxy", "Image of the Andromeda galaxy.", ImageType.ASTRONOMY, LocalDate.of(2026, 01, 01),
				"Andromeda.png");
		addImage("Lanyon QUB", "An image of the QUB Lanyon building.", ImageType.ARCHITECTURE, LocalDate.of(2023, 2, 1),
				"LanyonQUB.png");
		addImage("Kermit Plays Golf", "An image of Kermit the frog playing golf", ImageType.SPORT,
				LocalDate.of(2023, 3, 1), "KermitGolf.png");
		addImage("Mourne Mountains", "A panoramic view of the Mourne mountains.", ImageType.LANDSCAPE,
				LocalDate.of(2023, 4, 1), "Mournes.png");

		addImage("Homer Simpson", "Homer Simpson - A portrait of the man.", ImageType.PORTRAIT,
				LocalDate.of(2023, 3, 1), "Homer.png");

		addImage("Red Kite", "A Red Kite bird of prey in flight.", ImageType.NATURE, LocalDate.of(2023, 4, 1),
				"RedKite.png");

		addImage("Central Park", "An overhead view of Central Park New York USA.", ImageType.AERIAL,
				LocalDate.of(2023, 5, 1), "CentralPark.png");

		addImage("Apples", "A bunch of apples.", ImageType.FOOD, LocalDate.of(2023, 6, 1), "Apples.png");

		addImage("Programming Meme", "A Chat GPT programming meme.", ImageType.OTHER, LocalDate.of(2023, 7, 1),
				"ChatGPT.png");
		con.clear();
		int choice;
		do {
			choice = QUBMenu.getUserChoice();
			if (choice != QUIT) {
				processChoice(choice);
			}
		} while (choice != QUIT);
		con.println("\nGoodbye!");
		con.dispose();
	}

	/**
	 * Switches based on the input of the menu and calls necessary methods.
	 * 1 - addImage() 
	 * 2 - search() 
	 * 3 - displayAll()
	 * 
	 * @param choice - input from the menu to be switched on. Invalid choices will
	 *               display an error message
	 */
	public static void processChoice(int choice) {
		switch (choice) {
		case 1: {
			addImage();
			break;
		}
		case 2: {
			search();
			break;
		}
		case 3: {
			displayAll();
			break;
		}
		default:
			con.setColour(Color.red);
			con.println("Invalid choice: " + choice);
			con.setColour(Color.black);

		}

	}

	/**
	 * Displays all images in the Image Manager
	 */
	public static void displayAll() {
		ImageAlbum allImages = QUBImages.getAllImages();
		displayAlbum(allImages);
	}

	/**
	 * Switches based on the input of the search menu and calls any necessary
	 * methods. Invalid inputs will display an error message and go back to main
	 * menu.
	 *  1 - search ID 
	 *  2 - search Title 
	 *  3 - search Description 
	 *  4 - search Genre 
	 *  5 - search Dates
	 */
	public static void search() {

		int choice = searchMenu.getUserChoice();
		switch (choice) {
		case 1: {
			ImageRecord record = (QUBImages.searchId(getIDSearchTerm()));
			if (record != null) {
				displayRecord(record);
			} else {
				con.println("Record Not Found.");

			}
			con.println("Press ENTER to continue");
			con.readLn();
			con.clear();
			break;
		}
		case 2: {
			displayAlbum(QUBImages.searchTitle(getTextSearchTerm()));
			break;
		}
		case 3: {
			displayAlbum(QUBImages.searchDescription(getTextSearchTerm()));
			break;
		}
		case 4: {
			displayAlbum(QUBImages.searchGenre(getGenreSearchTerm()));
			break;
		}
		case 5: {
			displayAlbum(QUBImages.searchDates(getDateSearchTerm("start"), getDateSearchTerm("end")));
			break;
		}

		default:
			con.println("Invalid choice: " + choice);
		}

	}
/**
 * Will prompt user to enter a date recursively until it can be parsed.
 * @param position - string to be placed in the prompt sentence ( start or end)
 * @return User's input as a localdate variable
 */
	private static LocalDate getDateSearchTerm(String position) {
		con.print("Enter the " + position + " date[formatted yyyy-mm-dd]: ");
		String dateSearchString = con.readLn();
		try {
			LocalDate dateSearch = LocalDate.parse(dateSearchString);
			return dateSearch;
		} catch (Exception e) {
			con.setColour(Color.red);
			con.println("Error parsing date. Try again");
			con.setColour(Color.black);
			return getDateSearchTerm(position);
		}
	}
/**
 * Will prompt user to enter a genre. If genre is not valid, other is automatically chosen
 * @return User's input as a ImageType enum
 */
	private static ImageType getGenreSearchTerm() {
		con.print("Enter a genre: ");
		String genreChoice = con.readLn();
		genreChoice.toLowerCase();
		switch (genreChoice) {
		case "astronomy": {
			return ImageType.ASTRONOMY;
		}
		case "architecture": {
			return ImageType.ARCHITECTURE;
		}
		case "sport": {
			return ImageType.SPORT;
		}
		case "landscape": {
			return ImageType.LANDSCAPE;
		}
		case "portrait": {
			return ImageType.PORTRAIT;
		}
		case "nature": {
			return ImageType.NATURE;
		}
		case "aerial": {
			return ImageType.AERIAL;
		}
		case "food": {
			return ImageType.FOOD;
		}
		case "other": {
			return ImageType.OTHER;
		}
		default:
			con.setColour(Color.red);
			con.println("The type " + genreChoice + " is not a valid choice type. Genre 'other' selected.");
			con.setColour(Color.black);
			return ImageType.OTHER;
		}
	}
	/**
	 * Will prompt user to enter some text.
	 * @return User's input
	 */
	private static String getTextSearchTerm() {
		con.print("Enter a search term: ");
		return con.readLn();
	}
	/**
	 * Will prompt user to enter an ID int recursively until it can be parsed.
	 * @return User's input as an int variable
	 */
	private static int getIDSearchTerm() {
		con.print("Enter an ID to search for: ");
		try {
			int idSearch = Integer.parseInt(con.readLn());
			return idSearch;
		} catch (Exception e) {
			con.setColour(Color.red);
			con.println("Number could not be parsed. Try again.");
			con.setColour(Color.black);
			return getIDSearchTerm();
		}
		
	}
/**
 * Attempts to create a new ImageRecord and add it to the ImageManager object. Will output success or failure statements
 * @param Title of the image
 * @param Description of the image
 * @param Genre - ImageType variable of the genre of the image
 * @param DateTaken - localdate variable of when the photo was taken
 * @param Thumbnail of the image. Must end ".png",".jpeg",".gif" AND contain no spaces.
 */
	public static void addImage(String title, String description, ImageType genre, LocalDate dateTaken,
			String thumbnail) {
		try {
			ImageRecord newImageRecord = new ImageRecord(title, description, genre, dateTaken, thumbnail);
			con.setColour(Color.green);
			con.println("Record created succesfully");
			con.setColour(Color.black);
			QUBImages.addImage(newImageRecord);
		} catch (Exception e) {
			con.setColour(Color.red);
			con.println("Record unsucsessful");
			con.setColour(Color.black);
		}

	}
/**
 * Prompts user to enter details needed to create a new ImageRecord object.
 * Then creates it and adds it to the ImageManager.
 * It will recursively try until details entered correctly.
 */
	public static void addImage() {
		String title, description, thumbnail;
		ImageType genre;
		LocalDate dateTaken;
		try {
			con.print("Enter image title: ");
			title = con.readLn();
			con.print("Enter image description: ");
			description = con.readLn();
			genre = getGenreSearchTerm();
			con.print("Enter date image taken[formatted yyyy-mm-dd]: ");
			dateTaken = LocalDate.parse(con.readLn());
			con.print("Enter image thumbnail: ");
			thumbnail = con.readLn();
			addImage(title, description, genre, dateTaken, thumbnail);
		} catch (Exception e) {
			con.setColour(Color.red);
			con.println("Error in details. Try again.");
			con.setColour(Color.black);
			addImage();
		}

	}
/**
 * Displays all ImageRecord items in the album
 * @param album - The ImageAlbum you wish to display.
 */
	public static void displayAlbum(ImageAlbum album) {
		ImageRecord record = album.getFirst();
		if (record == null) {
			con.setColour(Color.red);
			con.println("Empty Album.");
			con.setColour(Color.black);

		}
		while (record != null) {
			displayRecord(record);
			con.print("Press ENTER to view next");
			con.readLn();
			con.clear();
			record = album.getNext();
		}
	}

	/**
	 * Displays the individual ImageRecord items
	 * @param record - The ImageRecord you wish to display.
	 */
	public static void displayRecord(ImageRecord record) {
		con.setFont(titleFont);
		con.println(record.getTitle());
		printImage(record.getThumbnail());
		con.setFont(normalFont);
		con.println(record.getDescription());
		con.println("Genre: " + record.getGenre());
		con.println("Date Taken: " + record.getDateTaken());
	}
/**
 * Displays the image pointed to by the thumbnail on the console.
 * @param thumbnail - filename of what you wish to display.
 */
	public static void printImage(String thumbnail) {
		String userdir = System.getProperty("user.dir");
		String path = userdir + "/Images/";
		ImageIcon img = new ImageIcon(path + thumbnail);
		con.println(img);
	}

}
