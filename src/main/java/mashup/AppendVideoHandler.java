package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.AppendVideoRequest;
import mashup.http.AppendVideoResponse;

public class AppendVideoHandler implements RequestHandler<AppendVideoRequest, AppendVideoResponse> {
	
	public boolean appendVideo(String videoID, String playlistID) throws Exception {
		VideosDAO dao = VideosDAO.videosDAO();
		return dao.appendVideoToPlaylist(videoID, playlistID);
	}

    @Override
    public AppendVideoResponse handleRequest(AppendVideoRequest input, Context context) {
        AppendVideoResponse response = new AppendVideoResponse(403, "Video already in playlist");

        try {
			if(appendVideo(input.getVideoID(), input.getPlaylistID()))
				response = new AppendVideoResponse(200);
		} catch (Exception e) {
			response = new AppendVideoResponse(403, "Video could not be appended");
			e.printStackTrace();
		}
        
        return response;
    }

}
