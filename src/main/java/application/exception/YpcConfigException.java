package application.exception;

/**
 * say some thing
 *
 * @version v1.0
 * @author
 * @date 3/9/2018
 */
public class YpcConfigException extends YpcException{

    public YpcConfigException(String message){
        super(message);
    }

    public YpcConfigException(String message , Throwable throwable){
        super(message , throwable);
    }

    public YpcConfigException(Throwable throwable){
        super(throwable.getMessage(), throwable);
    }

}
