$(function(){
    // 當點擊這個按鈕，自動捲動到網頁最上方，0.333秒完成捲動的動作
	$('#backtop').click(function(){ 
		$('html,body').animate({scrollTop:0}, 333);
    });
    
	$(window).scroll(function() {
		if ( $(this).scrollTop() > 300 ){
			$('#backtop').fadeIn(222);
		} else {
			$('#backtop').stop().fadeOut(222);
		}
	}).scroll();
});