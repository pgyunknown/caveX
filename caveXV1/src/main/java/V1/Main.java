package V1;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        boolean continues = true;
        Connection con = null;

        DBConnections connection = new DBConnections();
        try{
             con = connection.getConnection();
            System.out.println(con);
        }
        catch(SQLException e){
            System.out.println("Could not connect ");
            System.out.println(e.getMessage());
        }
        Bookmarks bookmark = new Bookmarks();




        while(continues){
            System.out.println("Enter 1 to add a link");
            System.out.println("Enter 2 to delete link");
            System.out.println("Enter 3 to view all links");
            System.out.println("Enter 4 to search for a link");
            System.out.println("Enter 0 to exit");
            int n=sc.nextInt();
            sc.nextLine();
            BookmarksDAO bookmarksDAO = new BookmarksDAO(con);
            switch (n){
                case 1:

                    System.out.println("Enter the title:");
                    bookmark.setTitle(sc.nextLine());

                    System.out.println("Enter the link:");
                    bookmark.setLink(sc.nextLine());

                    System.out.println("Enter the category:");
                    bookmark.setCategory(sc.nextLine());

                    System.out.println("Enter notes or blank:");
                    bookmark.setNotes(sc.nextLine());

                    bookmarksDAO.setBookmark(bookmark);

                    System.out.println("Bookmark saved successfully!");
                    break;

                case 2:
                    System.out.println("Enter the title of the bookmark to be removed");

                    if(bookmarksDAO.deleteBookmark(sc.nextLine())) System.out.println("Bookmark deleted");
                    else System.out.println("Bookmark not found");
                    break;

                case 3:
                    bookmarksDAO.displayBookmarks();
                    break;

                case 4:
                    System.out.println("Enter the title to search");
                    bookmarksDAO.searchBookmark(sc.nextLine());

                case 0:
                    continues = false;
                    System.out.println("Exiting...");
                    try {
                        connection.closeConnection(con);
                    } catch (SQLException e) {
                        System.out.println("Error closing connection.");
                    }
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }
}
