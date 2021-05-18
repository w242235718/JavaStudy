## Review

#### IO

输入流/输出流

字节流/字符流



二进制文件：

FileInputStream 	FileInputStream

BufferedInputStream	 BufferedOutputStream



处理文本文件：

FileReader 	FileWriter	

BufferedReader	 BufferedWriter



字节流-->字符流

InputStreamReader	

字符流--->字节流

OutputStreamWriter



**序列化**

ObjectInputStream	ObjectOutputStream

​	需要实现 Serializable接口，内部成员变量都要实现序列化

​	关键字 transient 可以跳过成员变量 序列化



数据处理流

​	DateInputStream 	DateOutputStream

工具类

​	Properties



#### 泛型

​	作用：约束数据类型

​	泛型符号其实只是占位符，个数不固定，名称没有要求。（K V T E）

​	泛型可以用在方法、类、接口、成员变量(在本来上做已知存在)上、形参参数上	

​	泛型占位符-->引用数据类型

​	泛型的静态方法不能使用类的泛型，可以使用方法的泛型

​	泛型在创建对象的时候确定，如果不写默认为Object(泛型擦除)	

​	

​	泛型接口：

​	1）创建类的时候指定 

​			public interface inter2<K,V> extends inter<K,V>  			

​	2）创建实例对象的时候指定



方法

​	用在方法声明处，在实际传递值的时候确定数据类型，静态方法可以出现泛型符号



上下限

​	上限：<? extends T >

​	下限：<? super T>



泛型擦除：

​	把一个有泛型的引用 赋值给无泛型的引用



## 反射 

​	

#### 反射 Reflection

​		反射是框架的灵魂；学动态代理的基础

- 反射：在运行期间，动态获取类的信息，已经对类进行操作的机制。



.java--->.class--->ClassLoader---->Memeory

加载到方法区的类(字节码)，会生成Class类的对象



##### 2.1 如何获取Class对象？

```java
//方式一
Class<Car> carClass=Car.class;
//方式二
Car car=new Car();
Class<? extend Car> carClass=car.getClass();
//方式三
Class carClass=Class.forName("全路径");
```

```java
// 获取class对象(字节码对象)
public static void main(String[] args) {
    Class<Paper> paperClass = Paper.class;

    Paper paper = new Paper();
    Class<? extends Paper> aClass = paper.getClass();
    Class<?> pClass=null;
    try {
        pClass = Class.forName("com.demo.reflect.Paper");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    System.out.println(pClass==aClass);
    System.out.println(aClass==paperClass);
}
```

> 创建对象的四种方式

- new运算符
- Clone()
- 反序列化 readObject()
- 反射 newInstance()





##### 2.2 获取字节码文件中成员变量/方法

​	将字节码中的所有成员变量、方法和构造方法都作为对象来对待

> 获取成员变量

- getFieldes  获取**public修饰**的**成员变量**，**不管是本类声明的还是从父类继承**

  ```java
  private static void method1() throws ClassNotFoundException {
      Class<?> paper = Class.forName("com.demo.reflect.Paper");
      Field[] fields = paper.getFields();
      for (Field field : fields) {
          System.out.println(field.getName());
      }
  }
  ```

  ![image-20210517175722119](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210517175722119.png)

- getDeclaredFiles() 获取本类声明的（权限修饰符）不包含从父类继承

  ```java
  private static void method1() throws ClassNotFoundException {
      Class<?> paper = Class.forName("com.demo.reflect.Paper");
      Field[] fields = paper.getDeclaredFields();
      for (Field field : fields) {
          System.out.println(field.getName());
      }
  }
  ```

![image-20210517175733901](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210517175733901.png)

> 获取方法

- getMethods()

  ```java
  private static void method1() throws ClassNotFoundException {
      Class<?> paper = Class.forName("com.demo.reflect.Paper");
      Method[] methods = paper.getMethods();
      for (Method m : methods) {
          System.out.println(m.getName());
      }
  }
  ```

  ![image-20210517175840915](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210517175840915.png)

- getDeclaredMethods()

  ```java
  private static void method1() throws ClassNotFoundException {
      Class<?> paper = Class.forName("com.demo.reflect.Paper");
      Method[] methods = paper.getDeclaredMethods();
      for (Method m : methods) {
          System.out.println(m.getName());
      }
  }
  ```

  ![image-20210517175823798](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210517175823798.png)

获取public修饰的成员方法

​	Method[] methods=carClass.getMethods()

​	System.out.print(methods.length)



##### 2.3 方法调用

inovke(Object o, Object... args);

```java
public static void main(String[] args) {
    try {
        Class<?> pClass = Class.forName("com.demo.reflect.Paper2");
        Object instance = pClass.newInstance();
        Method[] methods = pClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        //getDeclaredMethod("方法名",方法类型);
        Method setNum = pClass.getDeclaredMethod("setNum", Integer.class);
        setNum.setAccessible(true);
        setNum.invoke(instance,20);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (NoSuchMethodException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    }

}
```

