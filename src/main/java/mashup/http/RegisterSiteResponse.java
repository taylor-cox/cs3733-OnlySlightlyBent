package mashup.http;

public class RegisterSiteResponse {
	public final int statusCode;
	public final String error;
	
	public RegisterSiteResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public RegisterSiteResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "registerSite()";
	}
}
