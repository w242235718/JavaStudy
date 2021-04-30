## 类与对象

### 1 相关概念

#### 1.1.1 类与对象

- 类是一组 相同属性或行为 (功能/方法) 的抽象，是一个抽象的概念。
- 对象：对象是某个类的具体实例。
- 先有类的定义，才能有类的实例



#### 1.1.2 定义一个类

1. 属性的数据类型可以是任意数据类型
2. 属性（成员变量/实例变量)
3. 成员变量作用域--->整个类体
4. 成员变量有默认值，其默认值是0或null



###### 案例

```java
public class task1 {
    private String name;
    private int id;

    public static void eat(String name) {
        System.out.println(name+"正在吃饭");
    }

    public static void sleep(String name) {
        System.out.println(name+"睡觉中");
    }
}
```

#### 1.1.3 JDK提前创建完毕，共程序员使用的类

  Java.lang包下的类 

​	e.g: int long....String..........



#### 1.2.1 成员变量vs局部变量

​	局部变量：在方法中出现的变量都算是局部变量(形参本质上也是局部变量)；

​	方法里面(定义)的变量都是局部变量

- 定义位置不同：成员变量定义在方法体外，局部变量定义在方法体中
- 使用上稍有不同：局部变量需要先赋值，后使用；成员变量因为有默认值，所以可以直接使用
- 作用域不同：成员变量的作用域在整个类整体，局部变量作用域在于方法体中



#### 1.2.2 引用

​	引用 Reference保存对象的内存地址

```java
//变量d 保存了Dog对象的内存地址
Dog d=new Dog();
```





#### 1.3.1 new 运算符

1. 创建对象

   ```java
   Dog d=new Dog();
   ```

#### 1.3.2 .运算符

1. 语法

   ```java
   //通过.调用
   d.run();
   ```

   

##   对象

#### 2.1 创建对象

- 使用new运算符创建对象，new创建对象都存存储在堆区

- 对象的成员变量存储在堆区中，对象的成员变量 有各自的存储空间

- 方法只是一个代码片段，存储在方法区，仅有一份。（被创建出来的对象共享）

#### 2.2 构造方法

- 语法

  - 构造方法没有返回值
  - 构造方法名与类名保持一致

- 作用

  - 给成员变量赋默认值

- 注意点

  - 如果类中无构造方法，JDK会自动添加无参构造器
  - 一旦创建有参构造器，JDK不会自动添加无参构造器

- 案例

  ```java
  //主程序
  private static void method2() {
      SmStudent s1 = new SmStudent("ww",1,new Computer());
      SmStudent s2 = new SmStudent("ls",2,new Computer());
      s1.study();
      s2.study();
      s1.pc.compute();
      s2.pc.programing();
  }
  ```

  ```java
  public class SmStudent {
      String name;
      Computer pc;
      int age;
  
      public SmStudent(){
  
      }
      public SmStudent(String name,int age,Computer pc){
          this.name=name;
          this.age=age;
          this.pc=pc;
      }
  
      public void study(){
          System.out.println(name+"正在学习");
      }
  
  }
  ```

  ```java
  public class Computer {
      String brand;
      int price;
  
  
      public void compute(){
          System.out.println("计算...");
      }
      public void programing(){
          System.out.println("编程....");
      }
  }
  ```

#### 2.3  this关键字

1. this使用场景：

   ​	当成员变量与局部变量重名的时候，可以使用this来加以区分当前成员变量。（程序会优先访问静态变量）

2. this使用：

   this() -->调用无参构造方法

   this(a,b)--->调用其他构造方法

   this--->表示当前对象

3. this是如何指向当前对象？

   对象创建的时候，this保存了指向自身的内存地

4. 注意点：

   1. this()调用本类的其他构造方法，只能放在构造方法第一行

   

#### 2.4 Static关键字

- static 表示静态，修饰方法/成员变量
- static如果修饰成员变量，表示该成员变量**只有一份**，所有**对象共享这一份**（静态）成员变量
- 被static修饰的类和成员变量 只能通过【类名.】调用
- 静态方法中不能够访问非静态成员（变量/方法）



