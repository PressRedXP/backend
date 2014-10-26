import static spark.Spark.*;

import java.util.List;

import com.google.gson.Gson;

import contacts.ContactsService;
import meetings.Attendee;
import meetings.MeetingsService;
import meetings.Position;
import meetings.AttendanceData;

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

         // GET meetings you are in
         get("/people/:id/meetings", (request, response) -> {
             response.header("Access-Control-Allow-Origin", "*");
             return MeetingsService.getInstance().getMeetingsForAttendee(request.params(":id"));
        }, new JsonTransformer());

        // PUT
        put("/meetings/:meetingId/people/:id/attendance", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.status(200);
            Position position = getPositionFrom(request.body());
            MeetingsService.getInstance().setAttendence(request.params(":meetingId"), request.params(":id"), position);
            return "";
        });
    }


    public static List<String> getIdsOfPeopleFrom(String body) {
        Gson gson = new Gson();
        MeetingCreation meetingCreation = gson.fromJson(body, MeetingCreation.class);
        List<String> ids = meetingCreation.getPeopleIds();
        return ids;
    }

    public static Attendee getOrganiserFrom(String body) {
        Gson gson = new Gson();
        MeetingCreation meetingCreation = gson.fromJson(body, MeetingCreation.class);
        return meetingCreation.getOrganiser();
    }

    public static Position getPositionFrom(String body) {
        Gson gson = new Gson();
        AttendanceData attendanceData = gson.fromJson(body, AttendanceData.class);
        return attendanceData.position;
    }
}
