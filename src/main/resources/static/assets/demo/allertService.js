function loadTimeSuccessAlert(message, time) {
    $.confirm({
        title: 'Congratulations!',
        content: message,
        autoClose: 'close|' + time,
        type: 'green',
        buttons: {
            close: function () {
            }
        }
    });
}
function loadSuccessAlert(message) {
    $.confirm({
        title: 'Congratulations!',
        content: message,
        type: 'green',
        buttons: {
            close: function () {
            }
        }
    });
}

function loadTimeErrorAlert(message, time) {
    $.confirm({
        title: 'Encountered an error!',
        content: message,
        icon: 'fas fa-exclamation-triangle',
        type: 'red',
        autoClose: 'close|' + time,
        buttons: {
            omg: {
                text: 'Try again',
                btnClass: 'btn-red',
            },
            close: function () {
            }
        }
    });
}

function loadErrorAlert(message) {
    $.confirm({
        title: 'Encountered an error!',
        content: message,
        icon: 'fas fa-exclamation-triangle',
        type: 'red',
        buttons: {
            omg: {
                text: 'Try again',
                btnClass: 'btn-red',
            },
            close: function () {
            }
        }
    });
}

function loadErrorCurrencyTypeAlert(message) {
    $.confirm({
        title: 'Encountered an error input price!',
        content: message,
        icon: 'fas fa-exclamation-triangle',
        type: 'red',
        buttons: {
            omg: {
                text: 'Try again',
                btnClass: 'btn-green',
            },
            changeCurrencyType: {
                text: 'Change currency type',
                btnClass: 'btn-green',
                action: function () {
                    $.get("")
                }
            }
        }
    });
}

function loadOrangeAlert(message, time) {
        $.confirm({
            title: message,
            content: 'Some content here.',
            type: 'orange',
            autoClose: 'close|' + time,
            buttons: {
                close: function () {
                }
            }
        });
}

function loadBlueAlert(message, time) {
        $.confirm({
            title: message,
            content: 'Some content here.',
            type: 'blue',
            autoClose: 'close|' + time,
            buttons: {
                close: function () {
                }
            }
        });
}

function loadPurpleAlert(message, time) {
        $.confirm({
            title: message,
            content: 'Some content here.',
            type: 'purple',
            autoClose: 'close|' + time,
            buttons: {
                close: function () {
                }
            }
        });
}

function loadDarkAlert(message, time) {
        $.confirm({
            title: message,
            content: 'Some content here.',
            type: 'dark',
            autoClose: 'close|' + time,
            buttons: {
                close: function () {
                }
            }
        });
}

function loadModernAlert(message){
        $.confirm({
            title: message,
            icon: 'far fa-smile',
            theme: 'modern',
            closeIcon: true,
            animation: 'scale',
            type: 'blue',
        });
}
function loadSupervanAlert (message){
    $.confirm({
        icon: 'far fa-smile',
        title: message,
        theme: 'supervan',
        closeIcon: true,
        animation: 'scale',
        type: 'orange',
    });
}
