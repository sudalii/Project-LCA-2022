// Set new default font family and font color to mimic Bootstrap's default styling

var co2=1
var methane=2;
/*
<c:foreach items="${voList}" var="vo">
  co2 = "${vo.flowLciaResult1}";
  methane = "${vo.flowLciaResult2}";
</c:foreach>
*/
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';
/*
var ctx1 = document.getElementById("myPieChart1");
var myPieChart1 = new Chart(ctx1, {
  type: 'doughnut',
  data: {
    labels: ["CO2", "Methane"],
    datasets: [{
      data: [co2, methane],
      backgroundColor: ['#4e73df', '#1cc88a'],
      hoverBackgroundColor: ['#2e59d9', '#17a673'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    title: {
      display: true,
      text: 'GWP(kg CO2-eq.)'
    },
    cutoutPercentage: 80,
  },
});
*/
var ctx2 = document.getElementById("myPieChart2");
var myPieChart2 = new Chart(ctx2, {
  type: 'doughnut',
  data: {
    labels: ["CO2", "Methane"],
    datasets: [{
      data: [0.005, 0.0000000000000133],
      backgroundColor: ['#4e73df', '#1cc88a'],
      hoverBackgroundColor: ['#2e59d9', '#17a673'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    title: {
      display: true,
      text: 'GWP(kg CO2-eq.)'
    },
    cutoutPercentage: 80,
  },
});

var ctx3 = document.getElementById("myPieChart3");
var myPieChart3 = new Chart(ctx3, {
  type: 'pie',
  data: {
    labels: ["제조전단계", "제조단계", "폐기단계"],
    datasets: [{
      data: [77, 3, 20],
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    title: {
      display: true,
      text: 'GWP(kg CO2-eq.)'
    },
    // cutoutPercentage: 80,
  },
});

var ctx4 = document.getElementById("myPieChart4");
var myPieChart4 = new Chart(ctx4, {
  type: 'rader',
  data: {
    labels: ["제조전단계", "제조단계", "폐기단계"],
    datasets: [{
      data: [57, 10, 33],
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    title: {
      display: true,
      text: 'GWP(kg CO2-eq.)'
    },
    cutoutPercentage: 80,
  },
});