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
})();
