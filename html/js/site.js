// Handles all front-end operations

function loadVideos(fromPlaylist) {
    if (!fromPlaylist) {
        document.getElementById("videos").innerHTML = '';
        for (var i in videos) addNewVideo(i);
    }
}

function loadPlaylists() {
    document.getElementById("playlists").innerHTML = '';
    for (var i in playlists) addNewPlaylist(i);
}

function loadRemoteSites() {
    for (var site in sites) {
        addNewSite(site);
    }
}

function addNewVideo(i) {
    var url = 'https://3733onlyslightlybent.s3.amazonaws.com/video-clips/';
    var videosElement = document.getElementById("videos");
    var videoURL = url + videos[i].fileName;
    var videoQuote = videos[i].quote;
    var videoCharacter = videos[i].character;
    var newDiv = document.createElement("DIV");
    newDiv.className = "interiorRow";

    newDiv.id = i;
    let x = i;
    newDiv.onclick = function () { selectVideo(x); };

    var textParagraph = document.createElement("P");
    textParagraph.className = "columnName";
    textParagraph.style = "width:50%";
    var character = document.createElement("B");
    character.innerText = videoCharacter;
    textParagraph.appendChild(character);
    textParagraph.appendChild(document.createElement("BR"));
    // textParagraph.appendChild(document.createElement("P").innerText = videoQuote)
    textParagraph.innerHTML = character.outerHTML + document.createElement("BR").outerHTML + '"' + videoQuote + '"';

    var deleteButton = document.createElement("BUTTON");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("style", "float: right");
    var trashCanImage = document.createElement("IMG");
    trashCanImage.setAttribute("src", "images/trash_can.png");
    deleteButton.onclick = function (event) {
        event.preventDefault();
        event.stopPropagation();
        handleDeleteVideo(x);
    };
    deleteButton.appendChild(trashCanImage);

    if(isAdmin) {
        var markButton = document.createElement("BUTTON");
        markButton.setAttribute("type", "button");
        markButton.setAttribute("style", "float: right");
        var markImage = document.createElement("IMG");
        markImage.setAttribute("src", "images/mark.png");
        markButton.onclick = function (event) {
            handleMarkVideo();
        };
        markButton.appendChild(markImage);
    }

    var addToPlaylistButton = document.createElement("BUTTON");
    addToPlaylistButton.setAttribute("type", "button");
    addToPlaylistButton.setAttribute("style", "float: right");
    var addImage = document.createElement("IMG");
    addImage.setAttribute("src", "images/add.png")
    addToPlaylistButton.onclick = function () { addVideoToPlaylist(x); };
    addToPlaylistButton.appendChild(addImage);

    var video = document.createElement("VIDEO");
    video.width = "250"; video.height = "150";
    video.controls = true;

    var videoSource = document.createElement("SOURCE");
    videoSource.type = "video/ogg";
    videoSource.src = videoURL;

    video.appendChild(videoSource);

    newDiv.appendChild(textParagraph);
    newDiv.appendChild(video);
    newDiv.appendChild(deleteButton);
    newDiv.appendChild(addToPlaylistButton);
    if(isAdmin) newDiv.appendChild(markButton);
    videosElement.appendChild(newDiv);
}

function addNewPlaylist(i) {
    var playlistsElement = document.getElementById("playlists");
    var newDiv = document.createElement("DIV");
    // if (i % 2 == 0) newDiv.className = "rowEven";
    // else newDiv.className = "rowOdd";

    newDiv.className = selectedPlaylist === i ? "rowSelected" : "interiorRow";

    let x = i;
    newDiv.id = playlists[i].id;
    newDiv.onclick = function () {
        selectPlaylist(x);
    };

    var textSpan = document.createElement("SPAN");
    textSpan.className = "columnName";
    textSpan.style = "width:50%";
    textSpan.innerText = playlists[i].id;

    var deleteButton = document.createElement("BUTTON");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("style", "float: right");
    var trashCanImage = document.createElement("IMG");
    trashCanImage.setAttribute("src", "images/trash_can.png");
    deleteButton.onclick = function (event) {
        event.preventDefault();
        event.stopPropagation();
        handleDeletePlaylist(x);
    };
    deleteButton.appendChild(trashCanImage);

    var viewButton = document.createElement("BUTTON");
    viewButton.setAttribute("type", "button");
    viewButton.setAttribute("style", "float: right");
    var viewImage = document.createElement("IMG");
    viewImage.setAttribute("src", "images/view.png");
    viewButton.onclick = function (event) {
        event.preventDefault();
        event.stopPropagation();
        handleViewPlaylist(x);
    };
    viewButton.appendChild(viewImage);

    newDiv.appendChild(textSpan);
    newDiv.appendChild(deleteButton);
    newDiv.appendChild(viewButton);
    playlistsElement.appendChild(newDiv);
}

