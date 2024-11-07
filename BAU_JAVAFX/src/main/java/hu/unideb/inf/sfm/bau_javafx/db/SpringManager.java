package hu.unideb.inf.sfm.bau_javafx.db;

import hu.unideb.inf.sfm.bau_javafx.BauJavafxApplication;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import hu.unideb.inf.sfm.bau_javafx.model.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringManager implements Manager{

    private ConfigurableApplicationContext context;

    @Override
    public void start() {
        context = SpringApplication.run(BauJavafxApplication.class);
    }

    @Override
    public void stop() {
        context.close();
    }

    @Override
    public User getUser(String username) {
        UserRepository u = (UserRepository) context.getBean(UserRepository.class);
        return u.findByUsername(username);
    }
}
