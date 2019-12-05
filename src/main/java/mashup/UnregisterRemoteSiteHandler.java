package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.UnregisterSiteRequest;
import mashup.http.UnregisterSiteResponse;

public class UnregisterRemoteSiteHandler implements RequestHandler<UnregisterSiteRequest, UnregisterSiteResponse> {

		public boolean unregisterSite(String url) throws Exception {
			VideosDAO dao = VideosDAO.videosDAO();
			return dao.unregisterRemoteSite(url);
		}
		
	    @Override
	    public UnregisterSiteResponse handleRequest(UnregisterSiteRequest input, Context context) {
	    	UnregisterSiteResponse response;
	    	
	    	try {
				if(unregisterSite(input.getUrl()))
					response = new UnregisterSiteResponse(200);
				else
					response = new UnregisterSiteResponse(401, "Site is already removed.");
			} catch (Exception e) {
				response = new UnregisterSiteResponse(402, "Site could not be removed at this time oh no.");
				e.printStackTrace();
			}
	        
	        return response;
	    }

}

//hi ben its me!!!
