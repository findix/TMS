var TableEditable = function () {

    var handleTable = function () {

        function restoreRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);
            oTable.fnUpdate(aData[0].value, nRow, 0, false);
            oTable.fnUpdate(aData[1].value, nRow, 1, false);
            $("select", jqTds[2]).val(aData[2]);
            $("select", jqTds[3]).val(aData[3]);
            oTable.fnUpdate(aData[4], nRow, 4, false);
            oTable.fnUpdate(aData[5], nRow, 5, false);
            oTable.fnDraw();
        }

        function editRow(oTable, nRow) {
            var type = ["学生", "校教务处管理员", "教学督导组", "院管理员", "教师"];
            var department = {
                "上海电力学院": "00",
                "计算机科学与技术学院": "01",
                "电子与信息工程学院": "02",
                "自动化工程学院": "03",
                "外国语学院": "04",
                "电气工程学院": "05",
                "能源与机械工程学院": "06",
                "环境与化学工程学院": "07",
                "经济与管理学院": "08",
                "国际交流学院": "09",
                "数理学院": "10"
            };
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);
            jqTds[0].innerHTML = '<input name="teacher.tid" type="text" class="form-control input-small" value="' + aData[0] + '">';
            jqTds[1].innerHTML = '<input name="teacher.tid" type="text" class="form-control input-small" value="' + aData[1] + '">';
            jqTds[2].innerHTML = '<select name="teacher.type" class="form-control form-filter input-sm"> <option value="1">校教务处管理员</option> <option value="2">教学督导组</option> <option value="3">院管理员</option> <option value="4">教师</option></select>';
            jqTds[3].innerHTML = '<select name="teacher.did" class="form-control form-filter input-sm"> <option value="00">上海电力学院</option> <option value="01">计算机科学与技术学院</option> <option value="02">电子与信息工程学院</option> <option value="03">自动化工程学院</option> <option value="04">外国语学院</option> <option value="05">电气工程学院</option> <option value="06">能源与机械工程学院</option> <option value="07">环境与化学工程学院</option> <option value="08">经济与管理学院</option> <option value="09">国际交流学院</option> <option value="10">数理学院</option> </select>';
            jqTds[4].innerHTML = '<a class="edit" href="">Save</a>';
            jqTds[5].innerHTML = '<a class="cancel" href="">取消</a>';
            $("select", jqTds[2]).val(aData[2] == "" ? "1" : type.indexOf(aData[2]));
            $("select", jqTds[3]).val(aData[3] == "" ? "00" : department[(aData[3])]);
        }

        function saveRow(oTable, nRow) {
            var type = ["学生", "校教务处管理员", "教学督导组", "院管理员", "教师"];
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
            oTable.fnUpdate(type[jqSelects[0].value], nRow, 2, false);
            oTable.fnUpdate(department[jqSelects[1].value], nRow, 3, false);
            oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 4, false);
            oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow, 5, false);
            oTable.fnDraw();
            $.post("/teacher/saveAndAdd",
                {
                    "teacher.tid": jqInputs[0].value,
                    "teacher.tname": jqInputs[1].value,
                    "teacher.type": jqSelects[0].value,
                    "teacher.did": jqSelects[1].value
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
            oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 4, false);
            oTable.fnDraw();
        }

        var table = $('#sample_editable_1');

        var oTable = table.dataTable({
            "lengthMenu": [
                [10, 15, 20, -1],
                [10, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 10,

            "language": {
                "lengthMenu": " _MENU_ records"
            },
            "columnDefs": [{ // set default column settings
                'orderable': false,
                'targets': [4,5]
            }, {
                "searchable": false,
                "targets": [4,5]
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

            if (confirm("您确定要删除这条记录吗?") == false) {
                return;
            }

            var nRow = $(this).parents('tr')[0];
            var jqInputs = $('td', nRow);
            $.post("/teacher/delete",
                {
                    "teacher.tid": jqInputs[0].innerHTML
                }, function (data, status) {

                });
            oTable.fnDeleteRow(nRow);
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
            } else {
                /* No edit in progress - let's start one */
                editRow(oTable, nRow);
                nEditing = nRow;
            }
        });
    };

    return {
        //main function to initiate the module
        init: function () {
            handleTable();
        }
    };
}();