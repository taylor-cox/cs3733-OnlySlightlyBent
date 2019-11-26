package mashup.model;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {
	String id;
	ArrayList<PlaylistEntry> entries = new ArrayList<PlaylistEntry>();
	
	@Override
	public String toString() {
		return "Playlist [id=" + id + ", entries=" + entries + "]";
	}

	public Playlist() {}
	public Playlist(String id) {
		this.id = id;
	}
	
	public void addPlaylistEntry(PlaylistEntry pe) {
		this.entries.add(pe);
	}
}
