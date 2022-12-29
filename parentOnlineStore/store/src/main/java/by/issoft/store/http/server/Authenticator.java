package by.issoft.store.http.server;

import com.sun.net.httpserver.BasicAuthenticator;

import static by.issoft.store.utilities.StoreConstants.Http.*;

public class Authenticator extends BasicAuthenticator {

    public Authenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return (username.equals(USERNAME) && password.equals(PASSWORD));
    }
}
