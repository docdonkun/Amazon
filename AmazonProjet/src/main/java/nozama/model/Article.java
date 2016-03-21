package nozama.model;
// Generated 21 mars 2016 08:24:02 by Hibernate Tools 4.3.1.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Article generated by hbm2java
 */
@Entity
@Table(name = "article", catalog = "nozama")
public class Article implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Integer idArticle;
  private Product product;
  private String nameSupport;
  private float price;
  private Set<ProductOrder> productOrders = new HashSet<ProductOrder>(0);

  public Article() {}


  public Article(Product product, String nameSupport, float price) {
    this.product = product;
    this.nameSupport = nameSupport;
    this.price = price;
  }

  public Article(Product product, String nameSupport, float price,
      Set<ProductOrder> productOrders) {
    this.product = product;
    this.nameSupport = nameSupport;
    this.price = price;
    this.productOrders = productOrders;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)


  @Column(name = "id_article", unique = true, nullable = false)
  public Integer getIdArticle() {
    return this.idArticle;
  }

  public void setIdArticle(Integer idArticle) {
    this.idArticle = idArticle;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_product", nullable = false)
  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }


  @Column(name = "name_support", nullable = false, length = 45)
  public String getNameSupport() {
    return this.nameSupport;
  }

  public void setNameSupport(String nameSupport) {
    this.nameSupport = nameSupport;
  }


  @Column(name = "price", nullable = false, precision = 12, scale = 0)
  public float getPrice() {
    return this.price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
  public Set<ProductOrder> getProductOrders() {
    return this.productOrders;
  }

  public void setProductOrders(Set<ProductOrder> productOrders) {
    this.productOrders = productOrders;
  }



}


