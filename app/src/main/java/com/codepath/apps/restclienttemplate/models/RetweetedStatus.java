
package com.codepath.apps.restclienttemplate.models;

import com.activeandroid.Model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class RetweetedStatus extends Model {

    private Object contributors;
    private Object coordinates;
    private String createdAt;
    private Integer favoriteCount;
    private Boolean favorited;
    private Object geo;
    private Double remoteId;
    private String idStr;
    private Object inReplyToScreenName;
    private Object inReplyToStatusId;
    private Object inReplyToStatusIdStr;
    private Object inReplyToUserId;
    private Object inReplyToUserIdStr;
    private String lang;
    private Object place;
    private Boolean possiblySensitive;
    private Integer retweetCount;
    private Boolean retweeted;
    private String source;
    private String text;
    private Boolean truncated;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The contributors
     */
    public Object getContributors() {
        return contributors;
    }

    /**
     * 
     * @param contributors
     *     The contributors
     */
    public void setContributors(Object contributors) {
        this.contributors = contributors;
    }

    /**
     * 
     * @return
     *     The coordinates
     */
    public Object getCoordinates() {
        return coordinates;
    }

    /**
     * 
     * @param coordinates
     *     The coordinates
     */
    public void setCoordinates(Object coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The favoriteCount
     */
    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    /**
     * 
     * @param favoriteCount
     *     The favorite_count
     */
    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    /**
     * 
     * @return
     *     The favorited
     */
    public Boolean getFavorited() {
        return favorited;
    }

    /**
     * 
     * @param favorited
     *     The favorited
     */
    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    /**
     * 
     * @return
     *     The geo
     */
    public Object getGeo() {
        return geo;
    }

    /**
     * 
     * @param geo
     *     The geo
     */
    public void setGeo(Object geo) {
        this.geo = geo;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Double getRemoteId() {
        return remoteId;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Double id) {
        this.remoteId = id;
    }

    /**
     * 
     * @return
     *     The idStr
     */
    public String getIdStr() {
        return idStr;
    }

    /**
     * 
     * @param idStr
     *     The id_str
     */
    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    /**
     * 
     * @return
     *     The inReplyToScreenName
     */
    public Object getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    /**
     * 
     * @param inReplyToScreenName
     *     The in_reply_to_screen_name
     */
    public void setInReplyToScreenName(Object inReplyToScreenName) {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    /**
     * 
     * @return
     *     The inReplyToStatusId
     */
    public Object getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    /**
     * 
     * @param inReplyToStatusId
     *     The in_reply_to_status_id
     */
    public void setInReplyToStatusId(Object inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    /**
     * 
     * @return
     *     The inReplyToStatusIdStr
     */
    public Object getInReplyToStatusIdStr() {
        return inReplyToStatusIdStr;
    }

    /**
     * 
     * @param inReplyToStatusIdStr
     *     The in_reply_to_status_id_str
     */
    public void setInReplyToStatusIdStr(Object inReplyToStatusIdStr) {
        this.inReplyToStatusIdStr = inReplyToStatusIdStr;
    }

    /**
     * 
     * @return
     *     The inReplyToUserId
     */
    public Object getInReplyToUserId() {
        return inReplyToUserId;
    }

    /**
     * 
     * @param inReplyToUserId
     *     The in_reply_to_user_id
     */
    public void setInReplyToUserId(Object inReplyToUserId) {
        this.inReplyToUserId = inReplyToUserId;
    }

    /**
     * 
     * @return
     *     The inReplyToUserIdStr
     */
    public Object getInReplyToUserIdStr() {
        return inReplyToUserIdStr;
    }

    /**
     * 
     * @param inReplyToUserIdStr
     *     The in_reply_to_user_id_str
     */
    public void setInReplyToUserIdStr(Object inReplyToUserIdStr) {
        this.inReplyToUserIdStr = inReplyToUserIdStr;
    }

    /**
     * 
     * @return
     *     The lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * 
     * @param lang
     *     The lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * 
     * @return
     *     The place
     */
    public Object getPlace() {
        return place;
    }

    /**
     * 
     * @param place
     *     The place
     */
    public void setPlace(Object place) {
        this.place = place;
    }

    /**
     * 
     * @return
     *     The possiblySensitive
     */
    public Boolean getPossiblySensitive() {
        return possiblySensitive;
    }

    /**
     * 
     * @param possiblySensitive
     *     The possibly_sensitive
     */
    public void setPossiblySensitive(Boolean possiblySensitive) {
        this.possiblySensitive = possiblySensitive;
    }

    /**
     * 
     * @return
     *     The retweetCount
     */
    public Integer getRetweetCount() {
        return retweetCount;
    }

    /**
     * 
     * @param retweetCount
     *     The retweet_count
     */
    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }

    /**
     * 
     * @return
     *     The retweeted
     */
    public Boolean getRetweeted() {
        return retweeted;
    }

    /**
     * 
     * @param retweeted
     *     The retweeted
     */
    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     *     The truncated
     */
    public Boolean getTruncated() {
        return truncated;
    }

    /**
     * 
     * @param truncated
     *     The truncated
     */
    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
