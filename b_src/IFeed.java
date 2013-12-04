/*
 * File: IFeed.java
 * Author: Brent Parish
 * Create Date: 20/07/2013
 * Summary:Feed Interface
 */
package b_src;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public interface IFeed
{
    public void fetch(ObjectMapper mapper) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException;
    public String next();
    public Integer count();
    public void clear();
    public Consumer getConsumer();
    public void updateParams(String a_searchVal);
}
