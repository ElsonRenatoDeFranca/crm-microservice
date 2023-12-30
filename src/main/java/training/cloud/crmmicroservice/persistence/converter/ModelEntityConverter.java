package training.cloud.crmmicroservice.persistence.converter;

public interface ModelEntityConverter<M, E> {
    E toEntity(M model);

    M fromEntity(E entity);
}
