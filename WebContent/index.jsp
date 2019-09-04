<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style>
		form {
  			margin: 0 auto;
  			width: 400px;
			padding: 1em;
			border: 1px solid #CCC;
			border-radius: 1em;
		}

		div + div {
		  margin-top: 1em;
		}

		label {
		  display: inline-block;
		  width: 90px;
		  text-align: right;
		}

		input, textarea {
		  font: 1em sans-serif;
		  width: 300px;
		  -moz-box-sizing: border-box;
		  box-sizing: border-box;
		  border: 1px solid #999;
		}

		input:focus, textarea:focus {
		  border-color: #000;
		}

		.button {
		  padding-left: 90px; /* same size as the label elements */
		}

		button {
		  margin-left: .5em;
		}
	</style>
	<title>HTML form demo</title>
</head>
<body>
	<form name="bankForm" method="post" action="FormServlet">
		<div>
			<label for="countryCode">Šalies kodas:</label>
		    <input type="text" name="countryCode" id="countryCode" required minlength="2" maxlength="2" pattern="[A-Za-z]{2}"/>
		</div>
	    <div>
		    <label for="bankCode">Banko kodas:</label>
		    <input type="text" name="bankCode" id="bankCode" required minlength="2" maxlength="10" pattern="[0-9]{2,10}"/>
	    </div>
    	<div class="button">
			<button type="submit">Pateikti užklausą</button>
		</div>
		<c:if test="${countryCodeError == true}">
		    <h4>Klaidingai įvestas šalies kodas. Prašome įvesti dviejų <strong>raidžių</strong> šalies kodą ir pamėginti dar kartą.</h4>
		</c:if>
		<c:if test="${bankCodeError == true}">
		    <h4>Klaidingai įvestas banko kodas. Prašome įvesti nuo 2 iki 10 <strong>skaičių</strong> banko kodą ir pamėginti dar kartą.</h4>
		</c:if>
		<c:if test="${not empty formatedBankCode}">
		    <h4>Jūsų suformatuotas banko kodas: ${formatedBankCode}</h4>
		</c:if>
	</form>
	
	<script>
		var countryCode = document.getElementById("countryCode");
		countryCode.addEventListener("input", function (event) {
			var validity = countryCode.validity;
			if (validity.badInput || validity.patternMismatch || validity.tooLong || validity.tooShort || validity.valueMissing) {
				countryCode.setCustomValidity("Šalies kode turi būti tik dvi raidės");
			} else {
				countryCode.setCustomValidity("");
			}
		});
		
		var bankCode = document.getElementById("bankCode");
		bankCode.addEventListener("input", function (event) {
		  var validity = bankCode.validity;
		  if (validity.badInput || validity.patternMismatch || validity.tooLong || validity.tooShort || validity.valueMissing) {
			  bankCode.setCustomValidity("Banko kode turi būti nuo 2 iki 10 skaičių");
		  } else {
			  bankCode.setCustomValidity("");
		  }
		});
	</script>
</body>
</html>