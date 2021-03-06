package mashup.db;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import mashup.model.Library;
import mashup.model.Playlist;
import mashup.model.PlaylistEntry;
import mashup.model.PublicLibrary;
import mashup.model.PublicSegment;
import mashup.model.Site;
import mashup.model.Video;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author heineman
 *
 */
public class VideosDAO {

	static VideosDAO videosDAO;
	java.sql.Connection conn;

	VideosDAO() {
		try  {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public static VideosDAO videosDAO() {
		if (videosDAO == null) {
			videosDAO = new VideosDAO();
		}
		return videosDAO;
	}

	public boolean unmarkVideo(String videoID) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE videos SET isMarked='n' WHERE id = ?;");
			ps.setString(1, videoID);
			int result = ps.executeUpdate();
			if(result == 0) return false;
			return true;
		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public boolean markVideo(String videoID) throws Exception {
		try {
			PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM `videos` WHERE id=?;");
			ps2.setString(1, videoID);
			ResultSet result2 = ps2.executeQuery();
			result2.next();
			String marked = result2.getString("isMarked");
			String output;
			if(marked.equals("y")) output = "n";
			else output = "y";
			
			
			PreparedStatement ps = conn.prepareStatement("UPDATE `videos` SET isMarked=? WHERE id=?;");
			ps.setString(1, output);
			ps.setString(2, videoID);
			int result = ps.executeUpdate();
			if(result == 0) return false;
			return true;
		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public boolean appendVideoToPlaylist(String videoID, String playlistID) throws Exception {
		try {
			// Sets up the queries which we will be using to parse the databases

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM `playlist` WHERE id=?;");
			ps.setString(1,  playlistID);
			ResultSet videos = ps.executeQuery();
			int maxOrder = 0;
			while(videos.next()) {
				int order = Integer.parseInt(videos.getString("order"));
				if(videos.getString("video").equals(videoID)) return false;
				else if(order > maxOrder) maxOrder = order;
			}
			maxOrder++;
			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO `playlist` VALUES(?, ?, ?);");
			ps2.setString(1, playlistID);
			ps2.setString(2, videoID);
			ps2.setString(3, Integer.toString(maxOrder));
			if(ps2.executeUpdate() == 0) return false;

			return true;
		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public boolean removeVideoFromPlaylist(String videoID, String playlistID) throws Exception {
		try {							 
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE video=? AND id=?");
			ps.setString(1, videoID);
			ps.setString(2, playlistID);
			ResultSet videos = ps.executeQuery();
			if(videos.next()) {
				PreparedStatement ps2 = conn.prepareStatement("DELETE FROM playlist WHERE video=? AND id=?");
				ps2.setString(1, videoID);
				ps2.setString(2, playlistID);
				if(ps2.executeUpdate() == 0) return false;
				return true;
			}

			return false;
		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public Library getLibrary() throws Exception {
		/**
		 * Gets all the videos (and data corresponding) from the database(s).
		 */
		Library lib = new Library();
		try {
			// Sets up the querys which we will be using to parse the databases
			Statement statement = conn.createStatement();
			Statement statement2 = conn.createStatement();
			String query = "SELECT * FROM videos WHERE isMarked='n';";
			ResultSet videos = statement.executeQuery(query);
			query = "SELECT * FROM library;";
			ResultSet library = statement2.executeQuery(query);

			// Sets up the character, quote, ID for the videos in library
			while(videos.next()) {
				Video video = generateVideo(videos);
				lib.addVideo(video);
			}

			// Adds filenames to the corresponding videos
			while(library.next()) {
				lib.addFileName(library.getString("ID"), library.getString("FileName"));
			}

			// REMEMBER TO CLOSE ALL CONNECTIONS!
			library.close();
			videos.close();
			statement.close();
			return lib;

		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}
	
	public PublicLibrary getPublicLibrary() throws Exception {
		/**
		 * Gets all the videos (and data corresponding) from the database(s).
		 */
		PublicLibrary lib = new PublicLibrary();
		try {
			// Sets up the querys which we will be using to parse the databases
			Statement statement = conn.createStatement();
			Statement statement2 = conn.createStatement();
			String query = "SELECT * FROM videos WHERE isMarked='n';";
			ResultSet videos = statement.executeQuery(query);
			query = "SELECT * FROM library;";
			ResultSet library = statement2.executeQuery(query);

			// Sets up the character, quote, ID for the videos in library
			while(videos.next()) {
				PublicSegment video = generatePublicSegment(videos);
				lib.addPublicSegment(video);
			}

			// Adds filenames to the corresponding videos
			while(library.next()) {
				lib.addFileName(library.getString("ID"), "https://3733onlyslightlybent.s3.amazonaws.com/video-clips/" + library.getString("FileName"));
			}

			// REMEMBER TO CLOSE ALL CONNECTIONS!
			library.close();
			videos.close();
			statement.close();
			return lib;

		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public List<Playlist> getPlaylists() throws Exception {
		HashMap<String, Playlist> playlists = new HashMap<String, Playlist>();
		HashMap<String, String> videos = new HashMap<String, String>();
		try {
			// Sets up the querys which we will be using to parse the databases
			//			Statement statementnames = conn.createStatement();
			//			String querynames = "SELECT * FROM playlistnames";
			//			ResultSet playlistsRespnames = statementnames.executeQuery(querynames);
			//			while(playlistsRespnames.next()) {
			//				String id = playlistsRespnames.getString("id");
			//				String name = playlistsRespnames.getString("name");
			//				Playlist pl = new Playlist(id);
			//				playlists.put(pl.getId(), pl);
			//			}

			Statement statement = conn.createStatement();
			String query = "SELECT * FROM playlist";
			ResultSet playlistsResp = statement.executeQuery(query);

			// Sets up the character, quote, ID for the videos in library
			while(playlistsResp.next()) {
				String id = playlistsResp.getString("id");
				Playlist pl = playlists.get(id);
				if(pl == null) pl = new Playlist(id);
				PlaylistEntry toAdd = generatePlaylistEntry(playlistsResp);

				videos.put(toAdd.getVideoID(), toAdd.getVideoID());
				pl.addPlaylistEntry(toAdd);
				if(playlists.putIfAbsent(id, pl) == null) {
					pl = playlists.get(id);
					pl.addPlaylistEntry(toAdd);
				}
			}

			// REMEMBER TO CLOSE ALL CONNECTIONS!
			playlistsResp.close();
			statement.close();
			ArrayList<Playlist> plReturn = new ArrayList<Playlist>();
			for(Playlist p : playlists.values())
				plReturn.add(p);
			return plReturn;

		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public boolean registerRemoteSite(String url) throws Exception {
		try {
			// Sets up the queries which we will be using to parse the databases
			//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM `registered-sites` WHERE `url`=?");
			//			ps.setString(1, url);
			//			ResultSet sites = ps.executeQuery();
			//			if(!sites.next()) return false;

			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO `registered-sites` VALUES (?)");
			ps2.setString(1, url);
			if(ps2.executeUpdate() == 0) return false;
			return true;
		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public boolean unregisterRemoteSite(String url) throws Exception {
		try {
			PreparedStatement ps2 = conn.prepareStatement("DELETE FROM `registered-sites` WHERE url=?");
			ps2.setString(1, url);
			if(ps2.executeUpdate() == 0) return false;
			return true;
		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public boolean addPlaylist(Playlist p) throws Exception {
		// Sets up the querys which we will be using to parse the databases
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO playlist VALUES (?, ?, ?);");
			ps.setString(1, p.getId());
			ps.setString(2, "0");
			ps.setInt(3, 0);

			int playlistsResp = ps.executeUpdate();
			if(playlistsResp == 0) return false;
			return true;
		} catch (Exception e) {
			throw new Exception("Failed adding playlist: " + e.getMessage());
		}
	}

	public boolean deletePlaylist(String p) throws Exception {
		// Sets up the querys which we will be using to parse the databases
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM playlist WHERE id=?;");
			ps.setString(1, p);

			if(ps.executeUpdate() == 0) return false;
			return true;
		} catch (Exception e) {
			throw new Exception("Failed adding playlist: " + e.getMessage());
		}
	}

	public List<Site> getRegisteredSites() throws Exception {
		List<Site> output = new ArrayList<Site>();
		try {
			// Sets up the querys which we will be using to parse the databases
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM `registered-sites`";
			ResultSet registeredSiteResp = statement.executeQuery(query);
			//pls

			// Sets up the character, quote, ID for the videos in library
			while(registeredSiteResp.next())  {
				String url = registeredSiteResp.getString("url");
				Site nice = new Site(url);
				output.add(nice);
			}
			return output;
		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public boolean uploadVideo(Video v) throws Exception {
		// Sets up the querys which we will be using to parse the databases
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO videos VALUES (?, ?, ?, ?);");
			ps.setString(1, v.getID());
			ps.setString(2, v.getQuote());
			ps.setString(3, v.getCharacter());
			ps.setString(4, "n");

			int playlistsResp = ps.executeUpdate();
			if(playlistsResp == 0) return false;

			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO library VALUES (?, ?, ?, ?);");
			ps2.setString(1, v.getID());
			ps2.setString(2, v.getID());
			ps2.setString(3, v.getFileName());
			ps2.setInt(4, 1);

			int playlistsResp2 = ps2.executeUpdate();

			if(playlistsResp2 == 0) return false;

			return true;
		} catch (Exception e) {
			throw new Exception("Failed adding playlist: " + e.getMessage());
		}
	}

	public boolean deleteVideo(String videoID) throws Exception {
		PreparedStatement ps2 = conn.prepareStatement("DELETE FROM library WHERE ID=? AND uploaded='1';");
		ps2.setString(1, videoID);

		int playlistsResp2 = ps2.executeUpdate();

		if(playlistsResp2 == 0) return false;
		
		PreparedStatement ps = conn.prepareStatement("DELETE FROM videos WHERE id=?;");
		ps.setString(1, videoID);

		int playlistsResp = ps.executeUpdate();
		if(playlistsResp == 0) return false;

		return true;
	}

	private PlaylistEntry generatePlaylistEntry(ResultSet resultSet) throws Exception {
		String video = resultSet.getString("video");
		String order = resultSet.getString("order");
		return new PlaylistEntry(video, Integer.parseInt(order));
	}

	private Video generateVideo(ResultSet resultSet) throws Exception {
		String character  = resultSet.getString("Character");
		String quote = resultSet.getString("Quote");
		String ID = resultSet.getString("ID");
		Boolean isMarked = resultSet.getBoolean("isMarked");
		return new Video(ID, character, quote, "", isMarked);
	}
	
	private PublicSegment generatePublicSegment(ResultSet resultSet) throws Exception {
		String character  = resultSet.getString("Character");
		String text = resultSet.getString("Quote");
		String ID = resultSet.getString("ID");
		return new PublicSegment(ID, character, text, "");
	}

	public int largestVideoID() throws SQLException {
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM videos";
		ResultSet registeredSiteResp = statement.executeQuery(query);

		int toOutput = 0;
		while(registeredSiteResp.next())  {
			String ID = registeredSiteResp.getString("ID");
			if(Integer.parseInt(ID) > toOutput) toOutput = Integer.parseInt(ID);
		}

		return toOutput;
	}

}