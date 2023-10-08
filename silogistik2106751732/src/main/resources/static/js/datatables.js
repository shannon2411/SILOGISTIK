$(document).ready( function () {
    $('#myTable').DataTable({
        lengthMenu: [5, 10, 20], // Set the available options for the number of entries per page
        pageLength: 5, // Set the default number of entries per page
        destroy: true
    });
} );