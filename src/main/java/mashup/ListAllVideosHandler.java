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
import mashup.model.Video;

public class ListAllVideosHandler implements RequestHandler<Object, AllVideosResponse> {
	
	public LambdaLogger logger;
	
	List<Video> getVideos() throws Exception {
		logger.log("In getVideos");
		
		VideosDAO dao = new VideosDAO();
		
		return dao.getAllVideos();
	}
	
	private AmazonS3 s3 = null;
	
	List<Video> systemConstants() throws Exception {
		logger.log("in systemConstants");
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			logger.log("attach to S3 succeed");
		}
		
		ArrayList<Video> sysVideos = new ArrayList<>();
	    
		// retrieve listing of all objects in the designated bucket
		ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
				  .withBucketName("3733onlyslightlybent")    // top-level bucket
				  .withPrefix("video-clips");       // sub-folder declarations here (i.e., a/b/c)
												  
		
		// request the s3 objects in the s3 bucket 'cs3733wpi/constants' -- change based on your bucket name
		logger.log("process request");
		ListObjectsV2Result result = s3.listObjectsV2(listObjectsRequest);
		logger.log("process request succeeded");
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		
		for (S3ObjectSummary os: objects) {
	      String name = os.getKey();
		  logger.log("S3 found:" + name);

	      // If name ends with slash it is the 'constants/' bucket itself so you skip
	      if (name.endsWith("/")) { continue; }
			
	      S3Object obj = s3.getObject("3733onlyslightlybent", name);
	    	
	    	try (S3ObjectInputStream constantStream = obj.getObjectContent()) {
				Scanner sc = new Scanner(constantStream);
				String val = sc.nextLine();
				sc.close();
				
				// just grab name *after* the slash. Note this is a SYSTEM constant
				int postSlash = name.indexOf('/');
				sysVideos.add(new Video("", "", name.substring(postSlash+1)));
			} catch (Exception e) {
				logger.log("Unable to parse contents of " + name);
			}
	    }
		
		return sysVideos;
	}
	
    @Override
    public AllVideosResponse handleRequest(Object input, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lamdba Function to list all videos.");
        
        AllVideosResponse response;
        
        try {
			List<Video> list = getVideos();
			for (Video c : systemConstants()) {
				if (!list.contains(c)) {
					list.add(c);
				}
			}
			response = new AllVideosResponse(list, 200);
		} catch (Exception e) {
			response = new AllVideosResponse(403, e.getMessage());
		}
		
		return response;
    }

}
