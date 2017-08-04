var roo = {};
roo.alertBox = function (content) {
    $('#roo-modal').find('.box').text(content);
    $('#roo-modal').addClass('is-active');
};
$('.modal-close,.modal-background').click(function () {
    $('#roo-modal').removeClass('is-active');
});