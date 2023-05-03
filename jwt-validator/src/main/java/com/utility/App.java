package com.utility;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import java.net.URL;

public class App {
    public static void main(String[] args) {
        validate("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJIYkVIUE5COWRLOWlpNG16YUhzejJTYUZzTFo5ZkloYTdwcmpPdW5YUE5NIn0.eyJleHAiOjE2NzY4MTQ4NTYsImlhdCI6MTY3NjgxNDc5NiwiYXV0aF90aW1lIjowLCJqdGkiOiIyOGYxODUzYy00MGRiLTQ2ZGItYjc2My00ZTlhMTkyZDYzODAiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL21hc3RlciIsImF1ZCI6Im11bGUiLCJzdWIiOiIyYmRiMjUzMy04Y2ZlLTQ3ZWEtOWY0Zi1lOGJlMDEyYzdhM2EiLCJ0eXAiOiJJRCIsImF6cCI6Im11bGUiLCJhdF9oYXNoIjoiM3FGU0otNXI0R2NOT0ZoeWxpZnlIZyIsImFjciI6IjEiLCJjbGllbnRIb3N0IjoiMTcyLjE3LjAuMSIsImNsaWVudElkIjoibXVsZSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LW11bGUiLCJjbGllbnRBZGRyZXNzIjoiMTcyLjE3LjAuMSJ9.E4nINN9ebHnYxfJovL_Qwd9uB6AyGE6-jwSWiENfxzAOy0iZJriT0bsENjgf3XuoA0amfEwN6DKBr8eQeVxdkpOhq_rZpo4GgDYz11cOvYX-3-qVbr2-LCssSxmqcvvNq1lWj6VqkbcVGHZzrvjHdST05lNAJG1Jfnd59u9Tjoxmy7RJktVfasaSPLZm_qdmffAAPXKwLA-XD9HOZsUV-MHgOq2yg7q-sdzIokWtfCnKdjGjZ3XVMmTNAa_H1Vqh8Nab3Nsi1kxuAHi10Xac70NUgATcSEICfclwMmS02SkRo5PZbDRinKQMWWNmoWRCaS97qpT_NKb6Urez0ea1uw");
    }


    public static void validate(String accessToken) {
        try {
            ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();

            JWKSource<SecurityContext> keySource = new RemoteJWKSet<>(new URL("http://localhost:8080/realms/master/protocol/openid-connect/certs"));
            JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, keySource);
            jwtProcessor.setJWSKeySelector(keySelector);

            JWTClaimsSet claimsSet = jwtProcessor.process(accessToken, null);
            System.out.println(claimsSet.toJSONObject());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
