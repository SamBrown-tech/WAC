function initPage() {
	fetch('https://ipapi.co/json/')
	.then(function(response) {
		return response.json();
	})
	.then(function(myJson) {
		document.querySelector("#country").innerHTML += myJson.country;
		document.querySelector("#country_name").innerHTML += myJson.country_name;
		document.querySelector("#region").innerHTML += myJson.region;
		document.querySelector("#city").innerHTML += myJson.city;
		document.querySelector("#postal").innerHTML += myJson.postal;
		document.querySelector("#latitude").innerHTML += myJson.latitude;
		document.querySelector("#longitude").innerHTML += myJson.longitude;
		document.querySelector("#ip").innerHTML += myJson.ip;
		
		var lat = myJson.latitude;
		var long = myJson.longitude;
		var city = myJson.city;
		showWeather(lat,long,city);
		loadCountries();
		getMyLocation();
	})			
}

function showWeather(lat, long, city) {
	var uri = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + long + "&appid=a76d194b2adccf904633de0fb698bf82&units=metric";
	fetch(uri)
		.then(response => response.json())
		.then(function(myJson){		
			var stad = document.querySelector("#stad").innerHTML = city;
			var temperatuur = document.querySelector("#temperatuur").innerHTML = myJson.main.temp;
			var luchtvochtigheid = document.querySelector("#luchtvochtigheid").innerHTML = myJson.main.humidity;
			var windsnelheid = document.querySelector("#windsnelheid").innerHTML = myJson.wind.speed;
			var windrichting = document.querySelector("#windrichting").innerHTML = myJson.wind.deg;
			var zonsopgang = document.querySelector("#zonsopgang").innerHTML = myJson.sys.sunrise;
			var zonsondergang = document.querySelector("#zonsondergang").innerHTML = myJson.sys.sunset;
			var expiration_time = new Date().getTime() + (1000 * 60 * 10);
			var weather = { 
				"stad" : stad, 
				"temperatuur" : temperatuur, 
				"luchtvochtigheid" : luchtvochtigheid,
				"windsnelheid" : windsnelheid,
				"windrichting" : windrichting,
				"zonsopgang" : zonsopgang,
				"zonsondergang" : zonsondergang,
				"expiration_time" : expiration_time
			};					
			var current_time = new Date().getTime();
			
			window.localStorage.setItem(stad, JSON.stringify(weather));
		})
}

function loadCountries() {
	var uri = "http://localhost:8080/firstapp/restservices/countries";
	fetch(uri)
		.then(response => response.json())
		.then(function(myJson){
			for(const country of myJson){
				var table = document.getElementById("table");
				var row = table.insertRow(1);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				var cell6 = row.insertCell(5);
				var cell7 = row.insertCell(6);
				cell6.className = "hide";
				cell7.className = "hide";
				
				var stad = country.Capital;
				cell1.innerHTML = country.Name;
				cell2.innerHTML = stad;
				cell3.innerHTML = country.Region;
				cell4.innerHTML = country.Surface;
				cell5.innerHTML = country.Population;
				cell6.innerHTML = country.Latitude;
				cell7.innerHTML = country.Longitude;
				var current_time = new Date().getTime();
				var storage = window.localStorage.getItem(stad);
				
				if(storage != null){
					storage_array = JSON.parse(storage);
					expirationtime = storage_array.expiration_time;
					city = storage_array.stad;
					console.log(current_time + ' is tijd van nu');
					console.log(expirationtime + ' is tijd van storage')
					
					if(current_time > expirationtime){
						localStorage.removeItem(city);
						console.log(city + " is uit de lokale storage verwijderd.");
						
					}
				}
					
			}
		
			table = document.querySelectorAll("#table tr");
			for	(const row of table){
				row.addEventListener("click", function(){
					var lat = row.cells[5].innerHTML;
					var lng = row.cells[6].innerHTML;
					var city = row.cells[1].innerHTML;
					showWeather(lat, lng, city);
				});
			}
		})
}

function getMyLocation(){
	var city = document.querySelector("#city");
	city.addEventListener("click", function(){
		var lat = document.getElementById("latitude").innerHTML;
		var lng = document.getElementById("longitude").innerHTML;
		var city = document.getElementById("city").innerHTML;
		showWeather(lat,lng,city);
	});
}