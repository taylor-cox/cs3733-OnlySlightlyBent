package mashup.http;

public class RemoveVideoResponse {
	public final int statusCode;
	public final String error;
	
	public RemoveVideoResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public RemoveVideoResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "AppendVideo()";
	}
}
