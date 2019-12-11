// Handles all the post requests of the website

function registerSitePost(url) {
    var xhr = createCORSRequest("POST", register_remote_site_url);
    var request = {};
    request['url'] = url;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Website successfully registered.");
                sites.push(url);
                addNewSite(sites.indexOf(url));
            } else if (xhr.status == 400) {
                alert("unable to process request");
            }
        } else {
            alert("Warning: There was an error processing your website.");
        }
    };
}

function unregisterSitePost(url) {
    var xhr = createCORSRequest("POST", unregister_remote_site_url);
    var request = {};
    request['url'] = url;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Website successfully unregistered.");
                removeSite(url);
            } else if (xhr.status == 400) {
                alert("unable to process request");
            }
        } else {
            alert("Warning: There was an error processing your website.");
        }
    };
}

function createPlaylistPost(newName) {
    var xhr = createCORSRequest("POST", create_playlist_url);
    var request = {};
    request['playlistID'] = newName;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Create playlist successful.");
                playlistNum++;
                playlists[newName] = { id: newName, entries: [] };
                addNewPlaylist(newName);
            } else {
                alert("Create playlist unsuccessful.");
            }
        } else {
            console.log("what");
        }
    };
}

function deletePlaylistPost(p) {
    var videoElement = document.getElementById("playlists");
    var xhr = createCORSRequest("POST", delete_playlist_url);
    var request = {};
    request['playlistID'] = playlists[p].id;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Delete playlist successful.")
                videoElement.childNodes.forEach(function(playlistNode) {
                    if(playlistNode.id == playlists[p].id) videoElement.removeChild(playlistNode);
                });
                if(selectedPlaylist == p) selectedPlaylist = -1;
                delete playlists[p];
                playlistNum--;
            } else {
                alert("Delete playlist unsuccessful.");
            }
        } else {
            console.log("what");
        }
    };
}

function appendVideoToPlaylist(playlist, video) {
    var xhr = createCORSRequest("POST", append_video_url);
    var request = {};
    request['playlistID'] = playlists[playlist].id;
    request['videoID'] = videos[video].ID;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Append video successful.")
            } else {
                alert("Append video unsuccessful.");
            }
        } else {
            console.log("ERROR");
        }
        fetchPlaylists();
    };
}

function removeVideoFromPlaylist(v, p) {
    var xhr = createCORSRequest("POST", remove_video_url);
    var request = {};
    request['playlistID'] = playlists[p].id;
    request['videoID'] = videos[v].ID;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Remove video successful.");
                var videoElement = document.getElementById("videos");
                videoElement.childNodes.forEach(function (vidElement) {
                    if (vidElement.id == v) videoElement.removeChild(vidElement);
                });
                playlists[selectedPlaylist].entries.forEach(function(entry, i) {
                    if(entry.videoID === videos[v].ID) delete playlists[selectedPlaylist].entries[i];
                });
            } else {
                alert("Remove video unsuccessful.");
            }
        } else {
            console.log("ERROR");
        }
    };
}

function uploadVideo() {
    var data = {};
    data["character"] = document.getElementById("uploadCharacterName").value;
    data["quote"] = document.getElementById("uploadQuote").value;
    data["videoID"] = "1";
    var segments = document.getElementById("uploadVideoForm").childNodes[13].value.split(',');
    data["base64EncodedValue"] = segments[1];

    if(data["character"] === "") {
        alert("Must have a character name.");
        return;
    }
    if(data["quote"] === "") {
        alert("Must have a quote.");
        return;
    }
    if(data["base64EncodedValue"] === "") {
        alert("Must choose a file to upload.");
        return;
    }

    var xhr = createCORSRequest("POST", upload_video_url);
    xhr.send(JSON.stringify(data));
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Successfully uploaded video segment.");
                fetchVideos();
            } else {
                alert("Upload video unsuccessful.");
            }
        } else {
            console.log("ERROR");
        }
    };
}

function deleteVideo(v) {
    var xhr = createCORSRequest("POST", delete_video_url);
    var request = {};
    request['videoID'] = videos[v].ID;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Delete video successful.");
                var videoElement = document.getElementById("videos");
                videoElement.childNodes.forEach(function (vidElement) {
                    if (vidElement.id == v) videoElement.removeChild(vidElement);
                });
                delete videos[v];
            } else {
                alert("Delete video unsuccessful.");
            }
        } else {
            console.log("ERROR");
        }
    };
}

function markVideo(v) {
	var xhr = createCORSRequest("POST", mark_video_url);
    var request = {};
    request['isMarked'] = videoes[v].isMarked;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Mark video successful.");
                var videoElement = document.getElementByIsMarked("isMarked");
                videoElement.childNodes.forEach(function (vidElement) {
                    if (vidElement.id == v) videoElement.removeChild(vidElement);
                });
                delete videos[v];
            } else {
                alert("Mark video unsuccessful.");
            }
        } else {
            console.log("ERROR");
        }
    };
}