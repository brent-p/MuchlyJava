/*
 * File: InstagramObject.java
 * Author: Brent Parish
 * Create Date: 20/07/2013
 * Summary: Maps to the JSON that is returned from a call to the Instagram API
 * {
    "meta": {
        "code": 200
    },
    "data": {
        ...
    },
    "pagination": {
        "next_url": "...",
        "next_max_id": "13872296"
    }
   }
 * 
 */
package b_src;

import java.util.ArrayList;


/**
 *
 * @author
 * Brent
 */

public class InstagramObject 
{
    public static class Code 
    {
        private String _code;
        public String getCode()
        {
            return _code;
        }
        public void setCode(String s)
        {
            _code = s;
        }
    }
    public static class Pagination
    {
        private String _next_url;
        private String _next_max_tag_id;
        private String _next_max_id;
        private String _next_min_id;
        private String _min_tag_id;
        private String _deprecation;
         private String _deprecation_warning;
       

        /**
         * @return the _next_max_tag_id
         */
        public String getNext_max_tag_id() {
            return _next_max_tag_id;
        }

        /**
         * @param next_max_tag_id the _next_max_tag_id to set
         */
        public void setNext_max_tag_id(String next_max_tag_id) {
            this._next_max_tag_id = next_max_tag_id;
        }

        /**
         * @return the _next_url
         */
        public String getNext_url() {
            return _next_url;
        }

        /**
         * @param next_url the _next_url to set
         */
        public void setNext_url(String next_url) {
            this._next_url = next_url;
        }

        /**
         * @return the _deprecation
         */
        public String getDeprecation() {
            return _deprecation;
        }

        /**
         * @param deprecation the _deprecation to set
         */
        public void setDeprecation(String deprecation) {
            this._deprecation = deprecation;
        }

        /**
         * @return the _deprecation_warning
         */
        public String getDeprecation_warning() {
            return _deprecation_warning;
        }

        /**
         * @param deprecation_warning the _deprecation_warning to set
         */
        public void setDeprecation_warning(String deprecation_warning) {
            this._deprecation_warning = deprecation_warning;
        }

        /**
         * @return the _next_max_id
         */
        public String getNext_max_id() {
            return _next_max_id;
        }

        /**
         * @param next_max_id the _next_max_id to set
         */
        public void setNext_max_id(String next_max_id) {
            this._next_max_id = next_max_id;
        }

        /**
         * @return the _next_min_id
         */
        public String getNext_min_id() {
            return _next_min_id;
        }

        /**
         * @param next_min_id the _next_min_id to set
         */
        public void setNext_min_id(String next_min_id) {
            this._next_min_id = next_min_id;
        }

        /**
         * @return the _min_tag_id
         */
        public String getMin_tag_id() {
            return _min_tag_id;
        }

        /**
         * @param min_tag_id the _min_tag_id to set
         */
        public void setMin_tag_id(String min_tag_id) {
            this._min_tag_id = min_tag_id;
        }
     
    }
    private Code _meta;
    private ArrayList<Object> _data;
    private Pagination _pagination;
    
    public Code getMeta()
    {
        return _meta;
    }
    
    public ArrayList<Object> getData()
    {
        return _data;
    }
    
    public Pagination getPagination()
    {
        return _pagination;
    }
    
    public void setMeta(Code m)
    {
        _meta = m;
    }
    
    public void setData(ArrayList<Object> d)
    {
        _data = d;
    }
    
    public void setPagination(Pagination p)
    {
        _pagination = p;
    }
  
}// End of Instagram


