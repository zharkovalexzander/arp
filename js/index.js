function main() {
        
    var map = L.map('map').setView([32.7766642, -96.7969879], 16);

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 18,
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
            '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
            'Imagery © <a href="http://mapbox.com">Mapbox</a>',
        id: 'mapbox.light'
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

}
    
};