$(document).ready(loadProductDashboardTable());

function loadProductDashboardTable (){
    $('#productDashboard').DataTable({
        bAutoWidth: false,
        "columnDefs": [
            {"width": "10%", "targets": 0},
            {"width": "25%", "targets": 1},
            {"width": "25%", "targets": 2},
            {"width": "20%", "targets": 3},
            {"width": "10%", "targets": 4},
            {"width": "15%", "targets": 5},
        ]
    });
}