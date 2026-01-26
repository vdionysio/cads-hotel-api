package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enums.FormaPagamento;
import br.ifs.edu.cads.api.hotel.enums.StatusReserva;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim;

    @Column(name = "data_checkin")
    private LocalDateTime dataCheckIn;

    @Column(name = "data_checkout")
    private LocalDateTime dataCheckOut;

    @Column(name = "valor_reserva", nullable = false)
    private BigDecimal valorReserva;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_reserva", nullable = false)
    private StatusReserva statusReserva;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria")
    private CategoriaQuarto categoriaQuarto;

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private Quarto quarto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_hospede")
    private Hospede hospede;

    @ManyToMany
    @JoinTable(
            name = "convidado_reserva",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_hospede")
    )
    private List<Hospede> convidados;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

}
