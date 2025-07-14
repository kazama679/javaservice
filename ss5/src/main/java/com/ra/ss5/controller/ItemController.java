package com.ra.ss5.controller;

import com.ra.ss5.model.entity.Item;
import com.ra.ss5.model.entity.ItemList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final List<Item> itemList = List.of(
            new Item(1L, "Vivo X200 Ultra", 1000.0),
            new Item(2L, "Xiaomi S15 Ultra", 1050.0),
            new Item(3L, "Oppo Find X8 Ultra", 1100.0)
    );

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> getItemsJson() {
        return itemList;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ItemList getItemsXml() {
        return new ItemList(itemList);
    }
}
