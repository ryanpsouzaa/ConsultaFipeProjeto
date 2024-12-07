package br.com.alura.tabelafipe.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelo (@JsonAlias("modelos") List<Dados> modelos){
}
