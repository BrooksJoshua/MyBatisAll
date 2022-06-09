package org.example.mapper;

import org.example.pojo.Emp;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-06-08 19:40
 */
public interface EmpMapper {
    Emp getOnebyEmpno(Integer empno);
}
