package com.revature.models;

import com.revature.models.UserRole;

public class User {
    private int id;
    private String username;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private UserRole roleId;

    public User(){

    }
    public User(String username,String password,String fname,String lname,String email, UserRole roleId){
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.roleId = roleId;
    }
    public User(String username,String password,String fname,String lname,String email, UserRole roleId, int id){
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.roleId = roleId;
        this.id = id;
    }

    public User(String username,String password,String fname,String lname,String email, int id){
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRoleId() {
        return roleId;
    }

    public void setRoleId(UserRole roleId) {
        this.roleId = roleId;
    }


    public String toString(){
        return "ID#" + this.id + " " +
                "Full Name: " + this.fname + " " + this.lname + "\n" +
                "E-mail: " + this.email + "\n" +
                "Password: " + this.password + "\n";
    }
}
