package mashup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import mashup.db.VideosDAO;
import mashup.http.AllVideosResponse;
import mashup.http.PublicVideosResponse;
import mashup.model.Library;
import mashup.model.PublicLibrary;
import mashup.model.PublicSegment;
import mashup.model.Video;

public class ListPublicVideoSegments implements RequestHandler<Object, PublicVideosResponse> {
	
	public LambdaLogger logger;
	
	PublicLibrary getLibrary() throws Exception {
		/**
		 * Get Library returns the available library, consisting of s3 segments we uploaded.
		 */
		VideosDAO dao = VideosDAO.videosDAO();
		return dao.getPublicLibrary();
	}
	
    @Override
    public PublicVideosResponse handleRequest(Object input, Context context) {
    	/**
    	 * Gets all the videos from the s3 bucket and returns them in a response object.
    	 */
        logger = context.getLogger();
        logger.log("Loading Java Lamdba Function to list all videos.");
        
        PublicVideosResponse response;
        
        try {
			List<PublicSegment> list = getLibrary().getVideos();
			response = new PublicVideosResponse(list, 200);
		} catch (Exception e) {
			response = new PublicVideosResponse(403, e.getMessage());
		}
		
		return response;
    }

}
