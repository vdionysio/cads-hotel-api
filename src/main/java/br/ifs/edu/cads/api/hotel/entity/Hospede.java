package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospede")
public class Hospede {

    @Id
    @Column(name = "id_hospede")
    private Long id;
}