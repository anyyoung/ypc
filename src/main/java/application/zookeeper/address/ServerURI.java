package application.zookeeper.address;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 当前节点的远程访问地址
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
public class ServerURI implements Serializable{

    private String host;
    private int port;
    private static final long WAIT_TIME= 3000L;
    private long timeout;

    public ServerURI(String host , int port , long timeout){
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    private transient Lock lock = new ReentrantLock();
    private transient CountDownLatch countDownLatch = new CountDownLatch(1);

    public void await() throws InterruptedException {
        timeout = (timeout == 0 ? WAIT_TIME:timeout);
        countDownLatch.await(timeout, TimeUnit.MILLISECONDS);
    }

    public void countDown(){
        countDownLatch.countDown();
    }

    public boolean tryLock() throws InterruptedException {
        timeout = (timeout == 0 ? WAIT_TIME:timeout);
        return lock.tryLock(timeout , TimeUnit.MILLISECONDS);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static long getWaitTime() {
        return WAIT_TIME;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public String toString() {
        return "ClientURI{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", timeout=" + timeout +
                ", lock=" + lock +
                '}';
    }
}
