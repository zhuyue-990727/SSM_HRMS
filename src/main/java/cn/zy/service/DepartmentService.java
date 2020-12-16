package cn.zy.service;

import cn.zy.bean.Department;
import cn.zy.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2020-12-04 13:35
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    //添加
    public int addDept(Department department){
        return departmentMapper.insertDept(department);
    }

    //更新
    public int updateDeptById(Integer deptId,Department department){
        return departmentMapper.updateDeptById(deptId,department);
    }

    //删除
    public int deleteDeptById(Integer deptId){
        return departmentMapper.deleteDeptById(deptId);
    }

    //分页查询
    public List<Department> getDeptList(Integer offset, Integer limit){
        return departmentMapper.selectDeptsByLimitAndOffset(offset,limit);
    }

    //查询总数
    public int getDeptCount(){
        return departmentMapper.countDepts();
    }

    //根据id查询
    public Department getDeptById(Integer deptId){
        return departmentMapper.selectOneById(deptId);
    }

    //根据姓名查询
    public Department getDeptByName(String deptName){
        return departmentMapper.selectOneByName(deptName);
    }

    //查询集合
    public List<Department> getDeptName(){
        return departmentMapper.selectDeptList();
    }
}
