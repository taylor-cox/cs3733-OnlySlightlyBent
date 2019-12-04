package mashup.http;

public class AppendVideoResponse {
	public final int statusCode;
	public final String error;
	
	public AppendVideoResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public AppendVideoResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "AppendVideo()";
	}
}
