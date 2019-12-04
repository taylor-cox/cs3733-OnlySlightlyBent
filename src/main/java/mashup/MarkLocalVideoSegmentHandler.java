package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.AppendVideoResponse;
import mashup.http.MarkLocalVideoSegRequest;
import mashup.http.MarkLocalVideoSegResponse;

public class MarkLocalVideoSegmentHandler implements RequestHandler<MarkLocalVideoSegRequest, MarkLocalVideoSegResponse> {
	
	public boolean markVideo(String videoID) throws Exception {
		VideosDAO dao = VideosDAO.videosDAO();
		return dao.markVideo(videoID);
	}

    @Override
    public MarkLocalVideoSegResponse handleRequest(MarkLocalVideoSegRequest input, Context context) {
    	MarkLocalVideoSegResponse response;

        try {
			if(markVideo(input.getVideoID()))
				response = new MarkLocalVideoSegResponse(200);
			else
				response = new MarkLocalVideoSegResponse("Video could not be marked.", 403);
		} catch (Exception e) {
			response = new MarkLocalVideoSegResponse("Video could not be marked.", 403);
			e.printStackTrace();
		}
        
        return response;
    }

}
