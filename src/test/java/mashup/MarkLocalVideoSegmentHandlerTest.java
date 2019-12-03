package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.MarkLocalVideoSegmentHandler;
import mashup.http.MarkLocalVideoSegRequest;
import mashup.http.MarkLocalVideoSegResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class MarkLocalVideoSegmentHandlerTest extends LambdaTest {

    @Test
    public void testMarkLocalVideoSegmentHandler() {
        MarkLocalVideoSegmentHandler handler = new MarkLocalVideoSegmentHandler();
        MarkLocalVideoSegRequest input = new MarkLocalVideoSegRequest("1");
        Context ctx = createContext("mark");

        MarkLocalVideoSegResponse output = handler.handleRequest(input, ctx);
        System.out.println(output.httpCode + ", " + output.response);
    }
}
