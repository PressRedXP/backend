package meetings;

/*
{
    "meeting": {
        "href": "????",
        "status": "pending",

        "people": [
            {
                "id": "roberto",
                "position": {
                    "latitude": 1.23,
                    "longitude": 2.34
                },
                "status": "confirmed"
            },
            {
                "id": "alex",
                "status": "pending"
            }
        ]
    }
}
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Meeting {
    public String href;
    public MeetingStatus status;
    public Position position;

//    public Attendee organiser;
    public List<Attendee> people = new ArrayList<Attendee>();

//    public Contacts contacts;

    public Meeting(int index, List<String> ids, Attendee organiser) {
        this.href = String.format("https://justmeet-backend.herokuapp.com/meetings/%d", index);
        this.status = MeetingStatus.pending;


        for (String s : ids) {
            Attendee attendee = new Attendee(s, MeetingStatus.pending);
            people.add(attendee);
        }
        people.add(organiser);
    }

    public void updateAttendence(String attendeeId, MeetingStatus newStatus, Position position) {
        for (Attendee attendee : people) {
            if (attendee.id.equals(attendeeId)) {
                attendee.status = newStatus;
                attendee.position = Optional.of(position);
            }
        }

        status = MeetingStatus.confirmed;
        people.stream()
                .filter(attendee -> attendee.status.equals(MeetingStatus.pending))
                .forEach(attendee -> status = MeetingStatus.pending);
    }
}
