/*
 * File: Engine.java
 * Author: Brent Parish 
 * Create Date: 20/07/2013
 * Summary: Models the response from an OAuth2 Applicaiton token request
 */

package b_src;

/**
 *
 * @author
 * Brent
 */
public class Oauth2ResponseObject 
{
    private String _token_type;
    private String _access_token;
    
    public Oauth2ResponseObject()
    {
                
    }

    /**
     * @return the _token_type
     */
    public String getToken_type() {
        return _token_type;
    }

    /**
     * @param token_type the _token_type to set
     */
    public void setToken_type(String token_type) {
        this._token_type = token_type;
    }

    /**
     * @return the _access_token
     */
    public String getAccess_token() {
        return _access_token;
    }

    /**
     * @param access_token the _access_token to set
     */
    public void setAccess_token(String access_token) {
        this._access_token = access_token;
    }
    
}
