package mashup;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.db.VideosDAO;
import mashup.http.RemoveVideoRequest;
import mashup.http.RemoveVideoResponse;
import mashup.model.Library;
import mashup.model.Playlist;
import mashup.model.PlaylistEntry;
import mashup.model.Video;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class RemoveVideoHandlerTest extends LambdaTest {

    @Test
    public void testRemoveVideoHandler() {
        RemoveVideoHandler handler = new RemoveVideoHandler();
        RemoveVideoResponse output = null;
        Context ctx = createContext("remove");
        Playlist p = new Playlist("Append/Remove Test Playlist");
        Library l = null;
		try {
			l = VideosDAO.videosDAO().getLibrary();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Video v = l.getVideos().get(0);
        
        RemoveVideoRequest request = new RemoveVideoRequest(v.getID(), p.getId());
        boolean doesPlaylistContainVideo = false;

		try {
			VideosDAO.videosDAO().addPlaylist(p);
			VideosDAO.videosDAO().appendVideoToPlaylist(v.getID(), p.getId());
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
		        if (videos.get(i).getVideoID().matches(v.getID())) doesPlaylistContainVideo = true;
			}
	        Assert.assertEquals(doesPlaylistContainVideo, true);
	        
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
		        if (videos.get(i).getVideoID().matches(v.getID())) doesPlaylistContainVideo = true;
			}
	        Assert.assertEquals(doesPlaylistContainVideo, false);
	        VideosDAO.videosDAO().deletePlaylist(p.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(output.statusCode + ", " + output.error);
    }
}