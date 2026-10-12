package part01;

import java.util.ArrayList;

public class ImageAlbum {
	private ArrayList<ImageRecord> imageAlbum;
	private int lastIndexAccessed = -1;

	public ImageAlbum(ArrayList<ImageRecord> imageAlbum) {
		this.imageAlbum = imageAlbum;
	}

	public ImageAlbum() {
		this.imageAlbum = new ArrayList<ImageRecord>();
	}

	public ImageRecord getFirst() {
		if (imageAlbum.size() > 0) {
			setLastIndexAccessed(0);
			return imageAlbum.get(0);
		} else {
			return null;
		}

	}

	public ImageRecord getNext() {
		int index = getLastIndexAccessed() + 1;
		if (index < imageAlbum.size() && index != 0) {
			setLastIndexAccessed(index);
			return imageAlbum.get(index);
		} else {
			return null;
		}

	}

	public ImageRecord getPrevious() {
		int index = getLastIndexAccessed() - 1;
		if (index >= 0 && imageAlbum.size() > 0) {
			setLastIndexAccessed(index);
			return imageAlbum.get(index);
		} else {
			return null;
		}
	}

	public int getLastIndexAccessed() {
		return lastIndexAccessed;
	}

	public void setLastIndexAccessed(int lastIndexAccessed) {
		this.lastIndexAccessed = lastIndexAccessed;
	}

	public void addRecord(ImageRecord recordToAdd) {
		imageAlbum.add(recordToAdd);
	}

	public void sortAlbum() {
		int swaps = 0;
		do {
			swaps = 0;
			for (int i = 0; i < this.imageAlbum.size() - 1; i++) {
				if (imageAlbum.get(i).getDateTaken().isAfter(imageAlbum.get(i + 1).getDateTaken())) {
					ImageRecord temp = imageAlbum.get(i);
					imageAlbum.set(i, imageAlbum.get(i + 1));
					imageAlbum.set(i + 1, temp);
					swaps++;
				}
			}
		} while (swaps != 0);

	}

}
