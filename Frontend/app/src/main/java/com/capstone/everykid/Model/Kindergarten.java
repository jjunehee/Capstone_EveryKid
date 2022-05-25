package com.capstone.everykid.Model;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.ArrayList;
import java.util.List;

@Xml(name="response")
public class Kindergarten {
    @Element
    List<ItemClass> items;

    public List<ItemClass> getItems() {
        return items;
    }

    public void setItems(List<ItemClass> items) {
        this.items = items;
    }
}
