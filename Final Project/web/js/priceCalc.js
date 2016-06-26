/**
 * Created by Lenovo on 12.06.2016.
 */
function calcPrice(amount, id){
    var oldPrice = $('#'+id).parent().next().attr('value');
    var newPrice =  oldPrice * amount;
    if(newPrice !== NaN) {
        $('#' + id).parent().next().text(newPrice);
    }
    else{
        $('#' + id).parent().next().text(0);
    }
    calcTotalPrice();
}
function calcTotalPrice(){
    var totalPrice = 0;
    $(".priceDrugs").each(function(){
        totalPrice += +$(this).text();
    });
    if(totalPrice != NaN) {
        $(".totalPrice").text(totalPrice);
    }
    else{
        $(".totalPrice").text(0);
    }
}
$(document).ready(calcTotalPrice);