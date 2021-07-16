import authentication.AuthenticationHandler;
import database.QueryBuilder;
import newsCaster.NewsCaster;

public class Main {

    public static void main(String[] args) {
//        server bootstrap
        QueryBuilder queryBuilder = QueryBuilder.getSingletonInstance();
        NewsCaster newsCaster = NewsCaster.getSingletonInstance();

        new AuthenticationHandler(8080);
    }
}
