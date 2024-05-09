import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class openOrCloseConnection {

    public void openConnection(Connection connect ,  Statement state){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            state= connect.createStatement(); //make obj
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
