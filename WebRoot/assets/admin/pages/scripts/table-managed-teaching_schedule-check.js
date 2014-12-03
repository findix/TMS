var TableManaged = function () {

    var initTable2 = function () {

        var table = $('#sample_2');

        table.dataTable({
            "lengthMenu": [
                [10, 15, 20, -1],
                [10, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 10,
            "language": {
                "lengthMenu": " _MENU_ records",
                "paging": {
                    "previous": "Prev",
                    "next": "Next"
                }
            },
            "columnDefs": [{  // set default column settings
                'orderable': false,
                'targets': [4]
            }, {
                "searchable": false,
                "targets": [4]
            }],
            "order": [
                [0, "asc"]
            ] // set first column as a default sort by asc
        });

        var tableWrapper = jQuery('#sample_2_wrapper');

        table.find('.group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                } else {
                    $(this).attr("checked", false);
                }
            });
            jQuery.uniform.update(set);
        });

        tableWrapper.find('.dataTables_length select').select2(); // initialize select2 dropdown


    };

    $("a[data-target='#appoint']").click(function () {
        $("#cid")[0].value = $(this).attr("cid");

    });

    $("button#pass").click(function () {
        $.post("/schedule/status",
            {
                cid: $(this).attr('value'),
                status:"已通过"
            },
            function (data, status) {
            });
        $("#label",$(this).parent().parent()).html('<span class="label label-sm label-success"> 已通过 </span>')
    });

    $("button#nopass").click(function(){
        $.post("/schedule/status",
            {
                cid: $(this).attr('value'),
                status:"不通过"
            },
            function (data, status) {
            });
        $("#label",$(this).parent().parent()).html('<span class="label label-sm label-danger"> 不通过 </span>')
    });

    return {

        //main function to initiate the module
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }

            initTable2();
        }

    };

}();