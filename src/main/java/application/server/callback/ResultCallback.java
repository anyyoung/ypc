package application.server.callback;

import application.exception.YpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * say some thing
 *
 * @author
 * @version v1.0
 * @date 3/14/2018
 */
public class ResultCallback implements Callback{

    public long timeout;
    private String className;

    private Result result;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Logger LOGGER = LoggerFactory.getLogger(ResultCallback.class);

    public ResultCallback(long timeout, String className){
        super();
        this.timeout = timeout;
        this.className = className;
    }

    @Override
    public void putResult(Result result) {
        try{
            if (this.result == null){
                lock.lock();
                this.result = result;
                condition.signal();
            }
        }catch (Exception e){
            LOGGER.error("get put callback failed : ",e);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public Result getResult() {
        if (this.result == null){
            lock.lock();
            try{
                boolean isTimeOut = condition.await(timeout, TimeUnit.MILLISECONDS);
                if (isTimeOut){
                    return this.result;
                }else {
                    throw new YpcException("get call back timeout! "+className);
                }
            } catch (InterruptedException e) {
                LOGGER.error("get callback occur Exception :"+className, e);
            }finally {
                lock.unlock();
            }
        }
        return result;
    }
}
