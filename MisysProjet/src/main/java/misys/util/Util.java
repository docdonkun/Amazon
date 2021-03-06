package misys.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public final class Util {

  /**
   * Check if a string is Int
   * 
   * @param text
   * @return Boolean
   */
  public static boolean checkConvertToInt(String text) {

    try {
      Integer.parseInt(text);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Check if a string is Float
   * 
   * @param text
   * @return boolean
   */
  public static boolean checkConvertToFloat(String text) {

    try {
      Float.parseFloat(text);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Check if a string is Short
   * 
   * @param text
   * @return boolean
   */
  public static boolean checkConvertToShort(String sYearBirth) {
    try {
      Short.parseShort(sYearBirth);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * If string is null return null
   * 
   * @param text
   * @return String
   */
  public static String ConvertStringToNull(String string) {
    if (string == null || string.equals("") || string.equals("null")) {
      return null;
    }
    return string;
  }

  /**
   * Check if the price is compatible
   * 
   * @param email
   * @return boolean
   */
  public static boolean isPrice(String price) {
    Pattern p = Pattern.compile("^[0-9]{1,}(,[0-9]{1,2}|[.][0-9]{1,2}){0,1}$");
    Matcher m = p.matcher(price);
    return m.matches();
  }

  /**
   * Return Map with message
   * 
   * @param email
   * @return Map<String, Object>
   */
  public static Map<String, Object> returnMessage(String message) {
    Map<String, Object> redirect = new HashMap<String, Object>();
    redirect.put("message", message);
    return redirect;
  }

  /**
   * Check all session in cart
   * 
   * @param @Integer stepCart
   * @param @HttpServletRequest request
   * @return Map<String, Object> with Boolean checkCondition and String redirect
   */
  public static Map<String, Object> conditionCart(int stepCart, HttpServletRequest request) {
    Map<String, Object> conditionCart = new HashMap<String, Object>();
    conditionCart.put("checkCondition", false);

    if (request.getSession().getAttribute("cartControlTunnel") == null) {
      return changeConditionCartWithMessage(conditionCart, "mon-panier");
    }

    if (stepCart >= 2 && request.getSession().getAttribute("User") == null) {
      return changeConditionCartWithMessage(conditionCart, "mon-panier-etape-connexion");
    }

    if (stepCart >= 3 && request.getSession().getAttribute("address") == null) {
      return changeConditionCartWithMessage(conditionCart, "mon-panier-etape-adresse");
    }

    if (stepCart >= 4 && request.getSession().getAttribute("transport") == null) {
      return changeConditionCartWithMessage(conditionCart, "mon-panier-etape-livraison");
    }

    if (stepCart >= 5 && request.getSession().getAttribute("totalPrice") == null) {
      return changeConditionCartWithMessage(conditionCart, "mon-panier-etape-paiement");
    }

    if (stepCart >= 6 && request.getSession().getAttribute("payment") == null) {
      return changeConditionCartWithMessage(conditionCart, "mon-panier-etape-paiement");
    }

    return conditionCart;
  }

  /**
   * Change condition Cart With a Message and change checkCondition in true
   * 
   * @param Map<String, Object> conditionCart with Boolean checkCondition and String redirect
   * @param String redirect
   * @return Map<String, Object>
   */
  private static Map<String, Object> changeConditionCartWithMessage(
      Map<String, Object> conditionCart, String redirect) {
    conditionCart.put("checkCondition", true);
    conditionCart.put("redirect", redirect);
    return conditionCart;
  }

  /**
   * Generate a Json with a message and Statut
   * @param statut
   * @param message
   * @return Json in String
   * @throws JSONException
   */
  public static String getJsonStatutMessage(String statut, String message) throws JSONException {
    JSONObject json = new JSONObject();
    json.put("statut", statut);
    json.put("message", message);
    return json.toString();
  }

  /**
   * Generate a Json with a redirect and Statut
   * @param statut
   * @param redirect
   * @return Json in String
   * @throws JSONException
   */
  public static String getJsonStatutRedirect(String statut, String redirect) throws JSONException {
    JSONObject json = new JSONObject();
    json.put("statut", statut);
    json.put("redirect", "/" + redirect);
    return json.toString();
  }

  /**
   * Generate a Json with a number
   * @param number
   * @return Json in String
   * @throws JSONException
   */
  public static String getJsonNumber(int number) throws JSONException {
    JSONObject json = new JSONObject();
    json.put("number", number);
    return json.toString();
  }

  /**
   * Generate a Json with a statut, message and nomber of object in cart
   * @param number
   * @return Json in String
   * @throws JSONException
   */
  public static String getJsonStatutMessageNbCart(String statut, String message, int nbCart)
      throws JSONException {
    JSONObject json = new JSONObject();
    json.put("statut", statut);
    json.put("message", message);
    json.put("nbCart", nbCart);
    return json.toString();
  }

  /**
   * Generate a Json with a statut
   * @param statut
   * @return Json in String
   * @throws JSONException
   */
  public static String getJsonStatut(String statut) throws JSONException {
    JSONObject json = new JSONObject();
    json.put("statut", statut);
    return json.toString();
  }

  /**
   * Generate a Json with a statut, message and argent in account prepaye
   * @param statut
   * @param message
   * @param argent
   * @return Json in String
   * @throws JSONException
   */
  public static String getJsonStatutMessageArgent(String statut, String message,
      float argent) throws JSONException {
    JSONObject json = new JSONObject();
    json.put("statut", statut);
    json.put("message", message);
    json.put("argent", argent);
    return json.toString();
  }

  /**
   * Generate a Json with a statut, id
   * @param statut
   * @param id
   * @return Json in String
   * @throws JSONException
   */
  public static String getJsonStatutId(String statut, Integer id) throws JSONException {
    JSONObject json = new JSONObject();
    json.put("statut", statut);
    json.put("id", id);
    return json.toString();
  }

  /**
   * Check if the string is null and not empty
   * 
   * @param string
   * @return Boolean
   */
  public static boolean checkStringNotNull(String string) {
    if(string == null || string.equals("") || string.equals("{}") || string.equals("null")){
      return false;
    }
    return true;
  }
  
  /**
   * Check if the string is not null and not empty
   * 
   * @param string
   * @return Boolean
   */
  public static boolean checkStringIsNull(String string) {
    return !checkStringNotNull(string);
  }
  
  /**
   * get parameters optionnal of URL if is null insert default value
   * 
   * @param supportUrl
   * @param stringDefault
   * @return
   */
  public static String getParametersString(Optional<String> supportUrl, String stringDefault) {
    String stringParameters;
    if (supportUrl.isPresent()) {
      stringParameters = supportUrl.get();
    } else {
      stringParameters = stringDefault;
    }
    return stringParameters;
  }

}
