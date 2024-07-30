package com.revature.controllers;

import com.revature.models.LoggingSingleton;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.services.UserService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;

    public class AuthController {

        LoggingSingleton logger = LoggingSingleton.getLogger();

        public void authenticateLogin(Context ctx){
            //interpret login request
            Login login = ctx.bodyAsClass(Login.class);
            String user = login.username;
            String pass = login.password;
            int id = login.id;

            //fulfill login request
            UserService us = new UserService();
            User u = new User();
            u = us.getUserByUsernameAndPassword(user, pass);


            //if no object was created then we know the credentials didn't match our DB
            if (u==null){
                logger.warn("Invalid login credentials");
                throw new UnauthorizedResponse("Invalid login credentials");
            } else {
                //Now we check the user role
                if (u.getRoleId() == UserRole.MANAGER){
                    String authToken = u.getRoleId() + "-TOKEN";

                    //we send auth information back to the client in the header
                    ctx.header("Authorization", authToken);
                    ctx.status(200);
                }
                if(u.getRoleId() == UserRole.EMPLOYEE){
                    String authToken = u.getRoleId() + "-TOKEN";

                    //we send auth information back to the client in the header
                    ctx.header("Authorization", authToken);
                    ctx.status(200);
                }
                String idString = (String.valueOf(u.getId()));
                ctx.header("id", idString);

                }
            }


        public void authorizeManager(Context ctx){
            String authHeader = ctx.header("Authorization");
            if(authHeader!=null){
                if(authHeader.equals("MANAGER-TOKEN")){
                    return;
                } else {
                    logger.warn("You do not have access to this feature");
                    throw new ForbiddenResponse("You do not have access to this feature");
                }
            }

            logger.warn("You must login to continue");
            throw new UnauthorizedResponse("You must login to continue");

        }

        public void authorizeEmployee(Context ctx){
            String authHeader = ctx.header("Authorization");
            if(authHeader!=null){
                if(authHeader.equals("EMPLOYEE-TOKEN")){
                    return;
                } else {
                    logger.warn("You do not have access to this feature");
                    throw new ForbiddenResponse("You do not have access to this feature");
                }
            }

            logger.warn("You must login to continue");
            throw new UnauthorizedResponse("You must login to continue");

        }
}

class Login{
        public String username;
        public String password;
        public int id;
}