package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.pojo.Bonus;
import org.example.pojo.Dept;
import org.example.pojo.Emp;
import org.example.pojo.Salgrade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-06-08 18:11
 */
public class BTest {
    private SqlSession ss;

    @Before
    public void prepare() {
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapperConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory ssf = ssfb.build(is);
        ss = ssf.openSession(true);
    }

    /**
     * sqlsession的三种查询方式 selectOne  selectList  selectMap
     */
    @Test
    public void test_1() {
        Emp emp = ss.selectOne("getOne", Emp.class);
        System.out.println("One～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        List<Object> emps = ss.selectList("findAllEmp");
        //如果多个Mapper.xml文件中的方法名重复，则需要加上前面的namespace确保唯一定位到要执行的生sql
        Map<Object, Object> empMaps = ss.selectMap("org.example.mapper.EmpMapper.findMap", "empno");
        System.out.println("emp = " + emp);
        System.out.println("List～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        emps.stream().forEach(System.out::println);
        System.out.println("Map～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        System.out.println(empMaps);
    }

    /**
     * 三种参数传递方式 单个简单参数, 集合参数, 对象参数
     */
    @Test
    public void test_2() {
        Emp emp = ss.selectOne("getOnebyEmpNo", 7521);
        System.out.println("emp = " + emp);
        //查询部门号为20且员工薪资在1500以上的员工
        HashMap<String, Object> args = new HashMap<>();
        args.put("deptno",20);
        args.put("sal",1500);
        List<Object> emps = ss.selectList("findEmpsbyDeptNoandSal", args);
        System.out.println("部门号为20且员工薪资在1500以上的员工");
        emps.stream().forEach(System.out::println);

        Emp empParam = new Emp();
        empParam.setDeptno(30);
        empParam.setSal(1800.0F);
        List<Emp> emps2 = ss.selectList("findEmpsbyDeptNoandSalThruVO", empParam);
        System.out.println("部门号为30且员工薪资在1800以上的员工");
        emps2.stream().forEach(System.out::println);
    }

    /**
     * 测试CRUD
     */
    @Test
    public void test_3() {
        //增
//        Emp empParam = new Emp(8888,"刘德华","天王",7839,new Date(),8888.88F,66.66F,10);
//        int inserted = ss.insert("insert", empParam);
//        System.out.println("inserted = " + inserted);
//        //ss.commit(); //需要手动提交， 否则会回滚。，或者可以在初始化时可以： ss = ssf.openSession(true); 表示自动提交
//        //ss.rollback();
//        System.out.println("inserted = " + inserted);
        //删
        int deleted = ss.delete("deletebyempno", 8888);
        System.out.println("deleted = " + deleted);



        //改
//        Emp empParam2 = new Emp();
//        empParam2.setEmpno(8888);
//        empParam2.setEname("AndyLau");
//        int updated = ss.update("update", empParam2);



    }



    @After
    public void clean() {
        ss.close();
    }
}
