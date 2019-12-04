package mashup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import mashup.db.VideosDAO;
import mashup.http.RegisterSiteRequest;
import mashup.http.RegisterSiteResponse;

public class RegisterRemoteSiteHandler implements RequestHandler<RegisterSiteRequest, RegisterSiteResponse> {

	public boolean registerSite(String url) throws Exception {
		VideosDAO dao = VideosDAO.videosDAO();
		return dao.registerRemoteSite(url);
	}
	
    @Override
    public RegisterSiteResponse handleRequest(RegisterSiteRequest input, Context context) {
    	RegisterSiteResponse response;
    	
    	try {
			if(registerSite(input.getUrl()))
				response = new RegisterSiteResponse(200);
			else
				response = new RegisterSiteResponse(401, "Site is already registered.");
		} catch (Exception e) {
			response = new RegisterSiteResponse(402, "Site could not be registered at this time.");
			e.printStackTrace();
		}
        
        return response;
    }

}
