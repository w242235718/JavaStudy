package miniCat.webApplication;



import miniCat.base.BaseServlet;
import miniCat.webApplication.http.MiniRequest;
import miniCat.webApplication.http.MiniResponse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class MiniCatApp {
    private static Map<String,Object> servletMap=new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            init();
            ServerSocket serverSocket = new ServerSocket(8088);
            System.out.println("The server start up!");

            //客户端信息
            Socket client = serverSocket.accept();
            System.out.println(client.getInetAddress().getHostAddress() +":"+ client.getPort());
            //获取Request
            MiniRequest request = new MiniRequest(client.getInputStream());
            //获取Response
            MiniResponse response = new MiniResponse(client.getOutputStream());
            //根据URL拿到实例并调用Servlet
            BaseServlet baseServlet = (BaseServlet) servletMap.get(request.getUrl());
            baseServlet.service(request,response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init() throws IOException {
        Properties pro = new Properties();
        pro.load(MiniCatApp.class.getClassLoader().getResourceAsStream("servlet.properties"));
        //配置文件指定的url和servlet
        pro.keySet().forEach(key -> {
            String URL = pro.getProperty(key.toString());
            String name = key.toString().replace("url", "className");
            String servletName = pro.getProperty(name);
            try {
            //反射获取Servlet实例
                BaseServlet baseServlet= ((BaseServlet) Class.forName(servletName).newInstance());
                servletMap.put(URL,baseServlet);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
    }
}
