package com.yunlg.oa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yunlg.oa.persistence.AdminDAO;
import com.yunlg.oa.persistence.AdminSignInDAO;
import com.yunlg.oa.persistence.StaffDAO;
import com.yunlg.oa.persistence.StaffSignInDAO;
import com.yunlg.oa.persistence.impl.AdminDAOImpl;
import com.yunlg.oa.persistence.impl.AdminSignInDAOImpl;
import com.yunlg.oa.persistence.impl.StaffDAOImpl;
import com.yunlg.oa.persistence.impl.StaffSignInDAOImpl;
import com.yunlg.oa.service.AccountService;
import com.yunlg.oa.service.impl.AccountServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest {

    private StaffDAO staffDAO = new StaffDAOImpl();
    private StaffSignInDAO staffSignInDAO = new StaffSignInDAOImpl();
    private AdminDAO adminDAO = new AdminDAOImpl();
    private AdminSignInDAO adminSignInDAO = new AdminSignInDAOImpl();
    private AccountService accountService = new AccountServiceImpl(staffDAO, staffSignInDAO, adminDAO, adminSignInDAO);
    private AccountController accountController = new AccountController(accountService);

    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @After
    public void after() {

    }

    @Test
    public void testStaffLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login/staff")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("userId", "3901170505")
                .param("password", "123456"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testAdminLogin() {

    }

}
