package contacts;

public class Person {
    public final String name;
    public final String id;
    public final String href;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
        this.href = makeHrefFromId(id);
    }

    private String makeHrefFromId(String id) {
        return String.format("https://justmeet-backend.herokuapp.com/people/%s", id);
    }
}
