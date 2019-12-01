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

    // This will process results and update HTML as appropriate.
    // FOR LIST VIDEOS
    console.log("Attempting to process videos...");
    xhr.onloadend = function () {
        // console.log(xhr);
        // console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                // console.log("XHR:" + xhr.responseText);
                processListVideoResponse(xhr.responseText);
            } else if (xhr.status == 400) {
                alert("unable to process request");
            }
        } else {
            processListVideoResponse("N/A");
        }
        loadVideos(false);
    };
}

function fetchPlaylists() {
    // Fetches the playlists, updates the playlists list (js) and reconstructs the
    // viewable list of playlists
    var xhr2 = createCORSRequest("GET", list_playlist_url);
    xhr2.send();

    console.log("Attempting to process playlists...");
    xhr2.onloadend = function () {
        // console.log(xhr);
        // console.log(xhr.request);
        if (xhr2.readyState == XMLHttpRequest.DONE) {
            if (xhr2.status == 200) {
                // console.log("XHR:" + xhr.responseText);
                processListPlaylistResponse(xhr2.responseText);
            } else if (xhr2.status == 400) {
                alert("unable to process request");
            }
        } else {
            processListPlaylistResponse("N/A");
        }
        // setTimeout(function () { makeList(1, 0, playlists.length) }, 2000);
        loadPlaylists();
    };
}

function processListVideoResponse(result) {
    // Takes a json of all the videos and puts it into videos array, and
    // returns the json.
    var js = JSON.parse(result);
    for (video in js.list) {
        videos[video] = js.list[video];
        videoNum++;
    }
    return js;
}

function processListPlaylistResponse(result) {
    // Takes a json of all the playlists and puts it into playlists array, and
    // returns the json.
    var js = JSON.parse(result);
    for (pl in js.list) {
        playlists[pl] = js.list[pl];
        playlistNum++;
    }
    return js;
}