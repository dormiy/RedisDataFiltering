package com.testdash;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by user on 7/8/17.
 */
public class RedisOutput {

    private final SimpleStringProperty yield;
    private final SimpleStringProperty product;



    public RedisOutput(String productS, String yieldS) {
        this.product = new SimpleStringProperty(productS);
        this.yield=new SimpleStringProperty (yieldS);

    }


    public String getProduct() {
        return product.get();
    }
    public void setProduct(String productS) {
        product.set (productS);
    }

    public String getYield() {
        return yield.get();
    }
    public void setYield(String productS) {
        yield.set (productS);
    }




}
