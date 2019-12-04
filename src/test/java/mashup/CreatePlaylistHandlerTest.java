package mashup;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.db.VideosDAO;
import mashup.http.CreatePlaylistRequest;
import mashup.model.Playlist;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreatePlaylistHandlerTest {

    private static CreatePlaylistRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new CreatePlaylistRequest("123456789", "Fav Star Trek Vids");
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

        
        String output = handler.handleRequest(input, ctx);
        Playlist p = new Playlist("123456789", "Fav Star Trek Vids");
        List<Playlist> playlists = null;
		try {
			playlists = VideosDAO.videosDAO().getPlaylists();
			System.out.print(playlists.get(playlists.size()-1).toString());
	        VideosDAO.videosDAO().deletePlaylist(p);
	        Assert.assertEquals(playlists.get(playlists.size()-1).toString(), p.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO: validate output here if needed.
    }
}
