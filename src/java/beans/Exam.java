/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import static beans.Subject.tableName;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.sql.DataSource;

/**
 *
 * @author Alex
 */
public class Exam {

    public static final String tableName = "exam";

    private int examId;
    private Stud stud;
    private Subject subject;
    private LocalDate examDate;
    private double mark;

    public Exam(int examId, int studId, int subjectId, LocalDate examDate, double mark) throws SQLException {
        this.examId = examId;
        stud = new Stud();
        stud.load(studId);
        subject = new Subject();
        subject.load(subjectId);
        this.examDate = examDate;
        this.mark = mark;
    }

    public Exam() {
        examId = -1;
        stud = new Stud();
        subject = new Subject();
        examDate = LocalDate.now();
    }

    public Stud getStud() {
        return stud;
    }

    public void setStud(Stud stud) {
        this.stud = stud;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }


    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void load(DataSource ds, int id) throws SQLException {
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        String qu = "SELECT  stud_id, subject_id, exam_date, mark  FROM " + tableName + " WHERE exam_id = " + id;
        ResultSet rs = st.executeQuery(qu);
        if (rs.next()) {
            examId = id;
            subject.load(rs.getInt("subject_id")); 
            stud.load( rs.getInt("stud_id"));
            Date dt = rs.getDate("exam_date");
            examDate = dt.toLocalDate();
            mark = rs.getDouble("mark");
            return;
        } else {
            examId = -1;
            examDate = LocalDate.now();
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
        st.executeUpdate("DELETE FROM " + tableName + " WHERE exam_id=" + id);
        st.close();
        con.close();
    }

    public void add(DataSource ds) throws SQLException {
        if (examId != -1) {
            return;
        }
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        String qu = "INSERT INTO " + tableName + " (stud_id, subject_id, exam_date, mark) "+
                String.format("VALUES( %d, %d, '%s', %.2f)", stud.getStudId(), subject.getSubjectId(), 
                    examDate.format(DateTimeFormatter.ISO_LOCAL_DATE), mark);
        System.out.println("--- "+qu);
        st.executeUpdate(qu);
        st.close();
    }

    public void update(DataSource ds) throws SQLException {
        if (examId == -1) {
            return;
        }
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        String qu = String.format( 
                "UPDATE %s SET stud_id = %d , subject_id=%d, exam_date='%s', mark=%.2f WHERE exam_id=%d",  
                tableName,stud.getStudId(), subject.getSubjectId(), examDate.format(DateTimeFormatter.ISO_LOCAL_DATE), mark);
        System.out.println("--- "+qu);
        st.executeUpdate(qu);
        st.close();
    }

    public void save(DataSource ds) throws SQLException {
        if (examId == -1) {
            add(ds);
        } else {
            update(ds);
        }
    }

}
