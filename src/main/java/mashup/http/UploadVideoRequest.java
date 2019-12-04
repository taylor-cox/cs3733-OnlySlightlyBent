package mashup.http;

public class UploadVideoRequest {
	String videoID;
	byte[] base64EncodedValue;
	
	public UploadVideoRequest(String videoID) {
		this.videoID = videoID;
	}
	
	public UploadVideoRequest() {}
	
	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	public byte[] getBase64EncodedValue() {
		return base64EncodedValue;
	}

	public void setBase64EncodedValue(byte[] base64EncodedValue) {
		this.base64EncodedValue = base64EncodedValue;
	}
}
