// Handles all the get requests for our site.

function createCORSRequest(method, url) {
    // Use this to create the correct CORS video request
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) {
        xhr.open(method, url, true);
    } else if (typeof XDomainRequest != "undefined") {
        xhr = new XDomainRequest();
        xhr.open(method, url);
    } else {
        xhr = null;
    }
    return xhr;
}

function fetchVideos() {
    // Fetches the videos, updates the videos list (js) and reconstructs the
    // viewable list of videos
    var xhr = createCORSRequest("GET", list_video_url);
    xhr.send();

    // Handles remote sites
    console.log("Attempting to process videos...");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                processListVideoResponse(xhr.responseText);
            } else if (xhr.status == 400) {
                alert("unable to process request");
            }
        } else {
            processListVideoResponse("N/A");
        }
        fetchRemoteVideos();
        loadVideos();
    };
    loadVideos();
}

function fetchRemoteVideos() {
    sites.forEach(function(siteURL) {
        var q = siteURL.indexOf("?apikey=");
        if(q == -1) {
            return;
        } else {
            var url = siteURL.substring(0, q);
            var key = siteURL.substring(q+8);
        }
        var siteXHR = createCORSRequest("GET", url);
        siteXHR.setRequestHeader("x-api-key", key);
        siteXHR.send();
        siteXHR.onloadend = function () {
            if (siteXHR.readyState == XMLHttpRequest.DONE) {
                if (siteXHR.status == 200) {
                    processListVideoRemoteResponse(siteXHR.responseText);
                } else if (siteXHR.status == 400) {
                    alert("unable to process request");
                }
            } else {
                processListVideoRemoteResponse("N/A");
            }
            loadVideos();
        }
    });

    loadVideos();
}

function fetchPlaylists() {
    // Fetches the playlists, updates the playlists list (js) and reconstructs the
    // viewable list of playlists
    var xhr2 = createCORSRequest("GET", list_playlist_url);
    xhr2.send();

    console.log("Attempting to process playlists...");
    xhr2.onloadend = function () {
        if (xhr2.readyState == XMLHttpRequest.DONE) {
            if (xhr2.status == 200) {
                processListPlaylistResponse(xhr2.responseText);
            } else if (xhr2.status == 400) {
                alert("unable to process request");
            }
        } else {
            processListPlaylistResponse("N/A");
        }
        document.getElementById("playlists").childNodes.forEach(function(node) {document.getElementById("playlists").removeChild(node)});
        loadPlaylists();
    };
}

function fetchRemoteSites() {
    // Fetches the remote sites, updates the remote sites list (js) and reconstructs the
    // viewable list of remote sites
    var xhr2 = createCORSRequest("GET", list_remote_site_url);
    xhr2.send();

    console.log("Attempting to process remote sites...");
    xhr2.onloadend = function () {
        if (xhr2.readyState == XMLHttpRequest.DONE) {
            if (xhr2.status == 200) {
                processListRemoteSitesResponse(xhr2.responseText);
            } else if (xhr2.status == 400) {
                alert("unable to process request");
            }
        } else {
            processListRemoteSitesResponse("N/A");
        }
        loadRemoteSites();
        fetchVideos();
    };
}

function processListVideoResponse(result) {
    // Takes a json of all the videos and puts it into videos array, and
    // returns the json.
    var js = JSON.parse(result);
    for (video in js.list) {
        videos[js.list[video].ID] = js.list[video];
        videoNum++;
    }
    return js;
}

function processListVideoRemoteResponse(result) {
    // Takes a json of all the videos and puts it into videos array, and
    // returns the json.
    var js = JSON.parse(result);
    console.log(js);
    for (video in js.segments) {
        videos[js.segments[video].character + js.segments[video].text] = js.segments[video];
        videoNum++;
    }
    return js;
}

function processListPlaylistResponse(result) {
    // Takes a json of all the playlists and puts it into playlists array, and
    // returns the json.
    playlistNum = 0;
    var js = JSON.parse(result);
    for (pl in js.list) {
        playlists[js.list[pl].id] = js.list[pl];
        playlistNum++;
    }
    return js;
}

function processListRemoteSitesResponse(result) {
    // Takes a json of all the playlists and puts it into playlists array, and
    // returns the json.
    var js = JSON.parse(result);
    for (site in js.list) {
        sites.push(js.list[site].url);
    }
    return js;
}