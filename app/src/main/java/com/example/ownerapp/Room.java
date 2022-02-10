package com.example.ownerapp;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public Integer room_no;
    public String time;
    public Integer total_price;
    public Boolean confirm;
    List<String> items = new ArrayList<String>();
    List<Integer> quantity = new ArrayList<Integer>();
    List<Integer> price = new ArrayList<Integer>();
}
