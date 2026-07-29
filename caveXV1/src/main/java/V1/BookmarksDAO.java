package V1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookmarksDAO {
    private Connection connection;
    private final String retrive="select link_id, title, link, category,notes, date_created from bookmarks";
    private final String addBookmark="insert into bookmarks(title, link, category, notes) values(?,?,?,?);";
    private final String removeBookmark="DELETE FROM bookmarks WHERE LOWER(title) = LOWER(?);";
    public BookmarksDAO(Connection connection) {
        this.connection=connection;
    }

    public void setBookmark(Bookmarks bookmarks) {
        try {
            PreparedStatement ps = connection.prepareStatement(addBookmark);
            ps.setString(1,bookmarks.getTitle());
            ps.setString(2,bookmarks.getLink());
            ps.setString(3,bookmarks.getCategory());
            ps.setString(4,bookmarks.getNotes());
            int rows_affected = ps.executeUpdate();
            if(rows_affected>0) System.out.println("Rows affected: "+rows_affected);
        }
        catch (SQLException e) {
            System.out.println("Unable to save bookmark.");
            System.out.println("No changes made");
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteBookmark(String title){
        try{
            PreparedStatement ps = connection.prepareStatement(removeBookmark);
            ps.setString(1,title.trim());
            int rows_affected = ps.executeUpdate();
            return rows_affected>0;
        }catch (SQLException e) {
            System.out.println("Unable to delete bookmark.");
            System.out.println("No changes made");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void displayBookmarks(){
        try{
            PreparedStatement ps = connection.prepareStatement(retrive);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("link_id") + " " +
                        rs.getString("title") + " " +
                        rs.getString("category") + " " +
                        rs.getString("notes") + " " +
                        rs.getTimestamp("date_created"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
