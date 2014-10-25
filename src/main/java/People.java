import static spark.Spark.*;

public class People {

    public static void main(String[] args) {
        setPort(Integer.parseInt(System.getenv("PORT")));
<<<<<<< HEAD
//        get("/people", (req, res) -> "[Test]");

        get("/people", (request, response) -> "[Test]");

        get("/hello", "application/json", (request, response) -> {
            return new Person("Mr Animals", "animal");
        }, new JsonTransformer());
=======
        get("/people", (req, res) -> "[Test - Alex pushing to heroku]");
>>>>>>> 4b17618cf5cd6bcd07d9f92f615f5a5e21690efa
    }
}
