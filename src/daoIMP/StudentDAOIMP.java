package daoIMP;
import bean.Student;
import dao.StudentDAO;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.DataBaseConnection;

public class StudentDAOIMP implements StudentDAO{
	// 添加操作
	    public void insert(Student s){
	    	String sql = "INSERT INTO student (id, name) values (?,?)";
	        PreparedStatement pstmt = null;
	        DataBaseConnection conn = null;
	        //针对数据库的具体操作
	        try{
	        	conn = new DataBaseConnection();
	        	pstmt = conn.getConnection().prepareStatement(sql);
	        	pstmt.setLong(1,s.getID());
	        	//pstmt.setString(1,s.getID());
				pstmt.setString(2,s.getName());
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
	        } catch(Exception e){  }
	    }

	    //作业
	    public void update(Student s){
			String sql = "UPDATE student SET name = ? WHERE id = ?";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			//针对数据库的具体操作
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setLong(1,s.getID());

				pstmt.setString(2,s.getName());
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			} catch(Exception e){  }
		}

	    public void delete(long iD){
			String sql = "DELETE FROM student WHERE id = ?";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setLong(1,iD);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			} catch(Exception e){  }
		}
	    
	    public List<Student> findAll(){
			String sql = "SELECT * FROM student";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			List<Student> l = new ArrayList<>();
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				ResultSet set = pstmt.executeQuery();
				while (set.next()){
					Student s = new Student();
					s.setID(set.getInt("id"));
					s.setName(set.getString("name"));
					l.add(s);
				}

				pstmt.close();
				conn.close();
			} catch(Exception e){  }
			return l;
	    }

	    public Student findByID(long iD){
			String sql = "SELECT * FROM student WHERE id = ?";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			Student s = new Student();

			try {
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				ResultSet set = pstmt.executeQuery();
				pstmt.setLong(1,iD);
				while (set.next()){
					s.setID(iD);
					s.setName(set.getString("name"));
				}

				pstmt.close();
				conn.close();
			}catch (Exception e){  }
			return s;
		}

}
