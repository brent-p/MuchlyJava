/*
 * File: Consumer.java
 * Author: Brent Parish 
 * Create Date: 20/07/2013
 * Summary: 3rd party api Web Service Consumer
 */
package b_src;

//Import Statements
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.net.URLEncoder;
import javax.ws.rs.core.MultivaluedMap;



public class Consumer {
    
    //Instance Variables
    
    Client client;      //Handles Consuming a resource
    WebResource webResource; //Encapsulates a web resource for the client
    private String uriString;       //Contains the URI of the web service
    private LinkedList<String> uriArgs; //Contains the Arguments to be specified at the end of the URI
    private LinkedList<String> uriVals; //Contains the Values for the URI Arguments
    MultivaluedMap queryParams;
    String consumerSecret;
    String consumerKey;
    
    //Zero Parameter Constructer
    public Consumer()
        {
            //Initialise instance Variables
                ClientConfig config = new DefaultClientConfig();     
                client = Client.create(config);
                uriString = "";
                uriArgs = new LinkedList<String>();
                uriVals = new LinkedList<String>();
                queryParams = new MultivaluedMapImpl();
	}
    
    //Initialise webResource with URI
    public Consumer(String a_uriString)
    {
        this();        
        uriString = a_uriString;
    }
    
    //Initialise webResource with URI and Parameters and Values
    public Consumer(String a_uriString, LinkedList<String> a_uriArgs, LinkedList<String> a_uriVals)
    {
        this();
        uriString = a_uriString;
        uriArgs = a_uriArgs;
        uriVals = a_uriVals;
    }
    
    public Consumer(String a_uriString, LinkedList<String> a_uriArgs, LinkedList<String> a_uriVals, String a_consumerSecret, String a_consumerKey)
    {
        this();
        uriString = a_uriString;
        uriArgs = a_uriArgs;
        uriVals = a_uriVals;
        try
        {
            consumerSecret = URLEncoder.encode(a_consumerSecret, "UTF-8");
            consumerKey = URLEncoder.encode(a_consumerKey,"UTF-8");
        }
        catch(Exception e)
        {
            
        }
    }
    
    
    public ClientResponse getQueryResponse() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        boolean status = setupWebResource();
        ClientResponse clientResponse;
        String bearerTokenCredentials = "";
        MultivaluedMap oAuthQueryParams = new MultivaluedMapImpl();
        if(status)
        {
            try
            {
                //OAuth 2.0 Application Authorisation ---- todo tidy up
                
                if((consumerSecret != null && consumerSecret != "") && (consumerKey != null && consumerSecret != ""))
                {
                    bearerTokenCredentials = consumerKey + ":"+consumerSecret;
                    bearerTokenCredentials = Base64.encode(bearerTokenCredentials.getBytes());
                            
                    webResource = client.resource("https://api.twitter.com/oauth2/token");
                    oAuthQueryParams.add("grant_type","client_credentials");
                    clientResponse = webResource.queryParams(oAuthQueryParams).header("Authorization","Basic "+bearerTokenCredentials).header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8").post(ClientResponse.class); 
                    String response = clientResponse.getEntity(String.class);
                    Oauth2ResponseObject oauth;
                    ObjectMapper mapper = new ObjectMapper();
                    oauth = mapper.readValue(response,Oauth2ResponseObject.class);
                    uriString = "https://api.twitter.com/1.1/search/tweets.json";
                    boolean status2 = setupWebResource();
                    
                    clientResponse = webResource.queryParams(queryParams).header("Authorization","Bearer "+oauth.getAccess_token()).get(ClientResponse.class);
                   clientResponse = clientResponse;
                    return clientResponse;
                }
                else
                {
                    
                    clientResponse = webResource.queryParams(queryParams).get(ClientResponse.class);      
                }
                
                return clientResponse;
            }  
            catch (Exception e)
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    public boolean setupWebResource()
    {
        queryParams = new MultivaluedMapImpl();
        if(uriString != null && (!uriString.isEmpty()))
        {
            webResource = client.resource(uriString);
            if(uriArgs.size() > 0 && uriVals.size() > 0 && uriVals.size() == uriArgs.size()) // Check that params and args are valid
            {
                ListIterator<String> iterArgs = uriArgs.listIterator();
                ListIterator<String> iterVals = uriVals.listIterator();
                
                while (iterArgs.hasNext() && iterVals.hasNext())
		{
                     queryParams.add(iterArgs.next(), iterVals.next());
                }
               return true;
            }
            else
            {
                if(uriArgs.size() != uriVals.size() && uriArgs.size() > 0 && uriVals.size() > 0)
                {
                    return false;   //Unequal number of Arguments and Values, these should be the same size
                }
                else
                {
                    return true;
                }           
            }
        }       
        else
        {
            return false;
        }
    }
    
    //Getters and Setters
    public Client get_client()
    {
        return client;
    }
    
    public void set_searchVal(Client a_client)
    {
        client = a_client;
    }

    /**
     * @return the uriString
     */
    public String getUriString() {
        return uriString;
    }

    /**
     * @param uriString the uriString to set
     */
    public void setUriString(String uriString) {
        this.uriString = uriString;
    }

    /**
     * @return the uriArgs
     */
    public LinkedList<String> getUriArgs() {
        return uriArgs;
    }

    /**
     * @param uriArgs the uriArgs to set
     */
    public void setUriArgs(LinkedList<String> uriArgs) {
        this.uriArgs = uriArgs;
    }

    /**
     * @return the uriVals
     */
    public LinkedList<String> getUriVals() {
        return uriVals;
    }

    /**
     * @param uriVals the uriVals to set
     */
    public void setUriVals(LinkedList<String> uriVals) {
        this.uriVals = uriVals;
    }
    
}// End of consumer
