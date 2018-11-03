package com.yunlg.oa.persistence;

import com.yunlg.oa.domain.model.Assessment;
import com.yunlg.oa.domain.orm.AssessmentORM;
import com.yunlg.oa.persistence.impl.AssessmentDAOImpl;
import com.yunlg.oa.utils.TimeUtil;
import org.junit.Test;

import java.util.List;

public class AssessmentDAOImplTest {

    private AssessmentDAO assessmentDAO = new AssessmentDAOImpl();

    @Test
    public void testSaveAssessment() {
        Assessment assessment = new Assessment();
        assessment.setUserId("3901170505");
        assessment.setMonth(TimeUtil.getBeforeMonth());
        assessment.setWorkRegular("今天值了一天的班");
        assessment.setWorkOutPlan("今天给cz倒茶了");
        assessment.setWorkOther("敲了10w行代码");
        assessment.setWorkExpanse("1000");
        assessment.setWorkPlanSimple("没有计划");
        assessment.setWorkModifyTime(TimeUtil.getCurrentDate());

        assessmentDAO.saveAssessment(assessment);
    }

    @Test
    public void getResult() {
        List<List<AssessmentORM>> lists = assessmentDAO.getResultORMLists(0, 9);
        System.out.println(lists.size());
        for(int i=0; i<lists.size(); i++) {
            for(int j=0; j<lists.get(i).size(); j++) {
                System.out.println(lists.get(i).size());
                System.out.println(lists.get(i).get(j));
            }
            System.out.print("");
        }
    }
}
