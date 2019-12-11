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
        Playlist p = new Playlist("Fav Star Trek Vids");
        Context ctx = createContext();
        boolean doesContainPlaylist = false;

		try {
	        List<Playlist> playlists = null;
			playlists = VideosDAO.videosDAO().getPlaylists();
			for (int i = 0; i < playlists.size(); i++) {
				System.out.print(playlists.get(i).toString() + "\n");
		        if (playlists.get(i).getId().matches(p.getId())) doesContainPlaylist = true;
			}
	        Assert.assertEquals(doesContainPlaylist, false);
			
	        doesContainPlaylist = false;
			CreatePlaylistResponse output = handler.handleRequest(input, ctx);
			playlists = VideosDAO.videosDAO().getPlaylists();
			for (int i = 0; i < playlists.size(); i++) {
				System.out.print(playlists.get(i).toString() + "\n");
		        if (playlists.get(i).getId().matches(p.getId())) doesContainPlaylist = true;
			}
	        VideosDAO.videosDAO().deletePlaylist(p.getId());
	        Assert.assertEquals(doesContainPlaylist, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO: validate output here if needed.
    }
}