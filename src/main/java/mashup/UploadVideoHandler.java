package mashup;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import mashup.db.VideosDAO;
import mashup.http.UploadVideoRequest;
import mashup.http.UploadVideoResponse;
import mashup.model.Video;

public class UploadVideoHandler implements RequestHandler<UploadVideoRequest, UploadVideoResponse> {
	
	private AmazonS3 s3 = null;
	
	boolean uploadVideo(Video v) throws Exception {
		return VideosDAO.videosDAO().uploadVideo(v);
	}
	
	int videoID() throws SQLException {
		return VideosDAO.videosDAO().largestVideoID();
	}

	boolean createSystemConstant(String name, byte[] contents) throws Exception {
		if (s3 == null) {
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);

		// makes the object publicly visible
		PutObjectResult res = s3.putObject(new PutObjectRequest("3733onlyslightlybent", "video-clips/" + name, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));

		// if we ever get here, then whole thing was stored
		return true;
	}

	@Override 
	public UploadVideoResponse handleRequest(UploadVideoRequest req, Context context)  {
		UploadVideoResponse response;
		try {
			String vidID = "" + (videoID()+1);
			byte[] encoded = java.util.Base64.getDecoder().decode(req.base64EncodedValue);
			System.out.println(encoded);
			if (createSystemConstant(vidID + ".ogg", encoded)) {
				response = new UploadVideoResponse(vidID);
			} else {
				response = new UploadVideoResponse(vidID, 422);
			}
			
			Video v = new Video(vidID, req.getCharacter(), req.getQuote(), "" + vidID + ".ogg");
			if(uploadVideo(v)) response = new UploadVideoResponse(vidID);
			else response = new UploadVideoResponse(vidID, 422);

		} catch (Exception e) {
			response = new UploadVideoResponse("Unable to create constant: " + req.getVideoID() + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
