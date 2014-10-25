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

import contacts.Contacts;
import contacts.ContactsService;

import java.util.ArrayList;
import java.util.List;

public class Meeting {
    public String href;
    public String status;       // TODO: enum
    public Position position;

    public Attendee host;
    public List<Attendee> people = new ArrayList<Attendee>();

    public Contacts contacts;


    public Meeting(int index) {
        this.href = String.format("https://justmeet-backend.herokuapp.com/meetings/%d", index);
        this.status = "pending";

        Attendee roberto = new Attendee("roberto", new Position(), "confirmed");
        Attendee alex = new Attendee("alex", "pending");
        people.add(roberto);
        people.add(alex);
    }

    public Meeting(int index, List<String> ids, Attendee host) {
        this.href = String.format("https://justmeet-backend.herokuapp.com/meetings/%d", index);
        this.status = "pending";
        this.host = host;

        for (String s : ids) {
            Attendee attendee = new Attendee(s, "pending");
            people.add(attendee);
        }
    }

    public void pretendItIsComplete() {
        status = "confirmed";
        position = new Position();
    }
}
