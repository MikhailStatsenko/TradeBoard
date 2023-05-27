$(document).ready(function() {
    $('.btn-add-characteristic').click(function() {
        const $characteristics = $('#characteristics');
        const index = $characteristics.find('input').length / 2;
        const html = '<div class="row mt-2">' +
            '<div class="col-5">' +
            '<input type="text" class="form-control" name="characteristics.keys" placeholder="Название характеристики">' +
            '</div>' +
            '<div class="col-5">' +
            '<input type="text" class="form-control" name="characteristics.values" placeholder="Значение">' +
            '</div>' +
            '<div class="col-2">' +
            '<button type="button" class="btn btn-outline-secondary btn-remove-characteristic">Удалить</button>' +
            '</div>' +
            '</div>';
        $characteristics.append(html);
    });

    $(document).on('click', '.btn-remove-characteristic', function() {
        $(this).closest('.row').remove();
    });
});