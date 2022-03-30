/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author Alex
 */
public class SubjectDir {

    private String findStr;
    private ArrayList<Subject> subjects;

    public SubjectDir() {
        findStr = "";
        subjects = new ArrayList<>();
    }

    public String getFindStr() {
        return findStr;
    }

    public void setFindStr(String findStr) {
        this.findStr = findStr;
    }

    public int getSubjectsCount() {
        return subjects.size();
    }

    public Subject[] getSubjects() {
        Subject[] res = new Subject[subjects.size()];
        subjects.toArray(res);
        return res;
    }

    public Subject getSubjects(int i) {
        if (i < 0 || i >= subjects.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return subjects.get(i);
    }

    public void setSubjects(int i, Subject ob) {
        if (i < 0 || i >= subjects.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        subjects.set(i, ob);
    }

    public void fill(DataSource ds) throws SQLException {
        subjects.clear();
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        String qu = "SELECT subject_id, name FROM " + Subject.tableName + " WHERE name LIKE '%" + findStr + "%'";
        ResultSet rs = st.executeQuery(qu);
        while (rs.next()) {
            subjects.add(new Subject(rs.getInt("subject_id"), rs.getString("name")));
        }
        rs.close();
        st.close();
        con.close();

    }
}
