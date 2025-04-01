package lk.ijse.dep13.auth.api;

import lk.ijse.dep13.auth.to.VipClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/clients")
public class VipClientHttpController {

    @GetMapping
    public List<VipClient> getAllVipClients(){
        try(Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/dep13_auth_app",
                "postgres", "psql");
            Statement stm = connection.createStatement()){
            ResultSet rst = stm.executeQuery("SELECT * FROM vip_client");
            List<VipClient> vipClientList = new ArrayList<>();
            while (rst.next()){
                String id = "VIP-%03d".formatted(rst.getInt("id"));
                String name = rst.getString("name");
                String address = rst.getString("address");
                String contact = rst.getString("contact");
                vipClientList.add(new VipClient(id, name, address, contact));
            }
            return vipClientList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
