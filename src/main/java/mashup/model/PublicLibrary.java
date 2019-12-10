package mashup.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PublicLibrary {
	HashMap<String, PublicSegment> videos = new HashMap<String, PublicSegment>();
	
	public PublicLibrary(ArrayList<PublicSegment> videos) {
		for(PublicSegment v : videos)
			this.videos.put(v.ID, v);
	}
	
	public PublicLibrary() { }
	
	public List<PublicSegment> getVideos() {
		List<PublicSegment> videos = new ArrayList<PublicSegment>();
		for(PublicSegment v : this.videos.values())
			videos.add(v);
		return videos;
	}
	
	public boolean addFileName(String ID, String fileName) {
		PublicSegment vid = videos.get(ID);
		if(vid != null) {
			vid.setFileName(fileName);
			return true;
		}
		return false;
	}
	
	public boolean addPublicSegment(PublicSegment ps) {
		videos.put(ps.ID, ps);
		return true;
	}
}
