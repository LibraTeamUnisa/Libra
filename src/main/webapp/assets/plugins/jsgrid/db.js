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


    db.aziende = [
        { Name: "", Id: 0 },
        { Name: "Microsoft", Id: 1 },
        { Name: "Hp", Id: 2 },
    ];

    db.clients = [
        {
            "Azienda": 1,
            "Studenti": 61,
            "Ambito": ["Uno","Due","Tre"],
            "Feedback": 4,
        },
        {
            "Azienda": 2,
            "Studenti": 140,
            "Ambito": ["Uno","Due","Tre"],
            "Feedback": 6,
        }]

}());