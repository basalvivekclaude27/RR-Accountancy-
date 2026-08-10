(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var overviewCanvas = document.getElementById('overviewPie');
    if (overviewCanvas) {
        new Chart(overviewCanvas.getContext('2d'), {
            type: 'pie',
            data: {
                labels: ['Completed', 'In Progress', 'Pending'],
                datasets: [{
                    data: [67, 42, 23],
                    backgroundColor: ['#22C55E', '#2E5BFF', '#F59E0B'],
                    borderWidth: 2,
                    borderColor: '#fff'
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
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

    var progressCanvas = document.getElementById('progressDonut');
    if (progressCanvas) {
        new Chart(progressCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Completed', 'In Progress', 'Draft', 'Pending'],
                datasets: [{
                    data: [67, 42, 10, 3],
                    backgroundColor: ['#22C55E', '#2E5BFF', '#8B5CF6', '#F59E0B'],
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
