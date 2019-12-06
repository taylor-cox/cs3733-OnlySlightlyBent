package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.CreatePlaylistRequest;
import mashup.http.CreatePlaylistResponse;
import mashup.model.Playlist;

public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResponse> {

    @Override
    public CreatePlaylistResponse handleRequest(CreatePlaylistRequest input, Context context) {
        context.getLogger().log("Input: " + input);
    	VideosDAO dao = VideosDAO.videosDAO();
    	CreatePlaylistResponse response;
    	try {
			dao.addPlaylist(new Playlist(input.getPlaylistID()));
<<<<<<< HEAD
			response = new CreatePlaylistResponse(200);
=======
>>>>>>> branch 'master' of https://taylor-cox@github.com/taylor-cox/OnlySlightlyBent.git
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new CreatePlaylistResponse(400, "Could not create playlist.");
		}
        // TODO: implement your handler
        return response;
    }

}
