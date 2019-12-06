package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.http.AllSitesResponse;
import mashup.http.ListRemoteSitesRequest;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListRemoteSitesHandlerTest extends LambdaTest {

    @Test
    public void testListRemoteSitesHandler() {
        ListRemoteSitesHandler handler = new ListRemoteSitesHandler();
        Context ctx = createContext("listremotesites");
        
        handler.handleRequest(new ListRemoteSitesRequest(), ctx);
        
    }
}