为什么静态方法不能 访问非静态成员变量/方法？

​	静态方法/变量 存储在方法区, 而成员变量/成员方法存储在堆区。

​	堆区中没有开辟成员变量/方法 堆应的空间，静态方法中也没有引用指向成员变量/方法。所以不能访问。	

​	

##   面向对象

#### 3.1 特点

封装：（数据安全问题）

继承：（代码冗余问题）

多态：（程序扩展问题）



##### 3.2 封装

- 将成员变量暴露在外，非常危险。因为任何地方都可以做改动，会导致数据不安全

解决方案：

- 将成员变量私有化、对外提供入口(Setter and Getter)



#### 3.3 继承

- 概念

  - 子类（派生类）可以继承父类的成员（变量、方法），但只能访问可以访问的的内容。
  - Java只支持单继承、不支持多继承
  - (多继承 参考接口)

- 语法

  用extends关键字继承

  ````java
  
  public class Se extend Employee{
      
  }
  ````

- 关系

  - 需要满足 class A is class B

- 案例

  ```java
  @Data
  public class Employee {
      private String name;
      private int id;
      private char gender;
      private double salary;
      protected String test;
  }
  ```

  ```java
  @Data
  public class Pm extends Employee{
      private double exp;
      private double bonus;
  }
  ```

- 执行流程

  1. 子类构造方法执行之前，一定会先执行父类的构造方法(默认执行无参构造方法)

     

- 注意事项

  - 当父类没有无参构造方法时候,子类需要 显示添加(super) 调用父类构造方法

  - 子类构造方法调用父类构造，并没有创建父类对象

  - Object是所有子类的超级父类(Super Class)/基类

    

  

  

##### 3.3.2 Super关键字

- this指向当前对象，super指向父类
- 使用场景
  - 子类---调用-->父类构造
  - 当子类成员变量与父类成员变量重名的时候，使用super区分



#### 3.4 多态

​		待补充

####  



> 扩展· 栈空间 

栈的基本单位：栈桢

一个方法对于一个栈桢，方法中的变量，栈针会在开辟一块空间存放变量。



> 扩展 

创建对象---类型信息--->.class







#### 3.5 权限修饰符

**`面试题`**：权限修饰符

| 权限修饰符 | 本类 | 同包 | (不同包)子类 | 其他类 |
| ---------- | ---- | ---- | ------------ | ------ |
| public     | √    | √    | √            | √      |
| protected  | √    | √    | √            | ×      |
| private    | √    | ×    | ×            | ×      |
| (default)  | √    | √    | ×            | ×      |

​	protected 允许本类所在包下的类访问和其它包下的子类访问



#### 3.6 方法重写(Override)

- 重写：发生在继承关系中

  ​	子类继承父类方法后，父类方法不能满足子类方法需求时，会产生方法重写。

- 语法

  - 方法名要与父类方法名相同
  - 形式参数要与父类方法的形式参数相同
  - （引用类型）子类返回值小于父类方法返回值
  - 子类权限修饰符大于父类方法权限修饰符

- 案例

  ```java
   @Override
      public double show() {
          return this.getSalary()+bonus;
      }
  ```



> `面试`:方法重载和方法重写有什么区别？

重载：是在本类中，才会产生重载。重载指的是方法名相同，形式参数列表不同。方法重载解决的是 不同参数实现相同/相似功能。	



重写：首先、重写是发生在继承关系中。

重写是在父类方法无法满足子类需求时，才会产生方法重写。

要实现重写，要求子类方法名、参数列表和父类相同。返回值小于等于父类方法返回值类型，子类权限修饰符大于父类方法权限修饰符。



## Package和Import

#### Package(包机制)

​	为了解决类名冲突的问题，因为同一个包下不能有相同的类名

​	

#### Import(导包)

​	同一个包下类 使用的时候的不需要import,不同包下的类 使用的时候需要使用import 将.java文件导入进来

​	Java.lang.* 所有的类都不需要显示引入，因为JVM会自动引入。

