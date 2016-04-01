package misys.model;
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
 * Adress generated by hbm2java
 */
@Entity
@Table(name = "adress", catalog = "misys")
public class Adress implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Integer idAdress;
  private User user;
  private String name;
  private String nameLastName;
  private String adressPrincipal;
  private String adressSecondaire;
  private String region;
  private int codePostal;
  private String pays;
  private String city;
  private String numberPhone;
  private Set<Order> orders = new HashSet<Order>(0);

  public Adress() {}


  public Adress(User user, String name, String nameLastName, String adressPrincipal, int codePostal,
      String pays, String numberPhone) {
    this.user = user;
    this.name = name;
    this.nameLastName = nameLastName;
    this.adressPrincipal = adressPrincipal;
    this.codePostal = codePostal;
    this.pays = pays;
    this.numberPhone = numberPhone;
  }

  public Adress(User user, String name, String nameLastName, String adressPrincipal,
      String adressSecondaire, String region, int codePostal, String pays, String numberPhone,
      Set<Order> orders) {
    this.user = user;
    this.name = name;
    this.nameLastName = nameLastName;
    this.adressPrincipal = adressPrincipal;
    this.adressSecondaire = adressSecondaire;
    this.region = region;
    this.codePostal = codePostal;
    this.pays = pays;
    this.numberPhone = numberPhone;
    this.orders = orders;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)


  @Column(name = "idAdress", unique = true, nullable = false)
  public Integer getIdAdress() {
    return this.idAdress;
  }

  public void setIdAdress(Integer idAdress) {
    this.idAdress = idAdress;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_users", nullable = false)
  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }


  @Column(name = "name", nullable = false)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Column(name = "nameLastName", nullable = false)
  public String getNameLastName() {
    return this.nameLastName;
  }

  public void setNameLastName(String nameLastName) {
    this.nameLastName = nameLastName;
  }


  @Column(name = "adress_principal", nullable = false, length = 1024)
  public String getAdressPrincipal() {
    return this.adressPrincipal;
  }

  public void setAdressPrincipal(String adressPrincipal) {
    this.adressPrincipal = adressPrincipal;
  }


  @Column(name = "adress_secondaire", length = 1024)
  public String getAdressSecondaire() {
    return this.adressSecondaire;
  }

  public void setAdressSecondaire(String adressSecondaire) {
    this.adressSecondaire = adressSecondaire;
  }


  @Column(name = "region")
  public String getRegion() {
    return this.region;
  }

  public void setRegion(String region) {
    this.region = region;
  }


  @Column(name = "code_postal", nullable = false)
  public int getCodePostal() {
    return this.codePostal;
  }

  public void setCodePostal(int codePostal) {
    this.codePostal = codePostal;
  }


  @Column(name = "pays", nullable = false)
  public String getPays() {
    return this.pays;
  }

  public void setPays(String pays) {
    this.pays = pays;
  }

  @Column(name = "city", nullable = false)
  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Column(name = "number_phone", nullable = false)
  public String getNumberPhone() {
    return this.numberPhone;
  }

  public void setNumberPhone(String numberPhone) {
    this.numberPhone = numberPhone;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "adress")
  public Set<Order> getOrders() {
    return this.orders;
  }

  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }



}

