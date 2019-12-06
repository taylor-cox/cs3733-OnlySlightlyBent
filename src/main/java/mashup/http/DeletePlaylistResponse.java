package mashup.http;

public class DeletePlaylistResponse {
	public final int statusCode;
	public final String error;
	
	public DeletePlaylistResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeletePlaylistResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "CreatePlaylist()";
	}
}
