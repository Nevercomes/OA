package com.yunlg.oa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.persistence.AssessmentDAO;
import com.yunlg.oa.persistence.impl.AssessmentDAOImpl;
import com.yunlg.oa.service.AssessService;
import com.yunlg.oa.service.impl.AssessServiceImpl;
import com.yunlg.oa.utils.TimeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AssessControllerTest {

    private AssessmentDAO assessmentDAO = new AssessmentDAOImpl();
    private AssessService assessService = new AssessServiceImpl(assessmentDAO);
    private AssessController assessController = new AssessController(assessService);

    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(assessController).build();
    }

    @After
    public void after() {

    }

    @Test
    public void testSubmitAssessment() throws Exception {

        Assessment assessment = new Assessment();
        assessment.setAssessId(1);
        assessment.setWorkRegular("今天值了一天的班");
        assessment.setWorkOutPlan("今天给cz倒了很多茶");
        assessment.setWorkOther("敲了10w行代码");
        assessment.setWorkExpanse("1000");
        assessment.setWorkPlanSimple("没有计划");
        assessment.setWorkModifyTime(TimeUtil.getCurrentDate());
        String requestJson = ow.writeValueAsString(assessment);

        mockMvc.perform(MockMvcRequestBuilders.post("/assess/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testEvaluateAssessment() throws Exception {
        Assessment assessment = new Assessment();
        assessment.setAssessId(1);
        assessment.setAssessDirectorScore(50.5f);
        assessment.setAssessDirectorEva("Good");
        assessment.setAssessHeadScore(60.5f);
        assessment.setAssessHeadEva("nice");
        assessment.setAssessModifyTime(TimeUtil.getCurrentDate());
        String requestJson = ow.writeValueAsString(assessment);

        mockMvc.perform(MockMvcRequestBuilders.post("/assess/evaluate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testViewAssessment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/assess/view")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("department", "2")
                .param("month", "9"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
