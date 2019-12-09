package mashup.http;

public class UploadVideoRequest {
	public UploadVideoRequest(String videoID, String character, String quote, String base64EncodedValue) {
		this.videoID = videoID;
		this.character = character;
		this.quote = quote;
		this.base64EncodedValue = base64EncodedValue;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	String videoID;
	String character;
	String quote;
	public String base64EncodedValue;
	
	public UploadVideoRequest() {}
	
	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	public String getBase64EncodedValue() {
		return base64EncodedValue;
	}

	public void setBase64EncodedValue(String base64EncodedValue) {
		this.base64EncodedValue = base64EncodedValue;
	}
}
