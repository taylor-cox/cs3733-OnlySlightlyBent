package mashup.http;

public class DeleteVideoRequest {
	String videoID;
	String playlistID;
	
	public DeleteVideoRequest(String videoID, String playlistID) {
		this.videoID = videoID;
		this.playlistID = playlistID;
	}
	
	public DeleteVideoRequest() {}
	
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
