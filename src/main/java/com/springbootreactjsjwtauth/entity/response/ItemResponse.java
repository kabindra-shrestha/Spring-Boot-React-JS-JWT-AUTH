package com.springbootreactjsjwtauth.entity.response;

import com.springbootreactjsjwtauth.entity.common.Item;

public class ItemResponse extends MessageResponse {

    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
