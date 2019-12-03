package mashup.http;

public class MarkLocalVideoSegResponse {
	public final String response;
	public final int httpCode;

	public MarkLocalVideoSegResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}

	// 200 means success
	public MarkLocalVideoSegResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public MarkLocalVideoSegResponse (int i) {
		this.response = "";
		this.httpCode = i;
	}

	public String toString() {
		return "Response(" + response + ")";
	}

}
