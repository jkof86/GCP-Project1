package com.revature.services;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImp;
import com.revature.models.Reimbursement;
import com.revature.models.User;

import java.util.List;

public class ReimbursementService {
    ReimbursementDao rd = new ReimbursementDaoImp();

    public boolean createRequest(Reimbursement r, User u){
        return rd.createRequest(r,u);
    }
    public Reimbursement getRequest(int id){
        return rd.getRequest(id);
    }
    public boolean updateRequest(Reimbursement r, User u){
        return rd.updateRequest(r,u);
    }
    public boolean deleteRequest(int id){
        return rd.deleteRequest(id);
    }
    public List<Reimbursement> getAllRequests(){
        return rd.getAllRequests();
    }
    public List<Reimbursement> getAllPendingRequests(){
        return rd.getAllPendingRequests();
    }
    public List<Reimbursement> getAllResolvedRequests(){
        return rd.getAllResolvedRequests();
    }
    public List<Reimbursement> getAllRequestsById(int id){
        return rd.getAllRequestsById(id);
   }
    public List<Reimbursement> getPendingRequestsById(int id){
        return rd.getPendingRequestsById(id);
    }
    public List<Reimbursement> getResolvedRequestsById(int id){
        return rd.getResolvedRequestsById(id);
    }

}
