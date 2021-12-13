<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/product_list.css">
    <script src="/assets/js/seller.js"></script>
    <!-- <script>
        $(function(){
            $(".main_menu a:nth-child(3)").addClass("active")
        })
    </script> -->
</head>
<body>
    <main>
        <h1><i class="fas fa-school"></i> 판매자 관리</h1>
        <button id="add_product"><i class="fas fa-plus-circle"></i> 판매자 정보 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <input type="text" id="keyword" placeholder="검색어 입력">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>아이디</th>
                            <th>비밀번호</th>
                            <th>생년월일</th>
                            <th>이메일</th>
                            <th>이름</th>
                            <th>주소</th>
                            <th>전화번호</th>
                            <th>추가일</th>
                            <th>수정일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.list.size() == 0}">
                            <tr>
                                <td id="nodata" colspan="1">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="s">
                            <tr>
                                <td>${s.si_seq}</td>
                                <td>${s.si_id}</td>
                                <td>${s.si_pwd}</td>
                                <td>${s.si_birth}</td>
                                <td>${s.si_email}</td>
                                <td>${s.si_name}</td>
                                <td>${s.si_address}</td>
                                <td>${s.si_phone_number}</td>
                                <td>${s.si_reg_dt}</td>
                                <td>${s.si_mod_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${s.si_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${s.si_seq}"><i class="fas fa-minus-circle"></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </tbody>
                </table>
            </div>
            <div class="pager_area">
                <button id="prev"><i class="fas fa-chevron-left"></i></button>
                <div class="pagers">
                    <c:forEach begin="1" end="${data.pageCnt}" var="i">
                        <a href="/seller?offset=${(i-1)*10}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
</body>
</html>