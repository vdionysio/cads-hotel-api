package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;

    @Column(name = "uf", nullable = false, length = 2, unique = true)
    private String uf;

    @Column(name = "nome_estado", nullable = false, unique = true)
    private String nome;

    public Estado(){

    }

    public Estado(String nome, String uf) {
        this.nome = nome;
        this.uf = uf;
    }

    public Long getId() {
        return id;
    }

    public String getUf() {
        return uf;
    }

    public String getNome() {
        return nome;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
