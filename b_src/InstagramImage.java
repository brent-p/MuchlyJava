/*
 * File: InstagramImage.java
 * Author: Brent Parish
 * Create Date: 20/07/2013
 * Summary:Handles Models Instagam Image JSON
 */


package b_src;

/**
 *
 * @author Brent
 */
public class InstagramImage 
{
    private String url;
    private Integer width;
    private Integer height;

    public InstagramImage()
    {
        url = "";
        width = 0;
        height = 0;
    }
    
    public InstagramImage(String url,Integer width,Integer height)
    {
        this.url = url;
        this.width = width;
        this.height = height;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
    }
}
