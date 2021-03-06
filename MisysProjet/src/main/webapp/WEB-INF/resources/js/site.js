var boolProgress = true;
var numberAlert = 0;
function showMessage(type, message) {

  if ($(".alert").length >= 3) {
    $("#alert").html("");
  }
  var html = '<div class="blockAlert'
          + numberAlert
          + '"><div class="alert alert'
          + numberAlert
          + ' alert-dismissible fade alert-fixed" role="alert"><span class="message"></span></div><br/></div>';
  $("#alert").append(html);
  $(".alert" + numberAlert).css({
    display: "inline-block"
  });
  $(".alert" + numberAlert).removeClass("alert-info alert-success");
  if (type === "error") {
    $(".alert" + numberAlert).addClass("alert-info in");
    $(".message").html("<strong>Oups !</strong> " + message);
  } else {
    $(".alert" + numberAlert).addClass("alert-success in");
    $(".message").html("<strong>Succ&#232;s !</strong> " + message);
  }
  setTimeout('$(".blockAlert" + ' + numberAlert + ').remove()', 2000);
  numberAlert++;

}

function addInCart(id, typeData) {
  if (boolProgress) {
    boolProgress = false;
    if (id !== "" && typeData !== "") {
      $.ajax({
        type: "post",
        url: "/ajaxAddCart",
        data: "id=" + id + "&typeData=" + typeData,
        success: function(t) {
          t = JSON.parse(t);
          if (t.statut == "success") {
            $(".nbCart").html(t.nbCart);
          }
          showMessage(t.statut, t.message)

          self.boolProgress = true;
        },
        error: function() {
          self.boolProgress = true;
        }
      });
    }
  }
}

function seeProduct(id) {
  $(".body-article").addClass("center");
  $(".body-article").html(
          "<img alt='Spinner' src='/resources/img/spinner.gif'/>");
  $.ajax({
    type: "post",
    url: "/seeProduct",
    data: "id=" + id,
    success: function(t) {
      $(".body-article").removeClass("center");
      $(".body-article").html(t);
    },
    error: function() {
      self.boolProgress = true;
    }
  });
}

function changeBlockFormAndAddMessage(blockForm, mess) {
  form = blockForm.parent().parent();
  form.addClass("has-warning");
  form.find(".help-block").html(mess);
}

function checkIfAllRequiredIsNotEmpty(thisClass) {
  var mess_required = "Aie ! Ce champ est obligatoire.";

  var submit = true;
  thisClass.each(function() {
    var type = $(this).attr('type');
    if (type === "checkbox") {
      if (!$(this).is(':checked')) {
        changeBlockFormAndAddMessage($(this), mess_required);
        submit = false;
      }
    } else {
      if ($(this).val() == '') {
        changeBlockFormAndAddMessage($(this), mess_required);
        submit = false;
      }
    }
  });
  return submit;
}

function checkLength(thisClass) {
  var submit = true;
  thisClass
          .each(function() {
            var length = $(this).data("length");
            if (length < $(this).val().length) {
              changeBlockFormAndAddMessage($(this),
                      "Aie ! Ce champ ne peut pas dépasser : " + length
                              + " caratères.");
              submit = false;
            }
          });
  return submit;
}

function checkLengthMandatory(thisClass) {
  var submit = true;
  thisClass.each(function() {
    var length = $(this).data("length");
    if (length != $(this).val().length) {
      changeBlockFormAndAddMessage($(this), "Aie ! Ce champ doi contenir : "
              + length + " caratères.");
      submit = false;
    }
  });
  return submit;
}

function checkInt(thisClass) {
  var submit = true;
  thisClass.each(function() {
    if (isNaN($(this).val())) {
      changeBlockFormAndAddMessage($(this),
              "Aie ! Ce champ doit &#234;tre un chiffre");
      submit = false;
    }
  });
  return submit;
}

var submit;

function checkForm(className) {
  $('.help-block').html("");
  $('.has-warning').removeClass("has-warning");

  if (className !== "") {
    className = "." + className;
  }
  submit = checkIfAllRequiredIsNotEmpty($(className + ' .required'));

  if (submit) {
    submit = checkLength($(className + ' .checkLength'));
  }

  if (submit) {
    submit = checkLengthMandatory($(className + ' .checkLengthMandatory'));
  }

  if (submit) {
    submit = checkInt($(className + ' .checkInt'));
  }

  return submit;
}

function addMoneyAccount(theThis) {

  var number = theThis.val().replace(",", ".");
  if (number !== "") {
    if (!isNaN(number)) {
      if (number >= 5) {
        if (number <= 1000) {
          if (checkPrice(number)) {
            $.ajax({
              type: "post",
              url: "/ajaxAddMoneyAccount",
              data: "numberAddAccount=" + number,
              success: function(t) {
                t = JSON.parse(t);
                if (t.statut == "success") {
                  $(".argent").html(t.argent.toFixed(2).replace(".", ","));
                  theThis.val("")
                }
                showMessage(t.statut, t.message)
                self.boolProgress = true;
              },
              error: function() {
                self.boolProgress = true;
              }
            });

          } else {
            showMessage("error", "Ceci n'est pas un prix")
          }
        } else {
          showMessage("error",
                  "Le montant maximum &#224;	 cr&#233;diter est de 1000,00 	&#128;")
          $(".numberProduct" + idtype).val(1000);
        }
      } else {
        showMessage("error",
                "Montant minimum &#224; cr&#233;diter: 5,00 &#128;")
        $(".numberProduct" + idtype).val(5);
      }
    } else {
      showMessage(
              "error",
              "Ce caract&#232;re n'est pas autoris&#233;. Seuls '.' et ',' sont autoris&#233;s")
    }
  }
}

function checkPrice(stringPrice) {
  var regexp_prix = new RegExp("^[0-9]{1,}(,[0-9]{1,2}|[.][0-9]{1,2}){0,1}$");
  if (!regexp_prix.test(stringPrice)) {
    return false;
  } else {
    return true;
  }
}

$(document).ready(function() {
  $('[data-toggle="tooltip"]').tooltip()

  $(".addCart").on("click", function(e) {
    e.preventDefault();
    addInCart($(this).data("id"), $(this).data("type"));
  });

  $("form").on("submit", function(e) {
    var className = e.target.className;
    return checkForm(className);
  });
  $(".clickProduct").on("click", function(e) {
    if (e.target.className.indexOf("addCart") != 0) {
      document.location.href = $(this).data("href");
    }
  });
  $(".btn-compte-prepaye").on("click", function() {
    addMoneyAccount($(".input-compte-prepaye"));
  })
  $(".btn-voitPrd").on("click", function() {
    seeProduct($(this).data("id"));
  })
});