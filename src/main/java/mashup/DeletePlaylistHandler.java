package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.DeletePlaylistRequest;
import mashup.http.DeletePlaylistResponse;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest, DeletePlaylistResponse> {

    @Override
    public DeletePlaylistResponse handleRequest(DeletePlaylistRequest input, Context context) {
        context.getLogger().log("Input: " + input);
        DeletePlaylistResponse response;
        try {
			VideosDAO.videosDAO().deletePlaylist(input.getPlaylistID());
			response = new DeletePlaylistResponse(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = new DeletePlaylistResponse(400, "Could not delete playlist.");
			e.printStackTrace();
		}
        // TODO: implement your handler
        return response;
    }

}
