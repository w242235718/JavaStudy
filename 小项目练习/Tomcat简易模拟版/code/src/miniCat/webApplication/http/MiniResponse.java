package miniCat.webApplication.http;

import java.io.OutputStream;

public class MiniResponse {
    private OutputStream writer;
    private static  String head="HTTP/1.1 200 OK\r\n"+"Content-Type:text/html;charset=utf-8\r\n\r\n";
    public MiniResponse(OutputStream outputStream) {
        this.writer=outputStream;
    }

    public OutputStream getWriter() {
        return writer;
    }

    public static String getHead() {
        return head;
    }
}
