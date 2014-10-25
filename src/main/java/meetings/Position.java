package meetings;

import java.util.Random;

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
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1);
        this.latitude = 51.5072 + randomInt;
        this.longitude = 0.1275  + randomInt;
    }
}
