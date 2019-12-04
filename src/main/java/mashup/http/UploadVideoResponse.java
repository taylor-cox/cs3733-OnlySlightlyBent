package mashup.http;

public class UploadVideoResponse {
	public final String response;
	public final int httpCode;

	public UploadVideoResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}

	// 200 means success
	public UploadVideoResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}

	public String toString() {
		return "Response(" + response + ")";
	}

}
