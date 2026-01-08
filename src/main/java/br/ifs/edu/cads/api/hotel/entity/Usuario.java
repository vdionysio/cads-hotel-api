package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enums.PapelUsuario;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "papel_usuario", nullable = false)
    @Enumerated(EnumType.STRING)
    private PapelUsuario papel;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Funcionario funcionario;

    @OneToOne
    @JoinColumn(name = "hospede_id", nullable = true)
    private Hospede hospede;
}
