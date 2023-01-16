/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacypos;

/**
 *
 * @author Jonas Lacandola
 */
public class Items {

    private int _code, _quantity;
    private double _price;
    private String _item;
    public Items(int code, double price, String item) {
        setCode(code);
        setPrice(price);
        setItem(item);
    }

    public void setCode(int numCode) {
        _code = numCode;
    }
    public int getCode() {
        return _code;
    }

    public void setPrice(double numPrice) {
        _price = numPrice;
    }

    public double getPrice() {
        return _price;
    }

    public void setItem(String strItem) {
        _item = strItem;
    }
    public String getItem() {
        return _item;
    }

    public void setQuantity(int qty) {
        _quantity += qty;
    }
    public int getQuantity() {
        return _quantity;
    }
}

