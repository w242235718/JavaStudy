# 数组

### review

#### 细节

- 变量作用域

  变量声明在那个方法体（花括号）内，该（方法体）花括号内的区间就属于改变量的作用域。



### Content

#### 1.数组概念

1. 数组：一组相同数据类型的集合
2. 数组是引用数据类型，数组元素可以是任意类型



#### 2 数组特性

- 数组里面每一个数组元素类型必须一致

  ```java
  //例外
  //char--->int (char类型会转换成ASCII码存入到int类型中)
  //'a'===>97
   int[] e={'a','b'};
  ```

- 数组长度是固定的，一旦确定不能改变

- 数组的内存空间是连续的，查询性能比较高。（数组末尾插入元素效率较高）





#### 3.数组的使用

##### 3.1 声明方式

```java
//方式一
int a[];
//方式一
int[] b;
```

##### 3.2 赋值

```java
//方式一
String[] b=new int[6];
b[0] ="";

//方式二
String[] b;
b=new String[]{"","","",""}

//方式三
String[] b={"","","","","",""}

```

##### 3.3 常用属性&方法

###### 属性

- length

  ````java
  int[] e={'a','b'};
  int length=e.length;
  ````

###### 方法

**Class**: Arrays

| **Modifier** | **Type** | **Method**           | **Description **                                             |
| ------------ | -------- | -------------------- | ------------------------------------------------------------ |
| `static `    | String   | toString(Object[] a) | Returns a string representation of the contents of the specified array. |
| static       | T[]      | copyOf               |                                                              |
| static       | booean   | equals               |                                                              |
| static       | void     | Sort                 |                                                              |

- toString

  ```java
  StringBuilder b = new StringBuilder();
  b.append('[');
  for (int i = 0; ; i++) {
      b.append(a[i]);
      if (i == iMax) //索引下标==数组长度
          return b.append(']').toString();
      b.append(", ");
  }
  
  ```

- copyOf

- equals

- Sort

```java
private static void demo6() {
    int[] nums={1,342,344,23543,322,522,123};
    int[] nums2 = Arrays.copyOf(nums, nums.length);
    boolean isEquals = Arrays.equals(nums, nums2);
    System.out.println(isEquals);

    Arrays.sort(nums2);
    System.out.println(Arrays.toString(nums2));

}
```



#### 4 数组扩容与删除

待补充



#### 5 二维数组及高维数组

1. 未初始化(取值)

   ```java
   //int[] 数组为引用类型
   int[][] arr=new int[10][];
   //取出二维数组第2个元素中的第一个一维数组
   System.out.println(arr[1][0])//null
   ```

2. 案例

   ```java
    private static void test2() {
           System.out.println("请输入班级个数");
           int classNum=input.nextInt();
   
           double[][] classScore=new double[classNum][];
           for (int i = 0; i < classScore.length; i++) {
               System.out.println("请输入"+(i+1)+"班人数:");
               int num = input.nextInt();
               classScore[i]=new double[num];
               for (int j = 0; j < classScore[i].length; j++) {
                   System.out.println("请输入"+(j+1)+"号学生成绩:");
                   classScore[i][j]= input.nextDouble();
               }
               System.out.println(Arrays.toString(classScore[i]));
           }
   
       }
   ```

#### 6 数组排序

##### 6.1 冒泡排序

​	

```java
private static void bubble(int[] arr) {
    int temp;
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length - i-1; j++) {
            if ( arr[j]>arr[j + 1]) {
                 temp=arr[j];
                 arr[j]=arr[j+1];
                 arr[j+1]=temp;
            }
            System.out.println(Arrays.toString(arr));
        }
    }


}
```

##### 6.2 选择排序

​	

```java
private static void selectSort(int arr[]){
    int minIndex;
    int temp=0;
    for (int i = 0; i < arr.length; i++) {
        minIndex=i;
        for (int j = i+1; j <arr.length ; j++) {
            if (arr[minIndex]>arr[j]){
                minIndex=j;
            }
        }
        if (minIndex!=0){
            temp=arr[minIndex];
            arr[minIndex]=arr[i];
            arr[i]=temp;
        }
        System.out.println(Arrays.toString(arr));
    }

}
```







## 方法

#### 1.1方法定义（签名）

- 为了实现某个功能，抽取出来的代码片段。
- 目的是为了提高代码的复用性。

> 语法

```java
 public static void add(int a,int b)
     
//解释
 public 权限修饰符(表示此方法能在那些地方能被访问到)
 static 一般修饰符(凡是static修饰的方法 表示静态方法)
 void 返回值类型
 add 方法名 （首字母小写_驼峰命名规范、见名知意)
 (int a,int b) 形式参数

```

#### 1.2 方法调用

```java
public static void main(String[] args){
    //method() 调用方法; 
    //括号里的20为实际参数,传递给该方法
     method1(20);
}
```

#### 1.3 值传递

- 传参引用类型地址,操作同一块 内存空间 

````java
//操作同一块堆的内存空间 
private static void test2() {
        int[] arr=new int[]{1,34,5,7,342};
        multArr(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void multArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i]*=2;
        }
    }
````

![image-20210427113747501](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210427113747501.png)

![image-20210427113809796](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210427113809796.png)



- 传参引用类型地址,返回不同内存空间 

```java

//操作不同的内存空间  
private static void test2() {
        int[] arr=new int[]{1,34,5,7,342};
        multArr(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void multArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i]*=2;
        }
        arr=Arrays.copyOf(arr,6);
    }

```

![image-20210427114344304](C:\Users\JAVASM\AppData\Roaming\Typora\typora-user-images\image-20210427114344304.png)





#### 1.4 方法重载 Overload

##### 1.4.1 概念/用途

​	为了方便使用者使用、实现相似功能

##### 1.4.2 语法

- 方法名相同,参数列表不同;
- 参数类型顺序不同
- 参数类型不同



##### 1.4.3 生成随机数 

要符合指定区间范围的数：

​	Math.random()*(最大值-最小值)+最小值



#####  1.4.4 递归(斐波那契数列)

```java
private static int fibonacci(int item){
    if (item==0 || item==1){
        return item;
    }
    return fibonacci(item-1) + fibonacci(item-2);
}
```





> 扩展 

`时间`：CPU片段时间

`空间`：内存使用率



  
