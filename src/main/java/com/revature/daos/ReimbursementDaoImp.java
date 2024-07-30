package com.revature.daos;

import com.revature.models.*;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImp implements ReimbursementDao {
    LoggingSingleton logger = LoggingSingleton.getLogger();

    Date currentTime = new java.sql.Date(System.currentTimeMillis());

    @Override
    public boolean createRequest(Reimbursement r, User u){
        String sql = "insert into project1.reimbursements (amount, submitted, description, author, statusid, typeid) values (?,?,?,?,?,?)";

        try(Connection con = ConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            r.setSubmitted(currentTime);

            ps.setDouble(1, r.getAmount());
            ps.setDate(2, r.getSubmitted());
            ps.setString(3, r.getDescription());
            ps.setInt(4, u.getId());
            ps.setInt(5, r.getStatusId());
            ps.setInt(6, r.getTypeId()+1);


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

    @Override
    public Reimbursement getRequest(int id){
        String sql = "SELECT * FROM project1.reimbursements where id = ?";

        try(Connection con = ConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Reimbursement r = new Reimbursement();


                r.setId(id);
                r.setAmount(rs.getDouble("amount"));
                r.setSubmitted(rs.getDate("submitted"));
                r.setResolved(rs.getDate("resolved"));
                r.setDescription(rs.getString("description"));
                r.setAuthor(rs.getInt("author"));
                r.setResolver(rs.getInt("resolver"));
                r.setStatusId(rs.getInt("statusid"));
                r.setTypeId(rs.getInt("typeid"));

                logger.info("Request obtained from database successfully");

                return r;

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return null;
    }

    @Override
    public boolean updateRequest(Reimbursement r, User u){
        String sql = "update project1.reimbursements set resolved = ? , resolver = ?, statusid = ? where id = ? ";

        try(Connection con = ConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            r.setResolved(currentTime);

            ps.setDate(1, r.getResolved());
            ps.setInt(2, u.getId());
            ps.setInt(3, r.getStatusId());
            ps.setInt(4, r.getId());

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
    public boolean deleteRequest(int id) {
        String sql = "delete from project1.reimbursements where id = ?";

        try(Connection con = ConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==0){
                logger.warn("Something went wrong - this request can't be removed");
                return false;
            }
            logger.info("Database was updated successfully");
            return true;

        }

        catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return false;
    }

    @Override
    public List<Reimbursement> getAllRequests(){
        List<Reimbursement> requests = new ArrayList<>();

        String sql = "SELECT * FROM project1.reimbursements";

        try(Connection con = ConnectionUtil.getConnection();
            Statement s = con.createStatement();){

            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                Reimbursement r = new Reimbursement();

                int id = rs.getInt("id");
                r.setId(id);
                r.setAmount(rs.getDouble("amount"));
                r.setSubmitted(rs.getDate("submitted"));
                r.setResolved(rs.getDate("resolved"));
                r.setDescription(rs.getString("description"));
                r.setAuthor(rs.getInt("author"));
                r.setResolver(rs.getInt("resolver"));
                r.setStatusId(rs.getInt("statusid"));
                r.setTypeId(rs.getInt("typeid"));

                requests.add(r);
            }
            logger.info("Requests obtained from database successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return requests;
    }

    @Override
    public List<Reimbursement> getAllPendingRequests(){
        List<Reimbursement> requests = new ArrayList<>();

        String sql = "SELECT * FROM project1.reimbursements";

        try(Connection con = ConnectionUtil.getConnection();
            Statement s = con.createStatement();){

            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                Reimbursement r = new Reimbursement();

                int id = rs.getInt("id");
                r.setId(id);
                r.setAmount(rs.getDouble("amount"));
                r.setSubmitted(rs.getDate("submitted"));
                r.setResolved(rs.getDate("resolved"));
                r.setDescription(rs.getString("description"));
                r.setAuthor(rs.getInt("author"));
                r.setResolver(rs.getInt("resolver"));
                r.setStatusId(rs.getInt("statusid"));
                r.setTypeId(rs.getInt("typeid"));

                if (r.getStatusId() == ReimbursementStatus.PENDING.ordinal()+1) {
                    requests.add(r);
                }
            }
            logger.info("Requests obtained from database successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return requests;
    }

    @Override
    public List<Reimbursement> getAllResolvedRequests(){
        List<Reimbursement> requests = new ArrayList<>();

        String sql = "SELECT * FROM project1.reimbursements";

        try(Connection con = ConnectionUtil.getConnection();
            Statement s = con.createStatement();){

            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                Reimbursement r = new Reimbursement();

                int id = rs.getInt("id");
                r.setId(id);
                r.setAmount(rs.getDouble("amount"));
                r.setSubmitted(rs.getDate("submitted"));
                r.setResolved(rs.getDate("resolved"));
                r.setDescription(rs.getString("description"));
                r.setAuthor(rs.getInt("author"));
                r.setResolver(rs.getInt("resolver"));
                r.setStatusId(rs.getInt("statusid"));
                r.setTypeId(rs.getInt("typeid"));

                if (r.getStatusId() != ReimbursementStatus.PENDING.ordinal()+1) {
                    requests.add(r);
                }
            }
            logger.info("Requests obtained from database successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return requests;
    }

    @Override
    public List<Reimbursement> getAllRequestsById(int id) {
        String sql = "SELECT * FROM project1.reimbursements where author = ?";

        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {


            List<Reimbursement> requests = new ArrayList<>();
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("id"));
                r.setAmount(rs.getDouble("amount"));
                r.setSubmitted(rs.getDate("submitted"));
                r.setResolved(rs.getDate("resolved"));
                r.setDescription(rs.getString("description"));
                r.setAuthor(id);
                r.setResolver(rs.getInt("resolver"));
                r.setStatusId(rs.getInt("statusid"));
                r.setTypeId(rs.getInt("typeid"));

                requests.add(r);

            }

            logger.info("Requests obtained from database successfully");
            return requests;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return null;
    }

    @Override
    public List<Reimbursement> getPendingRequestsById(int id) {
        String sql = "SELECT * FROM project1.reimbursements where author = ?";

        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {


            List<Reimbursement> requests = new ArrayList<>();
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("id"));
                r.setAmount(rs.getDouble("amount"));
                r.setSubmitted(rs.getDate("submitted"));
                r.setResolved(rs.getDate("resolved"));
                r.setDescription(rs.getString("description"));
                r.setAuthor(id);
                r.setResolver(rs.getInt("resolver"));
                r.setStatusId(rs.getInt("statusid"));
                r.setTypeId(rs.getInt("typeid"));

                if (r.getStatusId() == (ReimbursementStatus.PENDING.ordinal()+1)) {
                    requests.add(r);
                }
            }

            logger.info("Requests obtained from database successfully");
            return requests;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return null;
    }

    @Override
    public List<Reimbursement> getResolvedRequestsById(int id) {
        String sql = "SELECT * FROM project1.reimbursements where author = ?";

        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {


            List<Reimbursement> requests = new ArrayList<>();
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("id"));
                r.setAmount(rs.getDouble("amount"));
                r.setSubmitted(rs.getDate("submitted"));
                r.setResolved(rs.getDate("resolved"));
                r.setDescription(rs.getString("description"));
                r.setAuthor(id);
                r.setResolver(rs.getInt("resolver"));
                r.setStatusId(rs.getInt("statusid"));
                r.setTypeId(rs.getInt("typeid"));

                if (r.getStatusId() != (ReimbursementStatus.PENDING.ordinal()+1)) {
                    requests.add(r);
                }

            }

            logger.info("Requests obtained from database successfully");
            return requests;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Something went wrong - SQLException");
        }
        return null;
    }
}