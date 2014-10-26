import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import meetings.*;
import org.junit.Test;

public class MeetingServiceTest {

    @Test
    public void testCreateMeeting() {
        MeetingsService service = MeetingsService.getInstance();
        Attendee attendee = new Attendee("test", MeetingStatus.confirmed);
        List<String> ids = new ArrayList<String>();
        ids.add("test1");
        Meeting meeting = service.makeMeeting(attendee, ids);

        assertNotNull(meeting);
    }

    @Test
    public void testGetMeeting() {
        MeetingsService service = MeetingsService.getInstance();
        Attendee organiser = new Attendee("test", MeetingStatus.confirmed);
        List<String> ids = new ArrayList<String>();
        ids.add("test1");

        service.makeMeeting(organiser, ids);
        Meeting meeting = service.getMeeting("1");

        assertNotNull(meeting);
    }

    @Test
    public void givenOneMeetingWithFredAndOneWithout_whenGetMeetingsForFred_thenCorrectMeetingisReturned() {
        MeetingsService service = MeetingsService.getInstance();

        Attendee organiser = new Attendee("fred", MeetingStatus.confirmed);
        service.makeMeeting(organiser, Arrays.asList("wilma", "velma"));

        Attendee organiser2 = new Attendee("harry", MeetingStatus.confirmed);
        service.makeMeeting(organiser2, Arrays.asList("ron", "hermione"));

        MeetingList meetingList = service.getMeetingsForAttendee("fred");

        assertTrue(meetingList.meetings.size() == 1);
    }

    @Test
    public void givenMeetingWithTwoInvitees_whenBothInviteesAccept_thenMeetingIsConfirmed() {
        MeetingsService service = MeetingsService.getInstance();

        Attendee organiser = new Attendee("fred", MeetingStatus.confirmed);
        service.makeMeeting(organiser, Arrays.asList("wilma", "velma"));

        assertTrue(service.getMeeting("1").status.equals(MeetingStatus.pending));

        Position position = new Position();

        service.setAttendence("1", "wilma", position);
        assertTrue(service.getMeeting("1").status.equals(MeetingStatus.pending));

        service.setAttendence("1", "velma", position);
        assertTrue(service.getMeeting("1").status.equals(MeetingStatus.confirmed));
    }
}
