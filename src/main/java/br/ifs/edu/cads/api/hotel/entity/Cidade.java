package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class Cidade {
    @Id
    @Column(name = "id_cidade")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cidade", nullable = false, length = 100)
    private String nome;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "uf")
    private Estado estado;
}
