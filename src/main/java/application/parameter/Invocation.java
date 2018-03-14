package application.parameter;

import application.protocol.Protocol;
import application.zookeeper.address.ServerURI;

import java.io.Serializable;
import java.util.Arrays;

/**
 * say some thing
 *
 * @author
 * @version v1.0
 * @date 3/14/2018
 */
public class Invocation implements Serializable{

    private static final long serialVersionUID = 3216617387394049855L;
    private Class<?> interfaceClass;
    private String className;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramterTypes;
    private String protocol = Protocol.PROTO_BUF;
    private ServerURI serverURI;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Class<?>[] getParamterTypes() {
        return paramterTypes;
    }

    public void setParamterTypes(Class<?>[] paramterTypes) {
        this.paramterTypes = paramterTypes;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public ServerURI getServerURI() {
        return serverURI;
    }

    public void setServerURI(ServerURI serverURI) {
        this.serverURI = serverURI;
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "interfaceClass=" + interfaceClass +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                ", paramterTypes=" + Arrays.toString(paramterTypes) +
                ", protocol='" + protocol + '\'' +
                ", serverURI=" + serverURI +
                '}';
    }
}
