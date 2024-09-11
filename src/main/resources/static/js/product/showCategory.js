$(document).ready(function() {
    $('.mainCategoryCode').change(function () {
        var parentId = $(this).val();
        if (parentId) {
            $.ajax({
                url: '/category/subcategories',
                type: 'GET',
                data: {parentId: parentId},
                success: function (data) {
                    var subCategorySelect = $('.subCategoryCode');
                    subCategorySelect.empty();
                    subCategorySelect.append('<option value="">Select Sub Category</option>');
                    $.each(data, function (index, subCategory) {
                        subCategorySelect.append('<option value="' + subCategory.categoryCode + '">' + subCategory.categoryName + '</option>');
                    });
                }
            });
        } else {
            $('#subCategoryCode').html('<option value="">Select Sub Category</option>');
        }
    });
});