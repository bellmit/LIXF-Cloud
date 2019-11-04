package ll.mrli.lixf.auth.properties;

import lombok.Data;

@Data
public class LixfClientsProperties {

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
