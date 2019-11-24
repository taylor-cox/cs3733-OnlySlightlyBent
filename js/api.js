// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = "https://z4mjdesn38.execute-api.us-east-1.amazonaws.com/beta/";

var append_video_url = base_url + "appendVideo"; 							// POST
var create_playlist_url = base_url + "createPlaylist"; 						// POST
var delete_playlist_url = base_url + "deletePlaylist"; 						// POST
var delete_video_url = base_url + "deleteVideo"; 							// POST
var list_playlist_url = base_url + "listPlaylist"; 							// GET
var list_remote_sites_url = base_url + "listRemoteSites"; 					// GET
var list_video_url = base_url + "listVideo"; 								// GET
var mark_local_video_segment_url = base_url + "markLocalVideoSeg"; 			// POST
var play_playlist_url = base_url + "playPlaylist"; 							// POST
var register_remote_site_url = base_url + "registerRemoteSite"; 			// POST
var remove_video_url = base_url + "removeVideo"; 							// POST
var search_video_url = base_url + "searchVideo"; 							// GET
var unmark_local_video_segment_url = base_url + "unmarkLocalVideoSegment"; 	// POST
var unregister_remote_site_url = base_url + "unregisterRemoteSite"; 		// POST
var upload_video_url = base_url + "uploadVideo"; 							// POST

// generated from aws. BAD IDEA to encode here, but just getting something done.
var remote_url = "https://rct351a2w3.execute-api.us-east-1.amazonaws.com/secure/";

var search_remote_url = remote_url + "remote";

var apikey = "pyEG6SrJzP3kUyp2QhI7J4WCTxSA5Bs92b7PeIhC";

// test directly
// curl -X POST -H "x-api-key: theKey" -H "Content-Type: application/json" -d '{"key":"val"}' https://[api-id].execute-api.[region].amazonaws.com