(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var summaryCanvas = document.getElementById('ihtSummaryDonut');
    if (summaryCanvas) {
        new Chart(summaryCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Est. Tax Liability', 'Tax Saved', 'Planning in Progress', 'No Liability / Nil Tax'],
                datasets: [{
                    data: [1056480, 312750, 214600, 435320],
                    backgroundColor: ['#E2564F', '#22C55E', '#F59E0B', '#8B5CF6'],
                    borderWidth: 3, borderColor: '#fff'
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false, cutout: '68%',
                plugins: { legend: { display: false }, tooltip: { callbacks: { label: function (i) { return i.label + ': £' + i.parsed.toLocaleString('en-GB'); } } } }
            }
        });
    }

    var trendCanvas = document.getElementById('ihtTrendChart');
    if (trendCanvas) {
        var ctx = trendCanvas.getContext('2d');
        var gradient = ctx.createLinearGradient(0, 0, 0, 170);
        gradient.addColorStop(0, 'rgba(139, 92, 246, .12)');
        gradient.addColorStop(1, 'rgba(139, 92, 246, 0)');

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'],
                datasets: [{
                    data: [420000, 480000, 460000, 560000, 620000, 590000, 680000, 740000, 700000, 830000, 920000, 1056480],
                    borderColor: '#8B5CF6', borderWidth: 2.5, fill: true, backgroundColor: gradient,
                    tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#8B5CF6', pointBorderWidth: 2
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
                        min: 0, max: 1200000,
                        ticks: {
                            stepSize: 300000, color: '#8494AC', font: { size: 11.5 },
                            callback: function (v) { return v >= 1000000 ? '£' + (v / 1000000) + 'M' : '£' + (v / 1000) + 'K'; }
                        },
                        grid: { color: '#EEF2F8' }, border: { display: false }
                    },
                    x: { ticks: { color: '#8494AC', font: { size: 11.5 } }, grid: { display: false }, border: { display: false } }
                }
            }
        });
    }

    var estateCanvas = document.getElementById('ihtEstateDonut');
    if (estateCanvas) {
        new Chart(estateCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['£0 - £1M', '£1M - £2M', '£2M - £3M', '£3M+'],
                datasets: [{
                    data: [10, 12, 8, 8],
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
