package com.codepath.apps.restclienttemplate.models;

import com.activeandroid.Model;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Sizes extends Model {

    private Ipad ipad;
    private Web web;
    private Mobile mobile;
    @SerializedName("600x200")
    private _600x200 _600x200;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ipad
     */
    public Ipad getIpad() {
        return ipad;
    }

    /**
     * 
     * @param ipad
     *     The ipad
     */
    public void setIpad(Ipad ipad) {
        this.ipad = ipad;
    }


    /**
     * 
     * @return
     *     The web
     */
    public Web getWeb() {
        return web;
    }

    /**
     * 
     * @param web
     *     The web
     */
    public void setWeb(Web web) {
        this.web = web;
    }


    /**
     * 
     * @return
     *     The mobile
     */
    public Mobile getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile
     *     The mobile
     */
    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     *
     * @return
     *     The _600x200
     */
    public _600x200 get600x200() {
        return _600x200;
    }

    /**
     *
     * @param _600x200
     *     The 600x200
     */
    public void set600x200(_600x200 _600x200) {
        this._600x200 = _600x200;
    }

}
