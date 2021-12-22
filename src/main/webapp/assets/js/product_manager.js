$(function(){
    $(".main_menu a:nth-child(5)").addClass("active")
    $("#search_cate").click(function(){
        $(".category_search").css("display", "block");
    })
    $("#cate_search_close").click(function(){
        $(".category_search").css("display", "");
    })
    $("#add_productmanager").click(function(){
        $(".popup_wrap").addClass("open");
        $("#add_pm").css("display", "inline-block");
        $("#modify_pm").css("display", "none");
        $(".popup .top_area h2").html("제품관리자 추가");
        $(".popup .top_area p").html("제품관리자 정보를 입력해주세요");
    })
    $("#cate_search_btn").click(function(){
        $.ajax({
            url:"/productcategory/keyword?keyword="+$("#cate_keyword").val(),
            type:"get",
            success:function(r) {
                console.log(r);
                $(".search_result ul").html("");
                for(let i=0; i<r.list.length; i++) {
                    let str_status = "";
                    if(r.list[i].pci_status == 0) str_status = ""
                    let tag = 
                    '<li>'+
                        '<a href="#" data-cate-seq="'+r.list[i].pci_seq+'">'+r.list[i].pci_name+'</a>'+
                        '<span class="status'+r.list[i].pci_status+'">'+str_status+'</span>'+
                    '</li>';
                    $(".search_result ul").append(tag);
                }

                $(".search_result ul a").click(function(e){
                    e.preventDefault();
                    let seq = $(this).attr("data-cate-seq");
                    let name = $(this).html();

                    $("#pm_cate").attr("data-cate-seq", seq);
                    $("#pm_cate").val(name);

                    $(".search_result ul").html("");
                    $("#cate_keyword").val("");
                    $(".category_search").css("display", "");
                })
            }
        })
    })
    $("#add_pm").click(function(){
        if(confirm("판매자를 등록하시겠습니까?")==false) return;
        let category_name = $("#pm_cate").attr("data-cate-seq");
        let pm_id = $("#pm_id").val();
        let pm_pwd = $("#pm_pwd").val();
        let pm_pwd_confirm = $("#pm_pwd_confirm").val();
        let pm_birth = $("#pm_birth").val();
        let pm_email = $("#pm_email").val();
        let pm_name = $("#pm_name").val();
        let pm_phone_number = $("#pm_phone_number").val();
        let pm_status = $("#pm_status option:selected").val();

        if(pm_pwd == '') {
            alert("비밀번호를 입력해주세요");
            return;
        }
        if(pm_pwd != pm_pwd_confirm) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }

        let data = {
            pmi_pci_seq:category_name,
            pmi_id:pm_id,
            pmi_pwd:pm_pwd,
            pmi_birth:pm_birth,
            pmi_email:pm_email,
            pmi_name:pm_name,
            pmi_phone_number:pm_phone_number,
            pmi_status:pm_status
        }

        $.ajax({
            type:"post",
            url:"/productmanager/add",
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
    $("#cancel_pm").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)")==false) return;
        
        $("#pm_cate").val("");
        $("#pm_id").val("");
        $("#pm_pwd").val("");
        $("#pm_birth").val("");
        $("#pm_email").val("");
        $("#pm_name").val("");
        $("#pm_phone_number").val("");
        $("#pm_status").val("0").prop("selected", true);

        $(".popup_wrap").removeClass("open");
        $(".category_search").removeClass("open");
    })

    $(".delete_btn").click(function(){
        if(confirm("제품관리자를 삭제하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)")==false) return;
        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/productmanager/delete?seq="+seq,
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })

    let modify_seq=0;
    $(".modify_btn").click(function(){
        let seq=$(this).attr("data-seq");
        modify_seq=seq;
        $.ajax({
            type:"get",
            url:"/productmanager/get?seq="+$(this).attr("data-seq"),
            success:function(r) {
                console.log(r)
                $(".popup_wrap").addClass("open");
                $("#add_pm").css("display", "none");
                $("#modify_pm").css("display", "inline-block");
                $(".popup .top_area h2").html("제품관리자 수정");
                $(".popup .top_area p").html("수정할 내용을 입력해주세요");

                $("#pm_cate").attr("data-cate-seq", r.data.pmi_pci_seq);
                $("#pm_cate").val(r.data.category_name);
                $("#pm_id").val(r.data.pmi_id);
                $("#pm_pwd").val("*********").prop("disabled", true);
                $("#pm_pwd_confirm").val("*********").prop("disabled", true);
                $("#pm_birth").val(r.data.pmi_birth);
                $("#pm_email").val(r.data.pmi_email);
                $("#pm_name").val(r.data.pmi_name);
                $("#pm_phone_number").val(r.data.pmi_phone_number);
                $("#pm_status").val(r.data.pmi_status).prop("selected", true);
            }
        })
    })

    $("#modify_pm").click(function(){
        if(confirm("수정하시겠습니까?")==false) return;
        
        let data = {
            pmi_seq:modify_seq,
            pmi_pci_seq:$("#pm_cate").attr("data-cate-seq"),
            pmi_id:$("#pm_id").val(),
            pmi_birth:$("#pm_birth").val(),
            pmi_email:$("#pm_email").val(),
            pmi_name:$("#pm_name").val(),
            pmi_phone_number:$("#pm_phone_number").val(),
            pmi_status:$("#pm_status option:selected").val()
        }

        $.ajax({
            type:"patch",
            url:"/productmanager/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
    $("#search_btn").click(function(){
        location.href="/product_manager?keyword="+$("#keyword").val();
    })
    $("#keyword").keydown(function(e){
        if(e.keyCode == 13) {
            $("#search_btn").trigger("click");
        }
    })
})