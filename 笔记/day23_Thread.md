## Review

- .class字节码文件在方法区/堆区以什么方式存在？

  xxx.class 获取到 Class对象

- 获取到Class对象三种方式？

  

> 线程的创建方式有哪儿三种

- 实现Runable
- 继承Thread
- 继承FutureTask，实现Callable接口（匿名内部类）



## 线程(2)



#### 2.1 线程安全

- 多线程环境下，多线程可以共享进程资源（CPU、内存、硬盘、网络）
- JVM内存模型中，当前线程栈空间是某个线程的栈空间（工作空间），栈空间是独立的、私密的其他线程无法访问。 堆空间和方法区是共享的，多个线程可以同时访问，此时会涉及到数据安全问题（多个线程同时修改共享数据）。



###### 多个线程同时操作共享对象

- 取出balance的值
- 对balance进行加的操作
  - 线程A 操作
  - 线程B 操作
- 刷新回主存
  - 线程B 存回
  - 线程A 存回



##### 2.2.1 如何解决线程同步

- synchronized底层同步逻辑由JVM实现
- synchronized 可见性(**锁的状态对于其他任何线程都是可见**)

-  对象锁：每个对象都有一把锁（唯一的一把锁），任一时刻对象锁只会有被某一个线程所拥有
- (this) 表示线程执行同步代码块需要获取那个对象的对象锁

- synchronized 如果声明在方法声明处，表示线程必须先获取 当前对象的对象锁 才能执行此方法

- synchronized 互斥锁（排它锁），独享锁，可重入锁（递归锁，不需要重复获取）、非公平锁(后来先的)

  先来先得(公平锁)

````java
//可重入锁
private static void method2() {
    Object o = new Object();
    new Thread(()->{
        synchronized (o){
            System.out.println("获取到O对象锁");
            synchronized (o){
                System.out.println("直接进入同步代码块");
            }
        }
    }).start();
}
````

- synchronized用在静态方法上/静态方法体内部,synchronized获取的是 当前CLass对象的对象锁

  ```java
  public static synchronized void test(){
      System.out.println("test");
  }
  
  public static void test2(){
      synchronized (BalanceAcoount.class){
          System.out.println("test2");
      }
  }
  ```

  

  

  

#### 线程·锁释放

- 锁在方法声明处：执行完此方法后才会释放锁

- 锁在同步代码块：执行完同步代码块后会立马释放锁

- Sleep不会释放锁

  ````java
  private static void method4() {
      Object o = new Object();
      new Thread(()->{
          synchronized (o){
              System.out.println("A OOOOOOOOOOO!");
              try {
                  TimeUnit.SECONDS.sleep(2L);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      },"A").start();
  
      try {
          TimeUnit.MILLISECONDS.sleep(20L);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  
      new Thread(()->{
          synchronized (o){
              System.out.println("b OOOOOOOOOOOOOO!");
          }
      },"B").start();
  }
  ````

  ![image-20210518113233842](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210518113233842.png)

- 出现异常的时候，锁会释放吗？

  - 用的synchronized锁,出现异常，锁会自动释放

  ```java
  private static void method5() {
      Object o = new Object();
      new Thread(()->{
          synchronized (o){
              System.out.println("A OOOOOOOOOOO!");
              System.out.println(2/0);
          }
      },"A").start();
  
      try {
          TimeUnit.MILLISECONDS.sleep(20L);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  
      new Thread(()->{
          synchronized (o){
              System.out.println("b OOOOOOOOOOOOOO!");
          }
      },"B").start();
  }
  
  ```

  ![image-20210518113415576](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210518113415576.png)



#### 2.3 线程同步·使用Lock

java.util.concu 高并发相关的类

- JUC中的LOCk是代码层面的实现同步
- synchronized在1.5之前是重量级锁，性能较低；Lock性能较好
- synchronized是非公平锁（吞吐量较好）



##### ReentrantLock

- 默认是非公平锁，构造方法传参为TRUE 则是公平锁

- ReentrantLock中出现异常，会释放锁吗？

  **不会**

  ```java
  //不会
  private static void method() {
      new Thread(()->{
          for (int i = 0; i < 1000; i++) {
              reentrantLock.lock();
              a++;
              System.out.println(1/0);
              reentrantLock.unlock();
          }
          System.out.println("AAAAAAAAAAAAAAAAAA");
      },"A").start();
      try {
          TimeUnit.MILLISECONDS.sleep(200l);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      new Thread(()->{
          for (int i = 0; i < 1000; i++) {
              reentrantLock.lock();
              a--;
              reentrantLock.unlock();
          }
          System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBB");
      },"B").start();
      System.out.println("a="+a);
  
  }
  ```

  ![image-20210518154153050](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210518154153050.png)
  - 解决方案

  ````java
  private static void method() {
  new Thread(()->{
      for (int i = 0; i < 1000; i++) {
          reentrantLock.lock();
          try {
              a++;
              System.out.println(1/0);
          } finally {
              reentrantLock.unlock();
          }
      }
      System.out.println("AAAAAAAAAAAAAAAAAA");
  },"A").start();
  
      try {
          TimeUnit.SECONDS.sleep(2l);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  
      new Thread(()->{
          for (int i = 0; i < 1000; i++) {
              reentrantLock.lock();
              a--;
              reentrantLock.unlock();
          }
          System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBB");
      },"B").start();
  
      System.out.println("a="+a);
  }
  ````

- Reentrantlock可重入性

  - Lock和UnLock的次数必须匹配

  ```java
  private static void method1() {
      new Thread(()->{
          reentrantLock.lock();
          System.out.println("上锁成功");
          try {
              reentrantLock.lock();
              System.out.println("继续执行");
          }finally {
              reentrantLock.unlock();
              reentrantLock.unlock();
          }
      }).start();
  }
  ```



#### 2.4 死锁

```java
private static void method1() {
    new Thread(()->{
        lock1.lock();
        System.out.println("A获取Lock成功");
        try {
            TimeUnit.MILLISECONDS.sleep(200L);
            lock2.lock();
            System.out.println("A获取Lock2成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }).start();
    new Thread(()->{
        lock2.lock();
        System.out.println("B获取Lock成功");
        try {
            lock1.lock();
            System.out.println("B获取Lock2成功");
        }finally {
            lock1.unlock();
            lock2.unlock();
        }
    }).start();
}
```

- 解决死锁的方法
  - tryLock()
  - tryLock(timeout,unit);



- AtomicInteger

  - > CAS (CompareAndSwap) 比较并交换

    CAS是CPU同并原语（原子性的操作，中间不会被打断）

    ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200322202802595.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L25yc2MyNzI0MjAxOTk=,size_16,color_FFFFFF,t_70)

    ```java
    /*
    * Object var1 当前对象
    * long var2	内存地址
    * int var4	每次自增值
    *
    *
    */
    public final int getAndAddInt(Object var1, long var2, int var4) {
    	//存储int最新值
        int var5;
        do {
        	//获取int最新值
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
    	//返回从内存地址读到的旧值
        return var5;
    }
    
    /*
    *	boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
    *	var1 表示要操作对象
    *	var2 偏移量内存地址
    *	var4 期望值
    *	var5 修改值
    */
    ```

    

    







预习：



生产者模式

Volatile

线程池

单例模式

