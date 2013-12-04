/*
 * File: FeedBase.java
 * Author: Brent Parish 
 * Create Date: 20/07/2013
 * Summary: Handles code relevant to all feed types
 */
package b_src;

//Import Statements------------------------------------------------------------!
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FeedBase implements IFeed 
{
    //Instance Variables-------------------------------------------------------!
    public int count;

    //Instance Methods---------------------------------------------------------!
    
    //Method: buildImageTag
    //Parameters: imageURL,username,caption
    //Summary: Creates an image tag using the imageUrl passed in, also scans 
    //         caption text for hash tags and urls
    protected String buildImageTag(String imageURL,String username,String caption)
    {
        if(username.isEmpty() && caption.isEmpty())
        {
            return "<div class=\"imgContainer\"> <img class=\"r-image\" src=\""+imageURL+"\"></div>";
        }
        else
        {
            caption = parseStringForLinks(caption);
            return "<div class=\"imgContainer\"> <img class=\"r-image\" src=\""+imageURL+"\"><span class=\"caption\"><p class=\"pad-normal\">"+username+": "+caption+"</p></span></div>";
        }
    }
    
    //Method: buildStringTag
    //Parameters: text
    //Summary: Creates a tag for a text based post and scans the text for hashtags
    //         and urls
    protected String buildStringTag(String text)
    {
        text = parseStringForLinks(text);
        return "<p class=\"post-1\">"+text+"</p>";
    }
    
    //Method: parseStringForLinks
    //Parameters: text
    //Summary: Scans the text passed in for hashtags and URLs that start with
    //          http:// and www.
    private String parseStringForLinks(String text)
    {
        text = parseString(text,"www.",false);         
        text = parseString(text,"http://",false);
        text = parseString(text,"#",true);
        return text;
    }
    
    //Method: parseString
    //Parameters: stringToParse, whatToLookFor, isHashTag
    //Summary: Scans the stringToParse and converts anything that matches
    //         the whatToLookFor patten into an anchor tag.
    //***TODO*** Fix boolean method trap!, split into multiple methods
    private String parseString(String stringToParse,String whatToLookFor, Boolean isHashTag)
    {
        Boolean noMoreLinks = false;
        Integer searchFromPos = 0;
        Integer startPos;
        Integer endPos;
        String link;
        String substring;
        while(!noMoreLinks)
        {
            startPos = stringToParse.indexOf(whatToLookFor,searchFromPos); // Gets the next occurance of the pattern to look for
            if(startPos == -1)  
            {
                noMoreLinks = true; //No more occurances of pattern early abort
            }
            else
            {
                endPos = findEndPosOfLink(stringToParse,startPos+1);
                if(endPos == stringToParse.length())
                {
                    noMoreLinks = true;
                }
                String stringToBeReplaced = stringToParse.substring(startPos, endPos);
                
                if(isHashTag)//Hashtag anchors
                {
                    link = "<a href=\"index.jsp?searchVal="+stringToBeReplaced.replace("#","")+"\">"+stringToBeReplaced+"</a>";
                }
                else //Anyother type of anchor
                {
                    link = "<a href=\""+stringToBeReplaced+"\">"+stringToBeReplaced+"</a>";
                }
                substring = stringToParse.substring(startPos);

                stringToParse = stringToParse.substring(0,startPos) + stringToParse.substring(startPos).replaceFirst(stringToBeReplaced,link);
                searchFromPos = startPos +link.length();
            }
        }
        return stringToParse;
    }
    
    //Method: findEndPosOfPattern
    //Parameters: stringToParse, startPos
    //Summary: Finds the ending position of a URL or hashtag.
    private Integer findEndPosOfLink(String stringToParse,Integer startPos)
    {
        Integer endPos;
        String subString = stringToParse.substring(startPos);
        /* Regex Definitions
          \\s = whitespace character
          \\p{Punct} = One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ 
          && = AND
          [a-z&&[^bc]]	a through z, except for b and c: [ad-z] (subtraction)
         */
        Pattern p = Pattern.compile("[\\s\\p{Punct}&&[^/?&:%$_.-]]"); //This regex gets the position of the first white space or any other non alphanumeric character other than "^/?:&%$_.-"
        Matcher m = p.matcher(subString);
        if (m.find()) 
        {
            endPos = m.start() + startPos;
        }
        else 
        {
            endPos = stringToParse.length(); //Ending position is the end of the string
        }
        return endPos;
    }

    //Abstract Methods---------------------------------------------------------!
    @Override
    public abstract void fetch(ObjectMapper mapper) throws IOException, ClassNotFoundException,InstantiationException, IllegalAccessException;
    @Override
    public abstract String next();
    @Override
    public abstract Integer count();
    @Override
    public abstract void clear();
    @Override
    public abstract Consumer getConsumer();
    @Override
    public abstract void updateParams(String a_searchVal);
}
