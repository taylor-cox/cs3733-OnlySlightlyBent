package mashup.http;

public class RemoveVideoRequest {
	String videoID;
	String playlistID;
	
	public RemoveVideoRequest(String videoID, String playlistID) {
		this.videoID = videoID;
		this.playlistID = playlistID;
	}
	
	public RemoveVideoRequest() {}
	
	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	public String getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}
}
