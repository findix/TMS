var TableEditable = function () {

    var handleTable = function () {

        function restoreRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);
            oTable.fnUpdate(aData[0].value, nRow, 0, false);
            oTable.fnUpdate(aData[1].value, nRow, 1, false);
            oTable.fnUpdate(aData[2].value, nRow, 2, false);
            oTable.fnUpdate(aData[3].value, nRow, 3, false);
            $("select", jqTds[4]).val(aData[4]);
            $("select", jqTds[5]).val(aData[5]);
            oTable.fnUpdate(aData[6], nRow, 6, false);
            oTable.fnUpdate(aData[7], nRow, 7, false);
            oTable.fnDraw();
        }

        function editRow(oTable, nRow) {
            var department={"上海电力学院":"00","计算机科学与技术学院":"01","电子与信息工程学院":"02","自动化工程学院":"03","外国语学院":"04","电气工程学院":"05","能源与机械工程学院":"06","环境与化学工程学院":"07","经济与管理学院":"08","国际交流学院":"09","数理学院":"10"};
            var major={"软件工程":"001","信息安全":"002","计算机科学与技术":"003"};
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);
            jqTds[0].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[0] + '">';
            jqTds[1].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[1] + '">';
            jqTds[2].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[2] + '">';
            jqTds[3].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[3] + '">';
            jqTds[4].innerHTML = '<select name="course.did" class="form-control form-filter input-sm"> <option value="00">上海电力学院</option> <option value="01">计算机科学与技术学院</option> <option value="02">电子与信息工程学院</option> <option value="03">自动化工程学院</option> <option value="04">外国语学院</option> <option value="05">电气工程学院</option> <option value="06">能源与机械工程学院</option> <option value="07">环境与化学工程学院</option> <option value="08">经济与管理学院</option><option value="09">国际交流学院</option> <option value="10">数理学院</option> </select>';
            jqTds[5].innerHTML = '<select name="course.mid" class="form-control form-filter input-sm"> <option value="1">软件工程</option> <option value="2">信息安全</option> <option value="3">计算机科学与技术</option></select>';
            jqTds[6].innerHTML = '<a class="edit" href="">Save</a>';
            jqTds[7].innerHTML = '<a class="cancel" href="">Cancel</a>';
            $("select", jqTds[4]).val(aData[4] == "" ? "00" : department[(aData[4])]);
            $("select", jqTds[5]).val(aData[5] == "" ? "001" : major[(aData[5])]);
        }

        function saveRow(oTable, nRow) {
            var major={
                "001":"软件工程",
                "002":"信息安全",
                "003":"计算机科学与技术"
            };
            var department = {
                "00": "上海电力学院",
                "01": "计算机科学与技术学院",
                "02": "电子与信息工程学院",
                "03": "自动化工程学院",
                "04": "外国语学院",
                "05": "电气工程学院",
                "06": "能源与机械工程学院",
                "07": "环境与化学工程学院",
                "08": "经济与管理学院",
                "09": "国际交流学院",
                "10": "数理学院"
            };
            var jqInputs = $('input', nRow);
            var jqSelects = $('select', nRow);
            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
            oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
            oTable.fnUpdate(department[jqSelects[1].value], nRow, 4, false);
            oTable.fnUpdate(major[jqSelects[1].value], nRow, 5, false);
            oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 6, false);
            oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 7, false);
            oTable.fnDraw();
            $.post("/course/saveAndAdd",
                {
                    "course.cid": jqInputs[0].value,
                    "course.cname": jqInputs[1].value,
                    "course.cenglish": jqInputs[2].value,
                    "course.credit": jqInputs[3].value,
                    "course.did": jqSelects[4].value,
                    "course.mid": jqSelects[5].value
                }, function (data, status) {
                    //location.reload();
                });
        }

        function cancelEditRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
            oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
            oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
            oTable.fnUpdate(jqInputs[5].value, nRow, 5, false);
            oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 6, false);
            oTable.fnDraw();
        }

        var table = $('#sample_editable_1');

        var oTable = table.dataTable({
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 10,

            "language": {
                "lengthMenu": " _MENU_ records"
            },
            "columnDefs": [{ // set default column settings
                'orderable': true,
                'targets': [0]
            }, {
                "searchable": true,
                "targets": [0]
            }],
            "order": [
                [0, "asc"]
            ] // set first column as a default sort by asc
        });

        var tableWrapper = $("#sample_editable_1_wrapper");

        tableWrapper.find(".dataTables_length select").select2({
            showSearchInput: false //hide search box with special css class
        }); // initialize select2 dropdown

        var nEditing = null;
        var nNew = false;

        $('#sample_editable_1_new').click(function (e) {
            e.preventDefault();

            if (nNew && nEditing) {
                if (confirm("Previose row not saved. Do you want to save it ?")) {
                    saveRow(oTable, nEditing); // save
                    $(nEditing).find("td:first").html("Untitled");
                    nEditing = null;
                    nNew = false;

                } else {
                    oTable.fnDeleteRow(nEditing); // cancel
                    nEditing = null;
                    nNew = false;
                    
                    return;
                }
            }

            var aiNew = oTable.fnAddData(['', '', '', '', '', '']);
            var nRow = oTable.fnGetNodes(aiNew[0]);
            editRow(oTable, nRow);
            nEditing = nRow;
            nNew = true;
        });

        table.on('click', '.delete', function (e) {
            e.preventDefault();

            if (confirm("Are you sure to delete this row ?") == false) {
                return;
            }

            var nRow = $(this).parents('tr')[0];
            oTable.fnDeleteRow(nRow);
            alert("Deleted! Do not forget to do some ajax to sync with backend :)");
        });

        table.on('click', '.cancel', function (e) {
            e.preventDefault();

            if (nNew) {
                oTable.fnDeleteRow(nEditing);
                nNew = false;
            } else {
                restoreRow(oTable, nEditing);
                nEditing = null;
            }
        });

        table.on('click', '.edit', function (e) {
            e.preventDefault();

            /* Get the row as a parent of the link that was clicked on */
            var nRow = $(this).parents('tr')[0];

            if (nEditing !== null && nEditing != nRow) {
                /* Currently editing - but not this row - restore the old before continuing to edit mode */
                restoreRow(oTable, nEditing);
                editRow(oTable, nRow);
                nEditing = nRow;
            } else if (nEditing == nRow && this.innerHTML == "Save") {
                /* Editing this row and want to save it */
                saveRow(oTable, nEditing);
                nEditing = null;
                alert("Updated! Do not forget to do some ajax to sync with backend :)");
            } else {
                /* No edit in progress - let's start one */
                editRow(oTable, nRow);
                nEditing = nRow;
            }
        });
    }

    return {

        //main function to initiate the module
        init: function () {
            handleTable();
        }

    };

}();