package mashup.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PlaylistEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<PlaylistEntry> entries) {
		this.entries = entries;
	}

	String id;
	String name;
	List<PlaylistEntry> entries = new ArrayList<PlaylistEntry>();
	
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
