package io.github.cursodsousa.libraryapi.controller.dto;

import java.time.LocalDate;

public record AutorDTO(String nome, LocalDate dataNascimento, String nacionalidade) {
}
