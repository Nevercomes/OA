package com.yunlg.oa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.domain.model.SignIn;
import com.yunlg.oa.domain.wrapper.BatchRegister;
import com.yunlg.oa.domain.wrapper.UserModifyPwd;
import com.yunlg.oa.global.Position;
import com.yunlg.oa.persistence.UserDAO;
import com.yunlg.oa.persistence.SignInDAO;
import com.yunlg.oa.persistence.impl.UserDAOImpl;
import com.yunlg.oa.persistence.impl.SignInDAOImpl;
import com.yunlg.oa.service.AccountService;
import com.yunlg.oa.service.impl.AccountServiceImpl;
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

public class LoginControllerTest {

    private UserDAO userDAO = new UserDAOImpl();
    private SignInDAO signInDAO = new SignInDAOImpl();
//    private AdminDAO adminDAO = new AdminDAOImpl();
//    private AdminSignInDAO adminSignInDAO = new AdminSignInDAOImpl();
    private AccountService accountService = new AccountServiceImpl(userDAO, signInDAO);
    private LoginController loginController = new LoginController(accountService);

    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @After
    public void after() {

    }

//    @Test
//    public void testAdminRegister() throws Exception {
//        Admin admin = new Admin();
//        AdminSignIn adminSignIn = new AdminSignIn();
//        AdminRegister adminRegister = new AdminRegister();
//        admin.setAdminId("3901170505");
//        admin.setDepartment(DepartmentMapping.getDepartmentCode(Department.ALL));
//        admin.setPosition(PositionMapping.getPositionCode(Position.GROUP));
//        admin.setName("白云舒");
//
//        adminSignIn.setAdminId("3901170505");
//        adminSignIn.setPassword("123456");
//
//        adminRegister.setAdmin(admin);
//        adminRegister.setAdminSignIn(adminSignIn);
//
//        String requestJson = ow.writeValueAsString(adminRegister);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/register/admin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(requestJson.getBytes()))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }

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
        List<User> userList = new ArrayList<>();
        List<SignIn> signInList = new ArrayList<>();
        User user = new User();
        User user1 = new User();
        SignIn signIn = new SignIn();
        SignIn signIn1 = new SignIn();
        user.setUserId("3901170506");
        user.setDepartment(1);
        user.setName("Mike");
        user.setPosition(0);
        userList.add(user);
        signIn.setUserId("3901170506");
        signIn.setPassword("123");
        signInList.add(signIn);
        user1.setUserId("3901170507");
        user1.setName("Lily");
        user1.setDepartment(2);
        user1.setPosition(PositionMapping.getPositionCode(Position.HEAD));
        userList.add(user1);
        signIn1.setUserId("3901170507");
        signIn1.setPassword("123");
        signInList.add(signIn1);
//        batchRegister.setUserList(userList);
//        batchRegister.setSignInList(signInList);

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
        mockMvc.perform(MockMvcRequestBuilders.get("/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("userId", "3901170506")
                .param("password", "123"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testModifyStaffPassword() throws Exception {
        UserModifyPwd userModifyPwd = new UserModifyPwd();
//        userModifyPwd.setUserId("3901170506");
        userModifyPwd.setOldPassword("000");
        userModifyPwd.setNewPassword("123");

        String requestJson = ow.writeValueAsString(userModifyPwd);
        mockMvc.perform(MockMvcRequestBuilders.post("/modify/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andExpect(status().isOk())
                .andDo(print());
    }

//    @Test
//    public void testModifyAdminPassword() throws Exception {
//        AdminModifyPwd adminModifyPwd = new AdminModifyPwd();
//        adminModifyPwd.setAdminId("3901170505");
//        adminModifyPwd.setOldPassword("123456");
//        adminModifyPwd.setNumbering("J87cd7");
//        adminModifyPwd.setNewPassword("123");
//
//        String requestJson = ow.writeValueAsString(adminModifyPwd);
//        mockMvc.perform(MockMvcRequestBuilders.post("/modify/admin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(requestJson.getBytes()))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }

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