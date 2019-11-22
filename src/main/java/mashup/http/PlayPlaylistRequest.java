package mashup.http;

public class PlayPlaylistRequest {
	String playlistID;
	
	public PlayPlaylistRequest(String playlistID) {
		this.playlistID = playlistID;
	}
	
	public PlayPlaylistRequest() {}
	
	public String getVideoID() {
		return playlistID;
	}

	public void setVideoID(String playlistID) {
		this.playlistID = playlistID;
	}
}
