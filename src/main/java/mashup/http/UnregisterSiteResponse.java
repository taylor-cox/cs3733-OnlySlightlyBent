package mashup.http;

public class UnregisterSiteResponse {
	public final int statusCode;
	public final String error;
	
	public UnregisterSiteResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public UnregisterSiteResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		return "registerSite()";
	}

}
