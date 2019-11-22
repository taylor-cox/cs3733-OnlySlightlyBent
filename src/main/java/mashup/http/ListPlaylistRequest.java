package mashup.http;

public class ListPlaylistRequest {
	String playlistID;
	
	public ListPlaylistRequest(String playlistID) {
		this.playlistID = playlistID;
	}
	
	public ListPlaylistRequest() {}
	
	public String getVideoID() {
		return playlistID;
	}

	public void setVideoID(String playlistID) {
		this.playlistID = playlistID;
	}
}
