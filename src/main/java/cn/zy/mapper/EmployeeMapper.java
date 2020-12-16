package cn.zy.mapper;

import cn.zy.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zy
 * @date 2020-12-03 14:05
 */
public interface EmployeeMapper {
    //插入
    int insertOne(Employee employee);
    //更新
    int updateOneById(@Param("empId") Integer empId,
                      @Param("employee") Employee employee);
    //删除
    int deleteOneById(@Param("empId") Integer empId);

    //按id查询
    Employee selectOneById(@Param("empId") Integer empId);
    //按姓名查询
    Employee selectOneByName(@Param("empName") String empName);
    // 查询带有部门信息的Employee
    Employee selectWithDeptById(@Param("empId") Integer empId);
    // 分页查询
    List<Employee> selectByLimitAndOffset(@Param("offset") Integer offset, @Param("limit") Integer limit);
    //计算总数
    int countEmps();

    //员工清空
}
