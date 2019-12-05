package mashup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import mashup.db.VideosDAO;
import mashup.http.AllSitesResponse;
import mashup.http.ListRemoteSitesRequest;
import mashup.model.Site;

public class ListRemoteSitesHandler implements RequestHandler<ListRemoteSitesRequest, AllSitesResponse> {

	public LambdaLogger logger;
	
	List<Site> getRegisteredSites() throws Exception {
		
		/**
		 * Get the created Library's registered sites
		 */
		VideosDAO dao = VideosDAO.videosDAO();
		return dao.getRegisteredSites();
	}
	
    public AllSitesResponse handleRequest(ListRemoteSitesRequest input, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda Function to list all remote sites.");
        
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
