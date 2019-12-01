// Handles all front-end operations
function makeList(isPlaylist, isSite, num) {
    loadVideos();
    loadPlaylists();
    // var listHTML = '';
    // var isEven = 1;
    // var url = 'https://3733onlyslightlybent.s3.amazonaws.com/video-clips/';
    // for (var i = 0; i < num; i++) {
    //     var videoURL = url + videos[i].fileName;
    //     var videoQuote = videos[i].quote;
    //     var videoCharacter = videos[i].character;
    //     if ((isPlaylist ? selectedPlaylist : selectedVideo) == i) listHTML += '<div class="rowSelected"';
    //     else if (isEven) listHTML += '<div class="rowEven"';
    //     else listHTML += '<div class="rowOdd"';
    //     isEven = !isEven;
    //     listHTML += 'onClick="JavaScript:select' + (isPlaylist ? 'Playlist' : 'Video') + '(' + i + ')"';
    //     listHTML += '>\n <span class="columnName" style="width:50%">';
    //     if (isPlaylist) {
    //         listHTML += playlists[i].id + '</span>\n';
    //     } else {
    //         listHTML += videoCharacter + ', "' + videoQuote + '"';
    //         listHTML += '</span>\n';
    //     }

    //     if (!isPlaylist) {
    //         listHTML += '<video width=300 height=200 controls>';
    //         listHTML += '<source src=' + videoURL + ' type="video/ogg">';
    //         listHTML += '</video>'
    //     }
    //     if (isAdmin) listHTML += ' <input type="button" value="M" style="width:20%" onClick="JavaScript:handleClick(this)">\n';
    //     listHTML += ' <img src="images/trash_can.png" onClick="JavaScript:handleDelete'
    //     listHTML += isPlaylist ? "Playlist" : "Video";
    //     listHTML += '(';
    //     listHTML += i;
    //     listHTML += ')">\n';
    //     listHTML += '</div>\n';
    // }
    // document.getElementById(isPlaylist ? "playlists" : "videos").innerHTML = listHTML;
}

function loadVideos() {
    var url = 'https://3733onlyslightlybent.s3.amazonaws.com/video-clips/';
    var videosElement = document.getElementById("videos");
    videosElement.innerHTML = '';
    for(var i = 0; i < videos.length; i++) {
        var videoURL = url + videos[i].fileName;
        var videoQuote = videos[i].quote;
        var videoCharacter = videos[i].character;
        var newDiv = document.createElement("DIV");
        if(selectedVideo == i) newDiv.className = "rowSelected";
        else if(i % 2 == 0) newDiv.className = "rowEven";
        else newDiv.className = "rowOdd";

        newDiv.id = i;
        let x = i;
        newDiv.onclick = function() { selectVideo(x); };

        var textSpan = document.createElement("P");
        textSpan.className = "columnName";
        textSpan.style = "width:50%";
        textSpan.innerText = videoCharacter + ', "' + videoQuote + '"';

        var trashCanImage = document.createElement("IMG");
        trashCanImage.src = "images/trash_can.png";
        trashCanImage.onclick = function () { handleDeleteVideo(x); };

        var video = document.createElement("VIDEO");
        video.width = "300"; video.height = "200";
        video.controls = true;

        var videoSource = document.createElement("SOURCE");
        videoSource.type = "video/ogg";
        videoSource.src = videoURL;

        video.appendChild(videoSource);

        newDiv.appendChild(textSpan);
        newDiv.appendChild(video);
        newDiv.appendChild(trashCanImage);
        videosElement.appendChild(newDiv);
    }
}

function loadPlaylists() {
    var playlistsElement = document.getElementById("playlists");
    for(var i = 0; i < playlists.length; i++) {
        var newDiv = document.createElement("DIV");
        if(selectedPlaylist == i) newDiv.className = "rowSelected";
        else if(i % 2 == 0) newDiv.className = "rowEven";
        else newDiv.className = "rowOdd";

        let x = i;
        newDiv.id = i;
        newDiv.onclick = function() { selectPlaylist(x); };

        var textSpan = document.createElement("SPAN");
        textSpan.className = "columnName";
        textSpan.style = "width:50%";
        textSpan.innerText = playlists[i].id;

        var trashCanImage = document.createElement("IMG");
        trashCanImage.src = "images/trash_can.png";
        trashCanImage.onclick = function () { handleDeletePlaylist(x); };
        newDiv.appendChild(textSpan);
        newDiv.appendChild(trashCanImage);
        playlistsElement.appendChild(newDiv);
    }
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
    if(siteColumn.style.display === "") siteColumn.style.display = "none";
    else siteColumn.style.display = "";
}

function selectPlaylist(p) {
    var playlistElement = document.getElementById("playlists");
    playlistElement.childNodes[p].className = "rowSelected";
    if(selectedPlaylist >= 0) videoElement.childNodes[selectedPlaylist].className = "rowOdd";
    selectedPlaylist = p;
}

function selectVideo(v) {
    var videoElement = document.getElementById("videos");
    videoElement.childNodes[v].className = "rowSelected";
    if(selectedVideo >= 0 && selectedVideo != v) videoElement.childNodes[selectedVideo].className = "rowOdd";
    selectedVideo = v;
}

function handleDeleteVideo(v) {
    var videoElement = document.getElementById("videos");
    videoElement.childNodes[v].style.display = "none";
    videoElement.childNodes.splice(v, 1);
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

function handleDeletePlaylist(p) {
    var videoElement = document.getElementById("playlists");
    videoElement.childNodes[p].style.display = "none";
    videoElement.childNodes.splice(v, 1);
}

function handleNewPlaylist() {
    playlistNum++;
    makeList(1, playlistNum);
}

function handleMyLibraryClick() {
    window.alert("My library has been clicked.")
}

function handleSearchClick(e) {
    var form = document.addForm;
    var searchField = form.searchField.value;

    window.alert(searchField);
    processResponse();
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
}