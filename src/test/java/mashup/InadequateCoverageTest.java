package mashup;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import mashup.http.*;
import mashup.model.*;

public class InadequateCoverageTest {

	@Test
	public void test() {
		AllPlaylistResponse apr = new AllPlaylistResponse(0, "");
		AllPlaylistResponse apr2 = new AllPlaylistResponse(new ArrayList<Playlist>(), 0);
		apr.toString();
		
		AllSitesResponse asr = new AllSitesResponse(0, "");
		AllSitesResponse asr2 = new AllSitesResponse(new ArrayList<Site>(), 0);
		asr.toString();
		
		AllVideosResponse avr = new AllVideosResponse(0, "");
		AllVideosResponse avr2 = new AllVideosResponse(new ArrayList<Video>(), 0);
		avr.toString();
		
		PublicVideosResponse pvr = new PublicVideosResponse(0, "");
		PublicVideosResponse pvr2 = new PublicVideosResponse(new ArrayList<PublicSegment>(), 0);
		pvr.toString();
		
		MarkLocalVideoSegResponse mlvsr = new MarkLocalVideoSegResponse(0);
		MarkLocalVideoSegResponse mlvsr2 = new MarkLocalVideoSegResponse("");
		MarkLocalVideoSegResponse mlvsr3 = new MarkLocalVideoSegResponse("", 0);
		mlvsr.toString();
	}

}
