package cn.zy.service;

import cn.zy.bean.Employee;
import cn.zy.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2020-12-04 8:59
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;


    //添加雇员操作
    public int addEmp(Employee employee){
        return employeeMapper.insertOne(employee);
    }

    //通过id更新操作
    public int updateEmpById(Integer empId,Employee employee){
        return employeeMapper.updateOneById(empId,employee);
    }

    //删除操作
    public int deleteEmpById(Integer empId){
        return employeeMapper.deleteOneById(empId);
    }

    //通过ID查询
    public Employee getEmpById(Integer empId){

        return employeeMapper.selectOneById(empId);
    }

    //通过姓名查询
    public Employee getEmpByName(String empName){
        return employeeMapper.selectOneByName(empName);
    }

    //获取雇员总数
    public int getEmpCount(){
        return employeeMapper.countEmps();
    }

    //获取雇员列表
    public List<Employee> getEmpList(Integer offset, Integer limit){
        return employeeMapper.selectByLimitAndOffset(offset,limit);
    }

}
