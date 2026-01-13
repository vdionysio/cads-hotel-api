package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enums.Cargo;
import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;

    @Column(name = "nome_funcionario", nullable = false)
    private String nome;

    @Column(name = "cpf_funcionario", nullable = false, unique = true)
    private String cpf;

    @Column(name = "cargo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", unique = true, nullable = false)
    private Usuario usuario;
}
