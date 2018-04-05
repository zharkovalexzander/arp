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

    let lng = points[0][0];
    let lat = points[0][1];
    let i = 1;
    var marker = L.marker([lng, lat].reverse(), {icon: myIcon}).addTo(map);
    setInterval(function () {
        marker.setLatLng(points[i].reverse());
        if(i == points.length - 1) {
            i = 1;
        } else {
            i++;
        }
    }, 250);


}

window.onload = main;