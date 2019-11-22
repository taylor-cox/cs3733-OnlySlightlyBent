package mashup.http;

public class UnregisterRemoteSiteRequest {
	String remoteSiteID;
	
	public UnregisterRemoteSiteRequest(String remoteSiteID) {
		this.remoteSiteID = remoteSiteID;
	}
	
	public UnregisterRemoteSiteRequest() {}
	
	public String getRemoteSiteID() {
		return remoteSiteID;
	}

	public void setRemoteSiteID(String remoteSiteID) {
		this.remoteSiteID = remoteSiteID;
	}
}
