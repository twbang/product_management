<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/admin.css">
</head>
<body>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <main>
        <h1>제품관리 대시보드 (Product Management Dashboard)</h1>
        <div class="content_area">
            <div class="product_info">
                <h2><i class="fas fa-box-open"></i> 제품 정보</h2>
                <p>총 등록 제품 : <span>${cnt.product[0]}개</span></p>
                <p>총 판매된 제품 : <span>${cnt.product[1]}개</span></p>
                <p>구매정지된 제품 : <span>${cnt.product[2]}개</span></p>
                <p>수리 예정 제품 : <span>${cnt.product[3]}개</span></p>
                <p>업데이트 날짜 : <span><fmt:formatDate value="${update.product}" pattern="yy년 MM월 dd일 (EE) HH:mm:ss"/></span></p>
            </div>
            <div class="seller_info">
                <h2><i class="fas fa-user-circle"></i> 판매자 정보</h2>
                <p>등록된 판매자 : <span>${cnt.seller[0]}명</span></p>
                <p>등록대기 중 판매자 : <span>${cnt.seller[1]}명</span></p>
                <p>사용정지된 판매자 : <span>${cnt.seller[2]}명</span></p>
                <p>업데이트 날짜 : <span><fmt:formatDate value="${update.seller}" pattern="yy년 MM월 dd일 (EE) HH:mm:ss"/></span></p>
            </div>
            <div class="buyer_info">
                <h2><i class="fas fa-user-circle"></i> 일반회원 정보</h2>
                <p>등록된 일반회원 : <span>${cnt.buyer[0]}명</span></p>
                <p>등록대기 중 일반회원 : <span>${cnt.buyer[1]}명</span></p>
                <p>사용정지된 일반회원 : <span>${cnt.buyer[2]}명</span></p>
                <p>업데이트 날짜 : <span><fmt:formatDate value="${update.buyer}" pattern="yy년 MM월 dd일 (EE) HH:mm:ss"/></span></p>
            </div>
            <div class="product_manager_info">
                <h2><i class="fas fa-user-tie"></i> 제품관리자 정보</h2>
                <p>등록된 제품관리자 : <span>${cnt.productmanager[0]}명</span></p>
                <p>업무 중 제품관리자 : <span>${cnt.productmanager[1]}명</span></p>
                <p>휴직 중 제품관리자 : <span>${cnt.productmanager[2]}명</span></p>
                <p>업데이트 날짜 : <span><fmt:formatDate value="${update.product_manager}" pattern="yy년 MM월 dd일 (EE) HH:mm:ss"/></span></p>
            </div>
            <div class="page_manager_info">
                <h2><i class="fas fa-user-tie"></i> 페이지관리자 정보</h2>
                <p>등록된 페이지관리자 : <span>${cnt.pagemanager[0]}명</span></p>
                <p>업무 중 페이지관리자 : <span>${cnt.pagemanager[1]}명</span></p>
                <p>휴직 중 페이지관리자 : <span>${cnt.pagemanager[2]}명</span></p>
                <p>업데이트 날짜 : <span>2021-12-10 12:00:00</span></p>
            </div>
        </div>
    </main>
</body>
</html>