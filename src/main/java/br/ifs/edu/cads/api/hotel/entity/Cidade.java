package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "cidade",
        uniqueConstraints = {@UniqueConstraint(name = "UniqueUfAndNome", columnNames = {"nome_cidade", "uf"})}
)
public class Cidade {
    @Id
    @Column(name = "id_cidade")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cidade", nullable = false, length = 100)
    private String nome;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "uf", referencedColumnName = "uf")
    private Estado estado;

    public Cidade() {
    }

    public Cidade(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
