package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estado", uniqueConstraints = {
        @UniqueConstraint(name = "uk_estado_nome", columnNames = "nome_estado"),
        @UniqueConstraint(name = "uk_estado_uf", columnNames = "uf")
})
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @Column(name = "nome_estado", nullable = false)
    private String nome;

    public Estado() {

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
