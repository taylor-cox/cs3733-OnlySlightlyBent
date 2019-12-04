package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.http.AllSitesResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListRemoteSitesHandlerTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testListRemoteSitesHandler() {
        ListRemoteSitesHandler handler = new ListRemoteSitesHandler();
        Context ctx = createContext();
//        String output = handler.handleRequest(input, ctx);
        AllSitesResponse output = handler.handleRequest(input, ctx);
//        Assert.assertEquals("Hello from Lambda!", output);
        Assert.assertEquals("Hello from Lambda!", output.toString());
    }
}
