package com.bookaholic.backend.model;



import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


import java.util.ArrayList;
import java.util.List;
/**
 * Dados criados ou alterados da cobrança imediata via API Pix
 */

public class CobGerada {
  @JsonProperty("chave")
  private String chave = null;

  @JsonProperty("solicitacaoPagador")
  private String solicitacaoPagador = null;

  @JsonProperty("infoAdicionais")
  private List<CobBaseInfoAdicionais> infoAdicionais = null;

  @JsonProperty("devedor")
  private Object devedor = null;

  @JsonProperty("loc")
  private Loc loc = null;

  @JsonProperty("location")
  private String location = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    ATIVA("ATIVA"),
    CONCLUIDA("CONCLUIDA"),
    REMOVIDA_PELO_USUARIO_RECEBEDOR("REMOVIDA_PELO_USUARIO_RECEBEDOR"),
    REMOVIDA_PELO_PSP("REMOVIDA_PELO_PSP");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("valor")
  private Object valor = null;

  public CobGerada chave(String chave) {
    this.chave = chave;
    return this;
  }

   /**
   * # Formato do campo chave  * O campo chave determina a chave Pix registrada no DICT que será utilizada para a cobrança. Essa chave será lida pelo aplicativo do PSP do pagador para consulta ao DICT, que retornará a informação que identificará o recebedor da cobrança. * Os tipos de chave podem ser: telefone, e-mail, cpf/cnpj ou EVP. * O formato das chaves pode ser encontrado na seção \&quot;Formatação das chaves do DICT no BR Code\&quot; do [Manual de Padrões para iniciação do Pix](https://www.bcb.gov.br/estabilidadefinanceira/pagamentosinstantaneos). 
   * @return chave
  **/
  
  public String getChave() {
    return chave;
  }

  public void setChave(String chave) {
    this.chave = chave;
  }

  public CobGerada solicitacaoPagador(String solicitacaoPagador) {
    this.solicitacaoPagador = solicitacaoPagador;
    return this;
  }

   /**
   * O campo solicitacaoPagador, opcional, determina um texto a ser apresentado ao pagador para que ele possa digitar uma informação correlata, em formato livre, a ser enviada ao recebedor. Esse texto será preenchido, na pacs.008, pelo PSP do pagador, no campo RemittanceInformation &lt;RmtInf&gt;. O tamanho do campo &lt;RmtInf&gt; na pacs.008 está limitado a 140 caracteres.
   * @return solicitacaoPagador
  **/
  
  public String getSolicitacaoPagador() {
    return solicitacaoPagador;
  }

  public void setSolicitacaoPagador(String solicitacaoPagador) {
    this.solicitacaoPagador = solicitacaoPagador;
  }

  public CobGerada infoAdicionais(List<CobBaseInfoAdicionais> infoAdicionais) {
    this.infoAdicionais = infoAdicionais;
    return this;
  }

  public CobGerada addInfoAdicionaisItem(CobBaseInfoAdicionais infoAdicionaisItem) {
    if (this.infoAdicionais == null) {
      this.infoAdicionais = new ArrayList<CobBaseInfoAdicionais>();
    }
    this.infoAdicionais.add(infoAdicionaisItem);
    return this;
  }

   /**
   * Cada respectiva informação adicional contida na lista (nome e valor) deve ser apresentada ao pagador.
   * @return infoAdicionais
  **/

  public List<CobBaseInfoAdicionais> getInfoAdicionais() {
    return infoAdicionais;
  }

  public void setInfoAdicionais(List<CobBaseInfoAdicionais> infoAdicionais) {
    this.infoAdicionais = infoAdicionais;
  }

  public CobGerada devedor(Object devedor) {
    this.devedor = devedor;
    return this;
  }

   /**
   * Os campos aninhados sob o objeto devedor são opcionais e identificam o devedor, ou seja, a pessoa ou a instituição a quem a cobrança está endereçada. Não identifica, necessariamente, quem irá efetivamente realizar o pagamento. Um CPF pode ser o devedor de uma cobrança, mas pode acontecer de outro CPF realizar, efetivamente, o pagamento do documento. Não é permitido que o campo devedor.cpf e campo devedor.cnpj estejam preenchidos ao mesmo tempo. Se o campo devedor.cnpj está preenchido, então o campo devedor.cpf não pode estar preenchido, e vice-versa. Se o campo devedor.nome está preenchido, então deve existir ou um devedor.cpf ou um campo devedor.cnpj preenchido.
   * @return devedor
  **/
  
  public Object getDevedor() {
    return devedor;
  }

  public void setDevedor(Object devedor) {
    this.devedor = devedor;
  }

  public Loc loc(Loc loc) {
    this.loc = loc;
    return loc;
  }

   /**
   * Get loc
   * @return loc
  **/

  public Loc getLoc() {
    return (Loc) this.loc;
  }

  public void setLoc(Loc loc) {
    this.loc = loc;
  }

   /**
   * Localização do Payload a ser informada na criação da cobrança.
   * @return location
  **/
 
  public String getLocation() {
    return location;
  }

  public CobGerada status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
 
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public CobGerada valor(Object valor) {
    this.valor = valor;
    return this;
  }

   /**
   * Get valor
   * @return valor
  **/

  public Object getValor() {
    return valor;
  }

  public void setValor(Object valor) {
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
    CobGerada cobGerada = (CobGerada) o;
    return Objects.equals(this.chave, cobGerada.chave) &&
        Objects.equals(this.solicitacaoPagador, cobGerada.solicitacaoPagador) &&
        Objects.equals(this.infoAdicionais, cobGerada.infoAdicionais) &&
        Objects.equals(this.devedor, cobGerada.devedor) &&
        Objects.equals(this.loc, cobGerada.loc) &&
        Objects.equals(this.location, cobGerada.location) &&
        Objects.equals(this.status, cobGerada.status) &&
        Objects.equals(this.valor, cobGerada.valor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chave, solicitacaoPagador, infoAdicionais, devedor, loc, location, status, valor);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CobGerada {\n");
    
    sb.append("    chave: ").append(toIndentedString(chave)).append("\n");
    sb.append("    solicitacaoPagador: ").append(toIndentedString(solicitacaoPagador)).append("\n");
    sb.append("    infoAdicionais: ").append(toIndentedString(infoAdicionais)).append("\n");
    sb.append("    devedor: ").append(toIndentedString(devedor)).append("\n");
    sb.append("    loc: ").append(toIndentedString(loc)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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