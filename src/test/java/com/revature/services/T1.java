package com.revature.services;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.UserDao;
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

public class T1 {
    @Mock
    UserDao ud;
    ReimbursementDao rd;


    @InjectMocks
    UserService us;
    ReimbursementService rs;
    User u;
    Reimbursement r;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate(){

        u = new User();
        when(ud.createUser(u)).thenReturn(true);

        boolean userCreated = us.createUser(u);
        assertTrue(userCreated);
    }
    @Test
    public void testUpdate(){
        u = new User();
        when(ud.updateUser(u)).thenReturn(true);

        assertTrue(us.updateUser(u));
    }
    @Test
    public void testGetAllUsers(){
        u = new User();
        List<User> listU = new ArrayList<>();
        listU.add(u);
        when(ud.getAllUsers()).thenReturn(listU);

        assertNotNull(us.getAllUsers());
    }
    @Test
    public void testGetUserById(){
        u = new User();
        when(ud.getUserById(2)).thenReturn(u);

        assertNotNull(us.getUserById(2));
    }

    @Test
    public void testGetUserByUsernameAndPassword(){
        u = new User();
        when(ud.getUserByUsernameAndPassword("username","password")).thenReturn(u);

        assertNotNull(us.getUserByUsernameAndPassword("username","password"));
    }

    @Test
    public void testdeleteUser(){
        u = new User();
        when(ud.deleteUser(2)).thenReturn(true);

        assertTrue(us.deleteUser(2));
    }

}
