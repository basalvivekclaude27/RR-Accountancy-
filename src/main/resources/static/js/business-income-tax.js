(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var summaryCanvas = document.getElementById('taxSummaryDonut');
    if (summaryCanvas) {
        new Chart(summaryCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Tax Paid', 'In Progress', 'Pending', 'In Review'],
                datasets: [{
                    data: [118450, 34600, 24780, 14850],
                    backgroundColor: ['#22C55E', '#2E5BFF', '#F59E0B', '#8B5CF6'],
                    borderWidth: 3, borderColor: '#fff'
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false, cutout: '68%',
                plugins: { legend: { display: false }, tooltip: { callbacks: { label: function (i) { return i.label + ': £' + i.parsed.toLocaleString('en-GB'); } } } }
            }
        });
    }

    var trendCanvas = document.getElementById('taxTrendChart');
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
                    data: [9000, 12000, 10500, 15000, 18000, 16500, 20000, 24000, 22000, 28000, 32000, 36000],
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
                        min: 0, max: 40000,
                        ticks: { stepSize: 10000, color: '#8494AC', font: { size: 11.5 }, callback: function (v) { return '£' + (v / 1000) + 'K'; } },
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
                labels: ['Sole Trader', 'Limited Company', 'Partnership', 'LLP', 'Others'],
                datasets: [{
                    data: [42, 28, 16, 6, 4],
                    backgroundColor: ['#22C55E', '#2E5BFF', '#F59E0B', '#8B5CF6', '#A78BFA'],
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
