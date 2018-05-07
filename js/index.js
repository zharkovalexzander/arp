function main() {
        
    var map = L.map('map').setView([-96.80508818973559, 32.778226121521236].reverse(), 17);

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 17,
        id: 'mapbox.streets'
    }).addTo(map);


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
    let grades = ['#234', 'Price: 3.40$', 'Capacity: 40 seats', 'Available: 0 seats'];

    var legend = L.control({position: 'topright'});

    legend.onAdd = function (map) {

        var div = L.DomUtil.create('div', 'info legend');

        // loop through our density intervals and generate a label with a colored square for each interval
        for (var i = 0; i < grades.length; i++) {
            div.innerHTML += "" + grades[i] + '<br>';
        }

        return div;
    };

    legend.addTo(map);

    let i = 0;
    var marker = L.marker(points[i].reverse(), {icon: myIcon}).addTo(map);
    setInterval(function () {
        grades[3] = 'Available: ' + randomInteger(0, 40) + ' seats';
        marker.setLatLng(points[i++ % points.length].reverse());
        legend.remove();

        legend = L.control({position: 'topright'});

        legend.onAdd = function (map) {

            var div = L.DomUtil.create('div', 'info legend');

            // loop through our density intervals and generate a label with a colored square for each interval
            for (var i = 0; i < grades.length; i++) {
                div.innerHTML += "" + grades[i] + '<br>';
            }

            return div;
        };

        legend.addTo(map);


    }, 650);
}

function randomInteger(min, max) {
    let rand = min - 0.5 + Math.random() * (max - min + 1)
    rand = Math.round(rand);
    return rand;
}

window.onload = main;