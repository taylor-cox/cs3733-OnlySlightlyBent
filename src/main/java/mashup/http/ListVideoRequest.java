package mashup.http;

public class ListVideoRequest {
	String videoID;
	
	public ListVideoRequest(String videoID) {
		this.videoID = videoID;
	}
	
	public ListVideoRequest() {}
	
	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
}
