package org.sacc.smis.service;

import org.sacc.smis.entity.StudentMoralEducationItem;

/**
 * Created by 林夕
 * Date 2021/2/17 13:23
 */
public interface StudentMoralEducationService {

    boolean uploadItem(StudentMoralEducationItem item);

    boolean updateItem(StudentMoralEducationItem item);

    boolean updateCommentItem(StudentMoralEducationItem item);
}
