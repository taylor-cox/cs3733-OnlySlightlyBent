package mashup.http;

public class UnmarkLocalVideoSegRequest {
	String videoID;
	
	public UnmarkLocalVideoSegRequest(String videoID) {
		this.videoID = videoID;
	}
	
	public UnmarkLocalVideoSegRequest() {}
	
	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
}
