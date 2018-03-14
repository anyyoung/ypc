package application.exception;

/**
 * say some thing
 *
 * @author
 * @version v1.0
 * @date 3/14/2018
 */
public class YpcClientConnectionException extends YpcConfigException{

    public YpcClientConnectionException(String message) {
        super(message);
    }

    public YpcClientConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public YpcClientConnectionException(Throwable throwable) {
        super(throwable);
    }
}
