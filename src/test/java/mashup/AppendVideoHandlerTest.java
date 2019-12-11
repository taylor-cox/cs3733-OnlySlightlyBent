package mashup;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.db.VideosDAO;
import mashup.http.AppendVideoRequest;
import mashup.http.AppendVideoResponse;
import mashup.http.CreatePlaylistResponse;
import mashup.model.Playlist;
import mashup.model.PlaylistEntry;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AppendVideoHandlerTest extends LambdaTest {

    @Test
    public void testAppendVideoHandler() {
        AppendVideoHandler handler = new AppendVideoHandler();
        AppendVideoResponse output = null;
        Context ctx = createContext("append");
        Playlist p = new Playlist("Append/Remove Test Playlist");
        String v = "1";
        
        AppendVideoRequest request = new AppendVideoRequest(v, p.getId());
        boolean doesPlaylistContainVideo = false;

		try {
			VideosDAO.videosDAO().addPlaylist(p);
	        List<Playlist> playlists = null;
	        List<PlaylistEntry> videos = null;
			playlists = VideosDAO.videosDAO().getPlaylists();
			for (int i = 0; i < playlists.size(); i++) {
				if (playlists.get(i).getId().matches(p.getId())) {
					videos = playlists.get(i).getEntries();
				}
			}
			for (int i = 0; i < videos.size(); i++) {
				System.out.print(videos.get(i).toString() + "\n");
		        if (videos.get(i).getVideoID().matches(v)) doesPlaylistContainVideo = true;
			}
	        Assert.assertEquals(doesPlaylistContainVideo, false);
	        
	        output = handler.handleRequest(request, ctx);
			
	        doesPlaylistContainVideo = false;
			playlists = VideosDAO.videosDAO().getPlaylists();
			playlists = VideosDAO.videosDAO().getPlaylists();
			for (int i = 0; i < playlists.size(); i++) {
				if (playlists.get(i).getId().matches(p.getId())) {
					videos = playlists.get(i).getEntries();
				}
			}
			for (int i = 0; i < videos.size(); i++) {
				System.out.print(videos.get(i).toString() + "\n");
		        if (videos.get(i).getVideoID().matches(v)) doesPlaylistContainVideo = true;
			}
	        Assert.assertEquals(doesPlaylistContainVideo, true);
	        VideosDAO.videosDAO().deletePlaylist(p.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(output.statusCode + ", " + output.error);
    }
}