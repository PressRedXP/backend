import static spark.Spark.*;

import java.util.List;

import contacts.ContactsService;
import meetings.Attendee;
import meetings.MeetingsService;
import meetings.Position;

public class Controller {
    private static RequestExtractor requestExtractor = new RequestExtractor();


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
            List<String> idsOfPeople = requestExtractor.getIdsOfPeopleFrom(request.body());
            Attendee organiser = requestExtractor.getOrganiserFrom(request.body());
            return MeetingsService.getInstance().makeMeeting(organiser, idsOfPeople);
        }, new JsonTransformer());

        // delete to clear meetings
        delete("/meetings", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.status(200);
            MeetingsService.getInstance().clearMeetings();
            return "";
        });

        // GET meeting information
        get("/meetings/:meetingId", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            return MeetingsService.getInstance().getMeeting(request.params(":meetingId"));
        }, new JsonTransformer());

         // GET meetings you are in
         get("/people/:id/meetings", (request, response) -> {
             response.header("Access-Control-Allow-Origin", "*");
             return MeetingsService.getInstance().getMeetingsForAttendee(request.params(":id"));
        }, new JsonTransformer());

        // OPTIONS
        options("/meetings/:meetingId/people/:id/attendance", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "PUT, OPTIONS");
            response.status(200);
            return "";
        });

        // PUT
        put("/meetings/:meetingId/people/:id/attendance", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.status(200);
            Position position = requestExtractor.getPositionFrom(request.body());
            MeetingsService.getInstance().setAttendence(request.params(":meetingId"), request.params(":id"), position);
            return "";
        });
    }
}
