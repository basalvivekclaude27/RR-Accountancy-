(function () {
    'use strict';

    // Segmented toggle: highlights the chosen side and scrolls its card into view.
    var toggleAdmin = document.querySelector('.toggle-admin');
    var toggleClient = document.querySelector('.toggle-client');
    var adminCard = document.getElementById('admin-card');
    var clientCard = document.getElementById('client-card');

    function activate(which) {
        var isAdmin = which === 'admin';
        if (toggleAdmin) toggleAdmin.classList.toggle('is-inactive', !isAdmin);
        if (toggleClient) toggleClient.classList.toggle('is-inactive', isAdmin);
        var target = isAdmin ? adminCard : clientCard;
        if (target) target.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }

    if (toggleAdmin) toggleAdmin.addEventListener('click', function () { activate('admin'); });
    if (toggleClient) toggleClient.addEventListener('click', function () { activate('client'); });

    // Password show/hide eye toggle
    document.querySelectorAll('.toggle-eye').forEach(function (btn) {
        btn.addEventListener('click', function () {
            var input = document.getElementById(btn.getAttribute('data-target'));
            if (!input) return;
            var showing = input.type === 'text';
            input.type = showing ? 'password' : 'text';
            btn.classList.toggle('bi-eye', showing);
            btn.classList.toggle('bi-eye-slash', !showing);
        });
    });
})();
