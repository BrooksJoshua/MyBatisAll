import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-06-08 19:41
 */
public class ATest {
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

    @Test
    public void test1(){
        EmpMapper empMapper = ss.getMapper(EmpMapper.class); 
        Emp emp = empMapper.getOnebyEmpno(7839);
        System.out.println("emp = " + emp);
                
        
    }


    @After
    public void clean() {
        ss.close();
    }
}
