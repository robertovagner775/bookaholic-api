package com.bookaholic.backend.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CobBaseInfoAdicionais {
  @JsonProperty("nome")
  private String nome = null;

  @JsonProperty("valor")
  private String valor = null;

  public CobBaseInfoAdicionais nome(String nome) {
    this.nome = nome;
    return this;
  }

   /**
   * Nome do campo.
   * @return nome
  **/
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public CobBaseInfoAdicionais valor(String valor) {
    this.valor = valor;
    return this;
  }

   /**
   * Dados do campo.
   * @return valor
  **/
 
  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CobBaseInfoAdicionais cobBaseInfoAdicionais = (CobBaseInfoAdicionais) o;
    return Objects.equals(this.nome, cobBaseInfoAdicionais.nome) &&
        Objects.equals(this.valor, cobBaseInfoAdicionais.valor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, valor);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CobBaseInfoAdicionais {\n");
    
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    valor: ").append(toIndentedString(valor)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}