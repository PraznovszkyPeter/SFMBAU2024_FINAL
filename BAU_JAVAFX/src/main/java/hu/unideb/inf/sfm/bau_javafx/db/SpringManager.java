package hu.unideb.inf.sfm.bau_javafx.db;

import hu.unideb.inf.sfm.bau_javafx.BauJavafxApplication;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import hu.unideb.inf.sfm.bau_javafx.model.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

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
    public boolean saveUser(User user) {
        UserRepository userRepository = (UserRepository) context.getBean(UserRepository.class);
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean DeleteUser(User user) {
        UserRepository userRepository = (UserRepository) context.getBean(UserRepository.class);
        try {
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getUser(String username) {
        UserRepository userRepository = (UserRepository) context.getBean(UserRepository.class);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers(String username) {
        UserRepository userRepository = (UserRepository) context.getBean(UserRepository.class);
        return userRepository.findAllExcludingUser(username);
    }
}
