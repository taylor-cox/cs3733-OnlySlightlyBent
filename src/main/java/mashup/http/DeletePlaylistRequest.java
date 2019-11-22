package mashup.http;

public class DeletePlaylistRequest {
	String playlistID;
	
	public DeletePlaylistRequest(String playlistID) {
		this.playlistID = playlistID;
	}
	
	public DeletePlaylistRequest() {}
	
	public String getVideoID() {
		return playlistID;
	}

	public void setVideoID(String playlistID) {
		this.playlistID = playlistID;
	}
}
