## Review

#### IO Review

输入流、输出流

==>字节流

​	数据单位：byte

==>字符流

​	数据单位：char



- 字节流
  - 类
    - FileInputStream/FileOutputStream
    - BufferInputStream/BufferOutputStream
- 字符流
  - 类
    - FileReader/FileWriter
    - BufferedReader/BufferedWriter





## IO

#### 转换流

- 字节流和字符流之间的桥梁

- 输入转换流：InputStreamReader

  - 字节输入流--->字符输入流

  ```java
  //InputStreamReader获取用户输入值
  //输入值 和指定类型比较,不满足继续循环
  //满足返回指定值
  ```

- 输出转换流：OutputStreamReader

  - 字符输出流--->字节输出流

#### 序列化流

- 序列化：把内存中的对象存储到 存储介质中(磁盘)

  ```java
  //ObjectOutPutStream  序列化
  private static void method3() {
      try(ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(new File("./resources/obj.txt")))) {
          outputStream.writeObject(new Stu(1,"ww",new Car()));
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  ```

- 反序列化：硬盘中的数据 还原到内存中

  ```java
  //ObjectInputStream   反序列化
  private static void method4() {
      try(ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(new File("./resources/obj.txt")))) {
          Object o = inputStream.readObject();
          if (o instanceof Stu){
              Stu s = (Stu) o;
              System.out.println(s);
          }
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
  }
  ```

  

````java
//ObjectInputStream   反序列化
//ObjectOutPutStream  序列化
````

标记接口，给JVM看。

- 类型要实现Serilzable接口，并且内部的成员变量也需要实现其接口
- 序列化是JVM底层实现
- transient修饰的成员变量，表示序列化过程中跳过此成员变量



问题：

```java
java.io.EOFException===>endOfFileException
    1.读到文件末尾
    2.非正常退出
```

#### 基本数据处理流

DataOutputStream

- 写入的顺序和读取的顺序保持一致

#### Properties 

```java
private static void method5() {
    Properties properties=new Properties();
    try {
        properties.load(new FileInputStream("resources/test.properties"));
        System.out.println(properties.getProperty("username"));
        System.out.println(properties.getProperty("password"));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```



.class====>类加载器====>内存

```java
 properties.load(FileTest.class.getClassloader().getResourceAsStream());
```

## 泛型

#### 泛型(generic)  

​	泛型就是一个占位符，泛型符号在本类中可以作已知类型存在。而且泛型符号名称和个数不做限制。作用是为了约束数据类型。

- 泛型能够用在类、接口、方法声明处
- 泛型符号都作为引用类型来用，如果不指定泛型符号，统统当做Object类看待。
- 只是在编译期间起作用，运行期间没有作用



##### 泛型类

- 泛型符号用在类的声明处

- 在实例化时指明泛型类型
- 在本类中做已知存在



- 普通方法可以使用泛型类的泛型符号；静态方法不能用泛型类的泛型符号

![image-20210514163433920](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210514163433920.png)

![image-20210514163503560](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210514163503560.png)



##### 泛型 接口

​	泛型占位符定义在接口上

````java
//泛型 接口
public interface geMap<K,V> {
    void putKey(K k);
    void putValue(V v);
}

//泛型 实现类
public class geMapImpl implements geMap<Integer,String>{
    @Override
    public void putKey(Integer integer) {

    }

    @Override
    public void putValue(String s) {

    }
}

// 泛型 实现类
public class geMapImpl2<K,V> implements geMap<Integer,String>{
    private K k;
    private V v;

    @Override
    public void putKey(Integer integer) {

    }

    @Override
    public void putValue(String s) {

    }
}
````

##### 泛型 方法

​	泛型符号定义在方法声明(签名)处

​	根据实参确定 泛型的数据类型

```java
//实例方法
public <T> T getKey(T t){
    return t;
}
//静态方法
public static <E> void getElements(E e){
    
}
```

##### 泛型 上下限

###### 上限

````java
//? 通配符 表示不确定 
// 泛型上限
// List<? extends Animal> 只要是继承Animal的子类都可以放入
public void method1(List<? extends Animal>)
````

```java
private static void method1() {
    ArrayList<Dog> dogs=new ArrayList<>();
    dogs.add(new Dog());
    dogs.add(new Dog());
    dogs.add(new Dog());

    feeder feeder = new feeder();
    feeder.feed(dogs);
}
```

![image-20210514161027322](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210514161027322.png)



###### 下限

```java

//泛型下限
//List<? super Animal> animal 只能放入Animal的父类
public void method1(List<? super Animal> animal)
```

```java
private static void method2() {
    ArrayList<Cat> cats=new ArrayList<>();
    cats.add(new Cat());
    cats.add(new Cat());
    cats.add(new Cat());
    
    feeder feeder = new feeder();
    feeder.feed2(cats);
}
```

##### 泛型 擦除

```java
List<Dog> list1=new ArrayList<>();
List list2=new ArrayList();

list2=list1;	
```

