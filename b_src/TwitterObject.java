/*
 * File: TwitterObject.java
 * Author: Brent Parish
 * Create Date: 20/07/2013
 * Summary: Models a twitter JSON response
 */
/*Sample Twitter Response:
{
  "statuses": [
    {
      "coordinates": null,
      "favorited": false,
      "truncated": false,
      "created_at": "Mon Sep 24 03:35:21 +0000 2012",
      "id_str": "250075927172759552",
      "entities": {
        "urls": [
 
        ],
        "hashtags": [
          {
            "text": "freebandnames",
            "indices": [
              20,
              34
            ]
          }
        ],
        "user_mentions": [
 
        ]
      },
      "in_reply_to_user_id_str": null,
      "contributors": null,
      "text": "Aggressive Ponytail #freebandnames",
      "metadata": {
        "iso_language_code": "en",
        "result_type": "recent"
      },
      "retweet_count": 0,
      "in_reply_to_status_id_str": null,
      "id": 250075927172759552,
      "geo": null,
      "retweeted": false,
      "in_reply_to_user_id": null,
      "place": null,
      "user": {
        "profile_sidebar_fill_color": "DDEEF6",
        "profile_sidebar_border_color": "C0DEED",
        "profile_background_tile": false,
        "name": "Sean Cummings",
        "profile_image_url": "http://a0.twimg.com/profile_images/2359746665/1v6zfgqo8g0d3mk7ii5s_normal.jpeg",
        "created_at": "Mon Apr 26 06:01:55 +0000 2010",
        "location": "LA, CA",
        "follow_request_sent": null,
        "profile_link_color": "0084B4",
        "is_translator": false,
        "id_str": "137238150",
        "entities": {
          "url": {
            "urls": [
              {
                "expanded_url": null,
                "url": "",
                "indices": [
                  0,
                  0
                ]
              }
            ]
          },
          "description": {
            "urls": [
 
            ]
          }
        },
        "default_profile": true,
        "contributors_enabled": false,
        "favourites_count": 0,
        "url": null,
        "profile_image_url_https": "https://si0.twimg.com/profile_images/2359746665/1v6zfgqo8g0d3mk7ii5s_normal.jpeg",
        "utc_offset": -28800,
        "id": 137238150,
        "profile_use_background_image": true,
        "listed_count": 2,
        "profile_text_color": "333333",
        "lang": "en",
        "followers_count": 70,
        "protected": false,
        "notifications": null,
        "profile_background_image_url_https": "https://si0.twimg.com/images/themes/theme1/bg.png",
        "profile_background_color": "C0DEED",
        "verified": false,
        "geo_enabled": true,
        "time_zone": "Pacific Time (US & Canada)",
        "description": "Born 330 Live 310",
        "default_profile_image": false,
        "profile_background_image_url": "http://a0.twimg.com/images/themes/theme1/bg.png",
        "statuses_count": 579,
        "friends_count": 110,
        "following": null,
        "show_all_inline_media": false,
        "screen_name": "sean_cummings"
      },
      "in_reply_to_screen_name": null,
      "source": "<a href=\"http://itunes.apple.com/us/app/twitter/id409789998?mt=12\" rel=\"nofollow\">Twitter for Mac</a>",
      "in_reply_to_status_id": null
    },
    
  ],
  "search_metadata": {
    "max_id": 250126199840518145,
    "since_id": 24012619984051000,
    "refresh_url": "?since_id=250126199840518145&q=%23freebandnames&result_type=mixed&include_entities=1",
    "next_results": "?max_id=249279667666817023&q=%23freebandnames&count=4&include_entities=1&result_type=mixed",
    "count": 4,
    "completed_in": 0.035,
    "since_id_str": "24012619984051000",
    "query": "%23freebandnames",
    "max_id_str": "250126199840518145"
  }
}*/
package b_src;

import java.util.ArrayList;

public class TwitterObject 
{

    /**
     * @return the _statuses
     */
    
    public static class MetaData 
    {
        private String _max_id;
        private String _since_id;
        private String _refresh_url;
        private String _next_results;
        private String _count;
        private String _completed_in;
        private String _since_id_str;
        private String _query;
        private String _max_id_str;

        /**
         * @return the _max_id
         */
        public String getMax_id() {
            return _max_id;
        }

        /**
         * @param max_id the _max_id to set
         */
        public void setMax_id(String max_id) {
            this._max_id = max_id;
        }

        /**
         * @return the _since_id
         */
        public String getSince_id() {
            return _since_id;
        }

        /**
         * @param since_id the _since_id to set
         */
        public void setSince_id(String since_id) {
            this._since_id = since_id;
        }

        /**
         * @return the _refresh_url
         */
        public String getRefresh_url() {
            return _refresh_url;
        }

        /**
         * @param refresh_url the _refresh_url to set
         */
        public void setRefresh_url(String refresh_url) {
            this._refresh_url = refresh_url;
        }

        /**
         * @return the _next_results
         */
        public String getNext_results() {
            return _next_results;
        }

        /**
         * @param next_results the _next_results to set
         */
        public void setNext_results(String next_results) {
            this._next_results = next_results;
        }

        /**
         * @return the _count
         */
        public String getCount() {
            return _count;
        }

        /**
         * @param count the _count to set
         */
        public void setCount(String count) {
            this._count = count;
        }

        /**
         * @return the _completed_in
         */
        public String getCompleted_in() {
            return _completed_in;
        }

        /**
         * @param completed_in the _completed_in to set
         */
        public void setCompleted_in(String completed_in) {
            this._completed_in = completed_in;
        }

        /**
         * @return the _since_id_str
         */
        public String getSince_id_str() {
            return _since_id_str;
        }

        /**
         * @param since_id_str the _since_id_str to set
         */
        public void setSince_id_str(String since_id_str) {
            this._since_id_str = since_id_str;
        }

        /**
         * @return the _query
         */
        public String getQuery() {
            return _query;
        }

        /**
         * @param query the _query to set
         */
        public void setQuery(String query) {
            this._query = query;
        }

        /**
         * @return the _max_id_str
         */
        public String getMax_id_str() {
            return _max_id_str;
        }

        /**
         * @param max_id_str the _max_id_str to set
         */
        public void setMax_id_str(String max_id_str) {
            this._max_id_str = max_id_str;
        }
    }
    private ArrayList<Object> _statuses;
    private MetaData _search_metadata;
    
    public ArrayList<Object> getStatuses() {
        return _statuses;
    }

    /**
     * @param statuses the _statuses to set
     */
    public void setStatuses(ArrayList<Object> statuses) {
        this._statuses = statuses;
    }

    /**
     * @return the _search_metadata
     */
    public MetaData getSearch_metadata() {
        return _search_metadata;
    }

    /**
     * @param search_metadata the _search_metadata to set
     */
    public void setSearch_metadata(MetaData search_metadata) {
        this._search_metadata = search_metadata;
    }
}
