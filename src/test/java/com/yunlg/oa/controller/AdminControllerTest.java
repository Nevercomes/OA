package com.yunlg.oa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yunlg.oa.domain.wrapper.UserRegister;
import com.yunlg.oa.global.Position;
import com.yunlg.oa.persistence.*;
import com.yunlg.oa.persistence.impl.*;
import com.yunlg.oa.service.AccountService;
import com.yunlg.oa.service.AssessService;
import com.yunlg.oa.service.WorkService;
import com.yunlg.oa.service.impl.AccountServiceImpl;
import com.yunlg.oa.service.impl.AssessServiceImpl;
import com.yunlg.oa.service.impl.WorkServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminControllerTest {

    private UserDAO userDAO = new UserDAOImpl();
    private SignInDAO signInDAO = new SignInDAOImpl();
    private AssessmentDAO assessmentDAO = new AssessmentDAOImpl();
    private AssessResultDAO assessResultDAO = new AssessResultDAOImpl();
    private AssessRecordDAO assessRecordDAO = new AssessRecordDAOImpl();
    private WorkPlanDAO workPlanDAO = new WorkPlanDAOImpl();

    private AssessService assessService = new AssessServiceImpl(assessmentDAO, assessResultDAO, assessRecordDAO);
    private AccountService accountService = new AccountServiceImpl(userDAO, signInDAO);
    private WorkService workService = new WorkServiceImpl(workPlanDAO);
    private AdminController adminController = new AdminController(accountService, assessService, workService);

    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @After
    public void after() {

    }

    @Test
    public void testAdminRegister() throws Exception {

        UserRegister userRegister = new UserRegister();
        userRegister.setUserId("100");
        userRegister.setName("sun");
        userRegister.setDepartment(0);
        userRegister.setPosition(1);
        userRegister.setPassword("123");

        String requestJson = ow.writeValueAsString(userRegister);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testViewResult() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/result/view")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("department", "0")
                .param("month", "9"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
