package cn.zy.mapper;

import cn.zy.bean.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zy
 * @date 2020-12-03 15:07
 */
public interface DepartmentMapper {


    /**
     * =================================新增============================================
     */
    int insertDept(@Param("department") Department department);


    /**
     * =================================删除============================================
     */
    int deleteDeptById(@Param("deptId") Integer deptId);

    /**
     * =================================更改============================================
     */
    int updateDeptById(@Param("deptId") Integer deptId,
                       @Param("department") Department department);


    /**
     * =================================查询============================================
     */
    Department selectOneById(@Param("deptId") Integer deptId);

    Department selectOneByLeader(@Param("deptLeader") String deptLeader);

    Department selectOneByName(@Param("deptName") String deptName);


    List<Department> selectDeptList();

    List<Department> selectDeptsByLimitAndOffset(@Param("offset") Integer offset,
                                                 @Param("limit") Integer limit);

    int checkDeptsExistsByNameAndleader(@Param("deptLeader") String deptLeader,
                                        @Param("deptName") String deptName);

    int countDepts();

    //部门清空
}
