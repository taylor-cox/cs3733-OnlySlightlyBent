package mashup.http;

import java.util.ArrayList;
import java.util.List;

import mashup.model.PublicSegment;
import mashup.model.Video;

public class PublicVideosResponse {
	/**
	 * Contains a list of all the videos in the library (our s3 bucket),
	 * the status code and if there was an error, the error.
	 */
	public final List<PublicSegment> segments;
	public final int statusCode;
	public final String error;
	
	public PublicVideosResponse (List<PublicSegment> segments, int code) {
		this.segments = segments;
		this.statusCode = code;
		this.error = "";
	}
	
	public PublicVideosResponse (int code, String errorMessage) {
		this.segments = new ArrayList<PublicSegment>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (segments == null) { return "EmptyVideos"; }
		return "AllVideos(" + segments.size() + ")";
	}
}
