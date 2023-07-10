import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo {
    public static void main(String[] args) {
        /*
        1- Driver register etme +
        2- Veritabanı bağlantı açma +
        3- Çalıştırılacak sorgu için stament yaratma
         ResultSet resultSet = statement.executeQuery(); // SELECT
            int i = statement.executeUpdate(); // INSERT, UPDATE,DELETE, CREATE
           boolean execute = statement.execute();
        4- Sorguyu çalıştır
        5- Veritabanı bağlantısı kapatma

         */
        Student student = new Student(1,"ahmet",3.0);
        Student student2 = new Student(2,"asım",4.0);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/upschool", "root", "ahmet");
            Statement statement = connection.createStatement();

            String ddl = "CREATE TABLE IF NOT EXISTS students " +
                    "(id int PRIMARY KEY AUTO_INCREMENT," +
                    "name varchar(50), grade double)";

            statement.executeUpdate(ddl);

            String insert = "INSERT INTO students(name,grade) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);

            preparedStatement.setString(1,student.getName());
            preparedStatement.setDouble(2,student.getGrade());
            System.out.println("Created student number" + preparedStatement.executeUpdate());

            preparedStatement.setString(1,student2.getName());
            preparedStatement.setDouble(2,student2.getGrade());
            System.out.println("Created student number" + preparedStatement.executeUpdate());

            String select = "SELECT * from students";
            ResultSet resultSet = statement.executeQuery(select);
            List<Student> studentList = new ArrayList<>();

            while (resultSet.next()){
                Student setFromDb = new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),resultSet.getDouble("grade"));
                studentList.add(setFromDb);
            }

            studentList.forEach(System.out::println);

            //UPDATE

            String stUpdateSql = "UPDATE students SET name = 'AHMET GULTEKIN' where id=1";
            statement.executeUpdate(stUpdateSql);


            String prUpdateSql = "UPDATE students SET name = ? where id=1";
            PreparedStatement preparedStatement2 = connection.prepareStatement(prUpdateSql);

            preparedStatement2.setString(1, "ahmet gltkn");
            preparedStatement2.executeUpdate();

            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}