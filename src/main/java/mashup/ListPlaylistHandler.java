package mashup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.AllPlaylistResponse;
import mashup.http.AllVideosResponse;
import mashup.model.Library;
import mashup.model.Playlist;
import mashup.model.Video;

public class ListPlaylistHandler implements RequestHandler<Object, AllPlaylistResponse> {
	
	public LambdaLogger logger;
	
	List<Playlist> getPlaylists() throws Exception {
		/**
		 * Get Library returns the available library, consisting of s3 segments we uploaded.
		 */
		VideosDAO dao = new VideosDAO();
		return dao.getPlaylists();
	}
	
    @Override
    public AllPlaylistResponse handleRequest(Object input, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lamdba Function to list all playlists.");
        
        AllPlaylistResponse response;
        
        try {
			List<Playlist> list = getPlaylists();
			response = new AllPlaylistResponse(list, 200);
		} catch (Exception e) {
			response = new AllPlaylistResponse(403, e.getMessage());
		}
        
        return response;
    }

}
