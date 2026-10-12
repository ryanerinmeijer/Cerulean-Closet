package part02;

import java.time.LocalDate;
import java.util.ArrayList;

public class ImageManager {
	private ArrayList<ImageRecord> imageManager;

	//Constructor
	public ImageManager() {
		this.imageManager = new ArrayList<ImageRecord>();
	}
	public void addImage(ImageRecord newRecord) {
		imageManager.add(newRecord);

	}

	public ImageRecord searchId(int id) {
		for (ImageRecord record : imageManager) {
			if (record.getId() == id) {
				return record;
			}
		}
		return null;
	}

	public ImageAlbum searchTitle(String searchTerm) {
		ImageAlbum searchedNamesAlbum = new ImageAlbum();
		for (ImageRecord record : imageManager) {
			if (record.getTitle().contains(searchTerm)) {
				searchedNamesAlbum.addRecord(record);
			}
		}
		searchedNamesAlbum.sortAlbum();
		return searchedNamesAlbum;
	}

	public ImageAlbum searchDescription(String searchTerm) {
		ImageAlbum searchedDescriptionAlbum = new ImageAlbum();
		for (ImageRecord record : imageManager) {
			if (record.getDescription().contains(searchTerm)) {
				searchedDescriptionAlbum.addRecord(record);
			}
		}
		searchedDescriptionAlbum.sortAlbum();
		return searchedDescriptionAlbum;
	}

	public ImageAlbum searchGenre(ImageType searchGenre) {
		ImageAlbum searchedGenreAlbum = new ImageAlbum();
		for (ImageRecord record : imageManager) {
			if (record.getGenre() == searchGenre) {
				searchedGenreAlbum.addRecord(record);
			}
		}
		searchedGenreAlbum.sortAlbum();
		return searchedGenreAlbum;
	}

	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		ImageAlbum searchedDateAlbum = new ImageAlbum();
		for (ImageRecord record : imageManager) {
			if ((record.getDateTaken().isAfter(start) || record.getDateTaken().equals(start))
					&& (record.getDateTaken().isBefore(end) || record.getDateTaken().equals(end))) {
				searchedDateAlbum.addRecord(record);
			}
		}
		searchedDateAlbum.sortAlbum();
		return searchedDateAlbum;
	}
	public ImageAlbum getAllImages() {
		ImageAlbum allImages = new ImageAlbum();
		for (ImageRecord record : imageManager) {
			allImages.addRecord(record);
		}
		allImages.sortAlbum();
		return allImages;
	}
}