function addNewSite(i) {
    var sitesElement = document.getElementById("sites");
    var newDiv = document.createElement("DIV");
    // if (i % 2 == 0) newDiv.className = "rowEven";
    // else newDiv.className = "rowOdd";

    newDiv.className = "interiorRow";

    let x = i;
    newDiv.id = i;
    newDiv.onclick = function () {
        selectSite(x);
    };

    var textSpan = document.createElement("SPAN");
    textSpan.className = sites[i];
    textSpan.style = "width:50%";
    textSpan.innerText = sites[i];

    var deleteButton = document.createElement("BUTTON");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("style", "float: right");
    var trashCanImage = document.createElement("IMG");
    trashCanImage.setAttribute("src", "images/trash_can.png");
    deleteButton.onclick = function (event) {
        event.preventDefault();
        event.stopPropagation();
        handleDeleteSite(x);
    };
    deleteButton.appendChild(trashCanImage);

    newDiv.appendChild(textSpan);
    newDiv.appendChild(deleteButton);
    sitesElement.appendChild(newDiv);
}

function removeSite(url) {
    var sitesElement = document.getElementById("sites");
    // hide(videoElement.childNodes[p]);
    sitesElement.childNodes.forEach(function (siteNode) {
        if (siteNode.id === sites.indexOf(url)) sitesElement.removeChild(siteNode);

    });
    var index = sites.indexOf(url);
    sites.splice(index, 1);
}

function hideAllVideos() {
    var videosElement = document.getElementById("videos").childNodes;
    videosElement.forEach(function (vid) {
        hide(vid);
    });
}

function hide(element) {
    element.style.display = "none";
}

function show(element) {
    element.style.display = "";
}

function updateAdminAccess(access) {
    isAdmin = access;
    updateAdminSettings();
    loadVideos();
}

function updateAdminSettings() {
    for (var n = 0; n < columns.length; n++) {
        columns[n].className = isAdmin ? "columnAdmin" : "column";
    }
    var pads = document.getElementsByName("pad");
    for (var n = 0; n < pads.length; n++) {
        pads[n].className = isAdmin ? "columnAdminPad" : "columnPad";
    }
    var siteColumn = document.getElementsByName("siteColumn")[0];
    if (!isAdmin) hide(siteColumn);
    else show(siteColumn);
}

function selectPlaylist(p) {
    var playlistElement = document.getElementById("playlists");
    if (selectedPlaylist !== undefined && selectedPlaylist != p) {
        playlistElement.childNodes.forEach(function (playlist) {
            if (playlist.id == selectedPlaylist) playlist.className = "interiorRow";
        });
    }
    playlistElement.childNodes.forEach(function (playlist) {
        if (playlist.id == p) playlist.className = "rowSelected";
    });
    selectedPlaylist = p;
}

function selectSite(p) {
    var playlistElement = document.getElementById("sites");
    if (selectedPlaylist !== undefined && selectedPlaylist != p) {
        playlistElement.childNodes.forEach(function (playlist) {
            if (playlist.id == selectedPlaylist) playlist.className = "interiorRow";
        });
    }
    playlistElement.childNodes.forEach(function (playlist) {
        if (playlist.id == p) playlist.className = "rowSelected";
    });
    selectedPlaylist = p;
}

function selectVideo(v) {
    var videoElement = document.getElementById("videos");
    videoElement.childNodes.forEach(function (child) {
        if (child.id == v) child.className = "rowSelected";
        else child.className = "interiorRow";
    });
    selectedVideo = v;
}

