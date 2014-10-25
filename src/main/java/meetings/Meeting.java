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
    public Position position;

    public List<Attendee> people = new ArrayList<Attendee>();



    public Meeting(int index) {
        this.href = String.format("https://justmeet-backend.herokuapp.com/meetings/%d", index);
        this.status = "pending";

        Attendee roberto = new Attendee("roberto", new Position(), "confirmed");
        Attendee alex = new Attendee("alex", "pending");
        people.add(roberto);
        people.add(alex);
    }

    public Meeting(int index, List<String> ids) {
        this.href = String.format("https://justmeet-backend.herokuapp.com/meetings/%d", index);
        this.status = "pending";

        Attendee roberto = new Attendee("roberto", new Position(), "confirmed");
        Attendee alex = new Attendee("alex", "pending");
        people.add(roberto);
        people.add(alex);
    }

    public void pretendItIsComplete() {
        status = "confirmed";
        position = new Position();
    }
}
