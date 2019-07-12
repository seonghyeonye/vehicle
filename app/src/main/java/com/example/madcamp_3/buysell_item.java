package com.example.madcamp_3;

import android.media.Image;

public class buysell_item {
    private String name;
    private int year;
    private int price;
    private String won;
    private int imageId;
    private Image heart;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Image getchecked(){
        return heart.i();
    }

//    public boolean setchecked(boolean heart){
//        this.heart = heart;
//    }
//
//

}
