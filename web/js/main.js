/**
 * Created by datht on 3/1/17.
 */
$(document).ready(function () {
    jQuery(function($) {
        function fixDiv() {
            var $cache = $('#side-bar>div');
            if ($(window).scrollTop() > 450)
                $cache.css({
                    'position': 'fixed',
                    'top': '60px'
                });
            else
                $cache.css({
                    'position': 'relative',
                    'top': 'auto'
                });
        }
        $(window).scroll(fixDiv);
        fixDiv();
    });

});