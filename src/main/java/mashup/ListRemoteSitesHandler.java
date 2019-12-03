package mashup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import mashup.db.VideosDAO;
import mashup.http.AllPlaylistResponse;
import mashup.http.AllSitesResponse;
import mashup.http.AllVideosResponse;
import mashup.model.Library;
import mashup.model.Playlist;
import mashup.model.Site;
import mashup.model.Video;

public class ListRemoteSitesHandler implements RequestHandler<Object, AllSitesResponse> {

	public LambdaLogger logger;
	
	List<Site> getRegisteredSites() throws Exception {
		
		/**
		 * Get the created Library's registered sites
		 */
		VideosDAO dao = new VideosDAO();
		return dao.getRegisteredSites();
	}
	
    @Override
    public AllSitesResponse handleRequest(Object input, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lamdba Function to list all remote sites.");
        
        AllSitesResponse response;
        
        try {
        	List<Site> list = getRegisteredSites();
        	response = new AllSitesResponse(list, 200); 
        } catch (Exception e) {
        	response = new AllSitesResponse(401, e.getMessage());
        }
         return response;
        }

}
