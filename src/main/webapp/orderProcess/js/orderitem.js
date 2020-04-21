

var seeBtn=document.getElementById('orderitem_rqcode');//span區塊
var printBtn=document.getElementById('print');

/*互動視窗跳出*/
seeBtn.addEventListener("click",function(){
    $('#orderitem_myModal').modal('show');
    mathway();

},false);

/*列印事件*/
printBtn.addEventListener("click",function(){
    window.print();
},false);


function mathway(){   //隨機產生條碼的值方法
var mathNumber=document.querySelector('.qrcodeNum');
   var str="";
   var abbarr=['A','B','C','D','E','F','G','H','I','J','K'];
   var arr=['1','2','3','4','5','6','7','8','9'];
   for(var i=0;i<4;i++)
   { 
       var abbindex=Math.floor(11*Math.random()); 
       var index=Math.floor(i*Math.random());
       str=str+abbarr[abbindex]+arr[index]+arr[index];
   }
    localStorage.setItem("QRcode",str);
    var bbb=localStorage.getItem("QRcode");
    mathNumber.innerHTML=bbb;
    $("#bcTarget").barcode(bbb, "code128");//調用條碼使用code128格式 因為有載入套件可以直接這樣使用

}

