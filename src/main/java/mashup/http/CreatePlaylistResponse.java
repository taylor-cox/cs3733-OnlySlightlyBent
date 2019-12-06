package mashup.http;

public class CreatePlaylistResponse {
	public final int statusCode;
	public final String error;
	
	public CreatePlaylistResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public CreatePlaylistResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "CreatePlaylist()";
	}
}
