package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "hospede")
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hospede")
    private Long id;

    @Column(name = "nome_hospede", nullable = false)
    private String nome;

    @Column(name = "cpf_hospede", nullable = false, unique = true)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "telefone_hospede", nullable = false)
    private String telefone;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", referencedColumnName = "id_cidade")
    private Cidade cidade;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", unique = true)
    private Usuario usuario;



}