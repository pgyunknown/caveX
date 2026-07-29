package V1;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean continues = true;

        DBConnections connection = new DBConnections();
        Connection con= connection.getConnection();




        while(continues){
            System.out.println("Enter 1 to add a link");
            System.out.println("Enter 0 to exit");
            int n=sc.nextInt();
            sc.nextLine();
            switch (n){
                case 1:
                    Bookmarks bookmark = new Bookmarks();

                    System.out.println("Enter the title:");
                    bookmark.setTitle(sc.nextLine());

                    System.out.println("Enter the link:");
                    bookmark.setLink(sc.nextLine());

                    System.out.println("Enter the category:");
                    bookmark.setCategory(sc.nextLine());

                    System.out.println("Enter notes or blank:");
                    bookmark.setNotes(sc.nextLine());


                    BookmarksDAO bookmarksDAO = new BookmarksDAO( con);
                    bookmarksDAO.setBookmark(bookmark);

                    System.out.println("Bookmark saved successfully!");
                    break;

                case 0:
                    continues = false;
                    System.out.println("Exiting...");
                    connection.closeConnection(con);
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }
}
