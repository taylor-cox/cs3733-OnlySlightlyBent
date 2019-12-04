package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.http.RegisterRemoteSiteRequest;
import mashup.http.RegisterSiteRequest;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class RegisterRemoteSiteHandlerTest extends LambdaTest {

    @Test
    public void testRegisterRemoteSiteHandler() {
        RegisterRemoteSiteHandler handler = new RegisterRemoteSiteHandler();
        Context ctx = createContext("registerremotesite");
        
        handler.handleRequest(new RegisterSiteRequest("http://google.com/"), ctx);
        
    }
}
