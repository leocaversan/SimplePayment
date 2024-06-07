package br.com.simple_payment.wallet;


import org.junit.jupiter.api.BeforeEach;
import br.com.simple_payment.wallet.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;


//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
class WalletRepositoryTest {

    private Wallet wallet = Wallet.builder()
            .cpf("5955414452")
            .email("espro@gmail.com")
            .full_name("espro 55 ")
            .type(1)
            .balance(BigDecimal.valueOf(50)).build();

//    @Autowired
//    private WalletRepository walletRepository;

//    @BeforeEach
//    public void init( WalletRepository walletRepository){
//        this.walletRepository = walletRepository;
//    }
    @Test
    public void saveTest(){

//        this.walletRepository.save(wallet);
//        var walletSaved = walletRepository.findAll();
        System.out.println(wallet);

    }

}