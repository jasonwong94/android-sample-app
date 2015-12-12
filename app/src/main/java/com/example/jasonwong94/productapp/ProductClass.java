package com.example.jasonwong94.productapp;

import java.text.DateFormat;

/**
 * Created by jasonwong94 on 15-12-10.
 */
public class ProductClass {
    String productName, productDescription, productDateCreatedOn;
    int productID, productQuantity;
    double productCost;

    double productTotalCost;

    public ProductClass(){
        this.productName = "defaultName";
        this.productDescription = "defaultProductDescription";
        this.productQuantity = 0;
        this.productCost = 0;
        this.productTotalCost = 0;
        this.productDateCreatedOn = DateFormat.getDateTimeInstance().toString();
    }

    public ProductClass(int productID, String productName, String productDescription, int productQuantity, double productCost, double productTotalCost, String productDateCreatedOn) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productQuantity = productQuantity;
        this.productCost = productCost;
        this.productTotalCost = productTotalCost;
        this.productDateCreatedOn = productDateCreatedOn;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductDateCreatedOn() {
        return this.productDateCreatedOn;
    }

    public int getProductID() {
        return this.productID;
    }

    public int getProductQuantity() {
        return this.productQuantity;
    }

    public double getProductCost() {
        return this.productCost;
    }

    public double getProductTotalCost() {
        return this.productTotalCost;
    }

    public void setProductName( String productName_ ){
        this.productName = productName_;
    }

    public void setProductDescription( String productDescription_ ){
        this.productDescription = productDescription_;
    }

    public void setProductCost( double productCost_ ){
        this.productCost = productCost_;
    }

    public void setProductQuantity( int productQuantity_ ){
        this.productQuantity = productQuantity_;
    }

    public void calculateTotalCost(){
        this.productTotalCost = this.productCost * this.productQuantity;
    }
}
