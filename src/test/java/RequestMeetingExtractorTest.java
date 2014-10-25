import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import meetings.Attendee;

public class RequestMeetingExtractorTest {

    /*
    {
    "organiser": {
        "id": "roberto",
        "position": {
            "latitude": 1,
            "longitude": 1
        }
    },
    "people": [
        {
            "id": "alex"
        },
        {
            "id": "will"
        }
    ]
    }
     */

    private static String testBodyWith3Invitees = "{\"organiser\":{\"id\":\"roberto\",\"position\":{\"latitude\":1.23,\"longitude\":2.34}},\"people\":[{\"id\":\"alex\"},{\"id\":\"will\"},{\"id\":\"krishnan\"}]}";

    @Test
    public void testParsingOfInvitees() {
        List<String> idsOfPeople = Controller.getIdsOfPeopleFrom(testBodyWith3Invitees);

        assertTrue(idsOfPeople.size() == 3);
    }

    @Test
    public void testParsingOfOrganiser() {
        Attendee organiser = Controller.getOrganiserFrom(testBodyWith3Invitees);

        assertTrue(organiser.id.equals("roberto"));
        assertTrue(organiser.position.isPresent());
        assertTrue(organiser.position.get().latitude == 1.0);
        assertTrue(organiser.position.get().longitude == 1.0);
    }
}
