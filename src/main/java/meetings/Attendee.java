package meetings;

import java.util.Optional;

public class Attendee {
    final public String id;
    final public Optional<Position> position;
    final public String status;

    public Attendee(String id, Position position, String status) {
        this.id = id;
        this.position = Optional.of(position);
        this.status = status;
    }

    public Attendee(String id, String status) {
        this.id = id;
        this.status = status;
        this.position = Optional.empty();
    }
}
