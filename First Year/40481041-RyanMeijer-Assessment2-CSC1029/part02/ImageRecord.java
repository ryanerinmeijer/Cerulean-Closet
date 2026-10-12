package part02;

import java.time.LocalDate;

public class ImageRecord {
	private static int nextId = 1;
	private int id;
	private String title;
	private String description;
	private ImageType genre;
	private LocalDate dateTaken;
	private String thumbnail;

	// Constructor
	public ImageRecord(String title, String description, ImageType genre, LocalDate dateTaken, String thumbnail) {

		if (this.setTitle(title)) {
			if (this.setDescription(description)) {
				if (this.setGenre(genre)) {
					if (this.setDateTaken(dateTaken)) {
						if (this.setThumbnail(thumbnail)) {
							this.id = nextId;
							nextId++;

						}
					}
				}
			}
		}
	}

	// toString
	public String toString() {
		return ("Tilte: " + this.title + " Description: " + this.description + " Genre: " + this.genre + " Date Taken: "
				+ this.dateTaken + " Thumbnail: " + this.thumbnail);
	}

	// Getters
	public int getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public ImageType getGenre() {
		return this.genre;
	}

	public LocalDate getDateTaken() {
		return this.dateTaken;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	// Setters
	public boolean setTitle(String title) {
		if (title != null && !title.isBlank()) {
			this.title = title;
			return true;
		} else {
			return false;
		}
	}

	public boolean setDescription(String description) {
		if (description != null && !description.isBlank()) {
			this.description = description;
			return true;
		} else {
			return false;
		}
	}

	public boolean setGenre(ImageType genre) {
		if (genre != null) {
			this.genre = genre;
			return true;
		} else {
			return false;
		}
	}

	public boolean setDateTaken(LocalDate dateTaken) {
		if (dateTaken != null) {
			this.dateTaken = dateTaken;
			return true;
		} else {
			return false;
		}
	}

	public boolean setThumbnail(String thumbnail) {
		if (thumbnail != null && (thumbnail.endsWith(".png") || thumbnail.endsWith(".gif") || thumbnail.endsWith(".jpeg"))  && !thumbnail.contains(" ")) {
			this.thumbnail = thumbnail;
			return true;
		} else {
			return false;
		}
	}

}
