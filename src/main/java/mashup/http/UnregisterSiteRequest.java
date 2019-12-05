package mashup.http;

public class UnregisterSiteRequest {
	String url;
	
	public UnregisterSiteRequest(String url) {
		this.url = url;
	}
	
	public UnregisterSiteRequest() {}
	
	public String getUrl () {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}
