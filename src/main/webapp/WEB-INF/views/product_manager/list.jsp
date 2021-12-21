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
    <link rel="stylesheet" href="/assets/css/product_manager_list.css">
    <script src="/assets/js/product_manager.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-user-circle"></i> 제품관리자 관리</h1>
        <button id="add_productmanager"><i class="fas fa-user-circle"></i> 제품관리자 정보 추가</button>
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
                            <th>담당제품과</th>
                            <th>아이디</th>
                            <th>비밀번호</th>
                            <th>생년월일</th>
                            <th>이메일</th>
                            <th>이름</th>
                            <th>전화번호</th>
                            <th>관리자상태</th>
                            <th>추가일</th>
                            <th>수정일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.list.size() == 0}">
                            <tr>
                                <td id="nodata" colspan="12">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="pm">
                            <tr>
                                <td>${pm.pmi_seq}</td>
                                <td>${pm.category_name}</td>
                                <td>${pm.pmi_id}</td>
                                <td>${pm.pmi_pwd}</td>
                                <td>${pm.pmi_birth}</td>
                                <td>${pm.pmi_email}</td>
                                <td>${pm.pmi_name}</td>
                                <td>${pm.pmi_phone_number}</td>
                                <td>
                                    <c:if test="${pm.pmi_status==0}">
                                        <td>관리중</td>
                                    </c:if>
                                    <c:if test="${pm.pmi_status==1}">
                                        <td>관리대기중</td>
                                    </c:if>
                                    <c:if test="${pm.pmi_status==2}">
                                        <td>휴직중</td>
                                    </c:if>
                                </td>
                                <td>${pm.pmi_reg_dt}</td>
                                <td>${pm.pmi_mod_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${pm.pmi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${pm.pmi_seq}"><i class="fas fa-minus-circle"></i></button>
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
                        <a href="/productmanager?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="productmanager_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-user-circle"></i>
                </div>
                <h2>제품관리자 추가</h2>
                <p>제품관리자 정보를 입력하세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="pm_cate" placeholder="담당제품과" disabled>
                <button id="search_cate">카테고리 검색</button>
                <input type="text" id="pm_id" placeholder="제품관리자 아이디"><br>
                <input type="password" id="pm_pwd" placeholder="제품관리자 비밀번호"><br>
                <input type="text" id="pm_birth" placeholder="제품관리자 생년월일"><br>
                <input type="text" id="pm_email" placeholder="제품관리자 이메일"><br>
                <input type="text" id="pm_name" placeholder="제품관리자 이름"><br>
                <input type="text" id="pm_phone_number" placeholder="제품관리자 전화번호"><br>
                <select id="pm_status">
                    <option value="0">관리중</option>
                    <option value="1">관리대기중</option>
                    <option value="2">휴직</option>
                </select>
            </div>
            <div class="btn_area">
                <button id="add_pm">등록하기</button>
                <button id="modify_pm">수정하기</button>
                <button id="cancel_pm">취소하기</button>
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
        <div class="pm_search_buttons">
            <button id="cate_search_close">닫기</button>
        </div>
    </div>
</body>
</html>