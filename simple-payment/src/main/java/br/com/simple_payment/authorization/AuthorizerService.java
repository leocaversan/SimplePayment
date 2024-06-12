package br.com.simple_payment.authorization;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.simple_payment.transaction.Transaction;
import br.com.simple_payment.transaction.UnauthorizedTransactionException;



@Service
public class AuthorizerService {

    private RestClient restClient;
     private RestClient.Builder builder;


    public AuthorizerService(RestClient.Builder builder){

        this.restClient = builder
                .baseUrl("https://util.devi.tools/api/v2/authorize")
                .build();
    }
    public boolean authorize(){
        var response = restClient.get()
                .retrieve()
                .toEntity(Authorization.class);
        if (response.getStatusCode().isError() || !response.getBody().isAuthorized())
           return false;

        return true;

    }
}
