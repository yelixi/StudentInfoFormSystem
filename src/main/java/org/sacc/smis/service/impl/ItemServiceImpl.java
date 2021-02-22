package org.sacc.smis.service.impl;

import org.sacc.smis.entity.*;
import org.sacc.smis.mapper.*;
import org.sacc.smis.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ApplicationItemRepository applicationItemRepository;
    @Autowired
    private ItemTypeRepository itemTypeRepository;


    /**
     * 提交表单
     *
     * @param item
     * @return
     */
    @Override
    public boolean addItem(Item item) {
        ApplicationItem applicationItem = new ApplicationItem();
        applicationItem.setApplicationId(item.getId());
        applicationItem.setItemId(item.getId());
        itemRepository.save(item);
        applicationItemRepository.save(applicationItem);
        return true;
    }

    @Override
    public boolean addItemType(ItemType itemType) {
        itemTypeRepository.save(itemType);
        return true;
    }

    @Override
    public boolean addApplicationItem(ApplicationItem applicationItem) {
        applicationItemRepository.save(applicationItem);
        return true;
    }

    @Override
    public boolean uploadFile(MultipartFile file, Integer userId,Integer itemId) throws IOException {
        Item item = itemRepository.findById(itemId).get();
        ItemType itemType = itemTypeRepository.findById(item.getType().getId()).get();
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        int i = fileName.lastIndexOf('.');
        String fileSuffix = fileName.substring(i);
        byte[] bytes = file.getBytes();
        itemTypeRepository.uploadFile(bytes,fileSuffix,itemType.getId());
        itemTypeRepository.updateUpdateAt(LocalDateTime.now(),itemType.getId());
        return true;
    }

    @Override
    public boolean uploadText(String text, Integer userId, Integer itemId) {
        Item item = itemRepository.findById(itemId).get();
        ItemType itemType = itemTypeRepository.findById(item.getType().getId()).get();
        byte[] bytes = text.getBytes();
        itemTypeRepository.uploadText(bytes,itemType.getId());
        itemTypeRepository.updateUpdateAt(LocalDateTime.now(),itemType.getId());
        return true;
    }

    @Override
    public Integer uploadItem(Item item) {
        LocalDateTime localDateTime =LocalDateTime.now();
        item.setCreatedAt(localDateTime);
        item.setUpdatedAt(localDateTime);
        ItemType itemType = item.getType();
        itemType.setCreatedAt(localDateTime);
        itemType.setUpdatedAt(localDateTime);
        itemRepository.save(item);
        return itemRepository.findByUpdatedAt(localDateTime).getId();
    }

}
