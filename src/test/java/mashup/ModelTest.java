package mashup;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import mashup.model.Library;
import mashup.model.Playlist;
import mashup.model.PlaylistEntry;
import mashup.model.PublicLibrary;
import mashup.model.PublicSegment;
import mashup.model.Site;
import mashup.model.Video;

public class ModelTest {

	@Test
	public void testLibrary() {
		Library lib = new Library();
		lib.addVideo(new Video("", "", "", ""));
		lib.addFileName("", "");
		lib.getFileNames();
		lib.getVideos();
	}
	
	@Test
	public void testVideo() {
		Video v1 = new Video("", "", "", "");
		Video v2 = new Video("", "", "", "", true);
		
		v1.getCharacter();
		v1.getFileName();
		v1.getID();
		v1.getIsMarked();
		v1.getQuote();
		
		v1.setCharacter("");
		v1.setFileName("");
		v1.setID("");
		v1.setIsMarked(false);
		v1.setQuote("");
		v1.toString();
	}
	
	@Test
	public void testPlaylist() {
		Playlist p = new Playlist();
		Playlist p2 = new Playlist("");
		
		p.addPlaylistEntry(new PlaylistEntry("", 0));
		p.getEntries();
		p.getId();
		p.setId("");
		p.setEntries(new ArrayList<PlaylistEntry>());
	}
	
	@Test
	public void testPlaylistEntry() {
		PlaylistEntry pe = new PlaylistEntry("", 0);
		pe.getIndex();
		pe.getVideoID();
		pe.setIndex(0);
		pe.setVideoID("");
	}
	
	@Test
	public void testPublicLibrary() {
		PublicLibrary pl = new PublicLibrary();
		ArrayList<PublicSegment> alps = new ArrayList<PublicSegment>();
		alps.add(new PublicSegment("", "", "", ""));
		PublicLibrary pl2 = new PublicLibrary(alps);
		
		pl.addFileName("", "");
		pl.addPublicSegment(new PublicSegment("", "", "", ""));
		pl.getVideos();
	}
	
	@Test
	public void testPublicSegment() {
		PublicSegment ps = new PublicSegment("", "", "", "");
		ps.getCharacter();
		ps.getFileName();
		ps.getID();
		ps.getQuote();
		
		ps.setCharacter("");
		ps.setFileName("");
		ps.setID("");
		ps.setQuote("");
		ps.toString();
	}
	
	@Test
	public void testSite() {
		Site site = new Site("");
		site.getUrl();
		site.setUrl("");
	}
}
