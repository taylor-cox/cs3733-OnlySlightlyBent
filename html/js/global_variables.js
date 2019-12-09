var base_url = "https://z4mjdesn38.execute-api.us-east-1.amazonaws.com/gamma/";
var list_video_url = base_url + "listVideo";
var list_playlist_url = base_url + "listPlaylist";
var list_remote_site_url = base_url + "listRemoteSites";
var delete_playlist_url = base_url + "deletePlaylist";
var create_playlist_url = base_url + "createPlaylist";
var register_remote_site_url = base_url + "registerRemoteSite";
var unregister_remote_site_url = base_url + "unregisterRemoteSite";
var append_video_url = base_url + "appendVideo";
var remove_video_url = base_url + "removeVideo";
var upload_video_url = base_url + "uploadVideo";
var delete_video_url = base_url + "deleteVideo";


// var get_url = "https://z4mjdesn38.execute-api.us-east-1.amazonaws.com/secure/";
var playlistNum = 0;
var videoNum = 1;
var selectedPlaylist = -1;
var selectedVideo = null;
var isAdmin = false;
var videos = {};
var searchVideos = [];
var playlists = {};
var columns = [];
var viewingPlaylist = false;
var sites = {};