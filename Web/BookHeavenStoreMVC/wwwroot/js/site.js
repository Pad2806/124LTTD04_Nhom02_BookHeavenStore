// Please see documentation at https://learn.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.
$(document).ready(function () {
    $('#donHangTable').DataTable({
        "scrollY": "450px",
        "scrollCollapse": true,
        "paging": true

    });
});
$(document).ready(function () {
    $('#khachHangTable').DataTable({
        "scrollY": "450px",
        "scrollCollapse": true,
        "paging": true

    });
});
$(document).ready(function () {
    $('#sanPhamTable').DataTable({
        "scrollY": "450px",
        "scrollCollapse": true,
        "paging": true

    });
});