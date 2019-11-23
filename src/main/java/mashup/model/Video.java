package mashup.model;

import java.util.UUID;

public class Video {
	public final String character;
	public final String quote;
	public final String fileName;
	
	public Video(String character, String quote, String fileName) {
		this.character = character;
		this.quote = quote;
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Video [character=" + character + ", quote=" + quote + ", fileName=" + fileName + "]";
	}
}
