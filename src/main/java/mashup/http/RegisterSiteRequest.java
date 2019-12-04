package mashup.http;

public class RegisterSiteRequest {
	String url;
	
	public RegisterSiteRequest(String url) {
		this.url = url;
	}
	
	public RegisterSiteRequest() {}
	
	public String getUrl () {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
