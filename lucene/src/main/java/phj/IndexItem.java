package main.java.phj;

public class IndexItem {
    private long id;
    private String artist;
    private String title;
    private String text;

    public static final String ID = "id";
    public static final String ARTIST = "artist";
    public static final String TITLE = "title";
    public static final String TEXT = "text";

    public IndexItem(long id, String artist, String title, String text)
    {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.text = text;
    }
    public Long getId()
    {
        return id;
    }
    public Long getArtist()
    {
        return artist;
    }
    public Long getTitle()
    {
        return title;        
    }
    public Long getText()
    {
        return text;
    }
    @Override
    public String toString()
    {
        return "IndexItem{" + 
        "id= " + id + 
        ", artist= " + artist +
        ", title= " + title +
        ", text= " + text + '}';
    }

}
