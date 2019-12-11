package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.MarkLocalVideoSegRequest;
import mashup.http.MarkLocalVideoSegResponse;
import mashup.http.UnmarkLocalVideoSegRequest;
import mashup.http.UnmarkLocalVideoSegResponse;

public class UnmarkLocalVideoSegmentHandler implements RequestHandler<UnmarkLocalVideoSegRequest, UnmarkLocalVideoSegResponse> {
	public boolean markVideo(String videoID) throws Exception {
		VideosDAO dao = VideosDAO.videosDAO();
		return dao.unmarkVideo(videoID);
	}

    @Override
    public UnmarkLocalVideoSegResponse handleRequest(UnmarkLocalVideoSegRequest input, Context context) {
    	UnmarkLocalVideoSegResponse response = new UnmarkLocalVideoSegResponse("Video could not be marked.", 403);

        try {
			if(markVideo(input.getVideoID()))
				response = new UnmarkLocalVideoSegResponse(200);
		} catch (Exception e) {
			response = new UnmarkLocalVideoSegResponse("Video could not be marked.", 403);
			e.printStackTrace();
		}
        
        return response;
    }
}
