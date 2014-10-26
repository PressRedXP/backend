import static org.junit.Assert.assertTrue;

import org.junit.Test;

import meetings.Position;

public class SetAttendanceExtractorTest {

    /*
    {
    status: confirmed,
    position: {
        longitude: 1,
        latitude: 1802321
    }
    */

    private static String putBodyWithConfirmedStatus = "{status:confirmed,position:{longitude:1,latitude:1802321}}";

    @Test
    public void testParsingOfAttendance() {
        RequestExtractor requestExtractor = new RequestExtractor();
        Position position = requestExtractor.getPositionFrom(putBodyWithConfirmedStatus);

        assertTrue(position.longitude == 1);
        assertTrue(position.latitude == 1802321);
    }
}
