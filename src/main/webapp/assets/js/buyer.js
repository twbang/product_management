$(function(){
    $(".main_menu a:nth-child(4)").addClass("active")
    $("#add_buyer").click(function(){
        $(".popup_wrap").addClass("open");
        $("#add_buy").css("display", "inline-block");
        $("#modify_buy").css("display", "none");
        $(".popup .top_area h2").html("일반유저 추가");
        $(".popup .top_area p").html("일반유저 정보를 입력해주세요");
    })
    $("#add_buy").click(function(){
        if(confirm("일반유저를 등록하시겠습니까?")==false) return;
        let buy_id = $("#buy_id").val();
        let buy_pwd = $("#buy_pwd").val();
        let buy_birth = $("#buy_birth").val();
        let buy_email = $("#buy_email").val();
        let buy_name = $("#buy_name").val();
        let buy_address = $("#buy_address").val();
        let buy_phone_number = $("#buy_phone_number").val();
        let buy_status = $("#buy_status option:selected").val();

        let data = {
            bi_id:buy_id,
            bi_pwd:buy_pwd,
            bi_birth:buy_birth,
            bi_email:buy_email,
            bi_name:buy_name,
            bi_address:buy_address,
            bi_phone_number:buy_phone_number,
            bi_status:buy_status
        }

        $.ajax({
            type:"post",
            url:"/buyer/add",
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
    $("#cancel_buy").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)")==false) return;
        
        $("#buy_id").val("");
        $("#buy_pwd").val("");
        $("#buy_birth").val("");
        $("#buy_email").val("");
        $("#buy_name").val("");
        $("#buy_address").val("");
        $("#buy_phone_number").val("");
        $("#buy_status").val("0").prop("selected", true);

        $(".popup_wrap").removeClass("open");
    })

    $(".delete_btn").click(function(){
        if(confirm("일반유저를 삭제하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)")==false) return;
        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/buyer/delete?seq="+seq,
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
        $("#add_buy").css("display", "none");
        $("#modify_buy").css("display", "inline-block");
        $(".popup .top_area h2").html("판매자 정보 수정");
        $(".popup .top_area p").html("수정할 내용을 입력해주세요");
        $.ajax({
            type:"get",
            url:"/buyer/get?seq="+$(this).attr("data-seq"),
            success:function(r) {
                $("#buy_id").val(r.data.bi_id);
                $("#buy_pwd").val(r.data.bi_pwd);
                $("#buy_birth").val(r.data.bi_birth);
                $("#buy_email").val(r.data.bi_email);
                $("#buy_name").val(r.data.bi_name);
                $("#buy_address").val(r.data.bi_address);
                $("#buy_phone_number").val(r.data.bi_phone_number);
                $("#buy_status").val(r.data.bi_status).prop("selected", true);
            }
        })
    })

    $("#modify_buy").click(function(){
        if(confirm("수정하시겠습니까?")==false) return;

        let buy_id = $("#buy_id").val();
        let buy_pwd = $("#buy_pwd").val();
        let buy_birth = $("#buy_birth").val();
        let buy_email = $("#buy_email").val();
        let buy_name = $("#buy_name").val();
        let buy_address = $("#buy_address").val();
        let buy_phone_number = $("#buy_phone_number").val();
        let buy_status = $("#buy_status option:selected").val();

        let data = {
            bi_seq:modify_data_seq,
            bi_id:buy_id,
            bi_pwd:buy_pwd,
            bi_birth:buy_birth,
            bi_email:buy_email,
            bi_name:buy_name,
            bi_address:buy_address,
            bi_phone_number:buy_phone_number,
            bi_status:buy_status
        }
        $.ajax({
            type:"patch",
            url:"/buyer/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })

    $("#search_btn").click(function(){
        location.href="/buyer?keyword="+$("#keyword").val();
    })
    $("#keyword").keydown(function(e){
        console.log(e.keyCode)
        if(e.keyCode == 13) {
            $("#search_btn").trigger("click");
        }
    })
})