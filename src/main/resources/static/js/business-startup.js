(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var overviewCanvas = document.getElementById('startupOverviewDonut');
    if (overviewCanvas) {
        new Chart(overviewCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Business Plan', 'Company Formation', 'Registration', 'Document Setup', 'Launched'],
                datasets: [{
                    data: [24, 18, 12, 10, 18],
                    backgroundColor: ['#2E5BFF', '#14B8A6', '#F59E0B', '#8B5CF6', '#22C55E'],
                    borderWidth: 3, borderColor: '#fff'
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false, cutout: '68%',
                plugins: { legend: { display: false }, tooltip: { callbacks: { label: function (i) { return i.label + ': ' + i.parsed; } } } }
            }
        });
    }

    var sourceCanvas = document.getElementById('sourceDonut');
    if (sourceCanvas) {
        new Chart(sourceCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Website', 'Referral', 'Advertisement', 'Social Media'],
                datasets: [{
                    data: [28, 14, 8, 6],
                    backgroundColor: ['#2E5BFF', '#22C55E', '#F59E0B', '#8B5CF6'],
                    borderWidth: 3, borderColor: '#fff'
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false, cutout: '68%',
                plugins: { legend: { display: false }, tooltip: { callbacks: { label: function (i) { return i.label + ': ' + i.parsed; } } } }
            }
        });
    }
})();
