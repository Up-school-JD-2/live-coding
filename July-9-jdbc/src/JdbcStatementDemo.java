import java.sql.*;

public class JdbcStatementDemo {
    public static void main(String[] args) {

        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/upschool", "root", "ahmet");
        ) {

            try (Statement statement = connection.createStatement()) {

                String ddlSql = "CREATE TABLE IF NOT EXISTS employees"
                        + "(emp_id int PRIMARY KEY AUTO_INCREMENT, name varchar(30),"
                        + "position varchar(30), salary double)";

                statement.execute(ddlSql);

                String insertSql = "INSERT INTO employees(name, position, salary)"
                        + " VALUES('john', 'developer', 2000)";

                if (statement.executeUpdate(insertSql) == 1) {

                    String selectSql = "SELECT * FROM employees";

                    ResultSet resultSet = statement.executeQuery(selectSql);

                    while (resultSet.next()) {

                        String employee = "Id : '" + resultSet.getInt(1) + "' name : '"
                                + resultSet.getString(2) + "' position : '"
                                + resultSet.getString(3) + "' salary : '"
                                + resultSet.getDouble(4) + "'";

                        System.out.println(employee);
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
