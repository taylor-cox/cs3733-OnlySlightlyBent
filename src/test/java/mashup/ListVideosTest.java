package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import mashup.http.AllVideosResponse;
import mashup.model.Video;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListVideosTest extends LambdaTest {
	
    @Test
    public void testGetList() throws IOException {
    	ListAllVideosHandler handler = new ListAllVideosHandler();

        AllVideosResponse resp = handler.handleRequest(null, createContext("list"));
        
        boolean hasSpock = false;
        
        for (Video v : resp.list) {
        	if (v.character.equals("Spock")) { hasSpock = true; break; }
        }
       
        Assert.assertTrue("Spock needs to be in table.", hasSpock);
        Assert.assertEquals(200, resp.statusCode);
    }
}
