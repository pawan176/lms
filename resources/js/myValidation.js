
function validateMobile(mobile){
		var value=mobile.value;
		try
		{
		var fDigit = value.substring(0, 1);	
		if(!isInteger(value))
		{
			alert("Mobile number should be numeric");
			mobile.value="";
			return false;
		}
		if(value.length>10)
		{
			alert("Please enter 10 digit mobile number");
			mobile.value="";
			return false;
		}
		if(value.length<10)
		{
			alert("Please enter 10 digit mobile number");
			mobile.value="";
			return false;
		}
		
		if(fDigit!=7 && fDigit!=8 && fDigit!=9)
		{
			alert("Mobile number should start with 7 or 8 or 9");
			mobile.value="";
			return false;
		}
		if(fDigit==0)
		{
			alert("Mobile number should  start with 7 or 8 or 9");
			mobile.value="";
			return false;
		}
		else
		{
			return true;
		}
		}catch(e)
		{alert(e);}
	}

function validateEmail(x) {
	  var email=x.value;
	  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	  if(!re.test(email)){
		  alert("Please Enter Valid Email-ID");
		  x.value="";
		  return false;
	  }else{
		  return true;
	  }
	} 

function validatePinCode(x)
{
	var pinValue=x.value;
	if(isNaN(pinValue))
	{
		alert("Pin Code should be numeric");
		x.value="";
		return false;
	}
	if(pinValue.length!=6)
	{
		alert("Please enter valid pin code");
		x.value="";
		return false;
	}
	else
	{
		return true;
	}
}

function validateAdharNo(x)
{
	var aadhaarValue=x.value;
	if(aadhaarValue.length!=12)
	{
		alert("Aadhaar number should be of 12 digits");
		x.value="";
		return false;
	}
	else
	{
		return true;
	}
	
}

function validateBirthDate(val){
	var currDate=new Date();
	var dateBirth=val.value.split('-');	
	var date1 = (currDate.getFullYear()-10)+"-"+("0" + (currDate.getMonth() + 1)).slice(-2)+"-"+("0" + (currDate.getDate())).slice(-2);
	var birthDate=dateBirth[2]+"-"+dateBirth[1]+"-"+dateBirth[0];	
	var inputToDate = new Date(birthDate);
	var todayToDate = new Date(date1);
	if (inputToDate > todayToDate)
	{
	alert("BIRTHDATE SHOULD BE 10 YEAR LESS THAN FROM PRESENT DATE ");
	val.value="";
	}
}

function validateDateIncorporation(val){
	var currDate=new Date();
	var dateIncorporation=val.value.split('-');
	var date1 = currDate.getFullYear()+"-"+(currDate.getMonth()+1)+"-"+currDate.getDate();
	var incorpDate=dateIncorporation[2]+"-"+dateIncorporation[1]+"-"+dateIncorporation[0];
	var inputToDate = new Date(incorpDate);
	var todayToDate = new Date(date1);
	if (inputToDate > todayToDate)
	{
	alert("DATE OF INCORPORATION SHOULD NOT BE GREATER THAN PRESENT DATE");
	val.value="";
	}else{
		return true;
	}
}
