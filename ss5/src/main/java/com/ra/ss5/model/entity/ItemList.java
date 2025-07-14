package com.ra.ss5.model.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;

public class ItemList {
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Item> items;

    public ItemList() {}

    public ItemList(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}
