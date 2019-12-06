package mashup;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.db.VideosDAO;
import mashup.http.CreatePlaylistRequest;
import mashup.http.CreatePlaylistResponse;
import mashup.model.Playlist;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreatePlaylistHandlerTest {

    private static CreatePlaylistRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new CreatePlaylistRequest("Fav Star Trek Vids");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testCreatePlaylistHandler() {
        CreatePlaylistHandler handler = new CreatePlaylistHandler();
        Context ctx = createContext();

		try {
	        List<Playlist> playlists = null;
			playlists = VideosDAO.videosDAO().getPlaylists();
			for (int i = 0; i < playlists.size(); i++) {
				System.out.print(playlists.get(i).toString() + "\n");
			}
			
			CreatePlaylistResponse output = handler.handleRequest(input, ctx);
	        Playlist p = new Playlist("Fav Star Trek Vids");
	        
			playlists = VideosDAO.videosDAO().getPlaylists();
			for (int i = 0; i < playlists.size(); i++) {
				System.out.print(playlists.get(i).toString() + "\n");
			}
	        VideosDAO.videosDAO().deletePlaylist(p.getId());
	        Assert.assertEquals(playlists.get(playlists.size()-1).toString(), p.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO: validate output here if needed.
    }
}
