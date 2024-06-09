package br.com.simple_payment.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Builder
@Table("TRANSACTIONS")
public record Transaction(
    @Id Long id,
    Long payer,
    Long payee,
    BigDecimal value,
    @CreatedDate LocalDateTime createdAt) {

    public Transaction {
        value = value.setScale(2);
        createdAt = LocalDateTime.now();
    }


}
