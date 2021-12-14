$(function(){
    $(".main_menu a:nth-child(3)").addClass("active")
    $("#add_seller").click(function(){
        $(".popup_wrap").addClass("open");
    })
    $("#add_sel").click(function(){
        if(confirm("판매자를 등록하시겠습니까?")==false) return;
        let sel_id = $("#sel_id").val();
        let sel_pwd = $("#sel_pwd").val();
        let sel_birth = $("#sel_birth").val();
        let sel_email = $("#sel_email").val();
        let sel_name = $("#sel_name").val();
        let sel_address = $("#sel_address").val();
        let sel_phone_number = $("#sel_phone_number").val();
        let sel_status = $("#sel_status option:selected").val();

        let data = {
            si_id:sel_id,
            si_pwd:sel_pwd,
            si_birth:sel_birth,
            si_email:sel_email,
            si_name:sel_name,
            si_address:sel_address,
            si_phone_number:sel_phone_number,
            si_status:sel_status
        }

        $.ajax({
            type:"post",
            url:"/seller/add",
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
    $("#cancel_sel").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)")==false) return;
        
        $("#sel_id").val("");
        $("#sel_pwd").val("");
        $("#sel_birth").val("");
        $("#sel_email").val("");
        $("#sel_name").val("");
        $("#sel_address").val("");
        $("#sel_phone_number").val("");
        $("#sel_status").val("0").prop("selected", true);

        $(".popup_wrap").removeClass("open");
    })

    $(".delete_btn").click(function(){
        if(confirm("판매자를 삭제하시겟습니까?\n(이 동작은 되돌릴 수 없습니다.)")==false) return;
        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/seller/delete?seq="+seq,
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
})