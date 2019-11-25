package mashup.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	Library library;
	List<Video> videos = new ArrayList<Video>();
	List<Site> registeredSites = new ArrayList<Site>();
	
	public Model() { 
		
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
		this.videos = library.getVideos();
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public List<Site> getRegisteredSites() {
		return registeredSites;
	}

	public void setRegisteredSites(List<Site> registeredSites) {
		this.registeredSites = registeredSites;
	}
}
