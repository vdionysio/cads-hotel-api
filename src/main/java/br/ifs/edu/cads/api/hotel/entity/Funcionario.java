package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enums.Cargo;
import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nome_funcionario", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "cargo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
