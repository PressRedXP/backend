import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.setPort;

import contacts.ContactsService;
import meetings.Meeting;

public class Controller {

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));
        ContactsService contactsService = new ContactsService();

        get("/hello", (request, response) -> "hello");

        // https://justmeet-backend.herokuapp.com/people/mrbuttons/contacts
        get("/people/:id/contacts", "application/json", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            return contactsService.getContactsFor(request.params(":id"));
        }, new JsonTransformer());

        // 201 response?
        post("/meetings", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            return makeMeeting();
        }, new JsonTransformer());
    }

    static public Meeting makeMeeting() {
        return new Meeting();
    }
}
