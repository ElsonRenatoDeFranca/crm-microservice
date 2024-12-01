package com.poc.microservice.mapper;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.poc.microservice.model.UnnaxTransaction;
import com.poc.microservice.model.UnnaxTransactionDto;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UnnaxTransactionMapper {

    UnnaxTransactionDto toUnnaxTransactionDto(UnnaxTransaction unnaxTransaction);
    UnnaxTransaction toUnnaxTransaction(UnnaxTransactionDto unnaxTransactionDto);
    List<UnnaxTransactionDto> entityListToDtoList(List<UnnaxTransaction> unnaxTransactions);
}
