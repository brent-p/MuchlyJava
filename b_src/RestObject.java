/*
 * File: Engine.java
 * Author: Brent Parish 
 * Create Date: 20/07/2013
 * Summary: Handles the details needed to perform Rest query e.g. URL, Arguments etc
 *          
 */

package b_src;

import java.util.LinkedList;

/**
 *
 * @author Brent
 */
public class RestObject 
{
    private LinkedList<String> args;
    private LinkedList<String> vals;
    private String url;

    public RestObject()
    {
        url = "";
        args = new LinkedList<String>();
        vals = new LinkedList<String>();        
    }
    
    public RestObject(String a_url,LinkedList<String> a_args, LinkedList<String> a_vals)
    {
        url = a_url;
        args = a_args;
        vals = a_vals;
    }
    /**
     * @return the args
     */
    public LinkedList<String> getArgs() 
    {
        return args;
    }

    /**
     * @param args the args to set
     */
    public void setArgs(LinkedList<String> args) 
    {
        this.args = args;
    }
    
    public void addArg(String arg)
    {
        this.args.add(arg);
    }

    /**
     * @return the vals
     */
    public LinkedList<String> getVals() {
        return vals;
    }

    /**
     * @param vals the vals to set
     */
    public void setVals(LinkedList<String> vals) {
        this.vals = vals;
    }
    
     public void addVal(String val)
    {
        this.vals.add(val);
    }
    
    /**
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param URL the URL to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
