/*!
 *
 *  Copyright © Brent Parish and David McMurray
 *
 */
$(document).ready(function()
{
    $('.r-image').fadeTo(0,0.00);
    $('post-1').fadeTo(0,0.00);
    $('.r-image').load(function(){
        $(this).show();
        $('post-1').show();
    });
    
    $('#more').click(function(){
        showMoreF();
    });

    // Hide #container until the images have loaded
    var $container = $('#container');
    var $loader = $('#loader');

    $container.imagesLoaded( function(){
      $loader.hide();
      $container.show();
      $container.masonry({
        itemSelector : '.item'
      });

    });
    
    //Paginate and load the next posts when the user has scrolled to the end of the page
    $(window).scroll(function()
    {
        if ($(window).scrollTop() == $(document).height() - $(window).height())
        {
            $('div#loadmoreajaxloader').show();
            showMoreF();
        }
    });
    
   window.setTimeout(timedOut, 4000);
}); //End of Document.ready

//Retrieve the next posts using ajax
function showMoreF(urlVal)
{
    var urlVal = "more.jsp"+window.location.search;
    $.ajax({
            url: urlVal,
            cache: false
           }).done(function( html ) 
           {           
                var items = String(html);
                var $boxes = $(items);
                $('#container').append( $boxes ).masonry( 'appended', $boxes );
                $('#container').imagesLoaded( function() 
                {
                    //alert('here');
                    $('.item').removeClass('hidden');
                    $('#container').masonry();
                });
                $('.showMore').on('click',function()
                {
                    showMoreF();
                });
            });
}

//Show images if they take to long to load
function timedOut() 
{
    $('.r-image').fadeTo("slow",1);
};
