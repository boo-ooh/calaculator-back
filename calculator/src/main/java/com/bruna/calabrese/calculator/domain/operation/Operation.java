package com.bruna.calabrese.calculator.domain.operation;

import com.bruna.calabrese.calculator.domain.record.Record;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name = "operation")
@Entity(name = "operation")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private OperationTypes type;
    private Double cost;
    @Column(name = "display_name")
    private String displayName;
}
