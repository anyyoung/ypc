package application.server.callback;

import application.zookeeper.ZkConst;

/**
 * callBack的返回值信息
 *
 * @author
 * @version v1.0
 * @date 3/14/2018
 */
public class Result {

    private String serilNo;
    private Object result;
    private Exception exception;
    private String resultMsg;
    private String resultCode = ZkConst.SUCCESS_CODE;
    private String protocol;

    public Result(String serilNo, Object result, Exception exception, String resultMsg, String resultCode,
            String protocol) {
        super();
        this.result = result;
        this.exception = exception;
        this.resultMsg = resultMsg;
        this.resultCode = resultCode;
    }

    public Result(Object result) {
        super();
        this.result = result;
    }

    public String getSerilNo() {
        return serilNo;
    }

    public void setSerilNo(String serilNo) {
        this.serilNo = serilNo;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
