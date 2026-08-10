(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var overviewCanvas = document.getElementById('formationOverviewDonut');
    if (overviewCanvas) {
        new Chart(overviewCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Incorporation', 'Name Reservation', 'Documents', 'Review', 'Completed'],
                datasets: [{
                    data: [58, 28, 22, 18, 20],
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

    var trendCanvas = document.getElementById('formationTrendChart');
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
                    data: [8, 10, 9, 12, 14, 13, 15, 17, 20, 22, 28, 34],
                    borderColor: '#2E5BFF', borderWidth: 2.5, fill: true, backgroundColor: gradient,
                    tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#2E5BFF', pointBorderWidth: 2
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false,
                plugins: {
                    legend: { display: false },
                    tooltip: { callbacks: { label: function (i) { return i.parsed.y; } } }
                },
                scales: {
                    y: {
                        min: 0, max: 40,
                        ticks: { stepSize: 10, color: '#8494AC', font: { size: 11.5 } },
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
                labels: ['Private Limited', 'Limited by Guarantee', 'LLP', 'Public Limited', 'Others'],
                datasets: [{
                    data: [104, 18, 12, 8, 4],
                    backgroundColor: ['#2E5BFF', '#22C55E', '#F59E0B', '#8B5CF6', '#14B8A6'],
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
