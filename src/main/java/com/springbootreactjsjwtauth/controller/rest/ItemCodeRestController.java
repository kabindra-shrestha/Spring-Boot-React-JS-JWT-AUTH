package com.springbootreactjsjwtauth.controller.rest;

import com.springbootreactjsjwtauth.entity.common.Item;
import com.springbootreactjsjwtauth.entity.common.ItemCode;
import com.springbootreactjsjwtauth.entity.common.Lot;
import com.springbootreactjsjwtauth.entity.common.Users;
import com.springbootreactjsjwtauth.entity.response.SuccessResponse;
import com.springbootreactjsjwtauth.service.ItemCodeService;
import com.springbootreactjsjwtauth.service.ItemService;
import com.springbootreactjsjwtauth.service.LotService;
import com.springbootreactjsjwtauth.service.UserService;
import com.springbootreactjsjwtauth.utils.GenerateItemCode;
import com.springbootreactjsjwtauth.utils.GlobalSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.springbootreactjsjwtauth.utils.LinkConfig.*;

@RestController
@RequestMapping(value = BASE_URL + ITEM_CODE_URL)
public class ItemCodeRestController {

    private GenerateItemCode generateItemCode = new GenerateItemCode();

    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private LotService lotService;
    @Autowired
    private ItemCodeService itemCodeService;

    private GlobalSuccessResponse globalSuccessResponse;

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<SuccessResponse> add(@RequestHeader(value = "Authorization") String authorization,
                                               @RequestParam int lotName, @RequestParam int itemId) {
        Users user = userService.getAuthorization(authorization);

        Item item = itemService.checkItemById(itemId);

        Lot lot = lotService.checkLotByLotName(lotName);

        String secretKey = generateItemCode.getBcryptHashItemCode(lot.getName() + "" + lot.getItemid().getId());
        String viewKey = lot.getName() + "-" + lot.getItemid().getId();

        for (int i = 0; i < item.getQuantity(); i++) {
            itemCodeService.save(secretKey, viewKey, lot, null);
        }

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_ITEM_CODE_ADDED, HttpStatus.OK);
    }

    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity<SuccessResponse> update(@RequestHeader(value = "Authorization") String authorization,
                                                  @PathVariable long id, @RequestParam int lotName,
                                                  @RequestParam int itemId) {
        Users user = userService.getAuthorization(authorization);

        long ids = id;

        ItemCode itemCode = itemCodeService.checkItemCodeById(ids);

        itemService.checkItemById(itemId);

        Lot lot = lotService.checkLotByLotName(lotName);

        String secretKey = itemCode.getSecretKey();
        String viewKey = itemCode.getViewKey();

        itemCodeService.update(ids, secretKey, viewKey, lot, null);

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_ITEM_CODE_UPDATED, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<SuccessResponse> delete(@RequestHeader(value = "Authorization") String authorization,
                                                  @PathVariable long id) {
        userService.getAuthorization(authorization);

        long ids = id;

        itemCodeService.checkItemCodeById(ids);

        itemCodeService.deleteById(ids);

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_ITEM_CODE_DELETED, HttpStatus.OK);
    }

    /*@GetMapping(value = "/info/{id}")
    @ResponseBody
    public ResponseEntity<ItemResponse> info(@RequestHeader(value = "Authorization") String authorization,
                                             @PathVariable String id) {
        userService.getAuthorization(authorization);

        Item product = itemCodeService.checkItemUpdateId(Long.parseLong(id));

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.itemResponseHandler(true, SUCCESS_ITEM_CODE_FETCHED, HttpStatus.OK, product);
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<ItemListResponse> item(@RequestHeader(value = "Authorization") String authorization,
                                                 @RequestParam String startDate) {
        Users user = userService.getAuthorization(authorization);

        Iterable<Item> itemList = itemCodeService.checkRecentItem();

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.itemListResponseHandler(true, SUCCESS_ITEM_CODE_FETCHED, HttpStatus.OK, itemList);
    }*/

}
