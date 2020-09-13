$(document).ready(function () {
   var currentMenuItem = window.location.pathname;
   $('.nav-item').removeClass('active');
   $('.nav-item a[href="'+ currentMenuItem +'"]').parent().addClass('active');
});