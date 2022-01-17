package com.farinia.farinia.model;

public class Producto {
    private static int cuentaIdProducto = 1;
    private long id;
    private String title;
    private double price;

    public Producto(String title,double price){
        this.id = sumarId();
        this.title = title;
        this.price = price;
    }

    private long sumarId(){
        return (long)cuentaIdProducto++;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public double getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void SetTitle(String title) {
        this.title = title;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
