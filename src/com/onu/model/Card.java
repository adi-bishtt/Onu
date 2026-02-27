package com.onu.model;

public class Card {

    public Color color;
    public Type type;
    public int value;

    public Card(Color color, Type type , int value) {
        this.color = color;
        this.type = type;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
