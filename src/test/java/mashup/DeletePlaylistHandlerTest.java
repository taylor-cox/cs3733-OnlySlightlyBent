package mashup;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.db.VideosDAO;
import mashup.http.CreatePlaylistRequest;
import mashup.http.DeletePlaylistRequest;
import mashup.http.DeletePlaylistResponse;
import mashup.model.Playlist;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DeletePlaylistHandlerTest extends LambdaTest {

    private static DeletePlaylistRequest input;

    @BeforeClass
    public static void DeleteInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new DeletePlaylistRequest("Fav Star Trek Vids");
    }

    private Context DeleteContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testDeletePlaylistHandler() {
        DeletePlaylistHandler handler = new DeletePlaylistHandler();
        Context ctx = DeleteContext();
        boolean doesContainPlaylist = false;

        Playlist p = new Playlist("Fav Star Trek Vids");
        List<Playlist> playlists = null;
		try {
	        VideosDAO.videosDAO().addPlaylist(p);
			playlists = VideosDAO.videosDAO().getPlaylists();
			for (int i = 0; i < playlists.size(); i++) {
				System.out.print(playlists.get(i).toString() + "\n");
		        if (playlists.get(i).getId().matches(p.getId())) doesContainPlaylist = true;
			}
	        Assert.assertEquals(doesContainPlaylist, true);
			
	        doesContainPlaylist = false;
	        DeletePlaylistResponse output = handler.handleRequest(input, ctx);
			playlists = VideosDAO.videosDAO().getPlaylists();
			for (int i = 0; i < playlists.size(); i++) {
				System.out.print(playlists.get(i).toString() + "\n");
		        if (playlists.get(i).getId() == p.getId()) doesContainPlaylist = true;
			}
	        Assert.assertEquals(doesContainPlaylist, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO: validate output here if needed.
    }
}
