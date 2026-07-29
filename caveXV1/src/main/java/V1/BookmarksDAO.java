package V1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookmarksDAO {
    private Connection connection;
    private final String addBookmark="insert into bookmarks(title, link, category, notes) values(?,?,?,?);";
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
}
