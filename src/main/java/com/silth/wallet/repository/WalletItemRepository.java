package com.silth.wallet.repository;

import com.silth.wallet.entity.WalletItem;
import com.silth.wallet.util.enums.TypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {
    Page<WalletItem> findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(Long wallet, Date init, Date end, Pageable page);

    List<WalletItem> findByWalletIdAndType(Long wallet, TypeEnum type);

    @Query(value = "select sum(value) from WalletItem wi where wi.wallet.id = :id")
    BigDecimal sumByWalletId(Long id);
}
