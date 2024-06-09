package br.com.simple_payment.transaction;

import br.com.simple_payment.wallet.Wallet;
import org.hibernate.engine.transaction.internal.TransactionImpl;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    void createTransactionTest() {
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