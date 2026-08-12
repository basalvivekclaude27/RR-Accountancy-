(function () {
    'use strict';

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

    // Login page: sync the left theme panel to whichever role tab is active.
    var themePanel = document.querySelector('.theme-panel');
    if (themePanel) {
        document.querySelectorAll('[data-bs-toggle="pill"]').forEach(function (btn) {
            btn.addEventListener('shown.bs.tab', function (e) {
                var role = e.target.id === 'admin-tab' ? 'admin' : 'client';
                themePanel.setAttribute('data-role', role);
            });
        });
    }
})();
