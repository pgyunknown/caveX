package V1;
import java.time.LocalDateTime;
public class Bookmarks {
    private String title;
    private String link;
    private String category;
    private String notes;
    private LocalDateTime dateCreated;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (Utilities.isNull(title)) {
            throw new IllegalArgumentException("cannot be null or empty");
        }
        this.title = title.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        if (Utilities.isNull(link)) {
            throw new IllegalArgumentException("Link cannot be null or empty.");
        }

        if (!(link.contains("x.com") || link.contains("twitter.com"))) {
            throw new IllegalArgumentException("Only X/Twitter links are allowed.");
        }

        this.link = link;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if(Utilities.isNull(category)) throw new IllegalArgumentException("cannot be null or empty");
        this.category=category;

    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

//
//    public Bookmarks(String title, String link, String category, String notes, LocalDateTime dateCreated) {
//        setTitle(title);
//        setLink(link);
//        setCategory(category);
//        this.notes = notes;
//        this.dateCreated = dateCreated;
//    }

    @Override
    public String toString() {
        return "Bookmarks{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", category='" + category + '\'' +
                ", notes='" + notes + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
