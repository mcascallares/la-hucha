<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="minimum-scale=1.0, width=device-width, maximum-scale=0.6667, user-scalable=no" name="viewport" />
<link href="/css/style.css" rel="stylesheet" media="screen" type="text/css" />
<title>	La Hucha</title>
<script src="/javascript/functions.js" type="text/javascript"></script>
<script src="/javascript/jquery.js" type="text/javascript"></script>
<script src="/javascript/jquery.validate.js" type="text/javascript" ></script>
<script>	
$(document).ready(function(){
    var submitted = false;
    $("#reportForm").validate({
    	rules: {     		
			fromMonth: "required",
			fromYear: "required",
			untilMonth: "required",
			untilYear: "required"
    	},
    	messages: { 
    		fromMonth: "Please specify a valid 'from' month",
    		fromYear: "Please specify a valid 'from' year",
    		untilMonth: "Please specify a valid 'until' month",
    		untilYear: "Please specify a valid 'until' year"
    	},
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

</head>
<body>

<div id="topbar">
<div id="title">La Hucha</div>
<div id="leftbutton"><a href="/index.html">Menu</a></div>
</div>
<div id="content">
<span class="graytitle">New report</span>
<ul class="pageitem">
	<li class="textbox">
	<form:form modelAttribute="reportForm" action="/app/report/show" method="post" id="reportForm">
		
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="125">
				<ul class="pageitem">
				<li class="select">
					<select name="fromMonth" id="creationMonth">
					<option value="">-Month-</option>
					<option value="0">January</option>
					<option value="1">February</option>
					<option value="2">March</option>
					<option value="3">April</option>
					<option value="4">May</option>
					<option value="5">June</option>
					<option value="6">July</option>
					<option value="7">August</option>
					<option value="8">September</option>
					<option value="9">October</option>
					<option value="10">November</option>
					<option value="11">December</option>
					</select>
					<span class="arrow"></span>
				</li>
				</ul>
				</td>
				
				<td width="100">
				<ul class="pageitem">
				<li class="select">
					<select name="fromYear" id="creationYear">
					<option value="">-Year-</option>
					<option value="2011">2011</option>
					<option value="2012">2012</option>
					</select>
					<span class="arrow"></span>
				</li>
				</ul>
				</td>
			</tr>
		</table>
		
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="125">
				<ul class="pageitem">
				<li class="select">
					<select name="untilMonth" id="creationMonth">
					<option value="">-Month-</option>
					<option value="0">January</option>
					<option value="1">February</option>
					<option value="2">March</option>
					<option value="3">April</option>
					<option value="4">May</option>
					<option value="5">June</option>
					<option value="6">July</option>
					<option value="7">August</option>
					<option value="8">September</option>
					<option value="9">October</option>
					<option value="10">November</option>
					<option value="11">December</option>
					</select>
					<span class="arrow"></span>
				</li>
				</ul>
				</td>
				
				<td width="100">
				<ul class="pageitem">
				<li class="select">
					<select name="untilYear" id="creationYear">
					<option value="">-Year-</option>
					<option value="2011">2011</option>
					<option value="2012">2012</option>
					</select>
					<span class="arrow"></span>
				</li>
				</ul>
				</td>
			</tr>
		</table>
		<li class="button"><input type="submit" name="Send" value="Send"></li>	
	</form:form>
	</li>
</ul>
</div>
<div id="footer">
	<!-- Support iWebKit by sending us traffic; please keep this footer on your page, consider it a thank you for our work :-) -->
	<a class="noeffect" href="http://snippetspace.com">Powered by iWebKit</a></div>
</body>

</html>
