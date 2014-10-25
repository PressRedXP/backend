import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class RequestMeetingExtractorTest {

    /*
    {
    "organiser":
        {
            "id": "roberto",
            "position": {
                "latitude": 1.23,
                "longitude": 2.34
            }
        },
    "people": [
        {
            "id": "alex"
        },
        {
            "id": "will"
        },
        {
            "id": "krishnan"
        }
    ]
}

     */

    @Test
    public void testParsing() {
        List<String> idsOfPeople = Controller.getIdsOfPeopleFrom("{\"organiser\":{\"id\":\"roberto\",\"position\":{\"latitude\":1.23,\"longitude\":2.34}},\"people\":[{\"id\":\"alex\"},{\"id\":\"will\"},{\"id\":\"krishnan\"}]}");

        assertTrue(idsOfPeople.size() == 3);
    }
}
