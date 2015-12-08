package test;

/**
 * Created by hvpham on 11/11/2015.
 */
import ejb.DemoSessionBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Test {

    public static void main(String[] args) throws Exception {
        testRemoteEJB();
    }

    private static void testRemoteEJB() throws NamingException {
        final DemoSessionBean ejb = lookupRemoteEJB();
        String s = ejb.getHelloString();
        System.out.println(s);
    }

    private static DemoSessionBean lookupRemoteEJB() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,  "remote://localhost:4447");
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        // application username
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "testuser");
        // password
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "testuser123");

        final Context context = new InitialContext(jndiProperties);


        final String appName = "SampleEJB_ear_exploded";
        final String moduleName = "ejb";
        final String distinctName = "";
        final String beanName = "SampleBeanRemoteEJB";

        final String viewClassName = DemoSessionBean.class.getName();
        System.out.println("Looking EJB via JNDI ");
        System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

        return (DemoSessionBean) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
    }
}
