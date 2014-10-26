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

import Dao.Place;
import Dao.PubFinderDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Meeting {
    public String href;
    public MeetingStatus status;
    public Position position;
    public String pubname;

    public List<Attendee> people = new ArrayList<>();

    public Meeting(int index, List<String> ids, Attendee organiser) {
        this.href = String.format("https://justmeet-backend.herokuapp.com/meetings/%d", index);
        this.status = MeetingStatus.pending;


        for (String s : ids) {
            Attendee attendee = new Attendee(s, MeetingStatus.pending);
            people.add(attendee);
        }
        people.add(organiser);
    }

    public void updateAttendence(String attendeeId, MeetingStatus newStatus, Position attendeePosition) {
        for (Attendee attendee : people) {
            if (attendee.id.equals(attendeeId)) {
                attendee.status = newStatus;
                attendee.position = Optional.of(attendeePosition);
            }
        }

        status = MeetingStatus.confirmed;
        people.stream()
                .filter(attendee -> attendee.status.equals(MeetingStatus.pending))
                .forEach(attendee -> status = MeetingStatus.pending);

        if (status.equals(MeetingStatus.confirmed)) {
            setMeetingPositionToCentreOfAttendeesPosition();
            setMeetingPositionToBestPub();
        }
    }

    private void setMeetingPositionToCentreOfAttendeesPosition() {
        double sumLat = 0;
        double sumLong = 0;
        for (Attendee attendee: people) {
            // TODO
            if (attendee.position.isPresent()) {
                sumLat += attendee.position.get().latitude;
                sumLong += attendee.position.get().longitude;
            }
        }

        this.position = new Position(sumLat / people.size(), sumLong / people.size());
    }

    private void setMeetingPositionToBestPub() {
        PubFinderDao pubFinderDao = new PubFinderDao();
        Place place = pubFinderDao.getPlace(this.position.latitude,this.position.longitude);
        if (place != null) {
            this.position = new Position(place.latitude, place.longitude);
            this.pubname = place.name;
        } else {
            this.pubname = "Not found";
        }

}

}
