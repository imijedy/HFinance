$(document).ready(function() {
    moment.locale("hu");
    /*Hozzáadás gomb kattintás figyelés*/
    $('#addCostBtn').click(function(){
        $("#costTypeFormData").empty().attr("disabled", false);
        $('#cost_form')[0].reset();
        $('.modal-title').text("Költség hozzáadása");
        $('#action').val("Hozzáadás");
        $('#operation').val("Add");
        $.ajax({url: "/getcosttypes", success: function(result){
            var costTypeAppend='';
            $.each(result, function( index, value ) {
                costTypeAppend+='<option value="'+value.id+'">'+ value.name +'</option>>';
            });
            $("#costTypeFormData").append(costTypeAppend);
        }});
        $('#isPaidFormData').prop("checked", false ).change().attr("disabled", true);
    });
    /*Hozzáadás gomb kattintás figyelés vége*/
    /*Edit gomb kattintás figyelés*/
    $(document).on('click', '.deleteModalBtn', function(event){
        event.preventDefault();
        var costId = $('#costDelId').val();
        $.get("/deletecost/" + costId, function (result) {
            $('#deleteModal').modal('hide');
            dataTable.ajax.reload();
        });
    });
    $(document).on('click', '.deleteBtn', function(event){
        event.preventDefault();
        var costId = $(this).attr('data');
        $('#costDelId').val(costId);
    });
    $(document).on('click', '.editBtn', function(event){
        event.preventDefault();
        $("#costTypeFormData").empty();
        $('#cost_form')[0].reset();
        $('.modal-title').text("Költség szerkesztése");
        $('#action').val("Módosít");
        $('#operation').val("Update");
        var costId = $(this).attr('data');
        $('#idFormData').val(costId);

        $.ajax({url: "/getcost/" + costId, success: function(result){
            var costTypeAppend='';
            costTypeAppend+='<option value="'+result.costType.id+'">'+ result.costType.name +'</option>>';
            $("#costTypeFormData").append(costTypeAppend).attr("disabled", true);
            $("#paymentMethodFormData").val(result.paymentMethod);
            var paymentPeriodTmp = new Date(result.paymentPeriod);
            var paymentDeadlineTmp = new Date(result.paymentDeadline);
            var paymentTimeTmp = new Date(result.paymentTime);
            $("#paymentPeriodFormData").val(paymentPeriodTmp.getFullYear() + "-" + ("0" + (paymentPeriodTmp.getMonth() + 1)).slice(-2));
            if(paymentDeadlineTmp.getFullYear() != "1970"){
                $("#paymentDeadlineFormData").val(paymentDeadlineTmp.getFullYear() + "-" + ("0" + (paymentDeadlineTmp.getMonth() + 1)).slice(-2) + "-" + ("0" + paymentDeadlineTmp.getDate()).slice(-2));
            }
            if(paymentTimeTmp.getFullYear() != "1970"){
                $("#paymentTimeFormData").val(paymentTimeTmp.getFullYear() + "-" + ("0" + (paymentTimeTmp.getMonth() + 1)).slice(-2) + "-" + ("0" + paymentTimeTmp.getDate()).slice(-2));
            }
            $("#amountFormData").val(result.amount);
            if(result.paymentMethod == 'None'){
                $('#isPaidFormData').prop("checked", result.paid).change().attr("disabled", true);
            }else{
                $('#isPaidFormData').removeAttr("disabled").prop("checked", result.paid).change();
            }
        }});

    });
    /*Edit gomb kattintás figyelés vége*/
    /*Toggle*/
    $('#isPaidFormData').bootstrapToggle({
        on: 'Befizetve',
        off: 'Tartozás',
        onstyle: 'success',
        offstyle: 'danger',
        width: 125
    });
    /*Adattábla beállítása*/
    var dataTable = $('#dataTable').DataTable( {
        "language": {
            "url": "js/Hungarian.json"
        },
        "sAjaxSource": "/getcosts",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [

            {
                data: "costType.name",
                render: function ( data, type, row ) {
                    return '<b>' + data + '</b>';
                }
            },
            {
                data: "paymentMethod",
                render: function ( data, type, row ) {
                    if(data == null || data == 'None'){
                        return '';
                    }else{
                        return data;
                    }
                }
            },
            {
                data: "paymentPeriod",
                render: function ( data, type, row ) {
                    if(data == null){
                        return '';
                    }else{
                        return moment(data).format('YYYY. MMMM');
                    }
                }
            },
            {
                data: "paymentDeadline",
                render: function ( data, type, row ) {
                    if(data == null){
                        return '';
                    }else{
                        return moment(data).format('YYYY. MM. DD');
                    }
                }
            },
            {
                data: "paymentTime",
                render: function ( data, type, row ) {
                    if(data == null){
                        return '';
                    }else {
                        return moment(data).format('YYYY. MM. DD');
                    }
                }
            },
            {
                data: "amount",
                render: $.fn.dataTable.render.number( ',', '.', 0, '<b class="text-primary">', ' Ft</b>' )
            },
            {
                data: "paid",
                render: function ( data, type, row ) {
                    if(data){
                        return '<b class="text-success">Befizetve</b>';
                    }else{
                        return '<b class="text-danger">Tartozás</b>'
                    }
                }
            },
            {
                orderable: false,
                data: "id",
                render: function ( data, type, row ) {
                    return '<div class="row justify-content-start"><div class="col-1"><i data-toggle="modal" data-target="#costModal" data='+data+' class="fas fa-pen text-primary editBtn "></i></div><div class="col-1"><i data-toggle="modal" data-target="#deleteModal" data='+data+' class="fas fa-trash text-danger deleteBtn"></i></div></div></div>';
                }
            }
        ],
        "createdRow": function( row, data, dataIndex,cells){
            var currentDate = new Date();
            var currentDateLastDay = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0);
            var paymentPeriodDate = new Date(data.paymentPeriod);

            if( data.paid ==  true){
                $(row).addClass('dataTablePaidColor');
            }else if((paymentPeriodDate.getYear() == currentDate.getYear()) && (paymentPeriodDate.getMonth() == currentDate.getMonth())){
                $(row).addClass('dataTableNotPaidInMonthColor');
            }else if (paymentPeriodDate > currentDateLastDay){
                $(row).addClass('dataTableDefaultColor');
            }else{
                $(row).addClass('dataTableNotPaidColor');
            }
        }
    } );
    /*Adattábla beállítás vége*/
    /*Modal valtozasok figyelése*/
    $('#paymentMethodFormData').change(function () {
        var paymentMethodVal = $('#paymentMethodFormData').val();
        if(paymentMethodVal == 'None'){
            $('#isPaidFormData').prop("checked", false).change().attr("disabled", true);
        }else{
            $('#isPaidFormData').removeAttr("disabled").prop("checked", true).change();
        }
    });
    /*Modal valtozasok figyelése vége*/
    /*Elküldés gomb figyelése*/
    $(document).on('submit', '#cost_form', function(event){
        event.preventDefault();
        var newCost = {};

        var paymentMethod = $('#paymentMethodFormData').val();
        var paymentPeriod = $('#paymentPeriodFormData').val();
        var paymentDeadline = $('#paymentDeadlineFormData').val();
        var paymentTime = $('#paymentTimeFormData').val();
        var amount = $('#amountFormData').val();
        var paid = $('#isPaidFormData').is(':checked');
        var costTypeId = $('#costTypeFormData').val();

        /*Objektum feltöltés*/
        if(amount != '' && amount != null && paymentPeriod != null && paymentPeriod != '') {
            $.when(
                // Get the HTML
                $.get("/getcosttype/" + costTypeId, function (result) {
                    newCost ["costType"] = result;
                })
            ).then(function () {
                if($('#operation').val() == "Update"){
                    newCost ["id"] = $('#idFormData').val();
                }
                /*Lehet null*/
                newCost ["paymentMethod"] = paymentMethod;
                /*Lehet null*/
                newCost ["paymentPeriod"] = new Date(paymentPeriod);
                /*Lehet null*/
                newCost ["paymentDeadline"] = paymentDeadline;
                /*Lehet null*/
                newCost ["paymentTime"] = paymentTime;
                /*Nem lehet null*/
                newCost ["amount"] = parseFloat(amount).toFixed(1);
                /*Nem lehet null*/
                newCost ["paid"] = paid;
                $.ajax({
                    url: "/addcost",
                    method: 'POST',
                    data: JSON.stringify(newCost),
                    cache: false,
                    contentType: "application/json",
                    success: function (data) {
                        $('#cost_form')[0].reset();
                        $('#costModal').modal('hide');
                        dataTable.ajax.reload();
                    }
                });
            });
        }else {
            alert("Nem töltötte ki valamelyik kötelező mezőt!");
        }
    });
    /*Elküldés gomb figyelés vége*/
} );