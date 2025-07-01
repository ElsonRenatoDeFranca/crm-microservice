package training.cloud.crmmicroservice.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import training.cloud.crmmicroservice.model.dto.TransactionDTO;
import training.cloud.crmmicroservice.model.entity.Transaction;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface TransactionMapper {
    Transaction toEntity(TransactionDTO transaction);
    TransactionDTO toDomain(Transaction transaction);
}