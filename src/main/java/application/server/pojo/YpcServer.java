package application.server.pojo;

/**
 * say some thing
 *
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
public class YpcServer {

    private String appPort ;
    private String appName;

    public String getAppPort() {
        return appPort;
    }

    public void setAppPort(String appPort) {
        this.appPort = appPort;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "YpcServer{" +
                "appPort='" + appPort + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}
