package org.example;

import static org.junit.Assert.assertTrue;

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
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class ATest
{
    private SqlSession ss;

    @Before
    public void prepare(){
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapperConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory ssf = ssfb.build(is);
        ss = ssf.openSession();
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void test_1()
    {

        List<Bonus> bonuses = ss.selectList("findAllBonus");
        List<Dept> depts = ss.selectList("findAllDept");
        List<Emp> emps = ss.selectList("findAllEmp");
        List<Salgrade> salgrades = ss.selectList("findAllSalgrade");

        bonuses.stream().forEach(System.out::println);
        depts.stream().forEach(System.out::println);
        emps.stream().forEach(System.out::println);
        salgrades.stream().forEach(System.out::println);

    }
    @After
    public void clean(){
        ss.close();
    }

}
