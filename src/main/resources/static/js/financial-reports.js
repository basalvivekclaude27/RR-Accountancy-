(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var trendCanvas = document.getElementById('plTrendChart');
    if (trendCanvas) {
        var ctx = trendCanvas.getContext('2d');
        var revGradient = ctx.createLinearGradient(0, 0, 0, 210);
        revGradient.addColorStop(0, 'rgba(46, 91, 255, .15)');
        revGradient.addColorStop(1, 'rgba(46, 91, 255, 0)');
        var profitGradient = ctx.createLinearGradient(0, 0, 0, 210);
        profitGradient.addColorStop(0, 'rgba(22, 163, 74, .15)');
        profitGradient.addColorStop(1, 'rgba(22, 163, 74, 0)');

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug'],
                datasets: [
                    {
                        label: 'Revenue',
                        data: [42000, 38500, 48000, 52500, 47500, 58000, 96450].map(function (v) { return Math.min(v, 60000); }),
                        borderColor: '#2E5BFF', borderWidth: 2.5, fill: true, backgroundColor: revGradient,
                        tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#2E5BFF', pointBorderWidth: 2
                    },
                    {
                        label: 'Net Profit',
                        data: [15000, 13500, 18500, 21000, 17500, 22500, 25000].map(function (v) { return Math.min(v, 60000); }),
                        borderColor: '#16A34A', borderWidth: 2.5, fill: true, backgroundColor: profitGradient,
                        tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#16A34A', pointBorderWidth: 2
                    }
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { display: false },
                    tooltip: {
                        callbacks: {
                            label: function (item) { return item.dataset.label + ': £' + item.parsed.y.toLocaleString('en-GB'); }
                        }
                    }
                },
                scales: {
                    y: {
                        min: 0, max: 60000,
                        ticks: { stepSize: 15000, color: '#8494AC', font: { size: 11.5 }, callback: function (v) { return '£' + (v / 1000) + 'K'; } },
                        grid: { color: '#EEF2F8' },
                        border: { display: false }
                    },
                    x: {
                        ticks: { color: '#8494AC', font: { size: 11.5 } },
                        grid: { display: false },
                        border: { display: false }
                    }
                }
            }
        });
    }

    var expenseCanvas = document.getElementById('expenseBreakdownDonut');
    if (expenseCanvas) {
        new Chart(expenseCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Staff Costs', 'Rent & Rates', 'Office Expenses', 'Professional Fees', 'Other Expenses'],
                datasets: [{
                    data: [9250, 4850, 3780, 2950, 1510],
                    backgroundColor: ['#8B5CF6', '#22C55E', '#F59E0B', '#2E5BFF', '#E2564F'],
                    borderWidth: 3,
                    borderColor: '#fff'
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                cutout: '68%',
                plugins: {
                    legend: { display: false },
                    tooltip: {
                        callbacks: {
                            label: function (item) { return item.label + ': £' + item.parsed.toLocaleString('en-GB'); }
                        }
                    }
                }
            }
        });
    }
})();
