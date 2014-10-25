import static spark.Spark.*;

import java.util.List;

import com.google.gson.Gson;

import contacts.ContactsService;
import meetings.Attendee;
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
            List<String> idsOfPeople = getIdsOfPeopleFrom(request.body());
            Attendee organiser = getOrganiserFrom(request.body());
            return MeetingsService.getInstance().makeMeeting(organiser, idsOfPeople);
        }, new JsonTransformer());

        // GET meeting information
        get("/meetings/:meetingId", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            return MeetingsService.getInstance().getMeeting(request.params(":meetingId"));
        }, new JsonTransformer());
    }

    public static List<String> getIdsOfPeopleFrom(String body) {
//        System.out.println(body);
        Gson gson = new Gson();
        MeetingCreation meetingCreation = gson.fromJson(body, MeetingCreation.class);

//        System.out.println(meetingCreation.people.size());
        List<String> ids = meetingCreation.getPeopleIds();
        return ids;
    }

    public static Attendee getOrganiserFrom(String body) {
        System.out.println("!!!!!! hello !!!!!!");
        Gson gson = new Gson();
        MeetingCreation meetingCreation = gson.fromJson(body, MeetingCreation.class);

        Attendee organiser = meetingCreation.getOrganiser();

        System.out.println(organiser.id);
        return organiser;
    }
}
