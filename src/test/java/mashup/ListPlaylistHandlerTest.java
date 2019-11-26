package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.http.AllPlaylistResponse;
import mashup.http.AllVideosResponse;
import mashup.model.Playlist;
import mashup.model.Video;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListPlaylistHandlerTest extends LambdaTest {

	@Test
    public void testGetPlaylists() throws IOException {
    	ListPlaylistHandler handler = new ListPlaylistHandler();
    	
        AllPlaylistResponse resp = handler.handleRequest(null, createContext("list"));
        
        for (Playlist p : resp.list) {
        	System.out.println(p.toString());
//        	if (p.character.equals("Spock")) { hasSpock = true; break; }
        }
//        Assert.assertTrue("Spock needs to be in table.", hasSpock);
        Assert.assertEquals(200, resp.statusCode);
    }
}
