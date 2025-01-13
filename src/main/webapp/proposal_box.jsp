<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Proposal Box</title>
</head>
<body>
    <h1>Proposal Box</h1>

    <!-- 받은 제안서 목록 -->
    <h2>받은 제안서</h2>
    <table border="1">
        <thead>
            <tr>
                <th>제목</th>
                <th>내용</th>
                <th>예산</th>
                <th>마감일</th>
                <th>연락처</th>
                <th>상태</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="receivedProposal" items="${receivedProposals}">
                <tr>
                    <td>${receivedProposal.prop_title}</td>
                    <td>${receivedProposal.prop_content}</td>
                    <td>${receivedProposal.prop_account}</td>
                    <td>${receivedProposal.prop_ed_td}</td>
                    <td>${receivedProposal.prop_tell}</td>
                    <td>${receivedProposal.selected_yn}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 보낸 제안서 목록 -->
    <h2>보낸 제안서</h2>
    <table border="1">
        <thead>
            <tr>
                <th>제목</th>
                <th>내용</th>
                <th>예산</th>
                <th>마감일</th>
                <th>연락처</th>
                <th>상태</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="sentProposal" items="${sentProposals}">
                <tr>
                    <td>${sentProposal.prop_title}</td>
                    <td>${sentProposal.prop_content}</td>
                    <td>${sentProposal.prop_account}</td>
                    <td>${sentProposal.prop_ed_td}</td>
                    <td>${sentProposal.prop_tell}</td>
                    <td>${sentProposal.selected_yn}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 특정 svc_idx 값을 ProposalBoxController로 전달 -->
    <form action="ProposalBoxController" method="get" style="display:none;">
        <input type="hidden" name="svc_idx" value="<c:out value='${svc_idx}' />" /> <!-- 동적으로 svc_idx 설정 -->
        <button type="submit">제안서 보기</button>
    </form>

    <script>
        // 특정 svc_idx 값으로 자동 요청
        const svcIdx = "<c:out value='${svc_idx}' />"; // 동적으로 설정된 svc_idx
        console.log("[DEBUG] 자동 제출용 svc_idx: " + svcIdx);

        // 동적으로 폼 생성 및 제출
        const form = document.createElement("form");
        form.method = "get";
        form.action = "ProposalBoxController";

        const hiddenField = document.createElement("input");
        hiddenField.type = "hidden";
        hiddenField.name = "svc_idx";
        hiddenField.value = svcIdx;

        form.appendChild(hiddenField);
        document.body.appendChild(form);

        // 폼 자동 제출
        form.submit();
    </script>
</body>
</html>
