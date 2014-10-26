package meetings;

import java.util.Optional;

public class Attendee {
    final public String id;
    public Optional<Position> position;
    public MeetingStatus status;

    public Attendee(String id, Position position, MeetingStatus status) {
        this.id = id;
        this.position = Optional.of(position);
        this.status = status;
    }

    public Attendee(String id, MeetingStatus status) {
        this.id = id;
        this.status = status;
        this.position = Optional.empty();
    }
}
