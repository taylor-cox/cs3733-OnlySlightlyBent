package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.AppendVideoRequest;
import mashup.http.AppendVideoResponse;
import mashup.http.RemoveVideoRequest;
import mashup.http.RemoveVideoResponse;

public class RemoveVideoHandler implements RequestHandler<RemoveVideoRequest, RemoveVideoResponse> {

	public boolean removeVideo(String videoID, String playlistID) throws Exception {
		VideosDAO dao = VideosDAO.videosDAO();
		return dao.removeVideoFromPlaylist(videoID, playlistID);
	}

    @Override
    public RemoveVideoResponse handleRequest(RemoveVideoRequest input, Context context) {
        context.getLogger().log("Input: " + input);
        
        RemoveVideoResponse response;

        try {
			if(removeVideo(input.getVideoID(), input.getPlaylistID()))
				response = new RemoveVideoResponse(200);
			else
				response = new RemoveVideoResponse(403, "Video not in playlist");
		} catch (Exception e) {
			response = new RemoveVideoResponse(403, "Video could not be removed");
			e.printStackTrace();
		}
        
        return response;
    }

}
