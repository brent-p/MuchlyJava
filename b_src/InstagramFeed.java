/*
 * File: InstagramFeed.java
 * Author: Brent Parish
 * Create Date: 20/07/2013
 * Summary:Handles setting up calls to the Instagram Rest API
 */

/*
 *Instagram Response example:
 * "images => 
 * {
 *  low_resolution=
 *  {
 *      url=http://distilleryimage10.s3.amazonaws.com/5a4b0732f2c211e28df322000a1f9367_6.jpg, 
 *      width=306, 
 *      height=306
 *  }, 
 *  thumbnail=
 *  {
 *      url=http://distilleryimage10.s3.amazonaws.com/5a4b0732f2c211e28df322000a1f9367_5.jpg,
 *      width=150, 
 *      height=150
 *  }
 * standard_resolution=
 * {
 *      url=http://distilleryimage10.s3.amazonaws.com/5a4b0732f2c211e28df322000a1f9367_7.jpg, 
 *      width=612, 
 *      height=612}
 * }"
 */
package b_src;

import b_src.InstagramObject.Pagination;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;


public class InstagramFeed extends FeedBase
{
   
    private LinkedList<InstagramImages> imageList;
   // private RestObject popularRestObject;
    private RestObject searchRestObject;
    private InstagramObject instagramObject;
    private Consumer instagramConsumer;
    
    
    private ArrayList<Object> instagramData;
    private LinkedHashMap<String,LinkedHashMap> dataHashMap;
    private LinkedHashMap<String,LinkedHashMap> imagesHashMap;
    private LinkedHashMap<String,String> imageHashMap;
    private LinkedHashMap<String,String> captionHashMapStr;
    private LinkedHashMap<String,LinkedHashMap> captionHashMapLis;
    private LinkedHashMap<String,String> fromHashMap;
    private ListIterator<InstagramImages> imageListIter;
    private String paginateURL;
    
    public InstagramFeed()
    {
        imageList = new LinkedList<>();
        imageListIter = imageList.listIterator();
        //popularRestObject = new RestObject();
        //searchRestObject = new RestObject();
        paginateURL = "";
    }

    @Override
    public void updateParams(String a_searchVal)
    {
        //Setup Popular API call
        searchRestObject = new RestObject();
        searchRestObject .addArg("count");
        searchRestObject.addVal("7");
        searchRestObject.addArg("client_id");        
        searchRestObject.addVal("XXXXXXXXXREMOVEDXXXXXXXXXXXX");
        if(a_searchVal == null || a_searchVal.equalsIgnoreCase(""))
        {
            searchRestObject.setUrl("https://api.instagram.com/v1/media/popular");
        }
        else
        {
            if(paginateURL == null || paginateURL.isEmpty())
            {
                searchRestObject.setUrl("https://api.instagram.com/v1/tags/"+a_searchVal+"/media/recent");
            }
            else
            {
               searchRestObject.setUrl(paginateURL);
            }
        }
        instagramConsumer = new Consumer(searchRestObject.getUrl(),searchRestObject.getArgs(),searchRestObject.getVals());               
        
    }

    /**
     * @return the imageList
     */
    public LinkedList<InstagramImages> getImageList() {
        return imageList;
    }

    /**
     * @param imageList the imageList to set
     */
    public void setImageList(LinkedList<InstagramImages> imageList) {
        this.imageList = imageList;
    }    
    
    @Override
    public void fetch(ObjectMapper mapper) throws IOException, ClassNotFoundException,InstantiationException, IllegalAccessException
    {    
        String caption = "";
        String username = "";
        Integer status;
        if(!(paginateURL == null || paginateURL.isEmpty()))
        {
            instagramConsumer = new Consumer(paginateURL,searchRestObject.getArgs(),searchRestObject.getVals());  
        }
        ClientResponse response = instagramConsumer.getQueryResponse();
        status = response.getStatus();
        if(response.getStatus() == 200)
        {
            instagramObject = mapper.readValue(response.getEntity(String.class),InstagramObject.class);             
        
             instagramData = instagramObject.getData();
            Pagination paginateData = instagramObject.getPagination();
            if( paginateData == null || paginateData.getNext_url().isEmpty())
            {
                paginateURL = "";
            }
            else
            {
                paginateURL = paginateData.getNext_url();
            }    
            for(int x = 0; x < instagramData.size();x++)
            {
                dataHashMap = (LinkedHashMap<String,LinkedHashMap>)instagramData.get(x);
                imagesHashMap = dataHashMap.get("images");
                imageHashMap = imagesHashMap.get("thumbnail");                                   
                InstagramImage thumbnail = new InstagramImage(imageHashMap.get("url"),0,0);
                imageHashMap = imagesHashMap.get("low_resolution");
                InstagramImage lowResolution = new InstagramImage(imageHashMap.get("url"),0,0);
                imageHashMap = imagesHashMap.get("standard_resolution");
                InstagramImage standardResolution = new InstagramImage(imageHashMap.get("url"),0,0);
                caption = "";
                username = "";
                try
                {
                    captionHashMapStr = dataHashMap.get("caption");
                    captionHashMapLis = dataHashMap.get("caption");
                    fromHashMap = captionHashMapLis.get("from");
                    caption = captionHashMapStr.get("text");
                    username = fromHashMap.get("username");
                }
                catch(NullPointerException e)
                {
                    //No Caption information
                }
                imageListIter.add(new InstagramImages(thumbnail,lowResolution,standardResolution,username,caption));
            }
        }
        
    }
   
    @Override
    public String next()
    {        
        String tagString = "";
        try
        {
            InstagramImages instagramImages = imageListIter.previous();
            imageListIter.remove();
            tagString = super.buildImageTag(instagramImages.getLow_resolution().getUrl(),instagramImages.getUsername(), instagramImages.getCaption());
        }
        catch(Exception e)
        {
            tagString = "Crashed Here: "+e.toString();
        }
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
        return imageList.size();
    }
    
    @Override
    public void clear()
    {
        imageList = new LinkedList<>();
        imageListIter = imageList.listIterator();
        paginateURL = "";
    }
    
    @Override
    public Consumer getConsumer() {
        return instagramConsumer;
    }
   
    public void setConsumer(Consumer instagramConsumer) {
        this.instagramConsumer = instagramConsumer;
    }
}
