package ru.portalPFR.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.portalPFR.entity.PersistentLoginEntity;

public interface PersistentLoginRepository extends PagingAndSortingRepository<PersistentLoginEntity, String> {
    @Modifying
    @Transactional
    void deleteByUsername(String username);

    @Modifying
    @Transactional
    void deleteBySeries(String series);

    PersistentLoginEntity findByUsername(String username);
    PersistentLoginEntity findBySeries(String series);
}