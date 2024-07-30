
package com.revature.services;


import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImp;
import com.revature.models.User;
import com.revature.models.UserRole;
import org.eclipse.jetty.server.Authentication;

import java.util.List;

public class UserService {
    UserDao ud = new UserDaoImp();

    public boolean createUser(String username, String password, String fname, String lname, String email, UserRole roleId){
        User u = new User(username, password, fname, lname, email, roleId);
        return ud.createUser(u);
    }
    public boolean createUser(User u){
        return ud.createUser(u);
    }
    public List<User> getAllUsers(){return ud.getAllUsers();}
    public boolean updateUser(User u){
        return ud.updateUser(u);
    }
    public User getUserById(int id){
        return ud.getUserById(id);
    }
    public User getUserByUsernameAndPassword(String user, String pass) {return ud.getUserByUsernameAndPassword(user, pass);}
    public boolean deleteUser(int id){
            return ud.deleteUser(id);
        }

}
