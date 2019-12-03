package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.http.UnmarkLocalVideoSegRequest;
import mashup.http.UnmarkLocalVideoSegResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class UnmarkLocalVideoSegmentHandlerTest extends LambdaTest {

    @Test
    public void testUnmarkLocalVideoSegmentHandler() {
        UnmarkLocalVideoSegmentHandler handler = new UnmarkLocalVideoSegmentHandler();
        UnmarkLocalVideoSegRequest input = new UnmarkLocalVideoSegRequest("1");
        Context ctx = createContext("mark");

        UnmarkLocalVideoSegResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
