package com.testdash;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by user on 7/8/17.
 */
public class RedisOutput {

private final SimpleStringProperty product;


    public RedisOutput(String productS) {
        this.product = new SimpleStringProperty(productS);
    }


    public String getProduct() {
        return product.get();
    }
    public void setProduct(String productS) {
        product.set (productS);
    }






}
