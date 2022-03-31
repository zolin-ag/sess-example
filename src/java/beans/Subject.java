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
public class Subject {

    public static final String tableName = "subject";

    private int subjectId;
    private String name;

    private Connection con;

    public Subject(int sibjectId, String name) {
        this.subjectId = sibjectId;
        this.name = name;
    }

    public Subject() {
        subjectId = -1;
        name = "";
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(DataSource ds) throws SQLException {
        this.con = ds.getConnection();
    }

    public void load(DataSource ds, int id) throws SQLException {
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT subject_id, name FROM " + tableName + " WHERE subject_id = " + id);
        if (rs.next()) {
            subjectId = rs.getInt("subject_id");
            name = rs.getString("name");
            return;
        } else {
            subjectId = -1;
            name = "";
        }
        rs.close();
        st.close();
        con.close();
    }
    public void load(int id) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT subject_id, name FROM " + tableName + " WHERE subject_id = " + id);
        if (rs.next()) {
            subjectId = rs.getInt("subject_id");
            name = rs.getString("name");
            return;
        } else {
            subjectId = -1;
            name = "";
        }
        rs.close();
        st.close();
    }

    public static void del(DataSource ds, int id) throws SQLException {
        if (id == -1) {
            return;
        }
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM " + tableName + " WHERE subject_id=" + id);
        st.close();
        con.close();
    }

    public void del(int id) throws SQLException {
        if (id == -1) {
            return;
        }
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM " + tableName + " WHERE subject_id=" + id);
        st.close();
        subjectId = -1;

    }

    public void add() throws SQLException {
        if (subjectId != -1) {
            return;
        }

        Statement st = con.createStatement();
        st.executeUpdate("INSERT INTO " + tableName + " (name) VALUES('" + name + "')");
        st.close();
    }

    public void update() throws SQLException {
        if (subjectId == -1) {
            return;
        }
        Statement st = con.createStatement();
        String qu = "UPDATE " + tableName + " SET name='" + name + "' WHERE subject_id=" + subjectId;
        st.executeUpdate(qu);
        st.close();
    }

    public void save() throws SQLException {
        if (subjectId == -1) {
            add();
        } else {
            update();
        }
    }
}
