import authentication.AuthenticationHandler;
import database.QueryBuilder;
import newsCaster.NewsCaster;

public class Main {

    public static void main(String[] args) {
        int authenticationHandlerPort = 8081;
        new AuthenticationHandler(authenticationHandlerPort);
        QueryBuilder queryBuilder = QueryBuilder.getSingletonInstance();
        NewsCaster newsCaster = NewsCaster.getSingletonInstance();
    }
}
