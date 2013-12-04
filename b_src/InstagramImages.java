
/*
 * File: InstagramImages.java
 * Author: Brent Parish
 * Create Date: 20/07/2013
 * Summary: Handles the different instagram images e.g. High Res, Low Res and thumbnail
 */

package b_src;

public class InstagramImages 
{
    private InstagramImage thumbnail;
    private InstagramImage lowResolution;
    private InstagramImage standardResolution;
    private String caption;
    private String username;

    
    public InstagramImages()
    {
        thumbnail = new InstagramImage();
        lowResolution = new InstagramImage();
        standardResolution = new InstagramImage();
    }
    
    public InstagramImages(InstagramImage thumbnail,InstagramImage lowResolution,InstagramImage standardResolution)
    {
        this.thumbnail = thumbnail;
        this.lowResolution = lowResolution;
        this.standardResolution = standardResolution;
    }
    
    public InstagramImages(InstagramImage thumbnail,InstagramImage lowResolution,InstagramImage standardResolution,String username, String caption)
    {
        this.thumbnail = thumbnail;
        this.lowResolution = lowResolution;
        this.standardResolution = standardResolution;
        this.username = username;
        this.caption = caption;
    }
    /**
     * @return the thumbnail
     */
    public InstagramImage getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(InstagramImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return the lowResolution
     */
    public InstagramImage getLow_resolution() {
        return lowResolution;
    }

    /**
     * @param lowResolution the lowResolution to set
     */
    public void setLow_resolution(InstagramImage lowResolution) {
        this.lowResolution = lowResolution;
    }

    /**
     * @return the standardResolution
     */
    public InstagramImage getStandard_resolution() {
        return standardResolution;
    }

    /**
     * @param standardResolution the standardResolution to set
     */
    public void setStandard_resolution(InstagramImage standardResolution) {
        this.standardResolution = standardResolution;
    }

    /**
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
