import static spark.Spark.*;

public class People {

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));

        get("/people", (request, response) -> "[Test]");

        get("/hello", "application/json", (request, response) -> {
            return new Person("Mr Animals", "animal");
        }, new JsonTransformer());
    }
}
