package mashup.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Library {
	HashMap<String, Video> videos = new HashMap<String, Video>();
	
	public Library() { }
	
	public List<Video> getVideos() {
		List<Video> videos = new ArrayList<Video>();
		for(Video v : this.videos.values())
			videos.add(v);
		return videos;
	}
	
	public Iterator<String> getFileNames() {
		List<String> fileNames = new ArrayList<String>();
		
		for(Video v : videos.values())
			fileNames.add(v.fileName);
		
		return fileNames.iterator();
	}
	
	public boolean addFileName(String ID, String fileName) {
		Video vid = videos.get(ID);
		if(vid != null) {
			vid.setFileName(fileName);
			return true;
		}
		return false;
	}
	
	public boolean addVideo(Video v) {
		Video vid = videos.get(v.getID());
		if(vid == null) {
			this.videos.put(v.getID(), v);
			return true;
		}
		return false;
	}
}
