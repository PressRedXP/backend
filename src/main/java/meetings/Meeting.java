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

public class Meeting {
    public String href;
    public String status;       // TODO: enum

    public List<Attendee> people = new ArrayList<Attendee>();

    public Meeting() {
        this.href = "https://justmeet-backend.herokuapp.com/meetings/1";
        this.status = "pending";

        Attendee roberto = new Attendee("roberto", new Position(), "confirmed");
        Attendee alex = new Attendee("alex", "pending");
        people.add(roberto);
        people.add(alex);
    }
}
