var roo = {};
roo.alertBox = function (content) {
    $('#roo-modal').html(content).addClass('is-active');
};