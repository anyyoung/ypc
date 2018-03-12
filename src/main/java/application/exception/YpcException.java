package application.exception;

/**
 * say some thing
 *
 * @version v1.0
 * @author
 * @date 3/9/2018
 */
public class YpcException extends RuntimeException {

    public YpcException(String message){
        super(message);
    }

    public YpcException(String message , Throwable throwable){
        super(message, throwable);
    }

    public YpcException(Throwable throwable){
        super(throwable.getMessage(),throwable);
    }

}
