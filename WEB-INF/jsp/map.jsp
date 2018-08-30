<!DOCTYPE html>
<!-- New Page Added by Anuj on 19-jan-2017 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <style>
       #map {
        height: 600px;
        width: 100%;
       }
      .contactHeader{ border-top: 5px solid #00698C !important;}
    </style>

    
    <div id="map"></div>
     
    <script>
    var longitude=[];
    var latitude=[];
    var customerName=[];
       function initMap() {
    	   var myprop={
    			   center:new google.maps.LatLng(28.457523,77.026344),
    			   zoom:10,
    			   mapTypeId:google.maps.MapTypeId.ROADMAP
    	   };
    	   var map=new google.maps.Map(document.getElementById("map"),myprop);
    	   var trip=new Array();
    	   for(var i=0;i<longitude.length;i++){
    			for(var j=0;j<latitude.length;j++){
    		  				if(j==i){
    		  					trip.push(new google.maps.LatLng(latitude[j],longitude[i]));
    		  						var marker = new google.maps.Marker({
    		  		    		    position: new google.maps.LatLng(latitude[j],longitude[i]),
    		  		    		    /* title:"Customer"+(i+1) */ 
    		  		    		});
    		  					marker.setMap(map);
    		  					map.setZoom(15);
    		  					map.panTo(marker.position);
    		        }
    		}
    		}
    	      	var flightPath= new google.maps.Polyline({
    		    path: trip,
    		    strokeColor: '#003B52',
    		    strokeOpacity: 2.0,
    		    strokeWeight: 5,
    		    editable: false
    		  });
    	   flightPath.setMap(map);
         }
    </script>
    <c:forEach var="u" items="${location}" >
    
   <script>
   longitude.push('${u.actionLongitude}');
   latitude.push('${u.actionLatitude}');
   customerName.push('${u.customerName}');
        </script>  
</c:forEach>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDfvEARCp3l7E1zRCtiuYJMkzANOIt8Ilk&callback=initMap">
    </script>
  