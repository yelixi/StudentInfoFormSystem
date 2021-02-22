package org.sacc.smis.controller;


import org.sacc.smis.entity.ApplicationItem;
import org.sacc.smis.entity.Item;
import org.sacc.smis.entity.ItemType;
import org.sacc.smis.model.RestResult;
import org.sacc.smis.model.UserInfo;
import org.sacc.smis.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//学生提交表单API
@Controller
@Secured("ROLE_STUDENT")
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 学生填写能力类大类(如：学科竞赛和科技竞赛)，itemType表示具体获得的奖项，
     * 文件资料(证明材料)的上传{@link #uploadFile}
     * 文本材料(直接在网页中填写的文本)的上传{@link #uploadText}
     */
    @ResponseBody
    @PostMapping("/uploadItem")
    public RestResult<Integer> uploadItem(@RequestBody Item item, Authentication authentication) {
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        item.setUserId(userInfo.getId());
        return RestResult.success(itemService.uploadItem(item));
    }

    @ResponseBody
    @PostMapping("/uploadFile")
    public RestResult<Boolean> uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                          @RequestParam("itemId") Integer itemId,
                                          Authentication authentication) throws IOException {
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        return RestResult.success(itemService.uploadFile(multipartFile, userInfo.getId(), itemId));
    }

    @ResponseBody
    @PostMapping("/uploadText")
    public RestResult<Boolean> uploadText(@RequestParam("text") String text,
                                          @RequestParam("itemId") Integer itemId,
                                          Authentication authentication){
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        return RestResult.success(itemService.uploadText(text, userInfo.getId(), itemId));
    }

    @ResponseBody
    @GetMapping("/{item_id}")
    public RestResult<Item> getItemById(@PathVariable("item_id") Integer itemId) {
        return null;
    }

    @ResponseBody
    @PostMapping("/updateItem")
    public RestResult<Boolean> updateItem(@RequestBody Item item) {
        return null;
    }


}
