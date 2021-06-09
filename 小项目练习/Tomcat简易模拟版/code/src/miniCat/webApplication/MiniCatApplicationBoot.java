package miniCat.webApplication;

import miniCat.base.BaseServlet;
import miniCat.webApplication.annotation.MiniWebScan;
import miniCat.webApplication.annotation.MiniWebServlet;
import miniCat.webApplication.http.MiniRequest;
import miniCat.webApplication.http.MiniResponse;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现类
 */
public class MiniCatApplicationBoot {
    private static Class clazz;
    private static Map<String, BaseServlet> servletMap = new ConcurrentHashMap<>();

    public static void run(Class aClazz) {
        clazz = aClazz;
        Socket client=null;
        try {
            scanPackage();
            ServerSocket serverSocket = new ServerSocket(8088);
            while (true) {
                client = serverSocket.accept();
                MiniRequest request = new MiniRequest(client.getInputStream());
                MiniResponse response = new MiniResponse(client.getOutputStream());
                String url = request.getUrl();

                if (servletMap.get(url)==null) {
//                    String url = request.getUrl();
                  response.getWriter().write(MiniResponse.getHead().getBytes());
                  response.getWriter().write("<h1>粗错啦~！</h1>".getBytes());
                  client.close();
                  continue;
                }
                BaseServlet baseServlet = servletMap.get(url);
                baseServlet.service(request, response);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void scanPackage() throws IOException {
        MiniWebScan webScan = (MiniWebScan) clazz.getAnnotation(MiniWebScan.class);
        //miniCat.xx
        String packageName = webScan.value();
        String dirName = packageName.replace(".", "/");
        //当前线程类加载器拿到URL资源
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(dirName);
        //遍历资源拿到包路径
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if ("file".equals(url.getProtocol())) {
                String fileName = URLDecoder.decode(url.getFile(), "UTF-8");
                findClass(packageName, fileName);
            }
//            System.out.println(url.getFile());
//            System.out.println(url.getProtocol());
        }
    }

    private static void findClass(String packageName, String fileName) {
        //miniCat包路径
        File file = new File(fileName);
        //base book 等包
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                //目录
//                System.out.println(f.getName());
//                System.out.println(f.getAbsolutePath());
                findClass(packageName + "." + f.getName(), f.getAbsolutePath());
            } else {
                //文件
                String clazzName = f.getName();
                String mergedClazzName = packageName + "." + clazzName.substring(0, clazzName.length() - 6);
//                System.out.println(mergedClazzName);
                //拿到Class对象
                try {
                    Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(mergedClazzName);
                    if (aClass.isAnnotationPresent(MiniWebServlet.class)) {
                        MiniWebServlet servletAnnotation = (MiniWebServlet) aClass.getAnnotation(MiniWebServlet.class);
                        String url = servletAnnotation.value();
                        if (!url.isEmpty() && url.length() > 0) {
                            servletMap.put(url, (BaseServlet) aClass.newInstance());
//                            System.out.println(servletMap.get(url));
                        }
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
//                System.out.println(mergedClazzName);
            }
        }
    }

}
