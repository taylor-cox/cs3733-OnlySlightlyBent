package mashup.model;

import java.util.ArrayList;

public class Model {
	Library library;
	ArrayList<Video> videos = new ArrayList<Video>();
	ArrayList<Site> registeredSites = new ArrayList<Site>();
	
	public Model() { 
		
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public ArrayList<Video> getVideos() {
		return videos;
	}

	public void setVideos(ArrayList<Video> videos) {
		this.videos = videos;
	}

	public ArrayList<Site> getRegisteredSites() {
		return registeredSites;
	}

	public void setRegisteredSites(ArrayList<Site> registeredSites) {
		this.registeredSites = registeredSites;
	}
}
