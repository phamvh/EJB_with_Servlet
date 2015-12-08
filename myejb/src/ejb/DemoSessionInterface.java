package ejb;

import javax.ejb.Local;

/**
 * Created by hvpham on 11/11/2015.
 */
@Local
public interface DemoSessionInterface {
    public String getHelloString();
}
