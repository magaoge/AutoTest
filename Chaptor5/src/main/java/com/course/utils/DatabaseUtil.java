package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.Reader;

/**
 * Created by mgg on 2021/7/23
 */

public class DatabaseUtil {

    public static SqlSession getSqlSession(){

        Reader reader = null;
        try {
            //将数据库配置资源文件读取进流中，注意使用ibatis包下的类
            reader = Resources.getResourceAsReader("databascConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //与数据库建立连接
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(reader);
        //生成执行sql语句的对象
        SqlSession sqlSession = build.openSession();
        return sqlSession;
}
}
