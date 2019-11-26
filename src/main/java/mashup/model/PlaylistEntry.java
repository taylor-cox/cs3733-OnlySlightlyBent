package mashup.model;

public class PlaylistEntry {
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

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
