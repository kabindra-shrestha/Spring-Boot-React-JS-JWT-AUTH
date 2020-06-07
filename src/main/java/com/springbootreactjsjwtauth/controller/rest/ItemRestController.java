package com.springbootreactjsjwtauth.controller.rest;

import com.springbootreactjsjwtauth.entity.common.Item;
import com.springbootreactjsjwtauth.entity.response.ItemListResponse;
import com.springbootreactjsjwtauth.entity.response.ItemResponse;
import com.springbootreactjsjwtauth.entity.response.SuccessResponse;
import com.springbootreactjsjwtauth.service.ItemService;
import com.springbootreactjsjwtauth.service.UserService;
import com.springbootreactjsjwtauth.utils.GlobalSuccessResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.springbootreactjsjwtauth.utils.LinkConfig.*;

@RestController
@RequestMapping(value = BASE_URL + ITEM_URL)
public class ItemRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

    private GlobalSuccessResponse globalSuccessResponse;

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<SuccessResponse> add(@RequestHeader(value = "Authorization") String authorization,
                                               @RequestParam String name, @RequestParam int quantity) {
        userService.getAuthorization(authorization);

        itemService.checkItem(name);

        itemService.save(name, quantity);

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_ITEM_ADDED, HttpStatus.OK);
    }

    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity<SuccessResponse> update(@RequestHeader(value = "Authorization") String authorization,
                                                  @PathVariable long id, @RequestParam String name, @RequestParam int quantity) throws NotFoundException {
        userService.getAuthorization(authorization);

        long ids = id;

        itemService.checkItemUpdate(ids, name);

        itemService.checkItemById(ids);

        itemService.update(ids, name, quantity);

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_ITEM_UPDATED, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<SuccessResponse> delete(@RequestHeader(value = "Authorization") String authorization,
                                                  @PathVariable int id) throws NotFoundException {
        userService.getAuthorization(authorization);

        long ids = id;

        itemService.checkItemById(ids);

        itemService.deleteById(ids);

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_ITEM_DELETED, HttpStatus.OK);
    }

    @GetMapping(value = "/info/{id}")
    @ResponseBody
    public ResponseEntity<ItemResponse> info(@RequestHeader(value = "Authorization") String authorization,
                                             @PathVariable String id) {
        userService.getAuthorization(authorization);

        Item product = itemService.checkItemById(Long.parseLong(id));

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.itemResponseHandler(true, SUCCESS_ITEM_FETCHED, HttpStatus.OK, product);
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<ItemListResponse> item(@RequestHeader(value = "Authorization") String authorization,
                                                 @RequestParam String startDate) {
        userService.getAuthorization(authorization);

        Iterable<Item> itemList = itemService.checkRecentItem();

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.itemListResponseHandler(true, SUCCESS_ITEM_FETCHED, HttpStatus.OK, itemList);
    }

}
