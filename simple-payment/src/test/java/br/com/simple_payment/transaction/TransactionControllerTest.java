package br.com.simple_payment.transaction;

import br.com.simple_payment.error.Error;
import br.com.simple_payment.wallet.Wallet;
import org.hibernate.engine.transaction.internal.TransactionImpl;
import org.junit.jupiter.api.Test;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
@AutoConfigureWebTestClient
class TransactionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private Transaction transaction = Transaction.builder()
            .payee(1L)
            .payer(2L)
            .value(BigDecimal.valueOf(50)).build();

    @Test
    void createTransaction() {
        if (expectedFail() || expectedSucess()) { } else{
            assertEquals(1,2);
        }
    }

    boolean expectedSucess() {

        try {
            var postResponse = webTestClient
                    .post()
                    .uri("transaction")
                    .bodyValue(transaction)
                    .exchange()
                    .expectStatus().is2xxSuccessful()
                    .expectBody(Transaction.class)
                    .value(t -> assertNotNull(t.id()))
                    .value(t -> assertEquals(transaction.value(), t.value()))
                    .value(t -> assertEquals(transaction.payee(), t.payee()))
                    .value(t -> assertEquals(transaction.payer(), t.payer()))
                    .value(t -> assertNotNull(t.createdAt()))
                    .returnResult();

            return true;

        } catch (Exception e) {

            System.out.println("Error: "+ e);
            return false;
        }
    }

    boolean expectedFail() {
            try {
                var postResponse = webTestClient.post()
                        .uri("transaction")
                        .bodyValue(transaction)
                        .exchange()
                        .expectBody(Error.class)
                        .value( E -> assertEquals("Transaction not authorized", E.Error()))
                        .returnResult();

                return true;

            }catch (Exception e) {

                System.out.println("Error: "+ e);
                return false;
            }
    }

    @Test
    void listTest() {

        var getResponse = webTestClient.get()
                .uri("transaction")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}