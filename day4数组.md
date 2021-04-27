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