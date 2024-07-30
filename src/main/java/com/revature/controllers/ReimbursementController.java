package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import io.javalin.http.Context;

import java.sql.Date;
import java.util.List;

public class ReimbursementController {

    ReimbursementService rs = new ReimbursementService();

    public void handleGetAllRequests(Context ctx){
        List<Reimbursement> r = rs.getAllRequests();
        ctx.json(r);
    }

    public void handleGetAllPendingRequests(Context ctx){
        List<Reimbursement> r = rs.getAllPendingRequests();
        ctx.json(r);
    }

    public void handleGetAllResolvedRequests(Context ctx){
        List<Reimbursement> r = rs.getAllResolvedRequests();
        ctx.json(r);
    }

    public void handleGetAllRequestsById(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);

        List<Reimbursement> r = rs.getAllRequestsById(id);
        ctx.json(r);
    }

    public void handleGetPendingRequestsById(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);

        List<Reimbursement> r = rs.getPendingRequestsById(id);
        ctx.json(r);
    }

    public void handleGetResolvedRequestsById(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);

        List<Reimbursement> r = rs.getResolvedRequestsById(id);
        ctx.json(r);
    }

    public void handleCreateRequest(Context ctx){
        Reimbursement r = ctx.bodyAsClass(Reimbursement.class);
        UserService us = new UserService();

        User u = us.getUserById(r.getAuthor());
        boolean success = rs.createRequest(r,u);

        if (success){
            ctx.status(201);
        }else{
            ctx.status(400);
        }

    }

    public void handleDeleteRequest(Context ctx){
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        boolean success = rs.deleteRequest(id);

        if (success){
            ctx.status(200);
        }else{
            ctx.status(400);
        }

    }

    public void handleGetRequestById(Context ctx){
        String idPara = ctx.pathParam("id");
        int id = Integer.parseInt(idPara);
        Reimbursement r = rs.getRequest(id);
        ctx.json(r);
    }

    public void handleUpdateRequest(Context ctx){
        Resolver r = ctx.bodyAsClass(Resolver.class);

        UserService us = new UserService();

        String reimbursementId = ctx.pathParam("id");

        Reimbursement r2 = rs.getRequest(Integer.parseInt(reimbursementId));

        User u = us.getUserById(r.resolver);
        r2.setStatusId(r.statusId);
        boolean success = rs.updateRequest(r2,u);

        if (success){
            ctx.status(200);
        }else{
            ctx.status(400);
        }

    }
}

class Resolver{
    public int resolver;
    public int statusId;

    public String toString(){
        return ""+ resolver + " " + statusId;
    }
}
