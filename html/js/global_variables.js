var base_url = "https://z4mjdesn38.execute-api.us-east-1.amazonaws.com/gamma/";
var list_video_url = base_url + "listVideo";
var list_playlist_url = base_url + "listPlaylist";
var list_remote_site_url = base_url + "listRemoteSites";

// var get_url = "https://z4mjdesn38.execute-api.us-east-1.amazonaws.com/secure/";
var playlistNum = 1;
var videoNum = 1;
var selectedPlaylist = -1;
var selectedVideo = -1;
var isAdmin = false;
var videos = [];
var searchVideos = [];
var playlists = [];
var columns = [];
var playlistVideos = [];
var viewingPlaylist = false;
var sites = [];