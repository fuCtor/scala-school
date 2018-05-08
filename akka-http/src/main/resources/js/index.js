$(function () {
    const chart = new Chart(document.getElementById("chart").getContext('2d'), {
        type: 'line',
        data: {
            labels: [],
            datasets: []
        },
        options: {
            elements: {
                line: {
                    tension: 0, // disables bezier curves
                }
            },
            responsive: true
        }
    });

    const colors = [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)'
    ]

    const picker = $('#stock-picker')
    picker.on('select2:select', function (e) {
        loadStockInfo(currentStock().id)
    });

    $("#add-stock").on("click", addCurrentStock);

    function currentStock() {
        return picker.select2("data")[0];
    }

    function addCurrentStock() {
        let id = currentStock().id
        $.ajax("api/stock?id=" + id, {
            method: "PUT",
            success: updateStocks
        })
    }

    function updateStocks() {
        $.get("api/stock/info", updateAllStocksInfo, "json")
    }

    function updateAllStocksInfo(data) {
        let labels = (data[0] || []).prices.map(price => price.label)
        let datasets = data.map(function (stock, i) {
            let data = stock.prices.map(price => price.close)
            return {
                label: stock.name,
                data: data,
                borderColor: colors[i] || Chart.defaults.global.defaultColor,
                backgroundColor: 'rgba(0,0,0,0)'
            }
        })
        chart.data.labels = labels;
        chart.data.datasets = datasets;
        chart.update();

        $("#stocks .list-group-item").remove()
        if(data.length == 0) {
            $("#stocks").html('<li class="list-group-item d-flex justify-content-between align-items-center">Empty</li>')
        } else {
            let stocks = $("#stocks")
            data.forEach(stock => {
                let elem = $("<li>", {"class": "list-group-item d-flex justify-content-between align-items-center", text: stock.name});
                let badge = $("<span>", {"class": "badge badge-primary badge-pill", text: " - "})
                badge.on("click", function() {
                    $.ajax("api/stock?id=" + stock.id, {
                        method: "DELETE",
                        success: updateStocks
                    })
                })
                elem.append(badge);
                stocks.append(elem);
            });
        }
    }

    function updateInfo(data) {
        $("#stock-name").html(data.name);
        $("#stock-info").html(data.description);
    }

    function loadStockInfo(id) {
        $.get("api/stock", { id: id }, updateInfo, "json");
    }

    function updatePicker(data) {
        picker.select2({
            data: data.map(function (stock) {
                return {
                    id: stock.id,
                    text: stock.name
                }
            })
        });
        loadStockInfo(data[0].id);
    }

    $.get("api/stocks", updatePicker, "json")
})