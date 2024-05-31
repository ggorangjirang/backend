document.addEventListener('DOMContentLoaded', function () {
  let startDate = moment().startOf('day').format('YYYY-MM-DD HH:mm');
  let endDate = moment().endOf('day').format('YYYY-MM-DD HH:mm');

  document.getElementById(
      'dateRangeText').textContent = `${startDate} - ${endDate}`;
});

$(document).ready(function () {
  let startDate = moment().startOf('day').format('YYYY-MM-DD HH:mm');
  let endDate = moment().endOf('day').format('YYYY-MM-DD HH:mm');
  let timeUnit = '1시간';
  let apiTimeUnit = 'hourly';

  $('#showDatePickerBtn').daterangepicker({
    timePicker: true,
    timePicker24Hour: true,
    timePickerSeconds: false,
    timePickerIncrement: 1,
    opens: 'left',
    locale: {
      format: 'YYYY-MM-DD HH:mm'
    },
    startDate: startDate,
    endDate: endDate
  }, function (start, end, label) {
    startDate = start.format('YYYY-MM-DD HH:mm');
    endDate = end.format('YYYY-MM-DD HH:mm');
    $('#showDatePickerBtn').html(
        startDate + ' - ' + endDate
        + ' <i class="fa-solid fa-calendar-days"></i>');

    fetchSalesData();
  });

  var dropdownItems = document.querySelectorAll('.dropdown-item');
  var dropdownButton = document.getElementById('dropdownButton');

  dropdownItems.forEach(function (item) {
    item.addEventListener('click', function (event) {
      event.preventDefault();
      timeUnit = this.getAttribute('data-value');
      apiTimeUnit = this.getAttribute('api-data-value')
      dropdownButton.textContent = timeUnit;
    });
  });

  function fetchSalesData() {
    $.ajax({
      url: '/api/v1/sales',
      type: 'GET',
      data: {
        timeUnit: apiTimeUnit,
        start: startDate,
        end: endDate
      },
      success: function (response) {
        var timeLabels = response.dateTime;
        var saleData = response.totalSales;
        var refundData = response.totalRefunds;
        var totalOrders = response.totalOrders;
        if (refundData.length < saleData.length) {
          refundData = [...refundData, ...Array(saleData.length - refundData.length).fill(0)];
        }
        var netSaleData = saleData.map(
            (sales, index) => sales - refundData[index]);

        var interval = Math.floor(timeLabels.length / 10);

        if (interval < 1) {
          interval = 1;
        }

        var diff = moment(endDate).diff(moment(startDate), 'days');
        var previousStartDate = moment(startDate).subtract(diff, 'days').format(
            'YYYY-MM-DD HH:mm');
        var previousEndDate = moment(endDate).subtract(diff, 'days').format(
            'YYYY-MM-DD HH:mm');

        $.ajax({
          url: '/api/v1/sales',
          type: 'GET',
          data: {
            timeUnit: apiTimeUnit,
            start: previousStartDate,
            end: previousEndDate
          },
          success: function (prevResponse) {
            var previousTimeLabels = prevResponse.dateTime;
            var previousSaleData = prevResponse.totalSales;
            var previousRefundData = prevResponse.totalRefunds;
            var previousTotalOrders = prevResponse.totalOrders;
            if (previousRefundData.length < previousSaleData.length) {
              previousRefundData = [...previousRefundData, ...Array(previousSaleData.length - previousRefundData.length).fill(0)];
            }

            var previousNetSaleData = previousSaleData.map(
                (sales, index) => sales - previousRefundData[index]);

            var interval2 = Math.floor(previousTimeLabels.length / 10);
            if (interval2 < 1) {
              interval2 = 1;
            }

            Highcharts.setOptions({
              chart: {
                type: 'line',
                backgroundColor: {
                  stops: [
                    [0, 'rgb(255, 255, 255)'],
                  ]
                },
                borderWidth: 2,
              }
            });

            var chart1 = new Highcharts.Chart({
              chart: {
                renderTo: 'container',
                type: 'line'
              },
              title: {
                text: '순매출'
              },
              xAxis: [
                {
                  categories: timeLabels,
                  labels: {
                    step: interval
                  },
                  tickInterval: interval,
                  title: {
                    text: '선택한 기간',
                    style: {
                      color: Highcharts.getOptions().colors[0]
                    },
                  }
                },
                {
                  categories: previousTimeLabels,
                  labels: {
                    step: interval2
                  },
                  tickInterval: interval2,
                  title: {
                    text: '직전 기간',
                    style: {
                      color: Highcharts.getOptions().colors[1]
                    },
                    opposite: true
                  }
                },
              ],
              yAxis: [
                {
                  title: {
                    text: '순매출'
                  }
                },
              ],
              series: [
                {
                  name: '선택한 기간',
                  data: netSaleData,
                  xAxis: 0,
                  color: Highcharts.getOptions().colors[0]
                },
                {
                  name: '직전 기간',
                  data: previousNetSaleData,
                  xAxis: 1,
                  color: Highcharts.getOptions().colors[1]
                },
              ]
            });

            var chart2 = new Highcharts.Chart({
              chart: {
                renderTo: 'container2',
                type: 'line',
                color: Highcharts.getOptions().colors[2]
              },
              title: {
                text: '환불액'
              },
              xAxis: [
                {
                  categories: timeLabels,
                  labels: {
                    step: interval
                  },
                  tickInterval: interval,
                  title: {
                    text: '선택한 기간',
                    style: {
                      color: Highcharts.getOptions().colors[2]
                    },
                  }
                },
                {
                  categories: previousTimeLabels,
                  labels: {
                    step: interval2
                  },
                  tickInterval: interval2,
                  title: {
                    text: '직전 기간',
                    style: {
                      color: Highcharts.getOptions().colors[3]
                    },
                    opposite: true
                  }
                },
              ],
              yAxis: [
                {
                  title: {
                    text: '환불액'
                  }
                },
              ],
              series: [
                {
                  name: '선택한 기간',
                  data: refundData,
                  xAxis: 0,
                  color: Highcharts.getOptions().colors[2]
                },
                {
                  name: '직전 기간',
                  data: previousRefundData,
                  xAxis: 1,
                  color: Highcharts.getOptions().colors[3]
                },
              ]
            });

            var chart3 = new Highcharts.Chart({
              chart: {
                renderTo: 'container3',
                type: 'line',
                color: Highcharts.getOptions().colors[4]
              },
              title: {
                text: '주문량'
              },
              xAxis: [
                {
                  categories: timeLabels,
                  labels: {
                    step: interval
                  },
                  tickInterval: interval,
                  title: {
                    text: '선택한 기간',
                    style: {
                      color: Highcharts.getOptions().colors[4]
                    },
                  }
                },
                {
                  categories: previousTimeLabels,
                  labels: {
                    step: interval2
                  },
                  tickInterval: interval2,
                  title: {
                    text: '직전 기간',
                    style: {
                      color: Highcharts.getOptions().colors[5]
                    },
                    opposite: true
                  }
                },
              ],
              yAxis: [
                {
                  title: {
                    text: '주문 횟수'
                  }
                },
              ],
              series: [
                {
                  name: '선택한 기간',
                  data: totalOrders,
                  xAxis: 0,
                  color: Highcharts.getOptions().colors[4]
                },
                {
                  name: '직전 기간',
                  data: previousTotalOrders,
                  xAxis: 1,
                  color: Highcharts.getOptions().colors[5]
                },
              ]
            });
          },
          error: function (prevError) {
            console.error('이전 기간 API 호출 에러:', prevError);
          }
        });
      },
      error: function (error) {
        console.error('API 호출 에러:', error);
      }
    });
  }
});
