package mashup.http;

import java.util.ArrayList;
import java.util.List;
import mashup.model.Video;

public class AllVideosResponse {
	/**
	 * Contains a list of all the videos in the library (our s3 bucket),
	 * the status code and if there was an error, the error.
	 */
	public final List<Video> list;
	public final int statusCode;
	public final String error;
	
	public AllVideosResponse (List<Video> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllVideosResponse (int code, String errorMessage) {
		this.list = new ArrayList<Video>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyVideos"; }
		return "AllVideos(" + list.size() + ")";
	}
}
