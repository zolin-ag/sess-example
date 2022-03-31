/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author Alex
 */
public class Stud {

    public static final String tableName = "stud";

    private int studId;
    private int age;
    private String firstName;
    private String lastName;

    private Connection con;

    public Stud() {
        studId = -1;
        firstName = "";
        lastName = "";
    }

    public Stud(int studId, int age, String firstName, String lastName) {
        this.studId = studId;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getStudId() {
        return studId;
    }

    public void setStudId(int studId) {
        this.studId = studId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(DataSource ds) throws SQLException {
        this.con = ds.getConnection();
    }

    public void load(int id) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT stud_id, age, first_name, last_name FROM " + tableName + " WHERE stud_id = " + id);
        if (rs.next()) {
            studId = rs.getInt("stud_id");
            age = rs.getInt("age");
            firstName = rs.getString("first_name");
            lastName = rs.getString("last_name");
            return;
        } else {
            studId = -1;
            firstName = "";
            lastName = "";
        }
        rs.close();
        st.close();
    }
    public void load(DataSource ds, int id) throws SQLException {
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT stud_id, age, first_name, last_name FROM " + tableName + " WHERE stud_id = " + id);
        if (rs.next()) {
            studId = rs.getInt("stud_id");
            age = rs.getInt("age");
            firstName = rs.getString("first_name");
            lastName = rs.getString("last_name");
            return;
        } else {
            studId = -1;
            firstName = "";
            lastName = "";
        }
        rs.close();
        st.close();
        con.close();
    }

    public static void del(DataSource ds, int id) throws SQLException {
        if (id == -1) {
            return;
        }
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM " + tableName + " WHERE stud_id=" + id);
        st.close();
        con.close();
    }

    public void del(int id) throws SQLException {
        if (id == -1) {
            return;
        }
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM " + tableName + " WHERE stud_id=" + id);
        st.close();
        studId = -1;

    }

    public void add() throws SQLException {
        if (studId != -1) {
            return;
        }

        Statement st = con.createStatement();
        st.executeUpdate("INSERT INTO " + tableName + " ( age, first_name, last_name) VALUES(" + age + ", '" + firstName + "'" + ", '" + lastName + "')");
        st.close();
    }

    public void update() throws SQLException {
        if (studId == -1) {
            return;
        }
        Statement st = con.createStatement();
        String qu = "UPDATE " + tableName + " SET age=" + age + ", first_name='" + firstName + "'," + " last_name='" + lastName + "' WHERE stud_id=" + studId;
        st.executeUpdate(qu);
        st.close();
    }

    public void save() throws SQLException {
        if (studId == -1) {
            add();
        } else {
            update();
        }
    }

}
