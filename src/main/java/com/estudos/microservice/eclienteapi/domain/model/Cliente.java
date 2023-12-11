package com.estudos.microservice.eclienteapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Table(name= "T_CLIENTE")
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @SequenceGenerator(name="SQ_CLIENTE", sequenceName="SQ_CLIENTE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SQ_CLIENTE")
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NM_ALOCACAO_DE_PROVA", nullable = false, length = 50)
    @Size(min = 3, message = "Nome não pode ser menor que 3 caracteres")
    private String nome;
}
