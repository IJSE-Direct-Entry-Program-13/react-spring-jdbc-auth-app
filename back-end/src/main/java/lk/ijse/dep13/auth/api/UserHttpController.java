package lk.ijse.dep13.auth.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lk.ijse.dep13.auth.to.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;

@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "*")
@RequestMapping("/users")
public class UserHttpController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody User user, HttpServletRequest request){
        try(Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/dep13_auth_app",
                        "postgres", "psql");
            Statement stm = connection.createStatement()) {
            ResultSet rst = stm.executeQuery("SELECT * FROM \"user\" WHERE username='%s' AND password='%s'"
                    .formatted(user.username(), user.password()));
            if (rst.next()){
                request.getSession();
                return "logged in";
            }else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
    }
}
