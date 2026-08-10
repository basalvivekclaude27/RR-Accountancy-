(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var summaryCanvas = document.getElementById('payrollSummaryDonut');
    if (summaryCanvas) {
        new Chart(summaryCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Net Pay', 'Employer NI', 'Pension Contributions', 'Tax (PAYE)', 'Other Deductions'],
                datasets: [{
                    data: [28540, 5420, 4380, 6150, 3210],
                    backgroundColor: ['#2E5BFF', '#22C55E', '#F59E0B', '#8B5CF6', '#E2564F'],
                    borderWidth: 2,
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

    var trendCanvas = document.getElementById('payrollTrendChart');
    if (trendCanvas) {
        var ctx = trendCanvas.getContext('2d');
        var gradient = ctx.createLinearGradient(0, 0, 0, 220);
        gradient.addColorStop(0, 'rgba(46, 91, 255, .15)');
        gradient.addColorStop(1, 'rgba(46, 91, 255, 0)');

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                datasets: [{
                    data: [21000, 19500, 26000, 30500, 27500, 38500],
                    borderColor: '#2E5BFF',
                    borderWidth: 2.5,
                    fill: true,
                    backgroundColor: gradient,
                    tension: 0.4,
                    pointRadius: 5,
                    pointBackgroundColor: '#fff',
                    pointBorderColor: '#2E5BFF',
                    pointBorderWidth: 2
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { display: false },
                    tooltip: {
                        callbacks: {
                            label: function (item) { return '£' + item.parsed.y.toLocaleString('en-GB'); }
                        }
                    }
                },
                scales: {
                    y: {
                        min: 0, max: 50000,
                        ticks: { stepSize: 10000, color: '#8494AC', font: { size: 11.5 }, callback: function (v) { return '£' + (v / 1000) + 'K'; } },
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

    var distCanvas = document.getElementById('employeeDistDonut');
    if (distCanvas) {
        new Chart(distCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Full Time', 'Part Time', 'Contractor', 'Director'],
                datasets: [{
                    data: [34, 12, 8, 4],
                    backgroundColor: ['#2E5BFF', '#22C55E', '#F59E0B', '#8B5CF6'],
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
                            label: function (item) { return item.label + ': ' + item.parsed; }
                        }
                    }
                }
            }
        });
    }
})();
