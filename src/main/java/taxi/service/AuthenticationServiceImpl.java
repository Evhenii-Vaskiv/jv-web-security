package taxi.service;

import taxi.exception.AuthenticationException;
import taxi.lib.Injector;
import taxi.lib.Service;
import taxi.model.Driver;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Injector injector = Injector.getInstance("taxi");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driver = driverService.findByLogin(login, password).orElseThrow(() ->
                new AuthenticationException("Login or password is incorrect"));
        if (!driver.getPassword().equals(password)) {
            throw new AuthenticationException("Login or password is incorrect");
        }
        return driver;
    }
}
