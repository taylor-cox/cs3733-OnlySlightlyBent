
function registerWebsitePost() {
    var xhr = createCORSRequest("POST", register_remote_site_url);
    var request = {};
    request['url'] = document.get;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                processRegisterWebsiteResponse(xhr.responseText);
            } else if (xhr.status == 400) {
                alert("unable to process request");
            }
        } else {
            processRegisterWebsiteResponse("N/A")
        }
        loadVideos(false);
    };
}

function deletePlaylistPost(p) {
    var xhr = createCORSRequest("POST", delete_playlist_url);
    var request = {};
    request['playlistID'] = playlists[p].id;
    xhr.send(JSON.stringify(request));

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                alert("Delete playlist successful.")
            } else {
                alert("Delete playlist unsuccessful.");
            }
        } else {
            console.log("what");
        }
        loadVideos(false);
    };
}

function processRegisterWebsiteResponse(response) {
    if(response == "N/A") return;
    console.log(response)
}