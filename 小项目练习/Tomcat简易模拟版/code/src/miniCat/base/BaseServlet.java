package miniCat.base;

import miniCat.webApplication.http.MiniRequest;
import miniCat.webApplication.http.MiniResponse;



public abstract class BaseServlet {
    public void service(MiniRequest request, MiniResponse response){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        }else if ("POST".equalsIgnoreCase(request.getMethod())){
            doPost(request, response);
        }

    }
    protected abstract void doGet(MiniRequest request, MiniResponse response);
    protected abstract void doPost(MiniRequest request, MiniResponse response);
}
