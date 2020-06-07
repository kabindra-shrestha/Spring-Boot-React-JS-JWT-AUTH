package com.springbootreactjsjwtauth.entity.response;

import com.springbootreactjsjwtauth.entity.common.Item;

public class ItemListResponse extends MessageResponse {

    private Iterable<Item> items;

    public Iterable<Item> getItems() {
        return items;
    }

    public void setItems(Iterable<Item> items) {
        this.items = items;
    }

}
