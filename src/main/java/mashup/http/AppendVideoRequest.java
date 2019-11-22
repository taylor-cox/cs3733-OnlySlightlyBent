package mashup.http;

public class AppendVideoRequest {
	String videoID;
	String playlistID;
	
	public AppendVideoRequest(String videoID, String playlistID) {
		this.videoID = videoID;
		this.playlistID = playlistID;
	}
	
	public AppendVideoRequest() {}
	
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
