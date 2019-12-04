package mashup.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import mashup.model.Library;
import mashup.model.Playlist;
import mashup.model.PlaylistEntry;
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

	public Video getVideo(String id) throws Exception {
		//        try {
		//            Video video = null;
		//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM constants WHERE name=?;");
		//            ps.setString(1,  name);
		//            ResultSet resultSet = ps.executeQuery();
		//            
		//            while (resultSet.next()) {
		//                constant = generateConstant(resultSet);
		//            }
		//            resultSet.close();
		//            ps.close();
		//            
		//            return constant;
		//
		//        } catch (Exception e) {
		//        	e.printStackTrace();
		//            throw new Exception("Failed in getting constant: " + e.getMessage());
		//        }
		return null;
	}

	public boolean updateVideo(Video video) throws Exception {
		//        try {
		//        	String query = "UPDATE constants SET value=? WHERE name=?;";
		//        	PreparedStatement ps = conn.prepareStatement(query);
		//            ps.setDouble(1, constant.value);
		//            ps.setString(2, constant.name);
		//            int numAffected = ps.executeUpdate();
		//            ps.close();
		//            
		//            return (numAffected == 1);
		//        } catch (Exception e) {
		//            throw new Exception("Failed to update report: " + e.getMessage());
		//        }
		return false;
	}

	public boolean deleteVideo(Video video) throws Exception {
		//        try {
		//            PreparedStatement ps = conn.prepareStatement("DELETE FROM constants WHERE name = ?;");
		//            ps.setString(1, constant.name);
		//            int numAffected = ps.executeUpdate();
		//            ps.close();
		//            
		//            return (numAffected == 1);
		//
		//        } catch (Exception e) {
		//            throw new Exception("Failed to insert constant: " + e.getMessage());
		//        }
		return false;
	}


	public boolean addVideo(Video video) throws Exception {
		//        try {
		//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM constants WHERE name = ?;");
		//            ps.setString(1, constant.name);
		//            ResultSet resultSet = ps.executeQuery();
		//            
		//            // already present?
		//            while (resultSet.next()) {
		//                Constant c = generateConstant(resultSet);
		//                resultSet.close();
		//                return false;
		//            }
		//
		//            ps = conn.prepareStatement("INSERT INTO constants (name,value) values(?,?);");
		//            ps.setString(1,  constant.name);
		//            ps.setDouble(2,  constant.value);
		//            ps.execute();
		//            return true;
		//
		//        } catch (Exception e) {
		//            throw new Exception("Failed to insert constant: " + e.getMessage());
		//        }
		return false;
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
			PreparedStatement ps = conn.prepareStatement("UPDATE videos SET isMarked='y' WHERE id = ?;");
			ps.setString(1, videoID);
			int result = ps.executeUpdate();
			if(result == 0) return false;
			return true;
		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public boolean appendVideoToPlaylist(String videoID, String playlistID) throws Exception {
		try {
			// Sets up the querys which we will be using to parse the databases
			Statement statement = conn.createStatement();
			Statement statement2 = conn.createStatement();
			String query1 = "SELECT * FROM `playlist` WHERE id=\"" + playlistID + "\"";
			ResultSet videos = statement.executeQuery(query1);
			int maxOrder = 0;
			while(videos.next()) {
				int order = Integer.parseInt(videos.getString("order"));
				if(videos.getString("video") == videoID) return false;
				else if(order > maxOrder) maxOrder = order;
			}

			String query2 = "INSERT INTO `playlist` VALUES(\"" + playlistID + "\", \"" + videoID + "\", " + ++maxOrder + ")";
			statement2.executeUpdate(query2);

			return true;
		} catch (Exception e) {
			throw new Exception("Failed in getting books: " + e.getMessage());
		}
	}

	public boolean removeVideoFromPlaylist(String videoID, String playlistID) throws Exception {
		try {
			// Sets up the querys which we will be using to parse the databases
			Statement statement = conn.createStatement();
			Statement statement2 = conn.createStatement();
			String query1 = "SELECT * FROM `playlist` WHERE video='" + videoID + "' AND id='" + playlistID + "'";
			ResultSet videos = statement.executeQuery(query1);
			if(videos.next()) {
				String query2 = "DELETE FROM `playlist` WHERE video='" + videoID + "' AND id='" + playlistID + "'";
				statement2.executeUpdate(query2);
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
			String query = "SELECT * FROM videos";
			ResultSet videos = statement.executeQuery(query);
			query = "SELECT * FROM library";
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

	public List<Playlist> getPlaylists() throws Exception {
		HashMap<String, Playlist> playlists = new HashMap<String, Playlist>();
		HashMap<String, String> videos = new HashMap<String, String>();
		try {
			// Sets up the querys which we will be using to parse the databases
			Statement statementnames = conn.createStatement();
			String querynames = "SELECT * FROM playlistnames";
			ResultSet playlistsRespnames = statementnames.executeQuery(querynames);
			while(playlistsRespnames.next()) {
				String id = playlistsRespnames.getString("id");
				String name = playlistsRespnames.getString("name");
				Playlist pl = new Playlist(id, name);
				playlists.put(pl.getId(), pl);
			}
			
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM playlist";
			ResultSet playlistsResp = statement.executeQuery(query);

			// Sets up the character, quote, ID for the videos in library
			while(playlistsResp.next()) {
				String id = playlistsResp.getString("id");
				Playlist pl = playlists.get(id);
				if(pl == null) pl = new Playlist(id, id);
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
    
    public boolean addPlaylist(Playlist p) throws Exception {
    	// Sets up the querys which we will be using to parse the databases
        try {
        	Statement statement = conn.createStatement();
        	String query = "INSERT INTO playlistnames(id, name) VALUES (";
        	query = query + p.getId() + ", " + p.getName() + ")";
        	boolean playlistsResp = statement.execute(query);
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
            
            // Sets up the character, quote, ID for the videos in library
            while(registeredSiteResp.next()) 
            	output.add(generateSite(registeredSiteResp));
            return output;
        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
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
		return new Video(ID, character, quote, "");
	}
	
	private Site generateSite(ResultSet resultSet) throws Exception {
    	String id = resultSet.getString("id");
    	String url = resultSet.getString("url");
    	return new Site(id, url);
	}

}

//did you know jeb bush is the zodiac killer??