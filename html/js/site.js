// Handles all front-end operations

function loadVideos(fromPlaylist) {
    if(!fromPlaylist){
        document.getElementById("videos").innerHTML = '';
        for (var i = 0; i < videos.length; i++) addNewVideo(i);
    } else {
        showPlaylistVideos();
    }
}

function loadPlaylists() {
    for (var i = 0; i < playlists.length; i++) {
        addNewPlaylist(i);
    }
}

function addNewVideo(i) {
    var url = 'https://3733onlyslightlybent.s3.amazonaws.com/video-clips/';
    var videosElement = document.getElementById("videos");
    var videoURL = url + videos[i].fileName;
    var videoQuote = videos[i].quote;
    var videoCharacter = videos[i].character;
    var newDiv = document.createElement("DIV");
    // if (i % 2 == 0) newDiv.className = "rowEven";
    // else newDiv.className = "rowOdd";

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
    videosElement.appendChild(newDiv);
}

function addNewPlaylist(i) {
    var playlistsElement = document.getElementById("playlists");
    var newDiv = document.createElement("DIV");
    // if (i % 2 == 0) newDiv.className = "rowEven";
    // else newDiv.className = "rowOdd";

    newDiv.className = "interiorRow";

    let x = i;
    newDiv.id = i;
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
    viewButton.onclick = function(event) {
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

function showPlaylistVideos() {
    var videosElement = document.getElementById("videos").childNodes;
    hideAllVideos();
    videosElement.forEach(function(vid) {
        if(playlistVideos.includes(videos[vid.id])) show(vid);
    });
}

function hideAllVideos() {
    var videosElement = document.getElementById("videos").childNodes;
    videosElement.forEach(function(vid) {
        hide(vid);
    });
}

function hide(element) {
    element.style.display = "none";
}

function show(element) {
    element.style.display = "";
}

/*function addSites() {
    var html = '';
    html += '<div class="columnPad" style="background-color:#fff"></div>'
    html += '<div name="siteColumn" class="column">'
    html += '    <h2>Sites</h2>'
    html += '    <div class="rowEven">'
    html += '        <input name="searchField" value="" style="width:65%" />'
    html += '        <input type="button" value="Search" style="width:30%" onClick="JavaScript:handleSearchClick(this)">'
    html += '    </div>'
    html += '    <br>'
    html += '    <div class="rowOdd">'
    html += '        <input type="button" value="Upload Video" style="width:100%" onClick="JavaScript:handleNewVideo()">'
    html += '    </div>'
    html += '    <br>'
    var listHTML = '';
    var isEven = 1;

    for (var i = 0; i < num; i++) {
        var videoURL = url + videos[i].fileName;
        console.log(videoURL)
        if ((isPlaylist ? selectedPlaylist : selectedVideo) == i) listHTML += '<div class="rowSelected"';
        else if (isEven) listHTML += '<div class="rowEven"';
        else listHTML += '<div class="rowOdd"';
        isEven = !isEven;
        listHTML += 'onClick="JavaScript:select' + (isPlaylist ? 'Playlist' : 'Video') + '(' + i + ')"';
        listHTML += '>\n <span class="columnName" style="width:50%">(';
        listHTML += isPlaylist ? "Playlist" : "Video";
        listHTML += ' ' + (i + 1);
        listHTML += ' name)</span>\n';
        if (!isPlaylist) {
            listHTML += '<video width=300 height=200 controls>';
            listHTML += '<source src=' + videoURL + ' type="video/ogg">';
            listHTML += '</video>'
        }
        if (isAdmin) listHTML += ' <input type="button" value="M" style="width:20%" onClick="JavaScript:handleClick(this)">\n';
        listHTML += ' <img src="images/trash_can.png" onClick="JavaScript:handleDelete'
        listHTML += isPlaylist ? "Playlist" : "Video";
        listHTML += '(';
        listHTML += i;
        listHTML += ')">\n';
        listHTML += '</div>\n';
    }
    html += listHTML;
    html += '    <br>'
    html += '</div>'
    document.getElementById("sites").innerHTML = html;
 }*/

function updateAdminAccess(access) {
    isAdmin = access;
    updateAdminSettings();
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
    if (p == selectedPlaylist) return;
    var playlistElement = document.getElementById("playlists");
    if (selectedPlaylist >= 0 && selectedPlaylist != p) playlistElement.childNodes[selectedPlaylist].className = "interiorRow";
    playlistElement.childNodes[p].className = "rowSelected";
    selectedPlaylist = p;
}

function selectVideo(v) {
    if (v == selectedVideo) return;
    var videoElement = document.getElementById("videos");
    if (selectedVideo >= 0 && selectedVideo != v) videoElement.childNodes[selectedVideo].className = "interiorRow";
    videoElement.childNodes[v].className = "rowSelected";
    selectedVideo = v;
}

function handleDeleteVideo(v) {
    var videoElement = document.getElementById("videos");
    hide(videoElement.childNodes[v]);
    if(viewingPlaylist) {
        playlists[selectedPlaylist].entries.forEach(function(vid) {
            if(vid.id === v) {
                hide(vid);
            }
        });
        playlists[selectedPlaylist].entries = playlists[selectedPlaylist].entries.filter(function(value) {
            return !(value === videos[v]);
        });
    } else {
        // videoElement.removeChild(videoElement.childNodes[v]);
    }
}

function handleDeletePlaylist(p) {
    var videoElement = document.getElementById("playlists");
    hide(videoElement.childNodes[p]);
    // videoElement.removeChild(videoElement.childNodes[p]);
    if(selectedPlaylist == p) selectedPlaylist = -1;
    playlists.splice(p, 1);
}

function handleNewVideo() {
    // videoNum++;
    // makeList(0, videoNum);
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
    if(newName === "")
        playlists.push({ id: "New Playlist " + playlists.length, entries: [] });
    else
        playlists.push({ id: newName, entries: [] });
    addNewPlaylist(playlists.length - 1);
}

function handleViewPlaylist(i) {
    playlistVideos = playlists[i].entries;
    loadVideos(true);
    viewingPlaylist = true;
}

function handleMyLibraryClick() {
    var videosElement = document.getElementById("videos").childNodes;
    videosElement.forEach(function(vid) {
        show(vid);
    });
    viewingPlaylist = false;
}

function handleSearchVideosClick() {
    var searchText = document.getElementById("searchVideosField").value;
    var re = new RegExp('"(.*?)"');
    var searchElements = searchText.split(re);
    console.log(searchElements);
    processResponse();
}

function handleSearchSitesClick() {
    var searchText = document.getElementById("searchSitesField").value;
    window.alert(searchText);
    processResponse();
}

function addVideoToPlaylist(v) {
    if(selectedPlaylist < 0) {
        window.alert("Must select a playlist!");
        return;
    }
    if(playlists[selectedPlaylist].entries.includes(videos[v])) return;
    playlists[selectedPlaylist].entries.push(videos[v]);
}

function main() {
    fetchVideos();
    fetchPlaylists();

    columns = [
        document.getElementsByName('playlistColumn')[0],
        document.getElementsByName('videoColumn')[0],
        document.getElementsByName('siteColumn')[0]
    ]

    updateAdminAccess(false);
    setTimeout(function() {
        show(document.getElementById("adminButton"));
        if(window.scrollY == 0) window.scroll(0, 41);
    }, 3000)
}