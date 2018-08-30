function checkPAN(pannumber)
{
  var pan=pannumber.value;    
  if(!(pan==''))
  {
	     if (!isNaN((pan).charAt(0)))
	     {	              
	             alert('Invalid PanCard Number Format');
	             pannumber.value="";
	             return false;
	     }
	     if (!isNaN((pan).charAt(1)))
	     {
	             alert('Invalid PanCard Number Format');
	             pannumber.value="";
	             return false;
	     }
	     if (!isNaN((pan).charAt(2))) 
	     {      
              alert('Invalid PanCard Number Format');
              pannumber.value="";
              return false;
        }
        if (!isNaN((pan).charAt(3))) 
        {
              alert('Invalid PanCard Number Format');
              pannumber.value="";
              return false;
        }
        if (!isNaN((pan).charAt(4))) 
        {

              alert('Invalid PanCard Number Format');
              pannumber.value="";
              return false;
        }           
        if (isNaN((pan).charAt(5)))
        {

              alert('Invalid PanCard Number Format');
              pannumber.value="";
              return false;
        }

        if (isNaN((pan).charAt(6)))  
        {
              alert('Invalid PanCard Number Format');
              pannumber.value="";
              return false;
        }
        if (isNaN((pan).charAt(7)))  
        {
              alert('Invalid PanCard Number Format');
              pannumber.value="";
              return false;
        }
        if (isNaN((pan).charAt(8)))  
        {
               alert('Invalid PanCard Number Format');
               pannumber.value="";
               return false;
         }
     if (!isNaN((pan).charAt(9))) 
      {
              alert('Invalid PanCard Number Format');
              pannumber.value="";
              return false;
        }

        if ( pan.length >10) 
        {
              alert('Invalid PanCard Number Format');
              pannumber.value="";
              return false;
        }

        if ( pan.length <10)
         {
              alert('Invalid PanCard Number Format');
              pannumber.value="";
              return false;
         }
        return true;
        }
}


function validatePhone()
{
	if(document.getElementById("txtphone")!=null && (document.getElementById("txtphone").value!='' && document.getElementById("txtphone").value!='0'))
	{
		if(Number(document.getElementById("txtphone").value)<200000)
		{
			msgalertt=msgalertt+'Phone Number is not Valid.';
			//alert(msgalertt);
		}
	}
}

function specialCaraectorNotAllowd(id,fieldName)
{
var i;
var ValidChars = "!@#$%^&*+-[]()\\\';,/{}|\":<>?~_";
var IsNumber=true;
var Char;
var sText = trim(id.value);
for (i = 0; i < sText.length && IsNumber == true; i++)
{
    Char = sText.charAt(i);
    if (ValidChars.indexOf(Char) != -1)
    {
        alert('Special character are not allowed in '+fieldName);
        IsNumber = false;
        id.value= "";
    }
}
IsNotNumeric(id);
}

function onlyNumeric(evt)
{
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        		//alert(">>>>>>>>>>>>>>>>"+charCode);
        		
        if (charCode > 31 && (charCode < 48 || charCode > 57))
           return false;

        return true;
     }
/*	alert(">>>>>>>>>>")var i;
    var ValidChars = "0123456789";
    var IsNumber=true;
    var Char;
    var sText = id.value;

    for (i = 0; i < sText.length && IsNumber == true; i++)
    {
        Char = sText.charAt(i);
        if (ValidChars.indexOf(Char) == -1)
        {
            alert(fieldName+' Should be Numeric');
            IsNumber = false;
            id.value= "";
        }
    }*/

}

function onlyFloatNumeric(evt,fieldName)
{
	
	 var input = evt.value;
	 if (/^\d*\.?\d{0,2}$/.test(input)) {
	  
	 }
	 else{
	 alert(fieldName+" must be a number");
	 evt.value="";
	 return false;
	 }
}

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 
function valMobNumber(evt){
	var charCode = (evt.which) ? evt.which : event.keyCode
			if (charCode > 31 && (charCode < 48 || charCode > 57))
		           return false;
	              
	              return true;
	try
	{
	
	if(value.length>10)
	{
		alert("Please enter 10 digit mobile no");
		return false;
	}
	if(value.length<10)
	{
		alert("Please enter 10 digit mobile no");
		return false;
	}
	
	if(fDigit!=7 && fDigit!=8 && fDigit!=9)
	{
		alert("Mobile no should be start with 7 or 8 or 9")	;
		return false;
	}
	/* if(value.length==3)
	{
		alert("Mobile no should be start with 7or 8or 9");
		return false;
	} */	
	if(fDigit==0)
	{
		alert("Mobile no should be start with 7 or 8 or 9");
		return false;
	}
	else
	{
		//alert("validation complete")
		return true;
	}
	
	}catch(e)
	{alert(e);}
	
} 
function isInteger(s)
{  
	var i;
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    return true;
}

function validatePinCode(pinValue)
{
	
	if(isNaN(pinValue))
	{
		alert("Pin Code should be numeric");
		return false;
	}
	if(pinValue.length!=6)
	{
		alert("Please enter valid pin code");
		return false;
	}
	else
	{
		return true;
	}
	
}




function validateLandLineNumber(inputPhn) {
    // Get input
    var phone =inputPhn;
        // Remove whitespaces from input start and end
        phone = (phone || '').replace(/^\s+|\s+$/g, '');
        // Defined valid charset as regular expression
        validNumber = "/^[0123456789.-]+$/";

    // Just in case the input was empty
    if (phone.length == 0) {
        // This depends on your application - is an empty number also correct?
        // If not, just change this to "return false;"
        return true;
    }

    // Test phone string against the regular expression
    if (phone.match(validNumber)) {
        return true;
    }

    // Some invalid symbols are used
    return false;
}

function onlyCarector(evt)
{
	var charCode = (evt.which) ? evt.which : event.keyCode
	if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 8)
		  return true;
		     
		   return false;
		     
}
function isAlfaNumeric(field,fieldName)
{
	var rule = /^[a-zA-Z0-9]+$/;
	if (rule.test(policyNumber)) {
		return true;
	}
	else {
		alert(fieldName+" Should be Alpha Numeric");
		return false;
	}
}
function validateHhMm(inputField,fieldName) {
	
    var isValid = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/.test(inputField);

    if (isValid) {
        return true;
    } else {
    	 alert(fieldName+" Should be HH:MM formate");
    	 return false;
    }
}
function checkDecimals( field)
{
	var fieldValue=field.value;
decallowed = 2;
if (isNaN(fieldValue) || fieldValue == "")
{
alert("Not a valid number.try again.");
field.value='';

}
else
{
if (fieldValue.indexOf('.') == -1) fieldValue += ".";
dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
if (dectext.length > decallowed)
{
field.value='';
alert ("Enter a number with up to " + decallowed + "decimal places. try again.");

}
}
}