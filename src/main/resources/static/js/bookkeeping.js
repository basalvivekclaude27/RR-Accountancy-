(function () {
    'use strict';
    if (typeof Chart === 'undefined') return;

    var canvas = document.getElementById('expenseDonut');
    if (!canvas) return;

    new Chart(canvas.getContext('2d'), {
        type: 'doughnut',
        data: {
            labels: ['Staff Costs', 'Rent & Rates', 'Office Expenses', 'Other Expenses'],
            datasets: [{
                data: [3250, 2100, 1450, 1650],
                backgroundColor: ['#8B5CF6', '#22C55E', '#F59E0B', '#3B82F6'],
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
})();
