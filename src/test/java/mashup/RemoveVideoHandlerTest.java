package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.http.RemoveVideoRequest;
import mashup.http.RemoveVideoResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class RemoveVideoHandlerTest extends LambdaTest {

    @Test
    public void testRemoveVideoHandler() {
        RemoveVideoHandler handler = new RemoveVideoHandler();
        RemoveVideoRequest input = new RemoveVideoRequest("5", "My Playlist");
        Context ctx = createContext("remove");

        RemoveVideoResponse resp = handler.handleRequest(input, ctx);
        System.out.println(resp.statusCode + ", " + resp.error);

        // TODO: validate output here if needed.
//        Assert.assertEquals("Hello from Lambda!", output);
    }
}
