package app;

import javafx.collections.*;
import javafx.scene.control.*;
import java.sql.*;

public class StudentController {

    public static ObservableList<Student> getStudents() {
        ObservableList<Student> list = FXCollections.observableArrayList();
        try (Connection con = DBUtil.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM students");

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void insert(String name, String email, String course) {
        String sql = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);

            ps.executeUpdate();
            System.out.println("STUDENT INSERTED SUCCESSFULLY");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
