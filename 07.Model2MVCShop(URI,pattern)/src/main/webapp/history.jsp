<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
<head>

<title>열어본 상품 보기</title>

</head>
<body>
	최근 열어본 상품
<br>
<br>
  
<%
    request.setCharacterEncoding("euc-kr");
    response.setCharacterEncoding("euc-kr");

    Cookie[] cookies = request.getCookies();
    String history = null;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("history")) {
                history = cookie.getValue();
                break;
            }
        }
    }

    String currentProduct = request.getParameter("prodNo");

    if (currentProduct != null && !currentProduct.isEmpty()) {
        int prodNo = Integer.parseInt(currentProduct);
        
        if (history == null) {
            history = String.valueOf(prodNo);
        } else {
            history += "|" + prodNo;
        }

        Cookie historyCookie = new Cookie("history", history);
        historyCookie.setMaxAge(24 * 60 * 60); 
        response.addCookie(historyCookie);
    }

    if (history != null) {
        String[] h = history.split("\\|");
        for (int i = 0; i < h.length; i++) {
            if (!h[i].equals("null")) {
%>
<a href="/getProduct?prodNo=<%=h[i]%>&menu=search" target="rightFrame"><%=h[i]%></a>
<br>
<%
            }
        }
    }
%>


</body>
</html>