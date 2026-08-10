package com.rraccountancy.app.repository;

import com.rraccountancy.app.domain.CapitalGainReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CapitalGainReturnRepository
        extends JpaRepository<CapitalGainReturn, Long>, JpaSpecificationExecutor<CapitalGainReturn> {

    @Query("select distinct c.clientType from CapitalGainReturn c order by c.clientType")
    List<String> findDistinctClientTypes();

    @Query("select distinct c.assetType from CapitalGainReturn c order by c.assetType")
    List<String> findDistinctAssetTypes();

    @Query("select distinct c.taxYearStart from CapitalGainReturn c order by c.taxYearStart desc")
    List<Integer> findDistinctTaxYears();
}
