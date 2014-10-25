import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import meetings.Meeting;

public class MeetingServiceTest {

    @Test
    public void testCreateMeeting() {
        Controller controller = new Controller();

        Meeting meeting = controller.makeMeeting();

        assertNotNull(meeting);
    }
}
