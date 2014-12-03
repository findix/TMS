var ComponentsDropdowns = function () {

    var handleSelect2 = function () {

        $('#tid').select2({
            placeholder: "请选择",
            allowClear: true
        });
    };

    return {
        //main function to initiate the module
        init: function () {
            handleSelect2();
        }
    };

}();