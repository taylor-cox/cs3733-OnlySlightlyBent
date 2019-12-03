package mashup.http;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mashup.model.Playlist;
import mashup.model.Site;

public class AllSitesResponse {
	public final List<Site> list;
	public final int statusCode;
	public final String error;
	
	public AllSitesResponse(List<Site> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllSitesResponse(int code, String errorMessage) {
		this.list = new ArrayList<Site>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "NoSites"; }
		return "AllSites(" + list.size() + ")";
	}
}
