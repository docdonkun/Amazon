package nozama.repository;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import nozama.model.Article;
import nozama.model.Order;
import nozama.model.Product;
import nozama.model.ProductOrder;
import nozama.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class ProductRepository {

  private static Logger log = Logger.getLogger(ProductRepository.class.getName());

  /**
   * Select all product by conditions :
   * Type
   * Support
   * Date
   * Genre
   * 
   * join : 
   * articles
   * attrProducts
   * 
   * All facultative
   * 
   * @param useSupport boolean
   * @param support
   * @param useDate boolean
   * @param dateYears
   * @param dateYearsAfter
   * @param useType boolean
   * @param type
   * @param useGenre boolean
   * @param genre
   * @return List<@Product>
   */
  public List<Product> getAllProductByTypeAndSupportAndDateAndGenre(boolean useSupport, String support,
      boolean useDate, Date dateYears, Date dateYearsAfter, boolean useType, List<String> type,
      boolean useGenre, String genre) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);

    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);

    cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

    if (useType) {
      cr.add(Restrictions.in("type", type));
    }
    if (useSupport) {
      cr.add(Restrictions.eq("a.nameSupport", support));
    }
    if (useDate) {
      cr.add(Restrictions.between("dateReleased", dateYears, dateYearsAfter));
    }
    if (useGenre) {
      cr.add(Restrictions.eq("ap.attribut", "genre"));
      cr.add(Restrictions.eq("ap.value", genre));
    }
    List<Product> listProduct = cr.list();

    openSession.close();
    HibernateUtil.shutdown();
    return listProduct;
  }

  /**
   * Select one product by conditions :
   * nameTagDateReleased
   * 
   * join : 
   * articles
   * attrProducts
   * artistes
   * products
   * 
   * @param nameTagDateReleased
   * @return @Product
   */
  public Product getProductByNameTagDate(String nameTagDateReleased) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("artistes", "art", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("products", "prod", JoinType.LEFT_OUTER_JOIN);
    
    cr.add(Restrictions.eq("nameTagDateReleased", nameTagDateReleased));

    cr.addOrder(Property.forName("attrProducts").asc());
    cr.addOrder(Property.forName("ap.value").asc());
    
    List<Product> listProduct = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    if (listProduct.size() == 0) {
      return null;
    }
    return listProduct.get(0);
  }

  /**
   * Select one product by conditions :
   * nameTagDateReleased
   * 
   * join : 
   * articles
   * attrProducts
   * artistes
   * products
   * 
   * @param idProduct
   * @return
   */
  public Product getProductParent(int idProduct) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("artistes", "art", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("products", "prod", JoinType.LEFT_OUTER_JOIN);
    cr.add(Restrictions.eq("prod.idProduct", idProduct));

    List<Product> listProduct = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    if (listProduct.size() == 0) {
      return null;
    }
    return listProduct.get(0);
  }

  public List<Product> getProductById(List<Integer> idProduct) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.in("idProduct", idProduct));

    List<Product> listProduct = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    return listProduct;
  }

  public Product getProductById(Integer idProduct) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.eq("idProduct", idProduct));

    List<Product> listProduct = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    if (listProduct.size() == 0) {
      return null;
    }
    return listProduct.get(0);
  }

  public List<Article> getArticleById(List<Integer> allId) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Article.class);
    cr.createAlias("product", "p", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.in("idArticle", allId));

    List<Article> listProduct = cr.list();

    openSession.close();
    HibernateUtil.shutdown();
    return listProduct;
  }

  public Article getArticleById(Integer id) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Article.class);
    cr.createAlias("product", "p", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.eq("idArticle", id));

    List<Article> listProduct = cr.list();

    openSession.close();
    HibernateUtil.shutdown();
    
    if (listProduct.size() == 0) {
      return null;
    }
    return listProduct.get(0);
  }

  public void inserOrder(Order order) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = openSession.beginTransaction();

    try {
      openSession.save(order);

      tx.commit();
    } catch (RuntimeException e) {
      log.error("[ProductRepository] impossible to insert order in bdd");
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  public void inserOrderProduct(ProductOrder productOrder) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = openSession.beginTransaction();

    try {
      openSession.save(productOrder);

      tx.commit();
    } catch (RuntimeException e) {
      log.error("[ProductRepository] impossible to insert productOrder in bdd");
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  public List<Product> getAllProductByTypeAndAttribut(List<String> type, String attrProduct) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();


    Criteria cr = openSession.createCriteria(Product.class);


    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.in("type", type));

    cr.add(Restrictions.eq("ap.attribut", attrProduct));
    cr.add(Restrictions.eq("ap.value", attrProduct));

    List<Product> listProduct = cr.list();

    openSession.close();
    HibernateUtil.shutdown();
    return listProduct;
  }


}
