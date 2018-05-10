function main() {

    var myPos = [-96.80060824183424, 32.780095050808356];
    var busStop = [-96.80288368185352, 32.77899485490894];
        
    var map = L.map('map').setView([-96.80508818973559, 32.778226121521236].reverse(), 17);

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 17,
        id: 'mapbox.streets'
    }).addTo(map);

    var firstpolyline = new L.Polyline([myPos.reverse(), busStop.reverse()], {
        color: 'yellow',
        weight: 3,
        opacity: 1,
        smoothFactor: 1
    });

    firstpolyline.addTo(map);

    var myIcon = L.icon({
        iconUrl: 'http://www.icons101.com/icon_ico/id_67360/SchoolBus.ico',
        iconSize: [40, 40]
    });

    var myIcon1 = L.icon({
        iconUrl: 'https://www.freeiconspng.com/uploads/red-location-icon-1.png',
        iconSize: [25, 40],
        iconAnchor: [10, 40],
    });

    var myIcon2 = L.icon({
        iconUrl: 'http://files.softicons.com/download/social-media-icons/free-social-media-icons-by-aha-soft/png/512x512/User.png',
        iconSize: [40, 40]
    });

    var marker2 = L.marker(myPos, {icon: myIcon2}).addTo(map);

    var marker1 = L.marker(busStop, {icon: myIcon1}).addTo(map);


    L.geoJSON(freeBus, {

        filter: function (feature, layer) {
            if (feature.properties) {
                return feature.properties.underConstruction !== undefined ? !feature.properties.underConstruction : true;
            }
            return false;
        }

    }).addTo(map);
    
    map.on('click', function(e) {
         console.log("[" + e.latlng.lng + ", " + e.latlng.lat + "]");
    });

    var myIcon = L.icon({
        iconUrl: 'http://www.icons101.com/icon_ico/id_67360/SchoolBus.ico',
        iconSize: [40, 40]
    });

    let points = freeBus.features[0].geometry.coordinates;
    let grades = ['#234', 'Price: 3.40$', 'Available: 40 seats', 'Occupied: 0 seats'];

    var legend = L.control({position: 'topright'});

    legend.onAdd = function (map) {

        var div = L.DomUtil.create('div', 'info legend');

        for (var i = 0; i < grades.length; i++) {
            div.innerHTML += "" + grades[i] + '<br>';
        }

        return div;
    };

    legend.addTo(map);

    let i = 0;
    var marker = L.marker([0, 0], {icon: myIcon}).addTo(map);
    setInterval(function () {
        fetch("https://localhost:8443/get/1/gps").then(obj => obj.json()).then(obj => {
            fetch("https://localhost:8443/pass/1").then(pass => pass.json()).then(pass => {
                if (pass.value != null) {
                    grades[3] = 'Occupied: ' + pass.value + ' seats';
                }

                if (obj.value != null) {
                    marker.setLatLng([obj.value, obj.key]);
                }

                legend.remove();

                legend = L.control({position: 'topright'});

                legend.onAdd = function (map) {

                    var div = L.DomUtil.create('div', 'info legend');

                    for (var i = 0; i < grades.length; i++) {
                        div.innerHTML += "" + grades[i] + '<br>';
                    }

                    return div;
                };

                legend.addTo(map);
            });
        });
    }, 1000);
}

window.onload = main;