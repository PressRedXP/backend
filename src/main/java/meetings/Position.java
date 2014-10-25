package meetings;

public class Position {
    final public double latitude;
    final public double longitude;

    public Position (double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Position ()
    {
        this.latitude = 51.5072;
        this.longitude = 0.1275;
    }
}
