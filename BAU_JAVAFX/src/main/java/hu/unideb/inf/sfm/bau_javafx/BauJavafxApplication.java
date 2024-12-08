package hu.unideb.inf.sfm.bau_javafx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BauJavafxApplication {
<<<<<<< HEAD
    public static void main(String[] args) {
        SpringApplication.run(BauJavafxApplication.class, args);
=======

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) { //Mivel SpringWeb dependency lett beállítva, ezért alapból Tomcat server indulna
        SpringApplication app = new SpringApplication(BauJavafxApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE); //itt kikapcsolom,hogy ne legyen web app.
        app.run(args);

>>>>>>> backend_react
    }
}
