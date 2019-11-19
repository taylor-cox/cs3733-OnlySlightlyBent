package mashup;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import mashup.http.AppendVideoRequest;

public class LambdaFunctionHandler implements RequestHandler<AppendVideoRequest, String> {

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    public LambdaFunctionHandler() {}

    // Test purpose only.
    LambdaFunctionHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String handleRequest(AppendVideoRequest input, Context context) {
        
    	context.getLogger().log("Input 1\n");
    	context.getLogger().log("Arg1: " + input.getArg1() + "\n");
    	context.getLogger().log("Arg2: " + input.getArg2() + "\n");
    	context.getLogger().log("Input: " + input + "\n");
        return "Hello from Lambda";
    }
}