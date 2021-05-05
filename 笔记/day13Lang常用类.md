## Lang常用类

### Review

> `面试题`:Java中有几种引用类型?

引用（Reference）：存放某块内存的起始地址；

​	四种引用导致管理对象的生命周期不同（从创建到销毁）

四种：

- 强引用：Date d=new Date(); ===>强引用，永远不会被GC回收。(有用且必须的对象)

  - JVM宁可抛出OutOfMemoryError，也不会回收强引用对象。

    ```java
    public class ReferenceType {
        public static void main(String[] args) {
            int _1m=1024*1024;
            //强引用,6m
            byte[] b=new byte[6*_1m];
            //强引用,5m
            //超出JVM 10m堆大小 b引用不会被回收
            // ===>java.lang.OutOfMemoryError
            byte[] b2=new byte[5*_1m];
        }
    }
    ```

- 软引用(Soft Reference)：有用但不是必须的

  - JVM内存空间不足的时候，GC才会回收软引用指向的空间。

  - 如果空间充足，GC不会回收此类引用 引用的对象。

    ```java
    SoftReference<byte[]> sf=new SoftReference<>()
    ```

    ```java
    //空间不足
    public class ReferenceType {
        public static void main(String[] args) {
            int _1m=1024*1024;
            //创建6m的数组对象，软引用指向该数组对象 
            SoftReference<byte[]> reference=new SoftReference<>(new byte[6*_1m]);
            System.out.println(reference.get().length);
            //强引用创建5m 数组大小,会导致堆内存空间不足
            //堆内存空间不足,GC回收软引用
            byte[] b2=new byte[5*_1m];
            System.out.println(reference.get());
        }
    }
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //空间充足
    public class ReferenceType {
        public static void main(String[] args) {
            int _1m=1024*1024;
            //创建6m的数组对象，软引用指向该数组对象 
            SoftReference<byte[]> reference=new SoftReference<>(new byte[6*_1m]);
            System.out.println(reference.get().length);
    
            byte[] b2=new byte[_1m];
            System.out.println(b2.length);
            //空间充足,软引用不会被回收
            System.out.println(reference.get());
        }
    }
    ```

- 弱引用(Weak Reference)：弱引用的对象下一次GC的时候一定会被回收，而不管内存是否足够。

  - 弱引用指向的对象，必定会被这次显性调用GC回收。此类引用指向的对象必定会被全被回收，和空间是否充足无关。

    ```java
    public class ReferenceType {
        public static void main(String[] args) {
            int _1m=1024*1024;
     		//弱引用,创建6m大小数组空间
            WeakReference<byte[]> reference=new WeakReference<>(new byte[6*_1m]);
            System.out.println(reference.get().length);
            //GC回收
            System.gc();
            
            byte[] b2=new byte[2];
            System.out.println(b2.length);
            //弱引用指向对象被回收(不管是否有用)
            //null
            System.out.println(reference.get());
        }
    }
    ```

- 虚引用(Phantom Reference)：记录对象回收trance(踪迹)

  



`前提:`

1. 调整JVM堆内存空间

   ![image-20210505101807190](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210505101807190.png)

   ```java
   //最大空间	最小空间
   -Xmx10m  -Xms10m
   ```

   



> 扩展：变量槽

栈帧---->局部变量表---->变量槽(32位/64位)



reference占用一个变量槽空间大小





### Object类

1. java.lang包下的所有类不需要显性引入(import导入)

##### 1、equals()

- 基本类型数据比较的是数据

- 引用类型数据比较的是内存地址是否相同

  ```java
  
  //父类(基类)
  public boolean equals(Object obj) {
      return (this == obj);
  }
  
  //子类
  public class StudentTest {
      public static void main(String[] args) {
  
          Student ss1 = new Student();
          Student ss2 = new Student();
          System.out.println(ss1);
          System.out.println(ss2);
          System.out.println(ss1==ss2);
          
          //com.demo.exer.Student@1b6d3586
          //com.demo.exer.Student@4554617c
          
  	   System.out.println(ss1.equals(ss2))
  
      }
  }
  ```

- (子类)重写equals()方法

  ```java
  @Override
  public boolean equals(Object o){
      if (o==null){
          return false;
      }
      if (o instanceof Student){
          Student s=(Student)o;
          return this.id==s.id && this.name==s.name;
      }
      return false;
  }
  
  
  public class StudentTest {
      public static void main(String[] args) {
  
          Student s1 = new Student(1,"李四");
          Student s2 = new Student(2,"王五");
          Student s3 = new Student(1,"李四");
          
          System.out.println(s1.equals(s3));//false
          System.out.println(s1.equals(s3));//true
      }
  }
  
  ```

  

##### 2、hashCode()

​	是一个本地方法，方法体内部实现是C或C++语言实现的。

​	返回一个哈希值，代表一个对象。



​	hashCode()要和equals()一同重写

- Notes:	

​	If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects must produce the same integer result

- 如果两个对象equals() 哈希值必须相等
- 如果两个对象不equals()  , 哈希值就不应该相等



##### 3、toString()

​	返回对象的字符串形式

```java
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

##### 4、clone()

```java
CloneNotSupportedException:
    if the object's class does not support the Cloneable interface. Subclasses that override the clone method can also throw this exception to indicate that an instance cannot be cloned.
```

要实现克隆，需要实现Cloneable接口



- 浅克隆

  - 基本数据类型：克隆值
  - 引用数据类型：克隆对象内存地址

  ```java
  public demo(){
          this.id=2;
          this.name="abc";
      }
  
      public void demoClone(){
          try {
              Object c = this.clone();
              demo clone=(demo) c;
              //2
              System.out.println(((demo) c).id);
              
              ((demo) c).name="def";
              //def
              System.out.println(((demo) c).name);
              
          } catch (CloneNotSupportedException e) {
              e.printStackTrace();
          }
  
      }
  ```

  

- 深克隆

##### 5、finalize()

​	对象被回收时，GC自动调用

```java
Called by the garbage collector on an object when garbage collection determines that there are no more references to the object. A subclass overrides the finalize method to dispose of system resources or to perform other cleanup.
```



#### 扩展：对象创建方式

1. 使用new运算符
2. 克隆
3. 序列化



### Objects类

​	为了解决object类方法调用的时候出现空指针异常

##### 1、equals()

```java
public static boolean equals(Object a, Object b) {
    return (a == b) || (a != null && a.equals(b));
}
```

##### 2、hashCode()

```java
public static int hashCode(Object o) {
    return o != null ? o.hashCode() : 0;
}
```

##### 3、toString()

```java
public static String toString(Object o) {
    return String.valueOf(o);
}
```

##### 4、requireNonNull()

```java
public static <T> T requireNonNull(T obj) {
    if (obj == null)
        throw new NullPointerException();
    return obj;
}
```

##### 5、deepEquals()

​	比较数组元素是否相等