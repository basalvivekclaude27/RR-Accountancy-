(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var collectionCanvas = document.getElementById('collectionDonut');
    if (collectionCanvas) {
        new Chart(collectionCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Collected', 'Outstanding'],
                datasets: [{
                    data: [81.5, 18.5],
                    backgroundColor: ['#22C55E', '#F59E0B'],
                    borderWidth: 2,
                    borderColor: '#fff'
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false, cutout: '72%',
                plugins: { legend: { display: false }, tooltip: { callbacks: { label: function (i) { return i.label + ': ' + i.parsed + '%'; } } } }
            }
        });
    }

    var trendCanvas = document.getElementById('serviceChargeTrendChart');
    if (trendCanvas) {
        var ctx = trendCanvas.getContext('2d');
        var budgetGradient = ctx.createLinearGradient(0, 0, 0, 210);
        budgetGradient.addColorStop(0, 'rgba(46, 91, 255, .15)');
        budgetGradient.addColorStop(1, 'rgba(46, 91, 255, 0)');
        var collectedGradient = ctx.createLinearGradient(0, 0, 0, 210);
        collectedGradient.addColorStop(0, 'rgba(22, 163, 74, .15)');
        collectedGradient.addColorStop(1, 'rgba(22, 163, 74, 0)');

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'],
                datasets: [
                    {
                        label: 'Budgeted',
                        data: [28000, 34000, 40000, 48000, 55000, 60000, 66000, 72000, 76000, 80000, 84000, 90000],
                        borderColor: '#2E5BFF', borderWidth: 2.5, fill: true, backgroundColor: budgetGradient,
                        tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#2E5BFF', pointBorderWidth: 2
                    },
                    {
                        label: 'Collected',
                        data: [20000, 24000, 30000, 36000, 42000, 46000, 50000, 55000, 58000, 62000, 68000, 82650],
                        borderColor: '#16A34A', borderWidth: 2.5, fill: true, backgroundColor: collectedGradient,
                        tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#16A34A', pointBorderWidth: 2
                    }
                ]
            },
            options: {
                responsive: true, maintainAspectRatio: false,
                plugins: {
                    legend: { display: false },
                    tooltip: { callbacks: { label: function (i) { return i.dataset.label + ': £' + i.parsed.y.toLocaleString('en-GB'); } } }
                },
                scales: {
                    y: {
                        min: 0, max: 100000,
                        ticks: { stepSize: 20000, color: '#8494AC', font: { size: 11.5 }, callback: function (v) { return '£' + (v / 1000) + 'K'; } },
                        grid: { color: '#EEF2F8' }, border: { display: false }
                    },
                    x: { ticks: { color: '#8494AC', font: { size: 11.5 } }, grid: { display: false }, border: { display: false } }
                }
            }
        });
    }

    var arrearsCanvas = document.getElementById('arrearsDonut');
    if (arrearsCanvas) {
        new Chart(arrearsCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Over 90 Days', '61 - 90 Days', '31 - 60 Days', 'Up to 30 Days'],
                datasets: [{
                    data: [5120, 4380, 3270, 2560],
                    backgroundColor: ['#E2564F', '#F59E0B', '#FACC15', '#22C55E'],
                    borderWidth: 3, borderColor: '#fff'
                }]
            },
            options: {
                responsive: true, maintainAspectRatio: false, cutout: '68%',
                plugins: { legend: { display: false }, tooltip: { callbacks: { label: function (i) { return i.label + ': £' + i.parsed.toLocaleString('en-GB'); } } } }
            }
        });
    }

    var expenseCanvas = document.getElementById('svcExpenseDonut');
    if (expenseCanvas) {
        new Chart(expenseCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Repairs & Maintenance', 'Cleaning', 'Insurance', 'Management Fees', 'Other Expenses'],
                datasets: [{
                    data: [5420, 3250, 2180, 2950, 2640],
                    backgroundColor: ['#2E5BFF', '#22C55E', '#F97316', '#8B5CF6', '#14B8A6'],
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
