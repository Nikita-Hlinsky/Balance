import java.sql.ResultSet;
import java.sql.SQLException;

public class purchase {
    public static void getStudents(ResultSet rs) throws SQLException {
        while(rs.next()){
            int summ = rs.getInt("summ");
            int cost = rs.getInt("purch_cost");
            String name = rs.getString("purch_name");

            System.out.println("summ - " + summ);
            System.out.println("cost - " + cost);
            System.out.println("name - " + name);
        }
    }


}
