package part01;

//To Do:
//Comments
//ImageRecord validation

import java.time.LocalDate;
import java.util.Scanner;

public class QUBImages {
	// Scanner Declaration
	static Scanner input = new Scanner(System.in);
	// Declaration For Menu
	static String title = "QUBImages";
	static String options[] = { "Add Image", "Search", "Display All", "Quit" };
	static final int QUIT = options.length;
	static Menu QUBMenu = new Menu(title, options);
	// Search Menu
	static String searchTitle = "QUBImages Search";
	static String searchOptions[] = { "Search ID", "Search Title", "Search Description", "Search Genre",
			"searchDates" };
	static Menu searchMenu = new Menu(searchTitle, searchOptions);
	// Declaration of ImageManager
	static ImageManager QUBImages = new ImageManager();

	public static void main(String[] args) {
		// Declaration of images
		addImage("Andromeda Galaxy", "Image of the Andromeda galaxy.", ImageType.ASTRONOMY, LocalDate.of(2023, 01, 01),
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

		int choice;
		do {
			choice = QUBMenu.getUserChoice();
			if (choice != QUIT) {
				processChoice(choice);
			}
		} while (choice != QUIT);
		System.out.println("\nGoodbye!");
		input.close();
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
			System.out.println("Invalid choice: " + choice);
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
			System.out.println(QUBImages.searchId(getIDSearchTerm()));
			System.out.println();
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
			System.out.println("Invalid choice: " + choice);
		}

	}
	/**
	 * Will prompt user to enter a date recursively until it can be parsed.
	 * @param position - string to be placed in the prompt sentence ( start or end)
	 * @return User's input as a localdate variable
	 */
	private static LocalDate getDateSearchTerm(String position) {
		System.out.print("Enter the " + position + " date[formatted yyyy-mm-dd]: ");
		String dateSearchString = input.nextLine();
		try {
			LocalDate dateSearch = LocalDate.parse(dateSearchString);
			return dateSearch;
		} catch (Exception e) {
			System.out.println("Error parsing date. Try again");
			return getDateSearchTerm(position);
		}
	}
	/**
	 * Will prompt user to enter a genre. If genre is not valid, other is automatically chosen
	 * @return User's input as a ImageType enum
	 */
	private static ImageType getGenreSearchTerm() {
		System.out.print("Enter a genre: ");
		String genreChoice = input.nextLine();
		switch (genreChoice) {
		case "Astronomy": {
			return ImageType.ASTRONOMY;
		}
		case "Architecture": {
			return ImageType.ARCHITECTURE;
		}
		case "Sport": {
			return ImageType.SPORT;
		}
		case "Landscape": {
			return ImageType.LANDSCAPE;
		}
		case "Portrait": {
			return ImageType.PORTRAIT;
		}
		case "Nature": {
			return ImageType.NATURE;
		}
		case "Aerial": {
			return ImageType.AERIAL;
		}
		case "Food": {
			return ImageType.FOOD;
		}
		case "Other": {
			return ImageType.OTHER;
		}
		default:
			System.out.println("The type " + genreChoice + " is not a valid choice type. Genre 'other' selected.");
			return ImageType.OTHER;
		}
	}
	/**
	 * Will prompt user to enter some text.
	 * @return User's input
	 */
	private static String getTextSearchTerm() {
		System.out.print("Enter a search term: ");
		return input.nextLine();
	}
	/**
	 * Will prompt user to enter an ID int recursively until it can be parsed.
	 * @return User's input as an int variable
	 */
	private static int getIDSearchTerm() {
		try {
		System.out.print("Enter an ID to search for: ");
		int idSearch = input.nextInt();
		input.nextLine();
		return idSearch;
		} catch (Exception e) {
			System.out.println("Error parsing number try again.");
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
		QUBImages.addImage(newImageRecord);
		System.out.println("Image created successfully.");
		}catch (Exception e) {
			System.out.println("Record unsuccessful");
		}

	}
	/**
	 * Prompts user to enter details needed to create a new ImageRecord object.
	 * Then creates it and adds it to the ImageManager.
	 * It will recursively try until details entered correctly.
	 */
	public static void addImage() {
		try {
		System.out.print("Enter image title: ");
		String title = input.nextLine();
		System.out.print("Enter image description: ");
		String description = input.nextLine();
		ImageType genre = getGenreSearchTerm();
		System.out.print("Enter date image taken[formatted yyyy-mm-dd]: ");
		LocalDate dateTaken = LocalDate.parse(input.nextLine());
		System.out.print("Enter image thumbnail: ");
		String thumbnail = input.nextLine();

		addImage(title, description, genre, dateTaken, thumbnail);
		}catch (Exception e) {
			System.out.println("Error in details. Try again.");
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
			System.out.println("Empty Album.");
		}
		while (record != null) {
			System.out.println(record);
			record = album.getNext();
		}
System.out.println();
	}
}
