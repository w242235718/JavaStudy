package com.shopmanager.utils;

import jdk.nashorn.internal.ir.SplitReturn;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.function.Consumer;

public class DBUtils {
    private static Properties properties=null;

    static {
        properties=new Properties();
        try {
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("./db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValueByKey(String key){
        Objects.requireNonNull(key);
        if (properties.containsKey(key)){
            return properties.getProperty(key);
        }
        return null;
    }

    public static Connection getConn(){
        Connection connection=null;
        if (connection==null){
            try {
                Class.forName(getValueByKey("mysql.driver"));
                connection = DriverManager.getConnection(getValueByKey("mysql.url"),
                        getValueByKey("mysql.username"),getValueByKey("mysql.password"));
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return connection;
    }

    /**
     *执行SQL命令(针对 update delete insert)
     * @param sql SQL命令
     * @return 返回收影响行数
     */
    public static int execute(String sql,Object...args) throws SQLException {
        Objects.requireNonNull(sql);
        Connection conn = getConn();
        PreparedStatement preparedStatement=null;
        int row=-1;

        try {
            preparedStatement = conn.prepareStatement(sql);

            if (args!=null&&args.length>0){
                for (int j = 0; j < args.length; j++) {
                    preparedStatement.setObject(j+1,args[j]);
                }
            }
            row = preparedStatement.executeUpdate();
        } finally {

            close(preparedStatement,conn);
        }


        return row;
    }

    //INSERT INTO `shopmanager`.`t_goodstype`(`pid`, `typeName`, `isParentType`, `createTime`) VALUES (0, '手机', 1, '2021-05-26 19:29:46')
    public static int executeInsert(String databaseName,String tableName,Object condition) throws InvocationTargetException, IllegalAccessException, SQLException {
        Objects.requireNonNull(databaseName);
        Objects.requireNonNull(tableName);
        Objects.requireNonNull(condition);

        Connection conn=null;
        PreparedStatement preparedStatement=null;
        int row;

        try {

            conn = getConn();
            preparedStatement = null;
            String sql = getInsertSQL(databaseName, tableName, condition);
            preparedStatement = conn.prepareStatement(sql);
            row = preparedStatement.executeUpdate();
        } finally {
            close(preparedStatement,conn);
        }
        return row;
    }





    public static int executeInsertAndGet(String databaseName,String tableName,Object condition) throws SQLException, IllegalAccessException {
        Objects.requireNonNull(databaseName);
        Objects.requireNonNull(tableName);
        Objects.requireNonNull(condition);

        int index;
        Connection conn =getConn();
        PreparedStatement preparedStatement =null;
        ResultSet rs=null;
        try {
            String sql = getInsertSQL(databaseName, tableName, condition);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();


            sql="select * from "+tableName+" group by 1 desc limit 0,1";
            preparedStatement = conn.prepareStatement(sql);
            rs=preparedStatement.executeQuery();
            index = -1;
            if (rs.next()) {
                index= rs.getInt(1);
            }
        } finally {
            close(rs,preparedStatement,conn);
        }
        return index;
    }

    /***
     * 查询结果集
     * @param paramType 指定类型字节码
     * @param sql SQL语句
     * @param args 传参
     * @param <T> 指定类型
     * @return 返回结果集
     */
    public static<T> List<T> executeQueryAll(Class<T> paramType,String sql,Object... args) throws SQLException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException {
        Objects.requireNonNull(sql);

        Connection conn=getConn();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<T> list=new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            //args查询条件
            if (args!=null&&args.length>0){
                for (int i = 0; i < args.length; i++) {
                    //args查询条件赋值
                    ps.setObject(i+1,args[i]);
                }
            }

            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();


            while(rs.next()){
                //通过反射 设置结果集
                T instance = paramType.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    StringBuilder builder=new StringBuilder("set");
                    String columnName = metaData.getColumnName(i);
                    String[] varStr = columnName.split("_");
                    for (int j = 0; j < varStr.length; j++) {
                        builder.append(varStr[j].substring(0,1).toUpperCase()).append(varStr[j].substring(1));
                    }
                    Method method = paramType.getDeclaredMethod(builder.toString(), Class.forName(metaData.getColumnClassName(i)));
                    method.invoke(instance,rs.getObject(i));
                }
                list.add(instance);
            }

        } finally {
            close(rs,ps,conn);
        }

        return list;
    }

    /***
     * 根据条件查询指定结果
     * @param paramType 返回结果类型
     * @param sql 指定sql(select * from 表)即可
     * @param condition 条件
     * @return 返回封装好的指定类型结果
     * @throws IllegalAccessException
     * @throws SQLException
     * @throws InstantiationException
     */
    public static <T> T executeQueryByCondition(Class<T> paramType,String sql,Object condition) throws IllegalAccessException, SQLException, InstantiationException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        Objects.requireNonNull(sql);
        Objects.requireNonNull(condition);
        Connection conn = getConn();
        T instance=null;

        StringBuilder dynamicSql=new StringBuilder(sql);
        dynamicSql.append("where 1=1");

        //获取condition对象中属性的值
        Class<?> conditionClass = condition.getClass();
        Field[] fields = conditionClass.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            String name =f.getName();
            String value = String.valueOf(f.get(condition)).equals("null")?"":String.valueOf(f.get(condition));
            if (value.length()>0){
                dynamicSql.append(" and ").append(name).append("=").append("'"+value+"'");
            }
        }

        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            preparedStatement = conn.prepareStatement(dynamicSql.toString());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                //反射设置值
                instance= paramType.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String[] items = columnName.split("_");
                    StringBuilder methodName=new StringBuilder("set");

                    for (int j = 0; j < items.length; j++) {
                        methodName.append(items[j].substring(0,1).toUpperCase()).append(items[j].substring(1));
                    }
                    Method m = paramType.getDeclaredMethod(methodName.toString(), Class.forName(metaData.getColumnClassName(i)));
                    m.invoke(instance, resultSet.getObject(i));
                }
            }
        } finally {
            close(resultSet,preparedStatement,conn);
        }


        return instance;
    }


    /**
     *  适用于JavaBean
     * @param paramType
     * @param sql
     * @param args
     * @param <T>
     * @return
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    public static <T> T executeQueryByArgs(Class<T> paramType,String sql,Object...args) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        Objects.requireNonNull(sql);

        Connection conn = getConn();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        T instance = null;

        try {
            preparedStatement = conn.prepareStatement(sql);
            //args查询条件
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    //args查询条件赋值
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            //结果集
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                //通过反射 设置结果集
                instance = paramType.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    StringBuilder builder=new StringBuilder("set");
                    String columnName = metaData.getColumnName(i);
                    String[] varStr = columnName.split("_");
                    for (int j = 0; j < varStr.length; j++) {
                        builder.append(varStr[j].substring(0,1).toUpperCase()).append(varStr[j].substring(1));
                    }
                    Method method = paramType.getDeclaredMethod(builder.toString(), Class.forName(metaData.getColumnClassName(i)));
                    method.invoke(instance,resultSet.getObject(i));
                }
            }
        } finally {
            close(resultSet,preparedStatement,conn);
        }


        return instance;
    }

    public static int executeUpdateByPrimary(String databaseName,String tableName,Object condition) throws SQLException, IllegalAccessException {
        Objects.requireNonNull(databaseName);
        Objects.requireNonNull(tableName);
        Objects.requireNonNull(condition);

        Connection conn=null;
        PreparedStatement preparedStatement=null;
        int row=-1;

        try {
            conn = getConn();
            preparedStatement = null;
            String sql = getUpdateSQL(databaseName, tableName, condition);
            preparedStatement = conn.prepareStatement(sql);
            row = preparedStatement.executeUpdate();
        } finally {
            close(preparedStatement,conn);
        }
        return row;
    }

    private static String getInsertSQL(String databaseName, String tableName, Object condition) throws IllegalAccessException {
        //拼接SQL
        StringBuilder sql=new StringBuilder("insert into `"+databaseName+"`.`"+tableName+"` (");
        Class<?> conditionClass = condition.getClass();
        Field[] fields = conditionClass.getDeclaredFields();
        //列名
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(condition)!=null){
                sql.append(field.getName());
                sql.append(",");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(") values(");

        //值
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(condition)!=null){
                if (field.getType()==String.class||field.getType()== Date.class||field.getType()==Timestamp.class){
                    sql.append("'"+field.get(condition)+"'");
                    sql.append(",");
                    continue;
                }
                sql.append(field.get(condition));
                sql.append(",");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");

        return sql.toString();
    }

    private static String getUpdateSQL(String databaseName, String tableName, Object condition) throws IllegalAccessException {
        //UPDATE `shopmanager`.`t_goods` SET gid=1, gname='红米小金刚', gtype=2, gstore=20,
        //price=1499, gstatus=1, gdiscount=1.0, createtime='2021-05-25 20:33:34.0', updatetime='2021-05-26 20:33:40.0' WHERE gid=1
        //拼接SQL
        StringBuilder sql=new StringBuilder("UPDATE `"+databaseName+"`.`"+tableName+"` SET");
        Class<?> conditionClass = condition.getClass();
        Field[] fields = conditionClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(condition)!=null){
                sql.append(" "+field.getName()+"=");
                if (field.getType()==String.class||field.getType()== Date.class||field.getType()==Timestamp.class){
                    sql.append("'"+field.get(condition)+"'");
                    sql.append(",");
                    continue;
                }
                sql.append(field.get(condition));
                sql.append(",");
            }
        }
        sql.deleteCharAt(sql.length()-1);

        sql.append(" WHERE ");

        //获取主键
        fields[0].setAccessible(true);
        sql.append(fields[0].getName()+"=");
        sql.append(fields[0].get(condition));
        return sql.toString();
    }

    public static void close(Connection conn){
        close(null,null,conn);
    }
    public static void close(PreparedStatement ps,Connection conn){
        close(null,ps,conn);
    }

    /**
     * 关闭连接
     * @param rs    结果集
     * @param ps    预编译对象
     * @param conn  连接
     */
    public static void close(ResultSet rs,PreparedStatement ps,Connection conn){
        if (rs!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 查询数据库指定列 并返回单条值
     * @param type 返回类型
     * @param sql sql语句
     * @param args 参数
     * @param <T> 类型
     * @return 单条数据值
     */
    public static <T> T executeQuery(Class<T> type,String sql,Object... args) throws SQLException {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        T result=null;

        conn=getConn();
        ps=conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }

        rs=ps.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (rs.next()){
            for (int i = 1; i <= columnCount; i++) {
                result=(T)rs.getObject(columnCount);
            }
        }
        close(rs,ps,conn);

        return result;
    }
}
