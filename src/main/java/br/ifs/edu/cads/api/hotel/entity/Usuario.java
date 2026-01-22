package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enums.Cargo;
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

    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email, String senha, Cargo cargo) {
        this(email, senha);
        this.papel = PapelUsuario.valueOf(cargo.toString());
    }

    public Usuario(String email, String senha, PapelUsuario papel) {
        this(email, senha);
        this.papel = papel;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public PapelUsuario getPapel() {
        return papel;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
