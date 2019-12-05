package mashup.http;

public class DeletePlaylistRequest {
	String playlistID;
	
	public DeletePlaylistRequest(String playlistID) {
		this.playlistID = playlistID;
	}
	
	public DeletePlaylistRequest() {}
	
	public String getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}
}
