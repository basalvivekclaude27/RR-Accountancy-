(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var summaryData = { data: [285450, 98350, 34600], labels: ['Total Refunds', 'Tax Payable', 'In Progress'], colors: ['#22C55E', '#2E5BFF', '#F59E0B'] };

    var summaryCanvas = document.getElementById('taxSummaryDonut');
    if (summaryCanvas) {
        new Chart(summaryCanvas.getContext('2d'), {
            type: 'doughnut',
            data: { labels: summaryData.labels, datasets: [{ data: summaryData.data, backgroundColor: summaryData.colors, borderWidth: 3, borderColor: '#fff' }] },
            options: {
                responsive: true, maintainAspectRatio: false, cutout: '68%',
                plugins: { legend: { display: false }, tooltip: { callbacks: { label: function (i) { return i.label + ': £' + i.parsed.toLocaleString('en-GB'); } } } }
            }
        });
    }

    var refundPayableCanvas = document.getElementById('refundPayableDonut');
    if (refundPayableCanvas) {
        new Chart(refundPayableCanvas.getContext('2d'), {
            type: 'doughnut',
            data: { labels: summaryData.labels, datasets: [{ data: summaryData.data, backgroundColor: summaryData.colors, borderWidth: 3, borderColor: '#fff' }] },
            options: {
                responsive: true, maintainAspectRatio: false, cutout: '68%',
                plugins: { legend: { display: false }, tooltip: { callbacks: { label: function (i) { return i.label + ': £' + i.parsed.toLocaleString('en-GB'); } } } }
            }
        });
    }

    var trendCanvas = document.getElementById('refundTrendChart');
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
                    data: [95000, 130000, 110000, 150000, 175000, 160000, 190000, 220000, 205000, 250000, 265000, 285450],
                    borderColor: '#16A34A', borderWidth: 2.5, fill: true, backgroundColor: gradient,
                    tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#16A34A', pointBorderWidth: 2
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
                        min: 0, max: 400000,
                        ticks: { stepSize: 100000, color: '#8494AC', font: { size: 11.5 }, callback: function (v) { return '£' + (v / 1000) + 'K'; } },
                        grid: { color: '#EEF2F8' }, border: { display: false }
                    },
                    x: { ticks: { color: '#8494AC', font: { size: 11.5 } }, grid: { display: false }, border: { display: false } }
                }
            }
        });
    }
})();
