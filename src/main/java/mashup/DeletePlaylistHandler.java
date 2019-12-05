package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.DeletePlaylistRequest;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest, String> {

    @Override
    public String handleRequest(DeletePlaylistRequest input, Context context) {
        context.getLogger().log("Input: " + input);
        try {
			VideosDAO.videosDAO().deletePlaylist(input.getPlaylistID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO: implement your handler
        return "Hello from Lambda!";
    }

}
