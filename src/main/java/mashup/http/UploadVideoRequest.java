package mashup.http;

public class UploadVideoRequest {
	String videoID;
	
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
}
