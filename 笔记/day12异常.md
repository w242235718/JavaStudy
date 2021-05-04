## 异常

### 简介

​	影响程序正常执行的程序：

- 错误

- 异常

### 错误(error)

​	错误是无法通过编码方式解决的，只能修改源码或修改机器配置

​	常见错误：

​		java.lang.StackOverFlowError

​		java.lang.OutOfMemoryError(java heap space)



### 异常(Exception)

​	可以通过编码方式处理异常，让程序正常运行下去（体现Java健壮性）

​	如果不进行异常处理，程序是无法正常运行。



#### 1.1 异常体系

![image-20210504101125812](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210504101125812.png)

​	![image-20210504101049026](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210504101049026.png)



Exception分为两类

- 运行时异常(RuntimeException)

  RuntimeException的子类

- 非运行时异常(编译时异常)

  只要不是RuntimeException子类，就属于编译时异常



- 运行时异常和编译时异常区别
  - 运行时异常，可以不处理也能通过编译
  - 编译时异常，如果不处理无法通过编译



#### 1.2 对异常进行处理



##### 异常处理方式：

##### 1.2.1 捕捉

###### 	1.2.1-1 try

```java
try{
   //代码块
   //有可能会出现异常的代码
}catch(Exception e){
   //捕获异常信息 并处理
}finally{
   //释放资源
}	
```

​	注意事项：

 		一旦出现异常之后，直接跳出try语块，进入catch。try异常语句的后续代码不会执行。

###### 	1.2.1-2 Catch

​	**运行时异常，捕捉流程：**

````java
//JVM 自动创建异常对象
ArithmeticExeption c=new ArithmeticException("");

//JVM将异常对象通过catch(Exception)方法传递给捕获代码块
catch(ArithmeticExeption e){
    
}

//问题2：异常对象会进入多个catch语块吗？
//问题3：catch语块可以随意排列么？
//问题4：
````

语法：

1. catch语块可以出现多个	
2. 异常对象只会进入一个catch语块
3. 精准异常类型放在catch语块前面，范围较大的异常类型放在catch语块后面



注意事项：

- 类型越精准越好，方便排查（从小到大）

  

案例：

- 没有捕获到异常，return还会执行吗？

```java
//没有捕获到异常
   public int method3(int a,String b){
        int result=0;
        try{
            result=a/(Integer.parseInt(b));
        }catch (NullPointerException e){
            //没有捕捉到异常
            System.out.println(e.getMessage());
        }
       //以下代码不会执行
        System.out.println("执行完毕");
        return result;
    }
```



###### 1.2.1-2 finally

作用：

- 一定会执行的代码块，不管有没有产生异常对象、有没有处理异常对象。
- 用来释放资源

```java
//异常中的return语句,finally还会执行么？ 
  
public int method3(int a,String b){
        int result=0;
        try{
            result=a/(Integer.parseInt(b));
        }catch (ArithmeticException e){
            //捕捉到异常
            System.out.println(e.getMessage());
        	return 0;
        }finally{
            
            //JVM会互换return和finally指令
            //finally会先执行，在执行return
            System.out.println("一定会执行");
        }
        System.out.println("执行完毕");
        return result;
}
```



##### 1.2.2 抛出异常

​	异常对象抛给上层调用者

###### 1.2.2-1 使用情景

1. 不该由当前方法处理
2. 当前方法处理不了，由上层调用者处理。（警告信息）

###### 1.2.2-2 语法

```java
public void method1() throws XXException{
    //方法体
}
```

###### 1.2.2-3 Throws&Throw关键字

​	Throw是手动抛出异常对象

```java
throw new NullPointerException("异常信息");
```

​	Throws写在方法签名处，将方法中的异常抛给上层调用者

​	

###### 1.2.2-4 注意事项

​	抛异常：谁调用谁处理



#### 1.3 自定义异常

######  1.3.1 使用场景

​	让自定义异常和当前项目相关联，方便进行排查和定位异常。

######  1.3.2 语法

- 继承RuntimeException/Exception
- 继承父类构造方法

````java
public class KeyNotFoundException extends RuntimeException{
    public KeyNotFoundException(){
    }
    
    public KeyNotFoundException(String message){
        super(message);
    }
    
}
````

 





> Java健壮性体现在？

​	异常机制和GC回收机制

> `面试题`:请列举常见的异常

- NullPointerException
- ArrayOutOfBoundException
- NumberFormatException
- ClassCastException
- ClassNotFoundException
- IOException
- ...



 