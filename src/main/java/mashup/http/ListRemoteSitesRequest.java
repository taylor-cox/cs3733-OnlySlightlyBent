package mashup.http;

public class ListRemoteSitesRequest {
	String remoteSiteID;
	
	public ListRemoteSitesRequest(String remoteSiteID) {
		this.remoteSiteID = remoteSiteID;
	}
	
	public ListRemoteSitesRequest() {}
	
	public String getRemoteSiteID() {
		return remoteSiteID;
	}

	public void setRemoteSiteID(String remoteSiteID) {
		this.remoteSiteID = remoteSiteID;
	}
}
