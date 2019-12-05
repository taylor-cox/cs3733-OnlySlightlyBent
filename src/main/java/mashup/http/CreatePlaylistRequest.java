package mashup.http;

public class CreatePlaylistRequest {
	String playlistID;
	
	public CreatePlaylistRequest(String playlistID) {
		this.playlistID = playlistID;
	}
	
	public CreatePlaylistRequest() {}
	
	public String getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}
}
