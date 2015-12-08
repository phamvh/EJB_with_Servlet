package ejb;

import javax.ejb.Stateless;

/**
 * Created by hvpham on 11/11/2015.
 */
@Stateless(name = "MrBean")
public class DemoSessionBean implements DemoSessionInterface {
    public DemoSessionBean() {
    }

    @Override
    public String getHelloString(){
        return "Hello there; How are you today?";
    }
}