##### 2.4 构造器

> getDeclaredConstructors和getConstructors的区别？

- getDeclaredConstructors获取所有构造器

  ```java
  try {
      Class<?> pClass = Class.forName("com.demo.reflect.Paper2");
  
      Constructor<?>[] constructors = pClass.getDeclaredConstructors();
      for (Constructor<?> constructor : constructors) {
          System.out.println(constructor.getName());
      }
      
  
  } catch (ClassNotFoundException e) {
      e.printStackTrace();
  }
  ```

  ![image-20210517194843111](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210517194843111.png)

- getConstructors获取所有public修饰的构造器

  ```java
  Constructor<?>[] c = pClass.getConstructors();
  for (Constructor<?> constructor : c) {
      System.out.println(constructor.getName());
  }
  ```

![image-20210517194852744](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210517194852744.png)

##### 2.5 类加载

.java----编译器----->.class----类加载器---->JVM内存--->字节码校验----->解释器 机械指令---->OS



1. 类的加载时机

   - 按需加载，用的时候再加载
   - 只会被加载一次，不会重复加载（双亲委派机制）

2. 类加载器

   1. 核心类加载器	BootStrapClassLoader（核心类 Java.Lang)
   2. 扩展类加载器    ExtClassLoader （找JDK安装目录/lib/ext包下的所有类）
   3. 应用加载器       ApplicationClassLoader
   4. 其他类加载器

3. 一个字节码文件怎么做到只被一个类加载器加载？(双亲委派机制)

   当一个类加载器收到类加载任务时，不会主动完成加载任务，会先交给自己的父加载器（AppClassLoader)去完成，父加载器会继续把请求委派给其上级(ExtClassLoader)以此类推，直到BootStrapClassLoader；因此，最终加载任务都会传递给BootStrapClassLoader核心类加载器，只有当所有的父加载器都没有加载过的时候，BootStrapClassLoader会开始向下进行查找。

![img](https://upload-images.jianshu.io/upload_images/7634245-7b7882e1f4ea5d7d.png)



## 多线程

进程：程序，资源分配的基本单位

线程：线程归属于进程，每个进程中至少有一个进程；多个线程会共享进程资源



并发：同时发生

高并发：



并行：

​	真正的同时处理多个请求；CPU（多颗）；单核CPU做不到（假并行）

串行：一个一个处理；一个任务结束之后再去处理另外一个任务

同步：

​	一个任务开始必须等待另外一个任务的结束

异步：

​	多个任务可以同时进行

​	



##### 3.1 创建线程

1）继承Thread

- 自定义类继承Treadl类，重写run()方法

- 将核心逻辑写到run()中

- 开辟线程 run.start()方法

  

```java
//方法一
public class CustomThread extends Thread{
    public CustomThread() {
        super();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("T1:"+i);
        }
    }

}
```

```java
public static void main(String[] args) {
    Thread t=new CustomThread();
    t.start();
}
```

2）实现Runable接口



````java
//方法二
private static void method1() {
    Thread t =new Thread(()->{
        System.out.println("t1");
        for (int i = 0; i < 20; i++) {
            System.out.println("t1:"+i);
        }
    });


    Thread t2=new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println("t2:"+i);
            }
        }
    });
    t.start();
    t2.start();
}
````

3）继承 FutureTask



````java
new Futuretask<>(new Callable)
    
private static void method3() {
        FutureTask<String> f1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 0; i < 20; i++) {
                    System.out.println("t1:" + i);
                }
                return "1";
            }
        });
        FutureTask<String> f2 = new FutureTask<>(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("t2:" + i);
            }
            return "2";
        });

        Thread t1=new Thread(f1);
        Thread t2=new Thread(f2);
        t1.start();
        t2.start();

        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
````

##### 3.2 获取线程的状态/信息

- getState() 	获取线程状态

- getId()

- getName()

- setName()

- setId()

- getPriority()    获取线程执行优先级

- sleep()  让线程睡眠

  1s=1000ms    1ms=1000us    1us=1000ns    1ns=1000ps



//时间工具类

TimeUnit.Second.sleep();

````java
private static void method4() {

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    System.out.println("t1:"+i);
                }
            }
        });
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
    
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        System.out.println(t.getPriority());

        System.out.println(t.getName());
        System.out.println(t.getId());
        System.out.println(t.getState());
    }
````



##### 3.3 join()

Thread A 执行完成后，主线程才能继续执行。

```java
private static void method5() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("t1:" + i);
                }
            }
        });
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("main:"+i);
        }
    }
```





##### 3.4 yield()

Thread A 让出CPU时间片



