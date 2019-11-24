package mashup.model;

import java.util.UUID;

public class Video {
	public String ID;
	public String character;
	public String quote;
	public String fileName;

	public Video(String ID, String character, String quote, String fileName) {
		this.ID = ID;
		this.character = character;
		this.quote = quote;
		this.fileName = fileName;
	}
	
	public String getID() { return ID; }
	public void setID(String ID) { this.ID = ID; }
	public String getCharacter() { return character; }
	public void setCharacter(String character) { this.character = character; }
	public String getQuote() { return quote; }
	public void setQuote(String quote) { this.quote = quote; }
	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }

	@Override
	public String toString() {
		return "Video [character=" + character + ", quote=" + quote + ", fileName=" + fileName + "]";
	}
}
