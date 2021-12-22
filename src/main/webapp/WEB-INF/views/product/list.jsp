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
    <script src="/assets/js/product.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-box-open"></i> 제품 관리</h1>
        <button id="add_product"><i class="fas fa-plus-circle"></i> 제품 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <input type="text" id="keyword" placeholder="검색어 입력" value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제품명</th>
                            <th>제품 설명</th>
                            <th>제품 카테고리</th>
                            <th>제품 가격</th>
                            <th>제품 추천수</th>
                            <th>등록일</th>
                            <th>수정일</th>
                            <th>판매상태</th>
                            <th>as여부</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.list.size() == 0}">
                            <tr>
                                <td id="nodata" colspan="11">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="p">
                            <tr>
                                <td>${p.pi_seq}</td>
                                <td>${p.pi_name}</td>
                                <td>${p.pi_sub}</td>
                                <td>${p.category_name}</td>
                                <td>${p.pi_price}원</td>
                                <td>${p.pi_like}</td>
                                <td>${p.pi_reg_dt}</td>
                                <td>${p.pi_mod_dt}</td>
                                <c:if test="${p.pi_sell==1}">
                                    <td>판매완료</td>
                                </c:if>
                                <c:if test="${p.pi_sell==0}">
                                    <td>판매중</td>
                                </c:if>
                                <c:if test="${p.pi_as==1}">
                                    <td>수리요청</td>
                                </c:if>
                                <c:if test="${p.pi_as==0}">
                                    <td>정상사용</td>
                                </c:if>
                                <td>
                                    <button class="modify_btn" data-seq="${p.pi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${p.pi_seq}"><i class="fas fa-minus-circle"></i></button>
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
                        <a href="/product?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="product_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-box-open"></i>
                </div>
                <h2>제품 추가</h2>
                <p>제품 정보를 입력하세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="pro_name" placeholder="제품명"><br>
                <input type="text" id="pro_cate" placeholder="제품 카테고리">
                <button id="search_cate">카테고리 검색</button>
                <input type="text" id="pro_sub" placeholder="제품설명"><br>
                <input type="number" id="pro_price" placeholder="제품가격"><br>
                <select id="pro_sell">
                    <option value="0">판매중</option>
                    <option value="1">판매완료</option>
                </select><br>
                <select id="pro_as">
                    <option value="0">정상사용</option>
                    <option value="1">as요청</option>
                </select>
            </div>
            <div class="btn_area">
                <button id="add_pro">등록하기</button>
                <button id="modify_pro">수정하기</button>
                <button id="cancel_pro">취소하기</button>
            </div>
        </div>
    </div>
    <div class="category_search">
        <div class="cate_search_box">
            <input type="text" id="cate_keyword" placeholder="카테고리를 입력하세요">
            <button id="cate_search_btn"><i class="fas fa-search"></i></button>
        </div>
        <div class="search_result">
            <ul>
                
            </ul>
        </div>
        <div class="pro_search_buttons">
            <button id="cate_search_close">닫기</button>
        </div>
    </div>
</body>
</html>