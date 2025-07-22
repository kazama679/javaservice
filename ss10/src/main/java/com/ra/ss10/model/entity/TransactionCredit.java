package com.ra.ss10.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TransactionCredit {
    @Id
    private UUID id;

    @ManyToOne
    private Account accountReceiver;

    @ManyToOne
    private CreditCard creditCardSender;

    private String note;
    private Double money;

    private String status;
}
