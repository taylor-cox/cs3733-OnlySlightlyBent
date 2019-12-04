package mashup.http;

public class CreatePlaylistRequest {
	String playlistID;
	String playlistName;
	
	public CreatePlaylistRequest(String playlistID, String playlistName) {
		this.playlistID = playlistID;
		this.playlistName = playlistName;
	}
	
	public CreatePlaylistRequest() {}
	
	public String getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}
	
	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
}
