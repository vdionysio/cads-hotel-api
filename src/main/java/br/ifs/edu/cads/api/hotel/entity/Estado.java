package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

import java.util.List;

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

//    @OneToMany(mappedBy = "estado")
//    private List<Cidade> cidades;

    public Long getId() {
        return id;
    }

    public String getUf() {
        return uf;
    }

    public String getNome() {
        return nome;
    }

//    public List<Cidade> getCidades() {
//        return cidades;
//    }
}
