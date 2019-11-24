package mashup.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mashup.model.Library;
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
        Library lib = new Library();
        try {
            Statement statement = conn.createStatement();
            Statement statement2 = conn.createStatement();
            String query = "SELECT * FROM videos";
            ResultSet videos = statement.executeQuery(query);
            query = "SELECT * FROM library";
            ResultSet library = statement2.executeQuery(query);

            while(videos.next()) {
                Video video = generateVideo(videos);
                lib.addVideo(video);
            }
            
            while(library.next()) {
            	lib.addFileName(library.getString("ID"), library.getString("FileName"));
            }
            
            library.close();
            videos.close();
            statement.close();
            return lib;

        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }
    
    private Video generateVideo(ResultSet resultSet) throws Exception {
    	String character  = resultSet.getString("Character");
        String quote = resultSet.getString("Quote");
        String ID = resultSet.getString("ID");
        return new Video(ID, character, quote, "");
    }

}