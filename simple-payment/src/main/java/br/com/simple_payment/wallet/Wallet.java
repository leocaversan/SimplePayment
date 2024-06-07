package br.com.simple_payment.wallet;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Builder
@Table("WALLETS")
public record Wallet(
    @Id Long id,
    String full_name,
    String cpf,
    String email,
    int type,
    BigDecimal balance 
) {
    public Wallet debit(BigDecimal value){
        return new Wallet(id, full_name, cpf, email, type, balance.subtract(value));
    }
    public Wallet credit(BigDecimal value){
        return new Wallet(id, full_name, cpf, email, type, balance.add(value));
    }
} 
