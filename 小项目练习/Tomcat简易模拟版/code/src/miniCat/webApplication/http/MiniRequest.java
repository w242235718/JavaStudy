package miniCat.webApplication.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MiniRequest {
    private String method;
    private String url;

    public MiniRequest(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String[] str = bufferedReader.readLine().split(" ");
        method=str[0];
        url=str[1];
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
