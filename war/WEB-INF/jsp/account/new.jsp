<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="minimum-scale=1.0, width=device-width, maximum-scale=0.6667, user-scalable=no" name="viewport" />
<link href="/css/style.css" rel="stylesheet" media="screen" type="text/css" />
<script src="/javascript/functions.js" type="text/javascript"></script>
<script src="/javascript/jquery.js" type="text/javascript"></script>
<script src="/javascript/jquery.validate.js" type="text/javascript" ></script>
<script>	
$(document).ready(function(){
	var submitted = false;
    $("#accountForm").validate({
    	rules: { name: "required" },
		messages: { name: "Please specify an account name" },
		focusInvalid: false,
		invalidHandler: function(form, validator) {submitted = true;},
		showErrors: function(errorMap, errorList) {
			if (submitted) {
				var summary = "";
				$.each(errorMap, function(key, value) {
					summary += value + "\n";
				});
				alert(summary);
				submitted = false;
			}
		}
    });
});
</script>
<title>	La Hucha</title>
</head>
<body>

<div id="topbar">
<div id="title">La Hucha</div>
<div id="leftbutton"><a href="/index.html">Menu</a></div>
</div>
<div id="content">
<span class="graytitle">New finance account</span>
<ul class="pageitem">
	<li class="textbox">
		<form:form modelAttribute="account" action="/app/account/save" method="post" id="accountForm">
			<li class="smallfield"><span class="name">Name</span>
			<form:errors path="name" cssClass="errors" />
			<form:input path="name" placeholder="enter name"/></li>
			<li class="button"><input type="submit" name="Save" value="Save"></li>
		</form:form>
	</li>
</ul>
</div>
<div id="footer">
	<!-- Support iWebKit by sending us traffic; please keep this footer on your page, consider it a thank you for our work :-) -->
	<a class="noeffect" href="http://snippetspace.com">Powered by iWebKit</a></div>
</body>

</html>
