import static spark.Spark.get;
import static spark.Spark.setPort;

import java.util.ArrayList;
import java.util.List;

public class People {

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));

        get("/hello", (request, response) -> "hello");

        // https://justmeet-backend.herokuapp.com/people/mrbuttons/contacts
        get("/people/*/contacts", "application/json", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            return getContacts();
        }, new JsonTransformer());
    }

    private static Contacts getContacts() {
        List<Person> people = new ArrayList<Person>();
        people.add(new Person("Alex Sneddon", "alex"));
        people.add(new Person("Will Crossland", "will"));
        people.add(new Person("Krishnan Sambasivan", "krishnan"));
        people.add(new Person("Roberto Nerici", "roberto"));

        return new Contacts(people);
    }
}
