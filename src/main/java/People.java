import static spark.Spark.*;

public class People {

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));
        get("/people", (req, res) -> "[Test]");
    }
}
