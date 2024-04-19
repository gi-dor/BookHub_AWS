$('#id').on('keyup', function(event) {
    let inputValue = $(this).val();

    if (/^[^a-zA-Z0-9]*$/.test(inputValue)) {
        $(this).val("");
    }
});

