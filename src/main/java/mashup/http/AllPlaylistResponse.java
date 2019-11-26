package mashup.http;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mashup.model.Playlist;

public class AllPlaylistResponse {
	public final Collection<Playlist> list;
	public final int statusCode;
	public final String error;
	
	public AllPlaylistResponse(Collection<Playlist> list2, int code) {
		this.list = list2;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllPlaylistResponse(int code, String errorMessage) {
		this.list = new ArrayList<Playlist>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyVideos"; }
		return "AllPlaylists(" + list.size() + ")";
	}
}
