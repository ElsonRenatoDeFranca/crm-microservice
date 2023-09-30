package training.cloud.crmmicroservice.converter;

public interface ModelToModelConverter<M, N> {
    N convert(M modelFrom);
}
