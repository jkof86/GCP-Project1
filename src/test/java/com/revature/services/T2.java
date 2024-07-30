package com.revature.services;
import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class T2 {
    @Mock
    ReimbursementDao rd;


    @InjectMocks
    ReimbursementService rs;
    User u;
    Reimbursement r;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateRequest(){

        u = new User();
        r = new Reimbursement();

        when(rd.createRequest(r,u)).thenReturn(true);
        assertTrue(rs.createRequest(r,u));
    }
    @Test
    public void testGetRequest(){
        r = new Reimbursement();

        when(rd.getRequest(2)).thenReturn(r);

        assertNotNull(rs.getRequest(2));
    }
    @Test
    public void testUpdateRequest(){
        r = new Reimbursement();
        u = new User();

        when(rd.updateRequest(r, u)).thenReturn(true);

        assertTrue(rs.updateRequest(r,u));
    }
    @Test
    public void testDeleteRequest(){
        r = new Reimbursement();
        u = new User();

        when(rd.deleteRequest(2)).thenReturn(true);

        assertTrue(rs.deleteRequest(2));
    }
    @Test
    public void testGetAllRequests(){
        r = new Reimbursement();
        List<Reimbursement> listR = new ArrayList<>();
        listR.add(r);
        when(rd.getAllRequests()).thenReturn(listR);

        assertNotNull(rd.getAllRequests());
    }
    @Test
    public void testGetAllPendingRequests(){
        r = new Reimbursement();
        List<Reimbursement> listR = new ArrayList<>();
        listR.add(r);
        when(rd.getAllPendingRequests()).thenReturn(listR);

        assertNotNull(rd.getAllPendingRequests());
    }
    @Test
    public void testGetAllResolvedRequests(){
        r = new Reimbursement();
        List<Reimbursement> listR = new ArrayList<>();
        listR.add(r);
        when(rd.getAllResolvedRequests()).thenReturn(listR);

        assertNotNull(rd.getAllResolvedRequests());
    }
    @Test
    public void testGetPendingRequestsById(){
        r = new Reimbursement();
        List<Reimbursement> listR = new ArrayList<>();
        listR.add(r);
        when(rd.getPendingRequestsById(21)).thenReturn(listR);

        assertNotNull(rd.getPendingRequestsById(21));
    }
    @Test
    public void testGetResolvedRequestsById(){
        r = new Reimbursement();
        List<Reimbursement> listR = new ArrayList<>();
        listR.add(r);
        when(rd.getResolvedRequestsById(21)).thenReturn(listR);

        assertNotNull(rd.getResolvedRequestsById(21));
    }
}
