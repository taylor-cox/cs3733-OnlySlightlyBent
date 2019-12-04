package mashup.http;

public class UnmarkLocalVideoSegResponse {
	public final String response;
	public final int httpCode;

	public UnmarkLocalVideoSegResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}

	// 200 means success
	public UnmarkLocalVideoSegResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public UnmarkLocalVideoSegResponse (int i) {
		this.response = "";
		this.httpCode = i;
	}

	public String toString() {
		return "Response(" + response + ")";
	}

}
