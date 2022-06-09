package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-06-08 13:59
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements Serializable{
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Float sal;
    private Float comm;
    private Integer deptno;
}
