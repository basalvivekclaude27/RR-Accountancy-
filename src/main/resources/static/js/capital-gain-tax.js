(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var summaryCanvas = document.getElementById('cgtSummaryDonut');
    if (summaryCanvas) {
        new Chart(summaryCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Residential Property', 'Shares', 'Crypto Assets', 'Other Assets'],
                datasets: [{
                    data: [117450, 68660, 32140, 30500],
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

    var trendCanvas = document.getElementById('cgtTrendChart');
    if (trendCanvas) {
        var ctx = trendCanvas.getContext('2d');
        var gradient = ctx.createLinearGradient(0, 0, 0, 170);
        gradient.addColorStop(0, 'rgba(34, 197, 94, .12)');
        gradient.addColorStop(1, 'rgba(34, 197, 94, 0)');

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'],
                datasets: [{
                    label: 'Total Gain / (Loss)',
                    data: [45000, 120000, -20000, 80000, 150000, 60000, 90000, 180000, 40000, 220000, 260000, 248750],
                    borderColor: '#16A34A', borderWidth: 2.5, fill: true, backgroundColor: gradient,
                    tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#16A34A', pointBorderWidth: 2
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false,
                plugins: {
                    legend: { display: true, position: 'bottom', labels: { boxWidth: 10, color: '#4A5B75', font: { size: 12 } } },
                    tooltip: { callbacks: { label: function (i) { return '£' + i.parsed.y.toLocaleString('en-GB'); } } }
                },
                scales: {
                    y: {
                        min: -100000, max: 400000,
                        ticks: {
                            stepSize: 100000, color: '#8494AC', font: { size: 11.5 },
                            callback: function (v) { return (v < 0 ? '-' : '') + '£' + (Math.abs(v) / 1000) + 'K'; }
                        },
                        grid: { color: function (ctx) { return ctx.tick.value === 0 ? '#C7D2E0' : '#EEF2F8'; } }, border: { display: false }
                    },
                    x: { ticks: { color: '#8494AC', font: { size: 11.5 } }, grid: { display: false }, border: { display: false } }
                }
            }
        });
    }

    var assetCanvas = document.getElementById('cgtAssetDonut');
    if (assetCanvas) {
        new Chart(assetCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Residential Property', 'Shares', 'Crypto Assets', 'Other Assets'],
                datasets: [{
                    data: [589450, 345600, 160890, 152620],
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
})();
