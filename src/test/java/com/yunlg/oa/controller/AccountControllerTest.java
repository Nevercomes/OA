package com.yunlg.oa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yunlg.oa.domain.model.Admin;
import com.yunlg.oa.domain.model.AdminSignIn;
import com.yunlg.oa.domain.model.Staff;
import com.yunlg.oa.domain.model.StaffSignIn;
import com.yunlg.oa.domain.wrapper.AdminModifyPwd;
import com.yunlg.oa.domain.wrapper.AdminRegister;
import com.yunlg.oa.domain.wrapper.BatchRegister;
import com.yunlg.oa.domain.wrapper.StaffModifyPwd;
import com.yunlg.oa.global.Department;
import com.yunlg.oa.global.Position;
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
import com.yunlg.oa.utils.DepartmentMapping;
import com.yunlg.oa.utils.PositionMapping;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

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
    public void testAdminRegister() throws Exception {
        Admin admin = new Admin();
        AdminSignIn adminSignIn = new AdminSignIn();
        AdminRegister adminRegister = new AdminRegister();
        admin.setAdminId("3901170505");
        admin.setDepartment(DepartmentMapping.getDepartmentCode(Department.ALL));
        admin.setPosition(PositionMapping.getPositionCode(Position.GROUP));
        admin.setName("白云舒");

        adminSignIn.setAdminId("3901170505");
        adminSignIn.setPassword("123456");

        adminRegister.setAdmin(admin);
        adminRegister.setAdminSignIn(adminSignIn);

        String requestJson = ow.writeValueAsString(adminRegister);

        mockMvc.perform(MockMvcRequestBuilders.post("/register/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testAdminLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("userId", "3901170505")
                .param("password", "123456")
                .param("numbering", "J87cd7"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testBatchRegister() throws Exception {
        BatchRegister batchRegister = new BatchRegister();
        List<Staff> staffList = new ArrayList<>();
        List<StaffSignIn> staffSignInList = new ArrayList<>();
        Staff staff = new Staff();
        Staff staff1 = new Staff();
        StaffSignIn staffSignIn = new StaffSignIn();
        StaffSignIn staffSignIn1 = new StaffSignIn();
        staff.setUserId("3901170506");
        staff.setDepartment(1);
        staff.setName("Mike");
        staff.setPosition(0);
        staffList.add(staff);
        staffSignIn.setUserId("3901170506");
        staffSignIn.setPassword("123");
        staffSignInList.add(staffSignIn);
        staff1.setUserId("3901170507");
        staff1.setName("Lily");
        staff1.setDepartment(2);
        staff1.setPosition(PositionMapping.getPositionCode(Position.HEAD));
        staffList.add(staff1);
        staffSignIn1.setUserId("3901170507");
        staffSignIn1.setPassword("123");
        staffSignInList.add(staffSignIn1);
        batchRegister.setStaffList(staffList);
        batchRegister.setStaffSignInList(staffSignInList);

        String requestJson = ow.writeValueAsString(batchRegister);
        mockMvc.perform(MockMvcRequestBuilders.post("/register/batch")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testStaffLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login/staff")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("userId", "3901170506")
                .param("password", "123"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testModifyStaffPassword() throws Exception {
        StaffModifyPwd staffModifyPwd = new StaffModifyPwd();
        staffModifyPwd.setUserId("3901170506");
        staffModifyPwd.setOldPassword("000");
        staffModifyPwd.setNewPassword("123");

        String requestJson = ow.writeValueAsString(staffModifyPwd);
        mockMvc.perform(MockMvcRequestBuilders.post("/modify/staff")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testModifyAdminPassword() throws Exception {
        AdminModifyPwd adminModifyPwd = new AdminModifyPwd();
        adminModifyPwd.setAdminId("3901170505");
        adminModifyPwd.setOldPassword("123456");
        adminModifyPwd.setNumbering("J87cd7");
        adminModifyPwd.setNewPassword("123");

        String requestJson = ow.writeValueAsString(adminModifyPwd);
        mockMvc.perform(MockMvcRequestBuilders.post("/modify/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testForceModifyStaff() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/modify/force")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("userId", "3901170507"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testForceModifyAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/modify/force/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("userId", "3901170505"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}