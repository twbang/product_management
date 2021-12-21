$(function(){
    $(".main_menu a:nth-child(2)").addClass("active")
    $("#add_product").click(function(){
        $(".popup_wrap").addClass("open");
        $("#add_pro").css("display", "inline-block");
        $("#modify_pro").css("display", "none");
        $(".popup .top_area h2").html("제품 추가");
        $(".popup .top_area p").html("제품 정보를 입력해주세요");
    })
    $("#search_cate").click(function(){
        $(".category_search").css("display", "block");
    })
    $("#cate_search_close").click(function(){
        $(".category_search").css("display", "");
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

    let modify_data_seq=0;
    $(".modify_btn").click(function(){
        modify_data_seq=$(this).attr("data-seq");
        $(".popup_wrap").addClass("open");
        $("#add_pro").css("display", "none");
        $("#modify_pro").css("display", "inline-block");
        $(".popup .top_area h2").html("제품 수정");
        $(".popup .top_area p").html("수정할 내용을 입력해주세요");
        $.ajax({
            type:"get",
            url:"/product/get?seq="+$(this).attr("data-seq"),
            success:function(r) {
                $("#pro_name").val(r.data.pi_name);
                $("#pro_sub").val(r.data.pi_sub);
                $("#pro_price").val(r.data.pi_price);
                $("#pro_sell").val(r.data.pi_sell).prop("selected", true);
                $("#pro_as").val(r.data.pi_as).prop("selected", true);
            }
        })
    })

    $("#modify_pro").click(function(){
        if(confirm("수정하시겠습니까?")==false) return;

        let pro_name = $("#pro_name").val();
        let pro_sub = $("#pro_sub").val();
        let pro_price = $("#pro_price").val();
        let pro_sell = $("#pro_sell option:selected").val();
        let pro_as = $("#pro_as option:selected").val();

        let data = {
            pi_seq:modify_data_seq,
            pi_name:pro_name,
            pi_sub:pro_sub,
            pi_price:pro_price,
            pi_sell:pro_sell,
            pi_as:pro_as
        }
        $.ajax({
            type:"patch",
            url:"/product/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })

    $("#search_btn").click(function(){
        location.href="/product?keyword="+$("#keyword").val();
    })
    $("#keyword").keydown(function(e){
        console.log(e.keyCode)
        if(e.keyCode == 13) {
            $("#search_btn").trigger("click");
        }
    })
})