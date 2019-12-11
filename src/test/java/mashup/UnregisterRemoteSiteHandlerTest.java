package mashup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.http.DeleteVideoRequest;
import mashup.http.DeleteVideoResponse;
import mashup.http.RegisterSiteRequest;
import mashup.http.RegisterSiteResponse;
import mashup.http.UnregisterSiteRequest;
import mashup.http.UnregisterSiteResponse;
import mashup.http.UploadVideoRequest;
import mashup.http.UploadVideoResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class UnregisterRemoteSiteHandlerTest extends LambdaTest {

	@Test
    public void testUnregisterRemoteSite() throws IOException {
        RegisterRemoteSiteHandler handler2 = new RegisterRemoteSiteHandler();
        RegisterSiteRequest request2 = new RegisterSiteRequest("this is a test URL");
        RegisterSiteResponse resp2 = handler2.handleRequest(request2, createContext("registerSiteTest"));
        
        request2.getUrl();
        request2.setUrl("");
        
    	UnregisterRemoteSiteHandler handler = new UnregisterRemoteSiteHandler();
    	UnregisterSiteRequest request = new UnregisterSiteRequest("this is a test URL");
        UnregisterSiteResponse resp = handler.handleRequest(request, createContext("unregisterSiteTest"));
        
        request.getUrl();
        request.setUrl("");
    }
}
