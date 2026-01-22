package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "hospede")
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hospede")
    private Long id;

    @Column(name = "nome_hospede", nullable = false)
    private String nome;

    @Column(name = "cpf_hospede", nullable = false, unique = true)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "telefone_hospede", nullable = false)
    private String telefone;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", referencedColumnName = "id_cidade")
    private Cidade cidade;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", unique = true)
    private Usuario usuario;

    public Hospede() {}

    public Hospede(String nome, String cpf, LocalDate dataNascimento, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}