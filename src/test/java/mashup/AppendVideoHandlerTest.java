package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.http.AppendVideoRequest;
import mashup.http.AppendVideoResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AppendVideoHandlerTest extends LambdaTest {

    @Test
    public void testAppendVideoHandler() {
        AppendVideoHandler handler = new AppendVideoHandler();
        Context ctx = createContext("append");
        
        AppendVideoRequest request = new AppendVideoRequest("5", "My Playlist");
        AppendVideoResponse output = handler.handleRequest(request, ctx);
        System.out.println(output.statusCode + ", " + output.error);
    }
}
