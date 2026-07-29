package V1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookmarksDAO {
    private Connection connection;
    private final String retrive = "select link_id, title, link, category,notes, date_created from bookmarks";
    private final String addBookmark = "insert into bookmarks(title, link, category, notes) values(?,?,?,?);";
    private final String removeBookmark = "DELETE FROM bookmarks WHERE LOWER(title) = LOWER(?);";
    private final String searchBookmarks = "select link_id, title, link, category,notes, date_created from bookmarks " +
            "WHERE LOWER(title) = LOWER(?)";

    public BookmarksDAO(Connection connection) {
        this.connection = connection;
    }

    public void setBookmark(Bookmarks bookmarks) {
        try {
            PreparedStatement ps = connection.prepareStatement(addBookmark);
            ps.setString(1, bookmarks.getTitle());
            ps.setString(2, bookmarks.getLink());
            ps.setString(3, bookmarks.getCategory());
            ps.setString(4, bookmarks.getNotes());
            int rows_affected = ps.executeUpdate();
            if (rows_affected > 0) System.out.println("Rows affected: " + rows_affected);
        } catch (SQLException e) {
            System.out.println("Unable to save bookmark.");
            System.out.println("No changes made");
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteBookmark(String title) {
        try {
            PreparedStatement ps = connection.prepareStatement(removeBookmark);
            ps.setString(1, title.trim());
            int rows_affected = ps.executeUpdate();
            return rows_affected > 0;
        } catch (SQLException e) {
            System.out.println("Unable to delete bookmark.");
            System.out.println("No changes made");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void displayBookmarks() {
        String query = "SELECT link_id, title, link, category, notes, date_created FROM bookmarks";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n" + "=".repeat(105));
            System.out.printf("%-6s | %-20s | %-20s | %-15s | %-20s | %-19s%n",
                    "ID", "TITLE", "LINK", "CATEGORY", "NOTES", "DATE CREATED");
            System.out.println("-".repeat(105));

            boolean empty = true;
            while (rs.next()) {
                empty = false;
                System.out.printf("%-6d | %-20s | %-20s | %-15s | %-20s | %-19s%n",
                        rs.getInt("link_id"),
                        truncate(rs.getString("title"), 20),
                        truncate(rs.getString("link"), 20),
                        truncate(rs.getString("category"), 15),
                        truncate(rs.getString("notes"), 20),
                        rs.getTimestamp("date_created") != null ? rs.getTimestamp("date_created").toString() : "N/A");
            }

            if (empty) {
                System.out.println(" No bookmarks found.");
            }
            System.out.println("=".repeat(105) + "\n");

        } catch (SQLException e) {
            System.err.println("Error fetching bookmarks: " + e.getMessage());
        }
    }


    private String truncate(String text, int width) {
        if (text == null) return "N/A";
        if (text.length() <= width) return text;
        return text.substring(0, width - 3) + "...";
    }

    public void searchBookmark(String title) {
        try {
            ResultSet rs = null;
            try {
                PreparedStatement ps = connection.prepareStatement(searchBookmarks);
                ps.setString(1, title.trim());
                rs = ps.executeQuery();
            } catch (SQLException e) {
                connection.close();
                throw new RuntimeException(e);
            }
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("----------------------------------------");
                System.out.println("Link ID      : " + rs.getInt("link_id"));
                System.out.println("Title        : " + rs.getString("title"));
                System.out.println("Link         : " + rs.getString("link"));
                System.out.println("Category     : " + rs.getString("category"));
                System.out.println("Notes        : " + rs.getString("notes"));
                System.out.println("Date Created : " + rs.getTimestamp("date_created"));
                System.out.println("----------------------------------------");
            }
            if (!found) {
                System.out.println("No bookmark found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTitle(int id, String title) {
        String sql = "UPDATE bookmarks SET title = ? WHERE link_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, title.trim());
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bookmark title updated successfully.");
            } else {
                System.out.println("No bookmark found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Unable to update bookmark.");
            System.out.println(e.getMessage());
        }
    }

    public void updateLink(int id, String link) {
        String sql = "UPDATE bookmarks SET link = ? WHERE link_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, link.trim());
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bookmark link updated successfully.");
            } else {
                System.out.println("No bookmark found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Unable to update bookmark.");
            System.out.println(e.getMessage());

        }
    }

    public void updateCategory(int id, String category) {
        String sql = "UPDATE bookmarks SET category = ? WHERE link_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, category.trim());
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bookmark category updated successfully.");
            } else {
                System.out.println("No bookmark found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Unable to update bookmark.");
            System.out.println(e.getMessage());
        }
    }

    public void updateNotes(int id, String notes) {
        String sql = "UPDATE bookmarks SET notes = ? WHERE link_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, notes.trim());
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bookmark notes updated successfully.");
            } else {
                System.out.println("No bookmark found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Unable to update bookmark.");
            System.out.println(e.getMessage());
        }
    }

}
