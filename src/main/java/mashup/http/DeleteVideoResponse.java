package mashup.http;

public class DeleteVideoResponse {
	public final int statusCode;
	public final String error;
	
	public DeleteVideoResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteVideoResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "CreatePlaylist()";
	}
}
