package org.sacc.smis.service;

import org.sacc.smis.entity.ApplicationItem;
import org.sacc.smis.entity.Item;
import org.sacc.smis.entity.ItemType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ItemService {
    //    Item findItemByApplicationID(Integer applicationId);
    boolean addItem(Item item);

    boolean addItemType(ItemType itemType);

    boolean addApplicationItem(ApplicationItem applicationItem);

    boolean uploadFile(MultipartFile file,Integer userId,Integer itemId) throws IOException;

    boolean uploadText(String text,Integer userId,Integer itemId);

    Integer uploadItem(Item item);
}
