package com.revature.daos;

import com.revature.models.LoggingSingleton;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao{

    LoggingSingleton logger = LoggingSingleton.getLogger();

    @Override
    public boolean createUser(User u) {
        String sql = "insert into project1.user (username,password,fname,lname,email, roleid) values (?,?,?,?,?,?)";


        try(Connection con = ConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFname());
            ps.setString(4, u.getLname());
            ps.setString(5, u.getEmail());

            ps.setInt(6, (u.getRoleId().ordinal())+1);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected==1){
                logger.info("Database was updated successfully");
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }

        return false;
    }

    public boolean updateUser(User u){
        String sql = "update project1.user set username = ? , password = ? , fname = ? , lname = ?, email = ? where id = ? ";

        try(Connection con = ConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFname());
            ps.setString(4, u.getLname());
            ps.setString(5, u.getEmail());
            //ps.setInt(6, u.getRoleId().ordinal());
            ps.setInt(6, u.getId());


            int rowsAffected = ps.executeUpdate();

            if (rowsAffected==1){
                logger.info("Database was updated successfully");
                return true;
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM project1.user ";

        try(Connection con = ConnectionUtil.getConnection();
        Statement s = con.createStatement();){

            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                User u = new User();


                int id = rs.getInt("id");
                u.setId(id);
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFname(rs.getString("fname"));
                u.setLname(rs.getString("lname"));
                u.setEmail(rs.getString("email"));


                //gives the ENUMS a  numeric value
                UserRole[] roles = UserRole.values();
                int roleOrdinal = rs.getInt(("roleid"))-1;

                u.setRoleId(roles[roleOrdinal]);
                if (u.getRoleId() == UserRole.EMPLOYEE) {
                    users.add(u);
                }
            }
            logger.info("Users obtained from database successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        String sql = "select * from project1.user where id = ?";

        try(Connection con = ConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();


            if (rs.next()){
                User u = new User();


                //int id = rs.getInt("id");
                u.setId(id);
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFname(rs.getString("fname"));
                u.setLname(rs.getString("lname"));
                u.setEmail(rs.getString("email"));

                //gives the ENUMS a  numeric value
                UserRole[] roles = UserRole.values();

                int roleOrdinal = rs.getInt(("roleid"));
                u.setRoleId(roles[roleOrdinal-1]);

                logger.info("User obtained from database successfully");
                return u;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return null;
    }

    @Override
    public User getUserByUsernameAndPassword(String user, String pass) {
        //We pass in the id and query the DB by that id
        String sql = "select * from project1.user where username = ? and password = ?";

        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();


            if (rs.next()) {
                //then we convert result set to a User object and return it
                User u = new User();

                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFname(rs.getString("fname"));
                u.setLname(rs.getString("lname"));
                u.setEmail(rs.getString("email"));

                //gives the ENUMS a  numeric value
                UserRole[] roles = UserRole.values();

                int roleOrdinal = rs.getInt(("roleid"));
                u.setRoleId(roles[roleOrdinal-1]);

                logger.info("User obtained from database successfully");
                return u;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {

        String sql = "delete from project1.user where id = ?";

        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                logger.warn("Something went wrong - this request can't be removed");
                return false;
            }
            logger.info("Database was updated successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return false;
    }
}
