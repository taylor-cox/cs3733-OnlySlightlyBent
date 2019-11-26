package mashup.http;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mashup.model.Playlist;

public class AllPlaylistResponse {
	public final List<Playlist> list;
	public final int statusCode;
	public final String error;
	
	public AllPlaylistResponse(List<Playlist> list, int code) {
		this.list = list;
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