function handleDeleteVideo(v) {
    if (viewingPlaylist) {
        removeVideoFromPlaylist(v, selectedPlaylist);
    } else {
        deleteVideo(v);
    }
}

function handleDeletePlaylist(p) {
    // hide(videoElement.childNodes[p]);
    deletePlaylistPost(p);
}

function handleDeleteSite(s) {
    unregisterSitePost(sites[s]);
}

function handleNewVideo() {
    // TODO
    var form = document.createForm;
    var data = {};
    data["name"] = form.constantName.value;

    if (form.system.checked) {  // be sure to flag system constant requests...
        data["system"] = true;
    }
    var segments = document.createForm.base64Encoding.value.split(',');
}

function handleNewPlaylist() {
    var playlistsElement = document.getElementById("playlists");
    var newPlaylistName = document.getElementById("newPlaylistNameField");
    var newName = newPlaylistName.value;
    // THROW ERROR IF MULTIPLE OF THE SAME NAME
    if (newName === "") {
        newName = "New Playlist " + playlists.length;
    }
    if (playlists[newName] !== undefined) {
        alert("Must choose a unique name for a playlist!");
        return;
    }
    createPlaylistPost(newName);
}

function handleViewPlaylist(i) {
    videosToView = {};
    playlists[i].entries.forEach(function (video) {
        if (video.videoID > 0) {
            videosToView[video.index] = video.videoID;
        }
    });
    var videosElement = document.getElementById("videos").childNodes;
    hideAllVideos();
    document.getElementById("videos").innerHTML = '';
    for (var vid in videosToView) {
        addNewVideo(videosToView[vid]);
    }
    viewingPlaylist = true;
}

function handleMyLibraryClick() {
    document.getElementById("videos").innerHTML = '';
    viewingPlaylist = false;
    loadVideos(viewingPlaylist);
}

function handleSearchVideosClick() {
    var searchText = document.getElementById("searchVideosField").value;
    searchText = searchText.replace(/"/g,"");
    var searching = {};
    var toShow = [];

    Object.keys(videos).forEach(function(v) {
        searching[v] = videos[v].character + ", " + videos[v].quote;
    });

    var videosElement = document.getElementById("videos");

    for(var i in searching) {
        if(searching[i].includes(searchText)) {
            videosElement.childNodes.forEach(function(child) {
                if(child.id == i) show(child);
            });
        }
        else {
            videosElement.childNodes.forEach(function(child) {
                if(child.id == i) hide(child);
            });
        }
    }
}

function handleSearchSitesClick() {
    var searchText = document.getElementById("searchSitesField").value;
    window.alert(searchText);
}

function handleRegisterSiteClick() {
    var siteElement = document.getElementById("sites");
    var newSiteName = document.getElementById("registerSiteField").value;
    if (newSiteName === "")
        alert("Error: No site inputted.");
    else
        registerSitePost(newSiteName);
}

function addVideoToPlaylist(v) {
    if (selectedPlaylist === null) {
        window.alert("Must select a playlist!");
        return;
    }
    appendVideoToPlaylist(selectedPlaylist, v);
}

// prepares the base64-encoded string and enabled button
function getBase64(file) {
    var reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = function () {
        document.getElementById("uploadVideoForm").childNodes[13].value = reader.result;
    };
}

// When file is selected, stash base64 value in the encoding field.
function handleFileSelect(evt) {
    var files = evt.target.files;
    getBase64(files[0]); // request the load (async)
}


function main() {
    fetchVideos();
    fetchPlaylists();
    fetchRemoteSites();

    // register
    document.getElementById("uploadVideoForm").childNodes[15].addEventListener('change', handleFileSelect, false);

    columns = [
        document.getElementsByName('playlistColumn')[0],
        document.getElementsByName('videoColumn')[0],
        document.getElementsByName('siteColumn')[0]
    ]

    updateAdminAccess(false);
    setTimeout(function () {
        show(document.getElementById("adminButton"));
        if (window.scrollY == 0) window.scroll(0, 41);
    }, 3000)

}