package miniCat.book;

import miniCat.base.BaseServlet;
import miniCat.webApplication.annotation.MiniWebServlet;
import miniCat.webApplication.http.MiniRequest;
import miniCat.webApplication.http.MiniResponse;

import java.io.IOException;
import java.io.OutputStream;
@MiniWebServlet("/hlsp")
public class BookServlet extends BaseServlet {

    @Override
    protected void doGet(MiniRequest request, MiniResponse response) {
        try {
            OutputStream writer = response.getWriter();
            writer.write(MiniResponse.getHead().getBytes());
            writer.write("<h1>黄老师</h1>".getBytes());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(MiniRequest request, MiniResponse response) {

    }
}
