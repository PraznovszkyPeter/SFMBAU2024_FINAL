package hu.unideb.inf.sfm.bau_javafx.db;

import hu.unideb.inf.sfm.bau_javafx.model.User;

public interface Manager {
    public void start();
    public void stop();
    public void saveUser(User user);
    public User getUser(String username);
}
