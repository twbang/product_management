$(function(){
    $(".main_menu a:nth-child(2)").addClass("active")
    $("#add_product").click(function(){
        $(".popup_wrap").addClass("open");
    })
    $("#add_pro").click(function(){
        if(confirm("제품을 등록하시겠습니까?")==false) return;
        let pro_name = $("#pro_name").val();
        let pro_sub = $("#pro_sub").val();
        let pro_price = $("#pro_price").val();
        let pro_sell = $("#pro_sell option:selected").val();
        let pro_as = $("#pro_as option:selected").val();

        let data = {
            pi_name:pro_name,
            pi_sub:pro_sub,
            pi_price:pro_price,
            pi_sell:pro_sell,
            pi_as:pro_as
        }

        $.ajax({
            type:"post",
            url:"/product/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                if(r.status) {
                    location.reload();
                }
            }
        })
    })
    $("#cancel_pro").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)")==false) return;
        
        $("#pro_name").val("");
        $("#pro_sub").val("");
        $("#pro_price").val("");
        $("#pro_sell").val("0").prop("selected", true);
        $("#pro_as").val("0").prop("selected", true);

        $(".popup_wrap").removeClass("open");
    })

    $(".delete_btn").click(function(){
        if(confirm("제품 삭제하시겟습니까?\n(이 동작은 되돌릴 수 없습니다.)")==false) return;
        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/product/delete?seq="+seq,
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
})