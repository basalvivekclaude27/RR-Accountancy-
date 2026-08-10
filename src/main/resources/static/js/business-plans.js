(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var statusCanvas = document.getElementById('statusDistDonut');
    if (statusCanvas) {
        new Chart(statusCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['In Progress', 'Completed', 'Pending Review', 'On Hold', 'Not Started'],
                datasets: [{
                    data: [18, 12, 8, 6, 8],
                    backgroundColor: ['#22C55E', '#2E5BFF', '#F59E0B', '#8B5CF6', '#14B8A6'],
                    borderWidth: 3, borderColor: '#fff'
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false, cutout: '68%',
                plugins: { legend: { display: false }, tooltip: { callbacks: { label: function (i) { return i.label + ': ' + i.parsed; } } } }
            }
        });
    }

    var trendCanvas = document.getElementById('revenueTrendChart');
    if (trendCanvas) {
        var ctx = trendCanvas.getContext('2d');
        var gradient = ctx.createLinearGradient(0, 0, 0, 170);
        gradient.addColorStop(0, 'rgba(46, 91, 255, .12)');
        gradient.addColorStop(1, 'rgba(46, 91, 255, 0)');

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'],
                datasets: [{
                    data: [120000, 150000, 140000, 180000, 210000, 195000, 230000, 260000, 240000, 300000, 280000, 330000],
                    borderColor: '#2E5BFF', borderWidth: 2.5, fill: true, backgroundColor: gradient,
                    tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#2E5BFF', pointBorderWidth: 2
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false,
                plugins: {
                    legend: { display: false },
                    tooltip: { callbacks: { label: function (i) { return '£' + i.parsed.y.toLocaleString('en-GB'); } } }
                },
                scales: {
                    y: {
                        min: 0, max: 500000,
                        ticks: { stepSize: 100000, color: '#8494AC', font: { size: 11.5 }, callback: function (v) { return '£' + (v / 1000) + 'K'; } },
                        grid: { color: '#EEF2F8' }, border: { display: false }
                    },
                    x: { ticks: { color: '#8494AC', font: { size: 11.5 } }, grid: { display: false }, border: { display: false } }
                }
            }
        });
    }

    var typeCanvas = document.getElementById('typeDonut');
    if (typeCanvas) {
        new Chart(typeCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Growth Plan', 'Startup Plan', 'Expansion Plan', 'Operational Plan', 'Others'],
                datasets: [{
                    data: [18, 12, 8, 6, 8],
                    backgroundColor: ['#2E5BFF', '#14B8A6', '#F59E0B', '#F97316', '#8B5CF6'],
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
