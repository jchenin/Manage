package jin.chen.pojo;

public class Bgm {
    private String id;

    private String author;

    private String name;

    private String path;

    private String duration;

    private String isautoplay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public String getIsautoplay() {
        return isautoplay;
    }

    public void setIsautoplay(String isautoplay) {
        this.isautoplay = isautoplay == null ? null : isautoplay.trim();
    }
}