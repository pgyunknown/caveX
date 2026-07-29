package V1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continues = true;
        Connection con = null;

        DBConnections connection = new DBConnections();
        try {
            con = connection.getConnection();
            System.out.println("Connected to database successfully!");
        } catch (SQLException e) {
            System.out.println("Could not connect");
            System.out.println(e.getMessage());
        }

        Bookmarks bookmark = new Bookmarks();

        while (continues) {
            System.out.println("\n--- BOOKMARK MANAGER ---");
            System.out.println("Enter 1 to add a link");
            System.out.println("Enter 2 to delete link");
            System.out.println("Enter 3 to view all links");
            System.out.println("Enter 4 to search for a link");
            System.out.println("Enter 5 to update link");
            System.out.println("Enter 0 to exit");

            int n = sc.nextInt();
            sc.nextLine(); // Consume newline after nextInt()

            BookmarksDAO bookmarksDAO = new BookmarksDAO(con);

            switch (n) {
                case 1:
                    System.out.println("Enter the title:");
                    bookmark.setTitle(sc.nextLine());

                    System.out.println("Enter the link:");
                    bookmark.setLink(sc.nextLine());

                    System.out.println("Enter the category:");
                    bookmark.setCategory(sc.nextLine());

                    System.out.println("Enter notes or leave blank:");
                    bookmark.setNotes(sc.nextLine());

                    bookmarksDAO.setBookmark(bookmark);
                    System.out.println("Bookmark saved successfully!");
                    break;

                case 2:
                    System.out.println("Enter the title of the bookmark to be removed:");
                    if (bookmarksDAO.deleteBookmark(sc.nextLine())) {
                        System.out.println("Bookmark deleted");
                    } else {
                        System.out.println("Bookmark not found");
                    }
                    break;

                case 3:
                    bookmarksDAO.displayBookmarks();
                    break;

                case 4:
                    System.out.println("Enter the title to search:");
                    bookmarksDAO.searchBookmark(sc.nextLine());
                    break;

                case 5:
                    boolean found = true;

                    while (found) {
                        System.out.println("\n--- UPDATE MENU ---");
                        System.out.println("Enter 1 to update title");
                        System.out.println("Enter 2 to update Link");
                        System.out.println("Enter 3 to update category");
                        System.out.println("Enter 4 to update notes");
                        System.out.println("Enter 0 to return to main menu");

                        int updateChoice = sc.nextInt();
                        sc.nextLine(); // Clear newline buffer

                        if (updateChoice == 0) {
                            found = false;
                            System.out.println("Exiting update section...");
                            break;
                        }

                        System.out.println("Enter bookmark ID:");
                        int id = sc.nextInt();
                        sc.nextLine(); // Clear newline buffer

                        switch (updateChoice) {
                            case 1 -> {
                                System.out.println("Enter new title:");
                                String newTitle = sc.nextLine();
                                bookmarksDAO.updateTitle(id, newTitle);
                            }
                            case 2 -> {
                                System.out.println("Enter new link:");
                                String newLink = sc.nextLine();
                                bookmarksDAO.updateLink(id, newLink);
                            }
                            case 3 -> {
                                System.out.println("Enter new category:");
                                String newCategory = sc.nextLine();
                                bookmarksDAO.updateCategory(id, newCategory);
                            }
                            case 4 -> {
                                System.out.println("Enter new notes:");
                                String newNotes = sc.nextLine();
                                bookmarksDAO.updateNotes(id, newNotes);
                            }
                            default -> System.out.println("Invalid entry");
                        }
                    }
                    break; 
                case 0:
                    continues = false;
                    System.out.println("Exiting...");
                    try {
                        if (con != null) {
                            connection.closeConnection(con);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error closing connection.");
                    }
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
        sc.close();
    }
}