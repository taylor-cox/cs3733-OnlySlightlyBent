package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.DeleteVideoRequest;
import mashup.http.DeleteVideoResponse;
import mashup.http.UnmarkLocalVideoSegResponse;

public class DeleteVideoHandler implements RequestHandler<DeleteVideoRequest, DeleteVideoResponse> {
	
	boolean deleteVideo(String videoID) throws Exception {
		return VideosDAO.videosDAO().deleteVideo(videoID);
	}

    @Override
    public DeleteVideoResponse handleRequest(DeleteVideoRequest input, Context context) {
        context.getLogger().log("Input: " + input);
        DeleteVideoResponse response = new DeleteVideoResponse(403, "Video could not be marked.");
        
        try {
			if(deleteVideo(input.getVideoID()))
				response = new DeleteVideoResponse(200);
		} catch (Exception e) {
			response = new DeleteVideoResponse(403, "Video could not be marked.");
			e.printStackTrace();
		}
        
        return response;
    }

}
