package mashup.model;

import java.util.UUID;

public class Video {
	/**
	 * Holds all the data for an available video.
	 */
	public String ID;
	public String character;
	public String quote;
	public String fileName;
	public Boolean isMarked;

	public Video(String ID, String character, String quote, String fileName, Boolean isMarked) {
		this.ID = ID;
		this.character = character;
		this.quote = quote;
		this.fileName = fileName;
		this.isMarked = isMarked;
	}
	
	public String getID() { return ID; }
	public void setID(String ID) { this.ID = ID; }
	public String getCharacter() { return character; }
	public void setCharacter(String character) { this.character = character; }
	public String getQuote() { return quote; }
	public void setQuote(String quote) { this.quote = quote; }
	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }
	public Boolean getIsMarked() { return isMarked; }
	public void setIsMarked(Boolean marked) { this.isMarked = isMarked; }

	@Override
	public String toString() {
		return "Video [ID=" + ID + ", character=" + character + ", quote=" + quote + ", fileName=" + fileName + "isMarked=" + isMarked + "]";
	}
}
