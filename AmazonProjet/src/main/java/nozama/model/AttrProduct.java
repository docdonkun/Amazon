package nozama.model;
// Generated 21 mars 2016 08:24:02 by Hibernate Tools 4.3.1.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AttrProduct generated by hbm2java
 */
@Entity
@Table(name = "attr_product", catalog = "nozama")
public class AttrProduct implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Integer idAttrProduct;
  private Product product;
  private String attribut;
  private String value;

  public AttrProduct() {}

  public AttrProduct(Product product, String attribut, String value) {
    this.product = product;
    this.attribut = attribut;
    this.value = value;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)


  @Column(name = "id_attr_product", unique = true, nullable = false)
  public Integer getIdAttrProduct() {
    return this.idAttrProduct;
  }

  public void setIdAttrProduct(Integer idAttrProduct) {
    this.idAttrProduct = idAttrProduct;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_product", nullable = false)
  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }


  @Column(name = "attribut", nullable = false, length = 1024)
  public String getAttribut() {
    return this.attribut;
  }

  public void setAttribut(String attribut) {
    this.attribut = attribut;
  }


  @Column(name = "value", nullable = false, length = 1024)
  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }



}


