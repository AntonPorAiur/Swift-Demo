$('document').ready(function() {

    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

//    $('#paramSearchBtn').on('click',function(event) {
//
//        var searchParam = $("#tstSearch").val();
//        if(searchParam !== '') {
//
//            urlString = "http://localhost:8080/listar";
//            $.ajax({
//                method: "GET",
//                url: urlString,
//                data: {
//                 keyword: searchParam
//                },
//                success: function(response) {
//                //Do Something
//                },
//                error: function(xhr) {
//                //Do Something to handle error
//                }
//            })
//        }
//    });

    //Para formar mensaje Swift
    $('#submit-message').on('click',function(event) {
        event.preventDefault();

        var parameters = {
            ops: []
        }

        $("#lstOperaciones :checkbox:checked").each(function() {
            parameters.ops.push($(this).closest('tr').find("td:eq(1)").text());
        });

        urlString = "http://localhost:8080/createMessage/swift";

        $.ajax({
            type: "POST",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            url: urlString,
            data: JSON.stringify(parameters),

            beforeSend: function(xhr){
                xhr.setRequestHeader(csrfHeader,csrfToken);
             },

        }).done(function(response) {
                // $("message").val(response.swift);
                 console.log(response.mensaje);
            })
            .fail(function(){
                // alert("No se logró formar mensaje")
            })

//        alert("Object:" + selected);
        $('#messageModal').modal();
    });

//    //Guardar operacion
//    $('#guardarOperacion').on('click',function(event) {
//        url = "http://localhost:8080/nuevaOperacion";
//        jsonData= {
//            referencia: $("#referencia").val(),
//            fecha_valor: $("#fechaValor").val(),
//            moneda: $("#divisas :selected").val(),
//            monto: $("#monto").val(),
//            estatus: '8',
//            contrato_cliente: $("#contratoCliente").val(),
//            nombre_cliente: $("#nombreCliente").val(),
//            direccion_cliente: $("#direccionCliente").val(),
//            corresponsal: $("#corresponsales :selected").val(),
//            cta_banco_bnf: $("#ctaBancoBeneficiario").val(),
//            nombre_banco_bnf: $("#nomBancoBeneficiario").val(),
//            cta_beneficiario: $("#ctaBeneficiario").val(),
//            tipo_mensaje: 'MT103',
//        }
//
//        $.ajax({
//            type: "POST",
//            url: url,
//
//            beforeSend: function(xhr){
//                xhr.setRequestHeader(csrfHeader,csrfToken);
//            },
//            data: JSON.stringify(jsonData),
//            contentType: 'application/json'
//
//        }).done(function(){
//            $('#addNewModal').modal('hide');
//        }).fail(function(){
//            alert("Fail Add New");
//        })
//
//    });

    //Opcion para agregar nueva operacion
    $('#addNewBtn').on('click',function(event) {
        event.preventDefault();

        urlString = "http://localhost:8080/formOperacion/modal";

        $("#resultBlock").load(urlString);

         $('#corresponsales').empty();
         getCorresponsales();

         $('#divisas').empty();
         getDivisas();

        // $('#addNewModal').modal();
    });

    //Opcion para editar operacion
    $('#edit').on('click',function(event) {
        event.preventDefault();
        $('#editModal').modal();
    });

    // Petición para obtener corresponsales
    function getCorresponsales() {
        urlString = "http://localhost:8080/catalogos/corresponsales";

        $.ajax({
            method: "GET",
            url: urlString,

        }).done(function(response) {
                corresponsalesDropdown = $('#corresponsales');
                $.each(response, function(index, value) {
                    $("<option>").val(value.id).text(value.banco).appendTo(corresponsalesDropdown)
                });
            })
            .fail(function(){
                alert("Error no se puede obtener corresponsales")
            })
    }
    // Función para obtener Divisas
    function getDivisas() {
        urlString = "http://localhost:8080/catalogos/divisas";

        $.ajax({
            method: "GET",
            url: urlString

        }).done(function(response) {
                divisasDropdown = $('#divisas');
                $.each(response, function(index, value) {
                    $("<option>").val(value.id).text(value.iso).appendTo(divisasDropdown)
                });
            })
            .fail(function(){
                alert("Error no se puede obtener divisas")
            })
    }

});