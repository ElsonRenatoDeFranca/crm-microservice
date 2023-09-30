package training.cloud.crmmicroservice.api;

public final class ApiDocumentationConstants {
    protected ApiDocumentationConstants() {
        throw new UnsupportedOperationException("Instantiation not allowed");
    }

    static final String AUTHENTICATION_HEADER = "SSO-AUTH";

    static final String ACCEPT_HEADER = "application/json;version=1";

    static final String OK_CODE = "200";
    static final String CREATION_CODE = "201";
    static final String CREATION_DESCRIPTION = "Resource created into database";

    static final String ACCEPTED_CODE = "202";
    static final String NO_CONTENT_CODE = "204";
    static final String BAD_REQUEST_CODE = "400";
    static final String BAD_REQUEST_DESCRIPTION = "A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.";
    static final String SERVICE_UNAVAILABLE_CODE = "503";
    static final String SERVICE_UNAVAILABLE_DESCRIPTION = "Serviço indisponível.";
    static final String UNAUTHORIZED_CODE = "401";
    static final String UNAUTHORIZED_DESCRIPTION = "O token tem escopo incorreto ou uma política de segurança foi violada.";
    static final String NOT_FOUND_CODE = "404";
    static final String NOT_FOUND_DESCRIPTION = "Recurso não encontrado.";
    static final String DELETE_DESCRIPTION = "Recurso removido.";
    static final String UPDATE_DESCRIPTION = "Recurso atualizado.";


    static final String INTERNAL_SERVER_ERROR_CODE = "500";
    static final String INTERNAL_SERVER_ERROR_DESCRIPTION = "Ocorreu um erro no gateway da API ou no microsserviço.";

    public static final String CHANNEL_API_ROOT_PATH = "api/bbb/corporate/domain/channels";
    public static final String ERROR_API_ROOT_PATH = "api/bbb/corporate/domain/error";
    public static final String OFFERING_API_ROOT_PATH = "api/bbb/corporate/domain/offerings";
    public static final String SERVICE_API_ROOT_PATH = "api/bbb/corporate/domain/services";
    public static final String FLAG_API_ROOT_PATH = "api/bbb/corporate/domain/flag";
    public static final String ROLES_API_ROOT_PATH = "api/bbb/corporate/domain/roles";
    public static final String RALM_API_ROOT_PATH = "api/bbb/corporate/domain/realm";
    public static final String PARAMETERS_API_ROOT_PATH = "api/bbb/corporate/domain/parameters";
    public static final String CRM_API_ROOT_PATH = "/api/crm";

}
