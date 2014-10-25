import static spark.Spark.get;
import static spark.Spark.setPort;

public class People {

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));
        ContactsService contactsService = new ContactsService();

        get("/hello", (request, response) -> "hello");

        // https://justmeet-backend.herokuapp.com/people/mrbuttons/contacts
        get("/people/*/contacts", "application/json", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            return contactsService.getContacts();
        }, new JsonTransformer());
    }
}
