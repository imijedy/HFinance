$(document).ready(function() {
    moment.locale("hu");
    /*Hozzáadás gomb kattintás figyelés*/
    $('#addCostTypeBtn').click(function(){
        $("#costTypeCategoryFormData").empty();
        $('#cost_type_form')[0].reset();
        $('.modal-title').text("Költség kategória hozzáadása");
        $('#action').val("Hozzáadás");
        $('#operation').val("Add");
        $.ajax({url: "/getcosttypcecategories", success: function(result){
            var costTypeAppend='';
            $.each(result, function( index, value ) {
                costTypeAppend+='<option value="'+value.id+'">'+ value.name +'</option>>';
            });
            $("#costTypeCategoryFormData").append(costTypeAppend);
        }});
    });
    /*Hozzáadás gomb kattintás figyelés vége*/
    /*Edit gomb kattintás figyelés*/
    $(document).on('click', '.deleteCostTypeModalBtn', function(event){
        event.preventDefault();
        var costTypeId = $('#costTypeDelId').val();
        $.get("/getcostsbytype/" + costTypeId, function (costsResult) {
            if(costsResult.length == 0){
                $.get("/deletecosttype/" + costTypeId, function (result) {
                    $('#deleteCostTypeModal').modal('hide');
                    costTypeDataTable.ajax.reload();
                });
            }else{
                alert("Nem lehet olyan kategóriát törölni, amihez már tartozik költség!");
            }
        });
    });
    $(document).on('click', '.deleteCostTypeBtn', function(event){
        event.preventDefault();
        var costTypeId = $(this).attr('data');
        $('#costTypeDelId').val(costTypeId);
    });
    $(document).on('click', '.editCostTypeBtn', function(event){
        event.preventDefault();
        $("#costTypeCategoryFormData").empty();
        $('#cost_type_form')[0].reset();
        $('.modal-title').text("Költség kategória szerkesztése");
        $('#action').val("Módosít");
        $('#operation').val("Update");
        var costTypeId = $(this).attr('data');
        $('#idFormData').val(costTypeId);

        $.ajax({url: "/getcosttype/" + costTypeId, success: function(costTypeResult){
            $.ajax({url: "/getcosttypcecategories", success: function(costTypeCategoryResult){
                var costTypeCategoryAppend='';
                $.each(costTypeCategoryResult, function( index, value ) {
                    costTypeCategoryAppend+='<option value="'+value.id+'">'+ value.name +'</option>>';
                });
                $("#costTypeCategoryFormData").append(costTypeCategoryAppend).val(costTypeResult.category.id);;
            }});
            $("#costTypeNameFormData").val(costTypeResult.name);
        }});

    });
    /*Edit gomb kattintás figyelés vége*/
    /*Adattábla beállítása*/
    var costTypeDataTable = $('#costTypeDataTable').DataTable( {
        "language": {
            "url": "js/Hungarian.json"
        },
        "sAjaxSource": "/getcosttypes",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [

            {
                data: "name",
                render: function ( data, type, row ) {
                    return '<b>' + data + '</b>';
                }
            },
            {
                data: "category.name",
                render: function (data, type, row) {
                    return data;
                }
            },
            {
                orderable: false,
                data: "id",
                render: function ( data, type, row ) {
                    return '<div class="row justify-content-start"><div class="col-1"><i data-toggle="modal" data-target="#costTypeModal" data='+data+' class="fas fa-pen text-primary editCostTypeBtn "></i></div><div class="col-1"><i data-toggle="modal" data-target="#deleteCostTypeModal" data='+data+' class="fas fa-trash text-danger deleteCostTypeBtn"></i></div></div></div>';
                }
            }
        ]
    } );
    /*Adattábla beállítás vége*/
    /*Elküldés gomb figyelése*/
    $(document).on('submit', '#cost_type_form', function(event){
        event.preventDefault();
        var newCostType = {};
        var costTypeName = $('#costTypeNameFormData').val();
        var costTypeCategoryId = $('#costTypeCategoryFormData').val();

        /*Objektum feltöltés*/
        if(costTypeName != '' && costTypeName != null) {
            $.when(
                $.get("/getcosttypecategory/" + costTypeCategoryId, function (result) {
                    newCostType ["category"] = result;
                })
            ).then(function () {
                if($('#operation').val() == "Update"){
                    newCostType ["id"] = $('#idFormData').val();
                }
                /*Lehet null*/
                newCostType ["name"] = costTypeName;
                $.ajax({
                    url: "/addcosttype",
                    method: 'POST',
                    data: JSON.stringify(newCostType),
                    cache: false,
                    contentType: "application/json",
                    success: function (data) {
                        $('#cost_type_form')[0].reset();
                        $('#costTypeModal').modal('hide');
                        costTypeDataTable.ajax.reload();
                    }
                });
            });
        }else {
            alert("Nem töltötte ki valamelyik kötelező mezőt!");
        }
    });
    /*Elküldés gomb figyelés vége*/
} );