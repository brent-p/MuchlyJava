/*
 * File: Engine.java
 * Author: Brent Parish 
 * Create Date: 20/07/2013
 * Summary: Handles retrieval of posts from feeds based on the 
 *          users search
 */
package b_src;

//Import Statements------------------------------------------------------------!
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import java.util.*;
import java.io.IOException;

public class Engine implements java.io.Serializable
{
    
    //Instance Variables-------------------------------------------------------!
    private LinkedList<String> results;
    private String searchVal;  
    private String previousSearchVal;
    private ObjectMapper mapper;
    private InstagramFeed instagram;
    private TwitterFeed twitter;
    LinkedList<String> stringList;
    ListIterator<String> stringListIterator;
    LinkedList<IFeed> feedList;    
    
    //Zero Parameter Constructer
    public Engine()
        {
            //Initialise instance Variables
            results = new LinkedList<>();
            searchVal = "";
            previousSearchVal = "";
            LinkedList<String> args = new LinkedList<>();
            LinkedList<String> vals = new LinkedList<>();
            mapper = new ObjectMapper();
            stringList = new LinkedList<>();     
            
            //Setup Feeds initialise Feedlist with Feed
            instagram = new InstagramFeed();
            instagram.updateParams("");
            twitter = new TwitterFeed();
            twitter.updateParams("popular");
            feedList = new LinkedList<>();
            feedList.add(instagram);
            feedList.add(twitter);
	}
    
    
    //Instance Methods---------------------------------------------------------!
    
    //Method: Process
    //Parameters: None
    //Summary: Processes Feeds in feedlist, each feed sets up internal list
    //         with new post data.
    public void process() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        String s = "";
        ClientResponse clientResponse;        
        try
        {
           feedList.get(0).fetch(mapper);
           feedList.get(1).fetch(mapper);
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | ClientHandlerException | UniformInterfaceException | IOException e)
        {
            System.out.println(e);
        }
    }
    
    //Method: next
    //Parameters: None
    //Summary: Gets the next post from a feed in the feedlist
    public String next()
    {
        try
        {
            try //Catch any errors thrown by process method
            {
                if((feedList.get(0).count() == 0) && (feedList.get(1).count() == 0))
                {
                    this.process();
                }
            }
            catch(Exception e)
            {
                //return "Crashed calling process"+e.toString();
                return "";
            }
            
            try
            {
                if(feedList.get(0).count() == 0)
                {
                    return feedList.get(1).next();
                }
                else
                {
                    return feedList.get(0).next();
                }
            }
            catch(NoSuchElementException e) //No more elements in the list (Possibly because there is no more posts for that tag)
            {
                return "";
            }
            catch(Exception e)
            {
              return "Crashed calling next"+e.toString();
            }
        }
        catch(Exception e)
        {
            return "Crashed in engine"+e.toString();
        }
    }
    
    //Method: get_searchVal
    //Parameters: None
    //Summary: Gets the value the user searched for previously
    public String get_searchVal()
    {
        if(searchVal == null || searchVal.isEmpty())
        {
            return "Search...";
        }
        else
        {
            return searchVal;
        }
        
    }
    
    //Method: set_searchVal
    //Parameters: None
    //Summary: Sets the value the user searched for, parsers search term for
    //         invalid characters and updates feed with the latest search value, 
    //         and clears any posts the feeds may have from a previous search 
    //         term
    
    //***TODO split into multiple methods***
    public void set_searchVal(String a_searchVal, String a_top)
    {
        
        String top;
        if (a_top == null)
        {
            top = "";
        }
        else
        {
            top = a_top;
        }
        if((a_searchVal == null) || (a_searchVal.isEmpty()))
        {
            instagram.clear();
            twitter.clear();
            feedList.get(0).updateParams("");
            feedList.get(1).updateParams("popular");
        } 
        else 
        {
            //Regex Definition
            // ^ = not
            // \\p{Alpha} = An alphabetic character:[\p{Lower}\p{Upper}]
            a_searchVal = a_searchVal.replaceAll("[^\\p{Alpha}]","");
            
            searchVal = a_searchVal;
            if (!a_searchVal.equalsIgnoreCase(previousSearchVal) || top.equals("y"))
            {
                feedList.get(0).clear();
                feedList.get(1).clear();
            }
            feedList.get(0).updateParams(a_searchVal);
            feedList.get(1).updateParams(a_searchVal);
            previousSearchVal = a_searchVal;
        }
    }
}// end of engine
