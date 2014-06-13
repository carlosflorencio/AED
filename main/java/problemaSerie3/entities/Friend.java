package problemaSerie3.entities;

public class Friend {

    private String id;

    public Friend(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id+"";
    }
}
