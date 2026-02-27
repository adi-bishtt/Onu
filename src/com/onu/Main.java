package com.onu;

import com.onu.model.Color;
import com.onu.model.Type;
import com.onu.model.Card;

public class Main {
    public static void main(String[] args) {

        Card redFive = new Card(Color.RED , Type.SKIP , 5);
        System.out.println(redFive.getColor());
        System.out.println(redFive.getType());
        System.out.println(redFive.getValue());
    }
}
