
package com.codepath.apps.restclienttemplate.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Sizes_ {

    private Small_ small;
    private Thumb_ thumb;
    private Large_ large;
    private Medium___ medium;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The small
     */
    public Small_ getSmall() {
        return small;
    }

    /**
     * 
     * @param small
     *     The small
     */
    public void setSmall(Small_ small) {
        this.small = small;
    }

    /**
     * 
     * @return
     *     The thumb
     */
    public Thumb_ getThumb() {
        return thumb;
    }

    /**
     * 
     * @param thumb
     *     The thumb
     */
    public void setThumb(Thumb_ thumb) {
        this.thumb = thumb;
    }

    /**
     * 
     * @return
     *     The large
     */
    public Large_ getLarge() {
        return large;
    }

    /**
     * 
     * @param large
     *     The large
     */
    public void setLarge(Large_ large) {
        this.large = large;
    }

    /**
     * 
     * @return
     *     The medium
     */
    public Medium___ getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(Medium___ medium) {
        this.medium = medium;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
