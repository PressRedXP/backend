import java.util.List;

import com.google.gson.Gson;

import meetings.AttendanceData;
import meetings.Attendee;
import meetings.Position;

public class RequestExtractor {
    private Gson gson = new Gson();

    public List<String> getIdsOfPeopleFrom(String body) {
        MeetingCreation meetingCreation = gson.fromJson(body, MeetingCreation.class);
        List<String> ids = meetingCreation.getPeopleIds();
        return ids;
    }

    public Attendee getOrganiserFrom(String body) {
        Gson gson = new Gson();
        MeetingCreation meetingCreation = gson.fromJson(body, MeetingCreation.class);
        return meetingCreation.getOrganiser();
    }

    public Position getPositionFrom(String body) {
        Gson gson = new Gson();
        AttendanceData attendanceData = gson.fromJson(body, AttendanceData.class);
        return attendanceData.position;
    }

}
