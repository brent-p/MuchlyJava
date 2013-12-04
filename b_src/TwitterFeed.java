/*
 * File: TwitterFeed.java
 * Author: Brent Parish
 * Create Date: 20/07/2013
 * Summary:Handles setting up calls to the Twitter Rest API
 */
package b_src;

import b_src.TwitterObject.MetaData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Brent
 */
public class TwitterFeed extends FeedBase
{
    private LinkedList<String> tweetList;
    private ListIterator tweetListIter;
    private RestObject searchRestObject;
    private TwitterObject twitterObject;
    private String error;
    private String paginateURL;
    private Consumer twitterConsumer;
    
    public TwitterFeed()
    {
        tweetList = new LinkedList<>();
        tweetListIter = tweetList.listIterator();
    }

    @Override
    public void updateParams(String a_searchVal)
    {
        //Setup Popular API call
        searchRestObject = new RestObject();
        searchRestObject.addArg("q");
        searchRestObject.addVal(a_searchVal); //remember if blank setup popular
        searchRestObject.addArg("count");
        searchRestObject.addVal("3");
        searchRestObject.addArg("lang");
        searchRestObject.addVal("eu");
        searchRestObject.addArg("result_type");
        searchRestObject.addVal("recent");
        searchRestObject.setUrl("https://api.twitter.com/1.1/search/tweets.json");

        if(!(paginateURL == null || paginateURL.isEmpty()))
        {
            searchRestObject.addArg("max_id");
            searchRestObject.addVal(paginateURL);
        }
        
        twitterConsumer = new Consumer(searchRestObject.getUrl(),searchRestObject.getArgs(),searchRestObject.getVals(),"XXXXXXXREMOVEDXXXXXXXXX","XXXXXXXREMOVEDXXXXXXXXX");               
    }
    
    @Override
    public void fetch(ObjectMapper mapper) throws IOException, ClassNotFoundException,InstantiationException, IllegalAccessException
    {             
        if(!(paginateURL == null || paginateURL.isEmpty()))
        {
            LinkedList<String> vals = searchRestObject.getVals();
            updateParams(vals.peekFirst());
        }
        try
        {
            ClientResponse response = twitterConsumer.getQueryResponse();
            twitterObject = mapper.readValue(response.getEntity(String.class),TwitterObject.class);    
            MetaData metaData = twitterObject.getSearch_metadata();
                
            ArrayList<Object> twitterData = twitterObject.getStatuses();
            for(int x = 0; x < twitterData.size();x++)
            {
                LinkedHashMap<String,String> dataHashMap = (LinkedHashMap<String,String>)twitterData.get(x);
                
                String tweet = dataHashMap.get("text");
                String id_str = dataHashMap.get("id_str");

                paginateURL = Long.toString(Long.parseLong(id_str) - 1L);
                tweetListIter.add(tweet);

            }
        
        
        }
        catch (Exception e)
        {
            error = e.toString();
        }
        
    }
   
    @Override
    public String next()
    {
        String tagString = "";
        tagString = super.buildStringTag((String)tweetListIter.previous());
        tweetListIter.remove();
        return tagString;
    }

    /**
     * @return the searchRestObject
     */
    public RestObject getSearchRestObject() {
        return searchRestObject;
    }

    /**
     * @param searchRestObject the searchRestObject to set
     */
    public void setSearchRestObject(RestObject searchRestObject) {
        this.searchRestObject = searchRestObject;
    }
    
    @Override
    public Integer count()
    {
        return tweetList.size(); //To Do 
    }
    
    @Override
    public void clear()
    {
        tweetList = new LinkedList<>();
        tweetListIter = tweetList.listIterator();
        paginateURL = "";
    }

    @Override
    public Consumer getConsumer() {
        return twitterConsumer;
    }

    public void setConsumer(Consumer twitterConsumer) {
        this.twitterConsumer = twitterConsumer;
    }
    
}
