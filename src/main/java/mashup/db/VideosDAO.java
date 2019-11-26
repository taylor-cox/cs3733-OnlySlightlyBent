package mashup.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import mashup.model.Library;
import mashup.model.Playlist;
import mashup.model.PlaylistEntry;
import mashup.model.Video;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author heineman
 *
 */
public class VideosDAO {

	java.sql.Connection conn;

    public VideosDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
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
    
    public Collection<Playlist> getPlaylists() throws Exception {
    	HashMap<String, Playlist> playlists = new HashMap<String, Playlist>();
    	HashMap<String, String> videos = new HashMap<String, String>();
        try {
        	// Sets up the querys which we will be using to parse the databases
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
            
            query = "SELECT * FROM videos WHERE ID IN (";
            
            for(String ID : videos.values())
            	query += "'" + ID  + "', ";
            
            query += ")";
            
            
            // REMEMBER TO CLOSE ALL CONNECTIONS!
            playlistsResp.close();
            statement.close();
            return playlists.values();

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

}