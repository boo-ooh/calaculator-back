package com.bruna.calabrese.calculator.domain.record;

import com.bruna.calabrese.calculator.domain.operation.Operation;
import com.bruna.calabrese.calculator.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "record")
@Entity(name = "record")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="operation_id")
    private Operation operation;

    private Double amount;
    @Column(name="user_balance")
    private Double userBalance;
    @Column(name="operation_response")
    private String operationResponse;
    private LocalDate date;

}
