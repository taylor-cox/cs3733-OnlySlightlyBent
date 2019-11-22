package mashup.http;

public class SearchVideoRequest {
	String videoID;
	
	public SearchVideoRequest(String videoID) {
		this.videoID = videoID;
	}
	
	public SearchVideoRequest() {}
	
	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
}
