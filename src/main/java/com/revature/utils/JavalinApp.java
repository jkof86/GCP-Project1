package com.revature.utils;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class JavalinApp {

    static LoggingUtil logger = new LoggingUtil();
    static UserController uc = new UserController();
    static ReimbursementController rc = new ReimbursementController();
    static AuthController auth = new AuthController();



    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {config.enableCorsForAllOrigins();
        config.addStaticFiles("/static", Location.CLASSPATH);});


        //Initial logins
        app.post("/user", uc::handleCreateUser);
        app.post("/login", auth::authenticateLogin);
        //app.put("/logout")


        //employee stuff
        app.before("/employee/*", auth::authorizeEmployee);
        app.get("/employee/user/{id}", uc::handleGetUserById);
        app.put("/employee/user/{id}", uc::handleUpdateUser);
        app.post("/employee/request", rc::handleCreateRequest);
        app.get("/employee/request/{id}", rc::handleGetRequestById); //does an employee need this?
        app.get("/employee/requests/pending/{id}", rc::handleGetPendingRequestsById);
        app.get("/employee/requests/resolved/{id}", rc::handleGetResolvedRequestsById);

        //manager stuff
        app.before("/manager/*", auth::authorizeManager);
        app.get("/manager/users", uc::handleGetAllUsers);
        app.get("/manager/user/{id}", uc::handleGetUserById);
        app.put("/manager/request/{id}", rc::handleUpdateRequest);
        app.get("/manager/requests", rc::handleGetAllRequests);
        app.get("/manager/requests/pending", rc::handleGetAllPendingRequests);
        app.get("/manager/requests/resolved", rc::handleGetAllResolvedRequests);
        app.get("/manager/request/{id}", rc::handleGetRequestById); //same here
        app.get("/manager/requests/{id}", rc::handleGetAllRequestsById);

        //debug
        app.delete("/user/{id}", uc::handleDeleteUser);
        app.before("*", logger::logRequest);


        app.start(8080);
    }
}