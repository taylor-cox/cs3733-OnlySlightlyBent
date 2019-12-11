package mashup;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import mashup.http.AllVideosResponse;
import mashup.http.DeleteVideoRequest;
import mashup.http.DeleteVideoResponse;
import mashup.http.UploadVideoRequest;
import mashup.http.UploadVideoResponse;
import mashup.model.Video;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class UploadVideoHandlerTest extends LambdaTest {

	@Test
    public void testUploadDeleteVideo() throws IOException {
    	UploadVideoHandler handler = new UploadVideoHandler();
    	UploadVideoRequest request = new UploadVideoRequest("-100", "uploadTestCharacter", "uploadTestQuote", "");
        UploadVideoResponse resp = handler.handleRequest(request, createContext("uploadVideoTest"));
        
        request.getBase64EncodedValue();
        request.getCharacter();
        request.getQuote();
        request.getVideoID();
        
        request.setBase64EncodedValue("");
        request.setCharacter("");
        request.setQuote("");
        request.setVideoID("");
        
        DeleteVideoHandler handler2 = new DeleteVideoHandler();
        DeleteVideoRequest request2 = new DeleteVideoRequest("19", "");
        DeleteVideoResponse resp2 = handler2.handleRequest(request2, createContext("delteVideoTest"));
        
        request2.getPlaylistID();
        request2.getVideoID();
        
        request2.setPlaylistID("");
        request2.setVideoID("");
    }
}
