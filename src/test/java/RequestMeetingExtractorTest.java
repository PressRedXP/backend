import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import meetings.Attendee;
import meetings.MeetingStatus;

public class RequestMeetingExtractorTest {

    private RequestExtractor requestExtractor;
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

    @Before
    public void setup() {
        requestExtractor = new RequestExtractor();
    }

    @Test
    public void testParsingOfInvitees() {
        List<String> idsOfPeople = requestExtractor.getIdsOfPeopleFrom(testBodyWith3Invitees);

        assertTrue(idsOfPeople.size() == 3);
    }

    @Test
    public void testParsingOfOrganiser() {
        Attendee organiser = requestExtractor.getOrganiserFrom(testBodyWith3Invitees);

        assertTrue(organiser.id.equals("roberto"));
        assertTrue(organiser.status.equals(MeetingStatus.confirmed));

        assertNotNull(organiser.position);
        assertTrue(organiser.position.latitude == 1.23);
        assertTrue(organiser.position.longitude == 2.34);
    }
}
