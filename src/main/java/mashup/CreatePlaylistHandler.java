package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.CreatePlaylistRequest;
import mashup.model.Playlist;

public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest, String> {

    @Override
    public String handleRequest(CreatePlaylistRequest input, Context context) {
        context.getLogger().log("Input: " + input);
    	VideosDAO dao = VideosDAO.videosDAO();
    	try {
			dao.addPlaylist(new Playlist(input.getPlaylistID()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO: implement your handler
        return "Hello from Lambda!";
    }

}
