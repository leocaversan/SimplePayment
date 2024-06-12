package br.com.simple_payment.transaction;

import br.com.simple_payment.authorization.AuthorizerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TransactionControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    private Transaction transaction = Transaction.builder()
            .payee(1L)
            .payer(2L)
            .value(BigDecimal.valueOf(50)).build();
    @MockBean
    private AuthorizerService authorizerServiceMock;

    @Test
    void createTransactionExpectedSucessTest() {

        Mockito.when(authorizerServiceMock.authorize()).thenReturn(true);

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
    void createTransactionExpectedFailTest() {

        Mockito.when(authorizerServiceMock.authorize()).thenReturn(false);

        var postResponse = webTestClient
            .post()
            .uri("transaction")
            .bodyValue(transaction)
            .exchange()
            .expectStatus().is4xxClientError()
            .expectBody()
            .returnResult();
    }
    @Test
    void listExpectedSucessTest() {

        Mockito.when(authorizerServiceMock.authorize()).thenReturn(true);
        var getResponse = webTestClient.get()
            .uri("transaction")
            .exchange()
            .expectStatus()
            .is2xxSuccessful();
    }

    @Test
    void listExpectedFailTest() {

        Mockito.when(authorizerServiceMock.authorize()).thenReturn(false);
        var getResponse = webTestClient.get()
                .uri("transaction")
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}