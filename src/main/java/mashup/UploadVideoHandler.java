package mashup;

import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import mashup.http.UploadVideoRequest;
import mashup.http.UploadVideoResponse;

public class UploadVideoHandler implements RequestHandler<UploadVideoRequest, UploadVideoResponse> {
	
	private AmazonS3 s3 = null;

	boolean createSystemConstant(String name, byte[]  contents) throws Exception {
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
			byte[] encoded = java.util.Base64.getDecoder().decode(req.getBase64EncodedValue());
			if (createSystemConstant(req.getVideoID(), encoded)) {
				response = new UploadVideoResponse(req.getVideoID());
			} else {
				response = new UploadVideoResponse(req.getVideoID(), 422);
			}

		} catch (Exception e) {
			response = new UploadVideoResponse("Unable to create constant: " + req.getVideoID() + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
