// 導覽列功能設定
class StickyNavigation {
	constructor() {
		this.currentId = null;
		this.currentTab = null;
		this.tabContainerHeight = 70;
		let self = this;
		$('.et-hero-tab').click(function() { 
			self.onTabClick(event, $(this)); 
		});
		$(window).scroll(() => { this.onScroll(); });
	}
	
	onScroll() {
	this.checkTabContainerPosition();
	}
    
	// 固定延展功能
	checkTabContainerPosition() {
		let offset = $('.et-hero-tabs').offset().top + $('.et-hero-tabs').height() - this.tabContainerHeight;
		if($(window).scrollTop() > offset) {
			$('.et-hero-tabs-container').addClass('et-hero-tabs-container--top');
		} 
		else {
			$('.et-hero-tabs-container').removeClass('et-hero-tabs-container--top');
		}
	}
}
new StickyNavigation();


// 置頂鈕移動時間設定
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



// 發布新活動的上傳圖片
$(document).ready(function() {
    $("#input-b6").fileinput({
		showClose:false,
		showCaption:false,
		showRemove: false,
		showCancel:false,
        showUpload: false,
        dropZoneEnabled: false,
        maxFileCount: 10,
        mainClass: "input-group-lg"
    });
});

// 修改頁面的上傳圖片
$(document).ready(function() {
    $("#input-b7").fileinput({
		showClose:false,
		showCaption:false,
		showRemove: false,
		showCancel:false,
        showUpload: false,
        dropZoneEnabled: false,
        maxFileCount: 10,
        mainClass: "input-group-lg"
    });
});