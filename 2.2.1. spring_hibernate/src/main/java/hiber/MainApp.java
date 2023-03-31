package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"),
              new Car("BMW", 123));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"),
              new Car("NIVA", 456));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"),
              new Car("AUDI", 789));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"),
              new Car("PEGO", 1011));

      List<User> users = userService.listUsers();
      System.out.println(users);

      Car findCar = new Car("AUDI", 789);
      List<User> findUsers = userService.findUser(findCar.getModel(), findCar.getSeries());
      if (findUsers.size() == 0) {
          System.out.println("Не найден пользователь с автомобилем " + findCar.toString());
      } else { System.out.println(findUsers);}
         context.close();
   }
}
