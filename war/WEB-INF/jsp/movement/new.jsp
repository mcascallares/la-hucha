<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>	La Hucha</title>
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="minimum-scale=1.0, width=device-width, maximum-scale=0.6667, user-scalable=no" name="viewport" />
<link href="/css/style.css" rel="stylesheet" media="screen" type="text/css" />
<script src="/javascript/functions.js" type="text/javascript"></script>
<script src="/javascript/date.js" type="text/javascript"></script>
<script src="/javascript/jquery.js" type="text/javascript"></script>
<script src="/javascript/jquery.validate.js" type="text/javascript" ></script>
<script>	
$(document).ready(function(){
	
	// date parts appender
	$('#creationDate, #creationMonth, #creationYear').change(function() {  
		$('#completeDate').val($('#creationDate').val()+'/'+ (parseInt($('#creationMonth').val()) + 1) +'/'+ $('#creationYear').val());
	});

	// Today logic
	var currentTime = new Date();
	var currentMonth = currentTime.getMonth();
	var currentDate = currentTime.getDate();
	var currentYear = currentTime.getFullYear();
	
    $("#todayLink").click(function() {
    	$('#creationMonth').val(currentMonth);
    	$('#creationYear').val(currentYear);
    	$('#creationDate').val(currentDate);
    	$('#completeDate').val($('#creationDate').val()+'/'+ (parseInt($('#creationMonth').val()) + 1) +'/'+ $('#creationYear').val());
    });
    
    jQuery.validator.addMethod("validdate", function(value, element) {
        return Date.parseExact(value, "d/M/yyyy");
    }); 
    
    var submitted = false;
    $("#movementForm").validate({
    	rules: { 
    		amount: {
				required: true,
				number: true
			},
			finnanceAccountId: "required",
			completeDate: { 
				required: true,
				validdate: true
			} 
    	},
    	messages: { 
    		amount: {
    			required: "Please specify an amount",
    			number: "Please specify a valid amount"
    		},
    		finnanceAccountId: "Please specify an account",
    		completeDate: {
    			required: "Please specify a date",
    			validdate: "The specified date is invalid"
    		}
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
<span class="graytitle">New movement</span>
<ul class="pageitem">
	<li class="textbox">
	<form:form modelAttribute="movementForm" action="/app/movement/save" method="post" id="movementForm">
		<input id="completeDate"  name="completeDate" type="hidden"  />
		<li class="select">
			<form:select path="finnanceAccountId">
				<form:option value="" label="Select an account" />
				<form:options items="${accounts}" itemLabel="name" itemValue="key.id"/>
			</form:select>
			<span class="arrow"></span>
		</li>
		
		<li class="smallfield">
			<span class="name">Amount (&#8364;)</span>
			<input name="amount" placeholder="enter amount" type="number" /> 
		</li>
		
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="125">
				<ul class="pageitem">
				<li class="select">
					<select name="creationMonth" id="creationMonth">
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
					<select name="creationDate" id="creationDate">
					<option value="">-Day-</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
					</select>
					<span class="arrow"></span>
				</li>
				</ul>
				</td>
				
				<td width="100">
				<ul class="pageitem">
				<li class="select">
					<select name="creationYear" id="creationYear">
					<option value="">-Year-</option>
					<option value="2011">2011</option>
					<option value="2012">2012</option>
					</select>
					<span class="arrow"></span>
				</li>
				</ul>
				</td>
			</tr>
			
			<tr>
				<td colspan="3">
					<li class="smallfield">
					<span class="name"><a href="#" id="todayLink">Today</a></span> 
					</li>
				</td>
			</tr>
		</table>
		
		
		
		<li class="textbox"><form:textarea path="notes" rows="4"/></li>
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
