package com.lysenko.Payments.model.repository;


import com.lysenko.Payments.model.entity.request.RequestUnblock;
import com.lysenko.Payments.model.entity.request.StatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RequestUnblockRepository extends JpaRepository<RequestUnblock, Integer> {
    List<RequestUnblock> findRequestUnblockByStatusRequest(StatusRequest statusRequest);

    List<RequestUnblock> findRequestUnblockByAccountId(Integer accountId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO request_unblock (account_id) VALUE (?)", nativeQuery = true)
    void createRequestUnblock(int accountId);
}
