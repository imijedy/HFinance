$(document).ready(function() {
    moment.locale("hu");
    /*Hozzáadás gomb kattintás figyelés*/
    $('#addIncomeBtn').click(function(){
        $('#income_form')[0].reset();
        $('.modal-title').text("Bevétel hozzáadása");
        $('#action').val("Hozzáadás");
        $('#operation').val("Add");
    });
    /*Hozzáadás gomb kattintás figyelés vége*/
    /*Edit gomb kattintás figyelés*/
    $(document).on('click', '.deleteIncomeModalBtn', function(event){
        event.preventDefault();
        var incomeId = $('#incomeDelId').val();
        $.get("/deleteincome/" + incomeId, function (result) {
            $('#deleteIncomeModal').modal('hide');
            incomeDataTable.ajax.reload();
        });
    });
    $(document).on('click', '.deleteIncomeBtn', function(event){
        event.preventDefault();
        var incomeId = $(this).attr('data');
        $('#incomeDelId').val(incomeId);
    });
    $(document).on('click', '.editIncomeBtn', function(event){
        event.preventDefault();
        $('#income_form')[0].reset();
        $('.modal-title').text("Bevétel szerkesztése");
        $('#action').val("Módosít");
        $('#operation').val("Update");
        var incomeId = $(this).attr('data');
        $('#idFormData').val(incomeId);

        $.ajax({url: "/getincome/" + incomeId, success: function(result){
            $('#incomeInformationFormData').val(result.information);
            var incomeDateTmp = new Date(result.date);
            if(incomeDateTmp.getFullYear() != "1970"){
                $("#incomeDateFormData").val(incomeDateTmp.getFullYear() + "-" + ("0" + (incomeDateTmp.getMonth() + 1)).slice(-2) + "-" + ("0" + incomeDateTmp.getDate()).slice(-2));
            }
            $("#incomeAmountFormData").val(result.amount);
        }});

    });
    /*Edit gomb kattintás figyelés vége*/
    /*Adattábla beállítása*/
    var incomeDataTable = $('#incomeDataTable').DataTable( {
        "language": {
            "url": "js/Hungarian.json"
        },
        "sAjaxSource": "/getincome",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [

            {
                data: "information",
                render: function ( data, type, row ) {
                    return data;
                }
            },
            {
                data: "date",
                render: function ( data, type, row ) {
                    if(data == null){
                        return '';
                    }else{
                        return moment(data).format('YYYY. MM. DD');
                    }
                }
            },
            {
                data: "amount",
                render: $.fn.dataTable.render.number( ',', '.', 0, '<b class="text-success">', ' Ft</b>' )
            },
            {
                orderable: false,
                data: "id",
                render: function ( data, type, row ) {
                    return '<div class="row justify-content-start"><div class="col-1"><i data-toggle="modal" data-target="#incomeModal" data='+data+' class="fas fa-pen text-primary editIncomeBtn "></i></div><div class="col-1"><i data-toggle="modal" data-target="#deleteIncomeModal" data='+data+' class="fas fa-trash text-danger deleteIncomeBtn"></i></div></div>';
                }
            }
        ]
    } );
    /*Adattábla beállítás vége*/
    /*Elküldés gomb figyelése*/
    $(document).on('submit', '#income_form', function(event){
        event.preventDefault();
        var newIncome = {};
        var incomeInformation = $('#incomeInformationFormData').val();
        var incomeDate = $('#incomeDateFormData').val();
        var incomeAmount = $('#incomeAmountFormData').val();

        /*Objektum feltöltés*/
        if(incomeDate != '' && incomeDate != null && incomeAmount != '' && incomeAmount != null) {
            if($('#operation').val() == "Update"){
                newIncome ["id"] = $('#idFormData').val();
            }
            /*Lehet null*/
            newIncome ["information"] = incomeInformation;
            newIncome ["date"] = incomeDate;
            newIncome ["amount"] = incomeAmount;
            $.ajax({
                url: "/addincome",
                method: 'POST',
                data: JSON.stringify(newIncome),
                cache: false,
                contentType: "application/json",
                success: function (data) {
                    $('#income_form')[0].reset();
                    $('#incomeModal').modal('hide');
                    incomeDataTable.ajax.reload();
                }
            });
        }else {
            alert("Nem töltötte ki valamelyik kötelező mezőt!");
        }
    });
    /*Elküldés gomb figyelés vége*/
} );