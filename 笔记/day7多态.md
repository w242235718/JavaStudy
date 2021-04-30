## 多态

#### 概念

- 要有继承关系
- 要有重写
- 父类引用指向子类对象



##### 向上转型  

- 父类引用（保存）指向子类对象

- 父类引用不能调用子类新增的成员





##### 向下转向  

- 将父类对象转换成子类对象

## 抽象类/方法

#### 抽象类/抽象方法

​	前提：有些类是不需要new创建实例

- 语法

  - abstract

    ```java
    public abstract class Animal(){
        
    }
    ```

  - 抽象方法的类必须声明为抽象类

- 作用

  - 抽象类被定义出来就是子类继承
  - 抽象方法被定义出来，作用就是被子类继承重写



## final关键字

#### 概念

​	final表示最终的，final可以修饰类、成员变量、静态变量、成员方法

#### 注意

​	final修饰的类是无法被子类继承的

​	final修饰的方法无法被子类重写

​	final修饰的成员变量(首次赋值过后)无法被修改，表示常量。

​	final修饰的形参，表示方法内部不能重写赋值。

#### 案例

- ````java
  final private String admin="很好";
  ````

- 使用构造器给final修饰的变量赋值

- ![image-20210430154408323](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210430154408323.png)

  ```java
  
  public class Father {
      private String name;
      private final int sid;
      public Father(){
  		//无参构造 可能无法给sid赋上值
      }
  }
  //解决方法
  
  public class Father {
      private String name;
      private final int sid;
      public Father(){
  		this.sid=10;
      }
  }
  ```

  

子类继承父类方法之之后，如果父类中有静态方法，可以直接使用，但是不能重写。

```java
public class Father {
    private String name;

    public static void printTest(){
        System.out.println("111");
    }
}
```

```java
public class Son extends Father{

    public void test2(){
        Father.printTest();
    }
}
```

​                                                                                                                                

## 接口



- 目的

  - Java只支持单继承，接口的出现弥补了单继承

- 概念

  - 接口是非常特殊的一种抽象类，接口中只有抽象方法

- 语法

  - 声明

    ```java
    public interface Player(){
    	int age;
        void method1();
    }
    ```

- 作用

  - 定义接口需要 实现类去实现

- 特性

  - 成员变量都是public static final所修饰，表示常量
  - 方法都是public abstract所修饰
  - 接口支持多实现

- 新特性

  - 接口中出现普通方法(适配器)

    ```java
    public interface Player {
       
        default void method2(int i){
            System.out.println(i);
        }
    }
    
    ```

  - 静态方法

    ```java
    public interface Player {
        void show();
        static void method(){
            System.out.println("test");
        }
    }
    ```

    (子类继承父类方法之之后，如果父类中有静态方法，可以直接使用，但是不能重写。)

    接口中可以出现静态方法，接口中的静态方法 同过【类名(接口).】调用

  

- 接口之间关联

  - 关联

    - 接口与类之间是实现的关系
    - 接口与接口之间是继承的关系
    - 接口与接口之间是多继承的关系

  - 使用extend实现多继承

    - ```java
      public interface Player {
          int FINAL_ID=1011;
          void printID(String id);
      
          default void method2(int i){
              System.out.println(i);
          }
      }
      //------------------------------------------------
      //------------------------------------------------
      public interface Player2 extends Player{
          void method3();
      }
      ```

    - ![image-20210430160912962](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210430160912962.png)

- 案例

  - ```java
    public interface Player {
        void show();
    }
    
    public interface Eater {
        void eatFood();
    }
    
    public class PiPiDuck implements Player,Eater{
        @Override
        public void eatFood() {
            System.out.println("干饭人~干饭魂");
        }
    
        @Override
        public void show() {
            System.out.println("表演干饭");
            eatFood();
        }
    }
    
    public class DuckTest {
        public static void main(String[] args) {
            PiPiDuck piPiDuck = new PiPiDuck();
            piPiDuck.show();
        }
    }
    
    ```

> 小结

继承：满足 Class A is Class B，新加入的类与已经存在的类之间满足be的关系，可以使用继承

抽象类：父类是一模板，子类继承父类。

实现：



> `面试:`接口与抽象类的区别？

