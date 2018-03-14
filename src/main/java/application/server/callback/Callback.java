package application.server.callback;

/**
 * say some thing
 *
 * @author
 * @version v1.0
 * @date 3/14/2018
 */
public interface Callback {

    public void putResult(Result result);
    public Result getResult();
}
