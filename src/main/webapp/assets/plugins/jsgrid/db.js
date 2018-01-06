(function() {

    var db = {

        loadData: function(filter) {
            return $.grep(this.clients, function(client) {
                return (!filter.Studenti || client.Studenti.indexOf(filter.Studenti) > -1)
                    && (!filter.Azienda || client.Azienda === filter.Azienda)
                    && (!filter.Ambito || client.Ambito.indexOf(filter.Ambito) > -1)
            });
        },

    };

    window.db = db;
    
    db.clients = [
        {
            "Azienda": "Microsoft",
            "Studenti": 61,
            "Ambito": ["Uno","Due","Tre"],
            "Feedback": 4,
        },
        {
            "Azienda": "Hp",
            "Studenti": 140,
            "Ambito": ["Uno","Due","Tre"],
            "Feedback": 6,
        }]

}());