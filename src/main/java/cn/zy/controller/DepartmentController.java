package cn.zy.controller;

import cn.zy.bean.Department;
import cn.zy.service.DepartmentService;
import cn.zy.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @author zy
 * @date 2020-12-04 13:55
 */
@Controller
@RequestMapping(value = "/hrms/dept")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * 删除
     * @param deptId
     */
    @RequestMapping(value = "/delDept/{deptId}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteDept(@PathVariable("deptId") Integer deptId){
        int flag=0;
        if(deptId > 0){
            flag=departmentService.deleteDeptById(deptId);
        }
        if(flag!=1){
            return JsonMsg.fail().addInfo("del_dept_error","删除异常");
        }
        return JsonMsg.success();
    }

    /**
     * 部门更改
     * @param deptId
     * @param department
     */
    @RequestMapping(value = "/updateDept/{deptId}",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateDeptById(@PathVariable("deptId") Integer deptId, Department department){
        int flag=0;
        if(deptId>0){
            flag=departmentService.updateDeptById(deptId,department);
        }
        if(flag!=1){
            return JsonMsg.fail().addInfo("update_dept_error","部门更新失败");
        }
        return JsonMsg.success();
    }


    /**
     * 新增部门
     * @param department
     */

    @RequestMapping(value = "/addDept",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg addDept(Department department){
        int flag=departmentService.addDept(department);
        if(flag!=1){
            return JsonMsg.fail().addInfo("add_dept_error","添加异常");
        }
        return JsonMsg.success();
    }

    /**
     * 查询部门信息总页码数
     */
    @RequestMapping(value = "/getTotalPages",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPages(){
        //每页显示的记录行数
        int limit =5;
        //总记录数
        int totalItems = departmentService.getDeptCount();
        int temp = totalItems/limit;
        int totalPages=(totalItems % limit ==0)? temp : temp+1;
        return JsonMsg.success().addInfo("totalPages",totalPages);
    }

    @RequestMapping(value = "/getDeptById/{deptId}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeptById(@PathVariable("deptId") Integer deptId){
        Department department =null;
        if(deptId>0){
            department=departmentService.getDeptById(deptId);
        }
        if(department !=null){
            return JsonMsg.success().addInfo("department",department);
        }
        return JsonMsg.fail().addInfo("get_dept_error","无部门信息");
    }

    /**
     * 查询所有部门名称
     */
    @RequestMapping(value = "/getDeptName",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeptName(){
        List<Department> departmentList=departmentService.getDeptName();
        if(departmentList!=null){
            return JsonMsg.success().addInfo("departmentList",departmentList);
        }
        return JsonMsg.fail();
    }

    /**
     * 分页查询：返回指定页数对应的数据
     * @param pageNo
     */
    @RequestMapping(value = "/getDeptList",method = RequestMethod.GET)
    public ModelAndView getDeptList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo){
        ModelAndView mv=new ModelAndView("departmentPage");
        //每页显示的记录行数
        int limit=5;
        //总记录数
        int totalItems=departmentService.getDeptCount();
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp + 1;
        //每页的起始行(offset+1)数据，如第一页(offset=0，从第1(offset+1)行数据开始)
        int offset= (pageNo -1)*limit;
        List<Department> departments =departmentService.getDeptList(offset,limit);
        mv.addObject("departments",departments);
        mv.addObject("totalItems",totalItems);
        mv.addObject("totalPages",totalPages);
        mv.addObject("curPageNo",pageNo);
        return mv;
    }
}


