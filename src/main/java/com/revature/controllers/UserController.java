package com.revature.controllers;

import com.revature.daos.UserDaoImp;
import com.revature.models.LoggingSingleton;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Context;

import java.util.List;

public class UserController {
    UserService us = new UserService();
    LoggingSingleton logger = LoggingSingleton.getLogger();

    public void handleCreateUser(Context ctx){
        logger.info("User registration started...");
        User u = ctx.bodyAsClass(User.class);
        boolean success = us.createUser(u);

        if (success){
            ctx.status(201);
        }else{
            ctx.status(400);
        }
    }

    public void handleUpdateUser(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        User u = ctx.bodyAsClass(User.class);
        u.setId(id);

        boolean success = us.updateUser(u);

        if (success){
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }

    public void handleGetAllUsers(Context ctx){
        List<User> u = us.getAllUsers();
        ctx.json(u);
    }

    public void handleGetUserById(Context ctx){
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        User u = us.getUserById(id);
        ctx.json(u);
    }

    public void handleDeleteUser(Context ctx){
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        boolean success = us.deleteUser(id);

        if (success){
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }
}
