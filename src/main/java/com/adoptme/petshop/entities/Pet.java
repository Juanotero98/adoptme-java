package com.adoptme.petshop.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pets")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Schema(description = "Representa una mascota")
public class Pet {
    @Schema(description = "Identificador unico de la mascota", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Nombre de la mascota", example = " Arcan")
    @Getter @Setter private String name;
    @Schema(description = "Edad de la mascota", example = "3")
    @Getter @Setter private Integer age;

    @Schema(description = "Identificador del dueño de la mascota", example = "1")
    @ManyToOne @JoinColumn(name = "owner_id")
    @Getter @Setter private User owner;

}
