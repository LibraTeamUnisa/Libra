(function() {

    var db = {

        loadData: function(filter) {
            return $.grep(this.clients, function(client) {
                return (!filter.Azienda || client.Azienda.indexOf(filter.Azienda) > -1)
                    && (!filter.Studenti || client.Studenti === filter.Studenti)
                    && (!filter.Ambito || client.Ambito.indexOf(filter.Ambito) > -1)
            });
        },

    };

    window.db = db;


    db.aziende = [
        { Azienda: "", Id: 0 },
        { Azienda: "Microsoft", Id: 1 },
        { Azienda: "Hp", Id: 2 },
    ];

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