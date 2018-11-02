package com.yunlg.oa.controller;

import com.yunlg.oa.auth.AuthCode;
import com.yunlg.oa.auth.AuthValidate;
import com.yunlg.oa.domain.Result;
import com.yunlg.oa.domain.model.User;
import com.yunlg.oa.exception.AccountServiceException;
import com.yunlg.oa.exception.CatchServiceException;
import com.yunlg.oa.service.AccountService;
import com.yunlg.oa.utils.DepartmentMapping;
import com.yunlg.oa.utils.PositionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"userId", "name", "department", "position"})
@RequestMapping(value = {""})
public class LoginController {

    private AccountService accountService;

    @Autowired
    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @AuthValidate(AuthCode.Allow)
    public String defaultLogin() {
        return "login.jsp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @AuthValidate(AuthCode.Allow)
    public String login() {
        return "login.jsp";
    }

    @RequestMapping(value = "/login/do", method = RequestMethod.POST)
    @AuthValidate(AuthCode.Allow)
    public String userLogin(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "password") String password,
            ModelMap modelMap) {
        try {
            User user = accountService.userLogin(userId, password);
            if(user.getUserId().equals(Result.RESULT_NO_ACCOUNT)) {
                return "login_no_account.jsp";
            } else if(user.getUserId().equals(Result.RESULT_WRONG_PASSWORD)) {
                return "login_error.jsp";
            }
            modelMap.addAttribute("userId", user.getUserId());
            modelMap.addAttribute("name", user.getName());
            modelMap.addAttribute("department", DepartmentMapping.getDepartmentStr(DepartmentMapping.getDepartment(user.getDepartment())));
            modelMap.addAttribute("position", PositionMapping.getPositionStr(PositionMapping.getPosition(user.getPosition())));
            if (user.getAdmin() == 0) {
                return "redirect:/staff";
            } else {
                return "redirect:/admin";
            }
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    @AuthValidate(AuthCode.AU0000)
    public ModelAndView showHomeStaff() {
        try {
                return new ModelAndView("staff.jsp");
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @AuthValidate(AuthCode.AU0000)
    public ModelAndView showHomeAdmin() {
        try {
            return new ModelAndView("admin.jsp");
        } catch (AccountServiceException ae) {
            throw new CatchServiceException(ae);
        }
    }
}

// 这是假的
//    @RequestMapping(value = "/session", method = RequestMethod.GET)
//    public ResponseEntity<User> getSession(
//            @RequestParam(value = "userId") String userId) {
//        try {
//            User user = accountService.getUser(userId);
////            modelMap.addAttribute("userId", user.getUserId());
////            modelMap.addAttribute("name", user.getName());
////            modelMap.addAttribute("department", user.getDepartment());
////            modelMap.addAttribute("position", user.getPosition());
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (AccountServiceException ae) {
//            throw new CatchServiceException(ae);
//        }
//    }

//    @RequestMapping(value = "modify/force/admin", method = RequestMethod.POST)
//    public ResponseEntity<Result> forceModifyAdmin(
//            @RequestParam(value = "userId") String userId) {
//        try {
//            accountService.forceModifyAdmin(userId);
//            return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
//        } catch (AccountServiceException ae) {
//            throw new CatchServiceException(ae);
//        }
//    }

//    @RequestMapping(value = "modify/admin", method = RequestMethod.POST)
//    public ResponseEntity<Result> modifyAdminPassword(@RequestBody AdminModifyPwd adminModifyPwd) {
//        try {
//            if(accountService.adminModifyPwd(adminModifyPwd))
//                return new ResponseEntity<>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
//            else
//                return new ResponseEntity<>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
//        } catch (AccountServiceException ae) {
//            throw new CatchServiceException(ae);
//        }
//    }

//    @RequestMapping(value = "/user", method = RequestMethod.POST)
//    public ModelAndView userLogin(ModelMap modelMap) {
//        try {
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            String userId = userDetails.getUsername();
//            String password = userDetails.getPassword();
//            System.out.println(userId);
//            User user = accountService.userLogin(userId, password);
//            modelMap.addAttribute("userId", user.getUserId());
//            modelMap.addAttribute("name", user.getName());
//            modelMap.addAttribute("department", user.getDepartment());
//            modelMap.addAttribute("position", user.getPosition());
//            return new ModelAndView("user.jsp");
//        } catch (AccountServiceException ae) {
//            throw new CatchServiceException(ae);
//        }
//    }

//    @RequestMapping(value = "/login/admin", method = RequestMethod.POST)
//    public ResponseEntity<Admin> adminLogin(
//            @RequestParam(value = "userId") String userId,
//            @RequestParam(value = "password") String password,
//            @RequestParam(value = "numbering") String numbering) {
//        try {
//            Admin admin = accountService.adminLogin(userId, password, numbering);
//            return new ResponseEntity<>(admin, HttpStatus.OK);
//        } catch (AccountServiceException ae) {
//            throw new CatchServiceException(ae);
//        }
//    }

//    @RequestMapping(value = "/register/admin", method = RequestMethod.POST)
//    public ResponseEntity<String> adminRegister(@RequestBody AdminRegister adminRegister) {
//        try {
//            Admin admin = adminRegister.getAdmin();
//            AdminSignIn adminSignIn = adminRegister.getAdminSignIn();
//            String numbering = accountService.adminRegister(admin, adminSignIn);
//            return new ResponseEntity<>(numbering, HttpStatus.OK);
//        } catch (AccountServiceException ae) {
//            throw new CatchServiceException(ae);
//        }
//    }