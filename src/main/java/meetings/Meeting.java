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

import contacts.Contacts;

public class Meeting {
    public String href;
    public String status;       // TODO: enum
    public Position position;

    public Attendee organiser;
    public List<Attendee> people = new ArrayList<Attendee>();

    public Contacts contacts;

    public Meeting(int index, List<String> ids, Attendee organiser) {
        this.href = String.format("https://justmeet-backend.herokuapp.com/meetings/%d", index);
        this.status = "pending";


        for (String s : ids) {
            Attendee attendee = new Attendee(s, "pending");
            people.add(attendee);
        }
        people.add(organiser);
    }

    public void pretendItIsComplete() {
        status = "confirmed";
        position = new Position();
    }
}
