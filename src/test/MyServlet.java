package test;

import ejb.DemoSessionBean;
import ejb.DemoSessionInterface;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by hvpham on 11/11/2015.
 */
//@ManagedBean
@javax.servlet.annotation.WebServlet(name = "MyServlet", urlPatterns={"/hi"})
public class MyServlet extends HttpServlet {

    /**
     *  The type has to be an interface (DemoSessionInterface), NOT a class (DemoSessionBean). Strange!
     *  If there is only one class that implements DemoSessionInterface, then one can just write:
     *     @EJB
     *     DemoSessionInterface bean;
     *  However, if there are more than one class that implement DemoSessionInterface, then each class should be
     *  give a name, such as @Stateless(name = "MrBean"), and then here, we must specify the beanName for @EJB to
     *  disambiguate as follows:
     *     @EJB(beanName = "MrBean")
     *     DemoSessionInterface bean;
     *  The beanName is used to identify what implementing class to create an instance for.
     */
    @EJB(beanName = "MrBean")
    DemoSessionInterface bean;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + bean.getHelloString() + "</h1>");
    }
}
