package org.sacc.smis.mapper;

import org.sacc.smis.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ItemTypeRepository extends JpaRepository<ItemType, Integer> {
    ItemType findByUpdatedAt(LocalDateTime CreatedAt);

    @Transactional
    @Modifying
    @Query("update ItemType i set i.dataSource= :dataSource , i.fileSuffix=:fileSuffix where i.id= :id")
    void uploadFile(@Param("dataSource") byte[] dataSource,@Param("fileSuffix") String fileSuffix,@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update ItemType i set i.dataSource= :dataSource where i.id= :id")
    void uploadText(@Param("dataSource") byte[] dataSource,@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update ItemType i set i.updatedAt= :updateAt where i.id= :id")
    void updateUpdateAt(@Param("updateAt") LocalDateTime updateAt,@Param("id") Integer id);
}
