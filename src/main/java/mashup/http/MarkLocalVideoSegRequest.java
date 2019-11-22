package mashup.http;

public class MarkLocalVideoSegRequest {
	String videoID;
	
	public MarkLocalVideoSegRequest(String videoID) {
		this.videoID = videoID;
	}
	
	public MarkLocalVideoSegRequest() {}
	
	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
}
