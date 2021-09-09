var operacionesController = {

    services: {
        formMessage: {
            resource: "createMessage",
            url: "http://localhost:8080/",
            parameters: {
                idOperaciones: null
            },
            reponse: {
                OK: "1",
                ERROR: "2"
            }
        },
        depositFile: {
            resource: "depositFile",
            url: "http://localhost:8080/",
            parameters: {
                message: null
            }
        }
    },
    initEventHandlers: function() {

    },
    read: function() {

        // operacionesController.services.parameters = ;

        // $.ajax({
        //     url: this.services.formMessage.url+=this.services.formMessage.resource,
        //     type: "POST"
        //     data:
        // })
    }

}