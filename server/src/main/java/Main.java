import authentication.AuthenticationHandler;
import database.QueryBuilder;
import newsCaster.NewsCaster;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
//        server bootstrap
        QueryBuilder queryBuilder = QueryBuilder.getSingletonInstance();
        NewsCaster newsCaster = NewsCaster.getSingletonInstance();

        new AuthenticationHandler(8080);
    }
}
