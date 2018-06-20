package classLoader;

import java.net.URL;
import java.net.URLClassLoader;

public class GetClasspathUrl {
    private void printClassPaths() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }
    }

    private void projectTargetClassesPath() {
        // 打印当前项目target classes的绝对路径
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
    }
}
