import static spark.Spark.*;

import contacts.ContactsService;
import meetings.MeetingsService;

public class Controller {

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));
        ContactsService contactsService = new ContactsService();

        get("/status", (request, response) -> {
            MeetingsService meetingsService = MeetingsService.getInstance();
            return 0;
        });

        // https://justmeet-backend.herokuapp.com/people/mrbuttons/contacts
        get("/people/:id/contacts", "application/json", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            return contactsService.getContactsFor(request.params(":id"));
        }, new JsonTransformer());

        // POST to create new meeting. Returns with 201 response
        post("/meetings", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.status(201);
            return MeetingsService.getInstance().makeMeeting();
        }, new JsonTransformer());

        // GET meeting information
        get("/meetings/1", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            return MeetingsService.getInstance().getMeeting(1);
        }, new JsonTransformer());
    }
}
