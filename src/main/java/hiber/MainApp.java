package hiber;

import hiber.config.AppConfig;
import hiber.model.*;
import hiber.service.UserService;
import jakarta.persistence.NoResultException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Bob", "Marley", "bob1999@mail.ru");
      User user2 = new User("Steve", "Djobs", "steve_djobs@yandex.ru");
      User user3 = new User("Harley", "Quinn", "loveDjoker@inbox.ru");
      User user4 = new User("Spider", "Man", "piter_parker@gmail.com");

      Car car1 = new Car("Dodge", 2019);
      Car car2 = new Car("Ferrari", 2003);
      Car car3 = new Car("Lamborgini", 2022);
      Car car4 = new Car("Mercedes", 2016);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("=================================================================");
      }

      System.out.println(userService.getUserByCar("Dodge", 2019));
      System.out.println("=================================================================");

      try {
         userService.getUserByCar("Niva", 1985);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
