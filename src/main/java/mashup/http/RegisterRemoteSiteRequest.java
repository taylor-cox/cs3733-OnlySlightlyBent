package mashup.http;

public class RegisterRemoteSiteRequest {
	String remoteSiteID;
	
	public RegisterRemoteSiteRequest(String remoteSiteID) {
		this.remoteSiteID = remoteSiteID;
	}
	
	public RegisterRemoteSiteRequest() {}
	
	public String getRemoteSiteID() {
		return remoteSiteID;
	}

	public void setRemoteSiteID(String remoteSiteID) {
		this.remoteSiteID = remoteSiteID;
	}
}
