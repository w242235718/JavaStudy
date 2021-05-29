

## Review



> 面试题：如何把字符串放入到字符串常量池中？

```java
//将字符串放入到 字符串常量池中
String str="hello";
String str1=str+"java";
str1.intern()
```



#### 转义字符

```java
String path="\\";
System.out.println(path.length);//length=1

String path="\\\\";
System.out.println(path.length);//length=2

```









## 正则表达式

Regular Express

#### 作用：

- 匹配用户输入是否符合指定规则



##### 正则符号：

【*】匹配零或多次

【+】匹配(至少)一次或多次

【？】匹配零次或一次

【{n}】匹配n次

【{n,}】至少匹配n次

【{n,m}】 至少匹配n次，最多匹配m次



【\d】匹配数字字符

【\n】换行

【\f】换页

【\s】匹配任何空白符,制表符、换页符

【\w】匹配A-Za-z0-9_

【.】可以匹配【/r】回车【/n】换行 之外的任何字符

【[a,b,c]】 匹配[a,b,c]任意一个字符

【 [ ^a,b,c ]】 匹配非a,b,c的任意一个字符



【^】表示开头

【$】表示结束



##### 分组信息

```java
//获取匹配后，指定信息
Pattern p=Pattern.compile("");
Matcher m=p.matcher(str);

if(m.matches()){
    //0代表所有的组
    System.out.println(m.group(1));
}
```

##### 案例

```java
//匹配email
```







## 包装类



#### 作用

- 让基本数据类型具有对象的性质，丰富了基本数据类型的操作

  

| 基本数据类型 | 包装类型  |
| ------------ | --------- |
| byte         | Byte      |
| short        | Short     |
| int          | Integer   |
| long         | Long      |
| char         | Character |
| float        | Float     |
| double       | Double    |
| boolean      | Boolean   |

### Integer

#### 常用方法

1、parseInt()

```java
public static int parseInt(String s, int radix)
            throws NumberFormatException
{
    /*
     * WARNING: This method may be invoked early during VM initialization
     * before IntegerCache is initialized. Care must be taken to not use
     * the valueOf method.
     */

    if (s == null) {
        throw new NumberFormatException("null");
    }

    if (radix < Character.MIN_RADIX) {
        throw new NumberFormatException("radix " + radix +
                                        " less than Character.MIN_RADIX");
    }

    if (radix > Character.MAX_RADIX) {
        throw new NumberFormatException("radix " + radix +
                                        " greater than Character.MAX_RADIX");
    }

    int result = 0;
    boolean negative = false;
    int i = 0, len = s.length();
    int limit = -Integer.MAX_VALUE;
    int multmin;
    int digit;

    if (len > 0) {
        char firstChar = s.charAt(0);
        if (firstChar < '0') { // Possible leading "+" or "-"
            if (firstChar == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
            } else if (firstChar != '+')
                throw NumberFormatException.forInputString(s);

            if (len == 1) // Cannot have lone "+" or "-"
                throw NumberFormatException.forInputString(s);
            i++;
        }
        multmin = limit / radix;
        while (i < len) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            digit = Character.digit(s.charAt(i++),radix);
            if (digit < 0) {
                throw NumberFormatException.forInputString(s);
            }
            if (result < multmin) {
                throw NumberFormatException.forInputString(s);
            }
            result *= radix;
            if (result < limit + digit) {
                throw NumberFormatException.forInputString(s);
            }
            result -= digit;
        }
    } else {
        throw NumberFormatException.forInputString(s);
    }
    return negative ? result : -result;
}
```

2、valueOf()





#### 赋值

1. 值不可改变

```java
/**
     * The value of the {@code Integer}.
     * 存储值
     * @serial
     */
    private final int value;
```



#### Integer缓存池

```java
private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer cache[];

    static {
        // high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
            sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        if (integerCacheHighPropValue != null) {
            try {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
            } catch( NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;
		
        //cache 127-(-128)+1 ==>256
        cache = new Integer[(high - low) + 1];
        // j=-128
        int j = low;

        for(int k = 0; k < cache.length; k++)
        	//k从0开始, -128往上递增赋值给cache[k]
            cache[k] = new Integer(j++);

        // range [-128, 127] must be interned (JLS7 5.1.7)
        assert IntegerCache.high >= 127;
    }

    private IntegerCache() {}
}
```

#### 自动装箱

 int--->Integer

```java
public static Integer valueOf(int i) {
    //索引在-128~127之间
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        // 索引 10+(--128)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    //不满足直接new
    return new Integer(i);
}
```

#### 自动拆箱

Integer--->int

```java
//??
public int intValue() {
    return value;
}

public static int parseInt(String s, int radix){}
```





#### 注意事项

- 包装对象的值 都是不可变的
- Character缓存池 缓存的是所有的ASCII码值



### Character

#### 常用方法

###### 	1、toLowerCase()

```java
public static char toUpperCase(char ch) {
    return (char)toUpperCase((int)ch);
}
```

###### 	2、isDigit()

```java
public static boolean isDigit(char ch) {
    return isDigit((int)ch);
}

public static boolean isDigit(int codePoint) {
    return getType(codePoint) == Character.DECIMAL_DIGIT_NUMBER;
}
```

###### 	3、isLetter()

```java

```

###### 	4、isUpperCase() & isLowerCase()

````java

````

#### Character缓存池

```java
private static class CharacterCache {
    private CharacterCache(){}

    static final Character cache[] = new Character[127 + 1];

    static {
        for (int i = 0; i < cache.length; i++)
 			//存入ASCII码 
            cache[i] = new Character((char)i);
    }
}
```





## 二进制 相关运算



#### 位运算

```java
public static void method(){
    int m=8;
    // 0000 1000
    // 0100 0000 
    // 向左移动三位,相当于乘2的三次方
    System.out.println(m<<2);
}

public static void method(){
    int m=7;
    // 0000 0111
    // 0000 0001 
    // 向右移动两位,相当于除2的平方
    System.out.println(m>>2);
}

public static void method(){
    int m=7;
    // 0000 0111
    // 0000 0001 
    // 向左移动两位,相当于乘2的三次方
    System.out.println(m>>>2);
}

public static void method(){
    int m=16;
    //两边同时为1 才为1
    //0001 0000
    //0000 0111
    //---------
    //0000 0000
    System.out.println(n&7);
}

public static void method(){
    int m=16;
    //一边同为1 就为1
    //0001 0000
    //0000 0111
    //---------
    //0001 0111
    System.out.println(n|7);
}

public static void method(){
    int m=16;
    //相同为0，不同为1
    //0001 0000
    //0000 0111
    //---------
    //0001 0111
    System.out.println(n^7);
}
```



## Math类

###### sqrt()

开根号

###### ceil()

向上取最小的整数

###### floor()

向下取最大的整数

###### round()

四舍五入的值

