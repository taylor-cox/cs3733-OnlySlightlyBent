package mashup.model;

import java.util.UUID;

public class PublicSegment {
	/**
	 * Holds all the data for an available video.
	 */
	public String ID;
	public String character;
	public String text;
	public String url;

	public PublicSegment(String ID, String character, String text, String url) {
		this.character = character;
		this.text = text;
		this.url = url;
		this.ID = ID;
	}
	
	public String getID() { return ID; }
	public void setID(String iD) { ID = iD; }
	public String getCharacter() { return character; }
	public void setCharacter(String character) { this.character = character; }
	public String getQuote() { return text; }
	public void setQuote(String quote) { this.text = quote; }
	public String getFileName() { return url; }
	public void setFileName(String fileName) { this.url = fileName; }

	@Override
	public String toString() {
		return "PublicSegment [character=" + character + ", text=" + text + ", url=" + url + "]";
	}
}
