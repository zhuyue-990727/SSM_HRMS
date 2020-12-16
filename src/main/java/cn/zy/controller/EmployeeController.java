package cn.zy.controller;

import cn.zy.bean.Employee;
import cn.zy.service.EmployeeService;
import cn.zy.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author zy
 * @date 2020-12-04 9:19
 */
@Controller
@RequestMapping(value = "/hrms/emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 员工删除操作
     */
    @RequestMapping(value = "/deleteEmp/{empId}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteEmp(@PathVariable("empId") Integer empId)
    {
        int flag = 0;
        if(empId>0){
            flag=employeeService.deleteEmpById(empId);
        }
        if(flag!=1){
            return JsonMsg.fail().addInfo("emp_del_error","员工删除异常");
        }
        return JsonMsg.success();
    }


    /**
     * 更改员工信息
     * @Param empID
     * @Param employee
     */
    @RequestMapping(value = "/updateEmp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateEmp(@PathVariable("empId") Integer empdId, Employee employee){
        int flag=employeeService.updateEmpById(empdId,employee);
        if(flag!=1){
            return JsonMsg.fail().addInfo("emp_update_error","更改时出现异常");
        }
        return JsonMsg.success();
    }

    /**
     * 查询输入的员工姓名是否重复
     * @param empName
     */
    @RequestMapping(value = "/checkEmpExists",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkEmpExists(@RequestParam("empName") String empName){
        //对输入的姓名与邮箱格式进行验证
        String regName="(^[a-zA-Z0-9_-]{3,16}$)|(^[\\\\u2E80-\\\\u9FFF]{2,5})";
        if(!empName.matches(regName)){
            return JsonMsg.fail().addInfo("name_reg_error","输入姓名为2-5位中文或6-16位英文和数字组合");
        }
        Employee employee=employeeService.getEmpByName(empName);
        if(employee!=null){
            return JsonMsg.fail().addInfo("name_reg_error","用户名重复");
        }
        else {
            return JsonMsg.success();
        }
    }


    /**
     * 新增记录后，查询最新的页数
     */
    @RequestMapping(value = "/getTotalPages",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPage(){
        int totalItems=employeeService.getEmpCount();
        //获取总页数
        int temp=totalItems/5;
        int totalPages=(totalItems % 5 ==0)? temp : temp+1;
        return JsonMsg.success().addInfo("totalPages",totalPages);
    }

    /**
     * 新增员工
     * @Param employee 新增的员工信息
     */
    @RequestMapping(value = "/addEmp",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addEmp(Employee employee){
        int flag=employeeService.addEmp(employee);
        if(flag==1){
            return JsonMsg.success();
        }else{
            return JsonMsg.fail();
        }
    }

    /**
     * 根据ID查询员工信息
     * @Param empId
     */
    @RequestMapping(value = "/getEmpById/{empId}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getEmpById(@PathVariable("empId") Integer empId) {
        Employee employee = employeeService.getEmpById(empId);
        if (employee != null) {
            return JsonMsg.success().addInfo("employee", employee);
        } else {
            return JsonMsg.fail();
        }
    }

        /**
         * 查询
         * @param pageNo 查询指定页码中包含的数据
         */

        @RequestMapping(value = "/getEmpList",method = RequestMethod.GET)
                public ModelAndView getEmp(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo){
            ModelAndView mv = new ModelAndView("employeePage");
            int limit =5;
            // 记录的偏移量(即从第offset行记录开始查询)，
            // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
            // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
            int offset = (pageNo-1)*limit;
            //获取指定页数包含的员工信息
            List<Employee> employees =employeeService.getEmpList(offset,limit);
            //获取总的记录数
            int totalItems=employeeService.getEmpCount();
            //获取总的页数
            int temp =totalItems /limit;
            int totalPages =(totalItems % limit ==0)? temp: temp+1;
            //当前页数
            int curPage =pageNo;

            //将上述查询的结果放到Model中，以便在JSP页面中展示
            mv.addObject("employees",employees);
            mv.addObject("totalItems",totalItems);
            mv.addObject("totalPages",totalPages);
            mv.addObject("curPage",curPage);
            return mv;
        }

    }
