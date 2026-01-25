package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comodidade")
public class Comodidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comodidade")
    private Integer id;

    @Column(name = "nome_comodidade", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao_comodidade", nullable = false)
    private String descricao;

    public Comodidade() {
    }

    public Comodidade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
