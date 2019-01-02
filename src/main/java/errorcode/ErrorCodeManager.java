package errorcode;

import com.google.common.collect.Maps;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentMap;

public class ErrorCodeManager {

    private ResourceBundle resourceBundle;

    private static ConcurrentMap<String,ErrorCodeManager> managers = Maps.newConcurrentMap();

    public ErrorCodeManager(String bundleName) {

        if (resourceBundle == null) {
            resourceBundle = getResourceBundle(bundleName);
        }
    }

    public String getString(String errorCode) {

        try {
            return new String(resourceBundle.getString(errorCode).getBytes(), Charset.forName("utf-8"));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取ResourceBundle
     * @param path
     * @return
     */
    private ResourceBundle getResourceBundle(String path) {
        try {
            return ResourceBundle.getBundle(path, Locale.getDefault());
        } catch (MissingResourceException e) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            return ResourceBundle.getBundle(path,Locale.getDefault(),classloader);
        }
    }

    public static  ErrorCodeManager getErrorCodeManager(String bundleName) {
        return managers.computeIfAbsent(bundleName,t -> new ErrorCodeManager(t));
    }
}
