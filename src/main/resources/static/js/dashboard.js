(function () {
    'use strict';

    // ---- User menu dropdown ----
    var toggle = document.getElementById('userMenuToggle');
    var dropdown = document.getElementById('userMenuDropdown');
    if (toggle && dropdown) {
        toggle.addEventListener('click', function (e) {
            e.stopPropagation();
            var open = dropdown.classList.toggle('open');
            toggle.setAttribute('aria-expanded', open ? 'true' : 'false');
        });
        document.addEventListener('click', function () {
            dropdown.classList.remove('open');
            toggle.setAttribute('aria-expanded', 'false');
        });
    }

    // ---- Mobile sidebar drawer (hamburger, module pages) ----
    var sidebarToggle = document.getElementById('sidebarToggle');
    var sidebarEl = document.querySelector('.sidebar');
    if (sidebarToggle && sidebarEl) {
        sidebarToggle.addEventListener('click', function (e) {
            e.stopPropagation();
            sidebarEl.classList.toggle('mobile-open');
        });
        document.addEventListener('click', function (e) {
            if (sidebarEl.classList.contains('mobile-open') && !sidebarEl.contains(e.target)) {
                sidebarEl.classList.remove('mobile-open');
            }
        });
    }

    if (typeof Chart === 'undefined') return;

    // ---- Sparklines (KPI cards) ----
    var sparkData = {
        clients: { values: [96, 101, 99, 108, 112, 118, 121, 128], color: '#3B82F6' },
        revenue: { values: [28000, 31000, 29500, 34000, 38000, 36500, 41000, 45870], color: '#16A34A' },
        receivables: { values: [9800, 11200, 10500, 13800, 12000, 14500, 11800, 12430], color: '#F59E0B' },
        tax: { values: [6200, 7100, 6800, 9200, 8000, 9500, 8300, 8920], color: '#8B5CF6' }
    };

    document.querySelectorAll('canvas[data-spark]').forEach(function (canvas) {
        var cfg = sparkData[canvas.getAttribute('data-spark')];
        if (!cfg) return;
        var ctx = canvas.getContext('2d');
        var gradient = ctx.createLinearGradient(0, 0, 0, canvas.height || 56);
        gradient.addColorStop(0, cfg.color + '33');
        gradient.addColorStop(1, cfg.color + '00');

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: cfg.values.map(function (_, i) { return i; }),
                datasets: [{
                    data: cfg.values,
                    borderColor: cfg.color,
                    borderWidth: 2,
                    fill: true,
                    backgroundColor: gradient,
                    tension: 0.4,
                    pointRadius: 0
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                animation: false,
                plugins: { legend: { display: false }, tooltip: { enabled: false } },
                scales: {
                    x: { display: false },
                    y: { display: false }
                },
                elements: { line: { borderJoinStyle: 'round' } }
            }
        });
    });

    // ---- Revenue Overview (area line chart) ----
    var revenueCanvas = document.getElementById('revenueChart');
    if (revenueCanvas) {
        var rctx = revenueCanvas.getContext('2d');
        var rGradient = rctx.createLinearGradient(0, 0, 0, 240);
        rGradient.addColorStop(0, 'rgba(46, 91, 255, .18)');
        rGradient.addColorStop(1, 'rgba(46, 91, 255, 0)');

        new Chart(rctx, {
            type: 'line',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug'],
                datasets: [{
                    label: 'Revenue',
                    data: [8000, 15200, 24100, 21400, 30300, 34200, 27600, 45870],
                    borderColor: '#2E5BFF',
                    borderWidth: 2.5,
                    fill: true,
                    backgroundColor: rGradient,
                    tension: 0.4,
                    pointRadius: 5,
                    pointBackgroundColor: '#fff',
                    pointBorderColor: '#2E5BFF',
                    pointBorderWidth: 2,
                    pointHoverRadius: 6
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
                        min: 0,
                        max: 60000,
                        ticks: {
                            stepSize: 15000,
                            color: '#8494AC',
                            font: { size: 11.5 },
                            callback: function (v) { return '£' + (v / 1000) + 'K'; }
                        },
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

    // ---- Income vs Expenses (donut) ----
    var donutCanvas = document.getElementById('incomeExpenseChart');
    if (donutCanvas) {
        new Chart(donutCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Income', 'Expenses'],
                datasets: [{
                    data: [45870, 27120],
                    backgroundColor: ['#4CAF7D', '#E2564F'],
                    borderWidth: 0
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                cutout: '72%',
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
