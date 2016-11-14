package com.team16.oose_project.items;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Item {
    private int itemId;
    private int sellerId;
    private String category1;
    private String category2;
    private String name;
    private double price;
    private String imgLink;
    private String condition;
    private boolean isDeliver;
    private String pickUpAddress;
    private String desciption;
    private Date postDate;
    private Date avialableDate;
    private Date expireDate;
    private int numOfLikes;
    private ArrayList<String> contactMethods;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isDeliver() {
        return isDeliver;
    }

    public void setDeliver(boolean deliver) {
        isDeliver = deliver;
    }

    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getAvialableDate() {
        return avialableDate;
    }

    public void setAvialableDate(Date avialableDate) {
        this.avialableDate = avialableDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public ArrayList<String> getContactMethods() {
        return contactMethods;
    }

    public void setContactMethods(ArrayList<String> contactMethods) {
        this.contactMethods = contactMethods;
    }

    /**
     * parsing an array of JSON items dictionaries into an ArrayList
     * which we will use to manage the response of GET() request
     */

    public static ArrayList<Item> fromJson(JSONArray jsonArray) {
        ArrayList<Item> items = new ArrayList<Item>(jsonArray.length());
        // Process each result in json array, decode and convert to Item object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemJson = null;
            try {
                itemJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Item item = new Gson().fromJson(itemJson.toString(), Item.class);
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }


}

