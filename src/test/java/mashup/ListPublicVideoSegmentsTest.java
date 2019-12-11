package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import mashup.http.AllVideosResponse;
import mashup.http.PublicVideosResponse;
import mashup.model.Video;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListPublicVideoSegmentsTest extends LambdaTest {
	
    @Test
    public void testListPublicVideoSegments() throws IOException {
    	ListPublicVideoSegments handler = new ListPublicVideoSegments();
        PublicVideosResponse resp = handler.handleRequest(null, createContext("listPublicVideoSegments"));
    }
}
