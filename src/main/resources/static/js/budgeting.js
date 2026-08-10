(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    // ---- Budget vs Actual donut ----
    var bvaCanvas = document.getElementById('budgetVsActualDonut');
    if (bvaCanvas) {
        new Chart(bvaCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Actual', 'Budgeted'],
                datasets: [{
                    data: [68, 32],
                    backgroundColor: ['#2E5BFF', '#22C55E'],
                    borderWidth: 2,
                    borderColor: '#fff'
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                cutout: '72%',
                plugins: {
                    legend: { display: false },
                    tooltip: { callbacks: { label: function (item) { return item.label + ': ' + item.parsed + '%'; } } }
                }
            }
        });
    }

    // ---- Budget vs Actual Trend (dual line, Apr-Mar) ----
    var trendCanvas = document.getElementById('budgetTrendChart');
    if (trendCanvas) {
        var ctx = trendCanvas.getContext('2d');
        var budgetGradient = ctx.createLinearGradient(0, 0, 0, 210);
        budgetGradient.addColorStop(0, 'rgba(46, 91, 255, .15)');
        budgetGradient.addColorStop(1, 'rgba(46, 91, 255, 0)');
        var actualGradient = ctx.createLinearGradient(0, 0, 0, 210);
        actualGradient.addColorStop(0, 'rgba(22, 163, 74, .15)');
        actualGradient.addColorStop(1, 'rgba(22, 163, 74, 0)');

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'],
                datasets: [
                    {
                        label: 'Budgeted',
                        data: [60000, 78000, 95000, 105000, 118000, 130000, 140000, 148000, 155000, 160000, 165000, 172000],
                        borderColor: '#2E5BFF', borderWidth: 2.5, fill: true, backgroundColor: budgetGradient,
                        tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#2E5BFF', pointBorderWidth: 2
                    },
                    {
                        label: 'Actual',
                        data: [45000, 58000, 70000, 80000, 88000, 92000, 100000, 108000, 112000, 118000, 125000, 132000],
                        borderColor: '#16A34A', borderWidth: 2.5, fill: true, backgroundColor: actualGradient,
                        tension: 0.4, pointRadius: 4, pointBackgroundColor: '#fff', pointBorderColor: '#16A34A', pointBorderWidth: 2
                    }
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { display: false },
                    tooltip: { callbacks: { label: function (item) { return item.dataset.label + ': £' + item.parsed.y.toLocaleString('en-GB'); } } }
                },
                scales: {
                    y: {
                        min: 0, max: 200000,
                        ticks: { stepSize: 50000, color: '#8494AC', font: { size: 11.5 }, callback: function (v) { return '£' + (v / 1000) + 'K'; } },
                        grid: { color: '#EEF2F8' },
                        border: { display: false }
                    },
                    x: { ticks: { color: '#8494AC', font: { size: 11.5 } }, grid: { display: false }, border: { display: false } }
                }
            }
        });
    }

    // ---- Budget by Category donut ----
    var categoryCanvas = document.getElementById('categoryDonut');
    if (categoryCanvas) {
        new Chart(categoryCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Staff Costs', 'Operations', 'Marketing', 'Professional Fees', 'Other Expenses'],
                datasets: [{
                    data: [420000, 310000, 160000, 140000, 224800],
                    backgroundColor: ['#2E5BFF', '#14B8A6', '#F97316', '#8B5CF6', '#F59E0B'],
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
                    tooltip: { callbacks: { label: function (item) { return item.label + ': £' + item.parsed.toLocaleString('en-GB'); } } }
                }
            }
        });
    }

    // ---- Forecast Accuracy semicircular gauge ----
    var gaugeCanvas = document.getElementById('accuracyGauge');
    if (gaugeCanvas) {
        var gaugeValue = 86;

        var needlePlugin = {
            id: 'gaugeNeedle',
            afterDatasetsDraw: function (chart) {
                var meta = chart.getDatasetMeta(0);
                var arc = meta.data[0];
                if (!arc) return;
                var cx = arc.x, cy = arc.y;
                var outerRadius = arc.outerRadius;

                var chartCtx = chart.ctx;
                var angle = Math.PI + (gaugeValue / 100) * Math.PI;
                var needleLength = outerRadius * 0.78;

                chartCtx.save();
                chartCtx.translate(cx, cy);
                chartCtx.rotate(angle);
                chartCtx.beginPath();
                chartCtx.moveTo(-8, 0);
                chartCtx.lineTo(0, -3);
                chartCtx.lineTo(needleLength, 0);
                chartCtx.lineTo(0, 3);
                chartCtx.closePath();
                chartCtx.fillStyle = '#1B3358';
                chartCtx.fill();
                chartCtx.restore();

                chartCtx.beginPath();
                chartCtx.arc(cx, cy, 6, 0, Math.PI * 2);
                chartCtx.fillStyle = '#1B3358';
                chartCtx.fill();

                // Center value text, just above hub
                chartCtx.save();
                chartCtx.font = '700 24px Inter, sans-serif';
                chartCtx.fillStyle = '#0F2544';
                chartCtx.textAlign = 'center';
                chartCtx.fillText(gaugeValue + '%', cx, cy - 14);
                chartCtx.restore();
            }
        };

        new Chart(gaugeCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['0-25', '25-50', '50-75', '75-100'],
                datasets: [{
                    data: [25, 25, 25, 25],
                    backgroundColor: ['#E2564F', '#F59E0B', '#A3C93A', '#22C55E'],
                    borderWidth: 0
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                rotation: -90,
                circumference: 180,
                cutout: '72%',
                layout: { padding: { bottom: 10 } },
                plugins: { legend: { display: false }, tooltip: { enabled: false } }
            },
            plugins: [needlePlugin]
        });
    }
})();
