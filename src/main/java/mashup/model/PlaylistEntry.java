package mashup.model;

public class PlaylistEntry {
	int index;
	String videoID;
	
	public PlaylistEntry(String videoID, int index) {
		this.index = index;
		this.videoID = videoID;
	}
	
	public String getVideoID() {
		return this.videoID;
	}

	@Override
	public String toString() {
		return "PlaylistEntry [index=" + index + ", videoID=" + videoID + "]";
	}
}
