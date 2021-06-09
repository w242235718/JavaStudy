package miniCat.webApplication;

import miniCat.webApplication.annotation.MiniWebScan;

@MiniWebScan("miniCat")
public class MiniCatWebApplication {
    public static void main(String[] args) {
        MiniCatApplicationBoot.run(MiniCatWebApplication.class);
    }
}
