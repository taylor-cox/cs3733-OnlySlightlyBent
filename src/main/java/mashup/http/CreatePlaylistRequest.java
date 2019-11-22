package mashup.http;

public class CreatePlaylistRequest {
	String playlistID;
	
	public CreatePlaylistRequest(String playlistID) {
		this.playlistID = playlistID;
	}
	
	public CreatePlaylistRequest() {}
	
	public String getVideoID() {
		return playlistID;
	}

	public void setVideoID(String playlistID) {
		this.playlistID = playlistID;
	}
}
