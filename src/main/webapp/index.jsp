<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html class="no-js" lang="en">


<%@include file="commos/head.html"%>



<body class="config" id="js-scrollspy-trigger">

<%@include file="commos/preloader.html"%>

<!--====== Main App ======-->
<div id="app">
  

  <%@include file="home/scroll-list.html"%>
  
 <jsp:include page="commos/header.jsp"/>


  <!--====== App Content ======-->
  <div class="app-content">

    <!--====== Anti Flash White Background ======-->
    <div class="white-container">

      <!--====== White Container ======-->
      <div class="white-container">
        <div class="container">

          <!--====== Primary Slider ======-->
          <%@include file="home/slider.html"%>





        <!--====== Section 1 ======-->

          <%@include file="home/beans-static.html"%>


        <!--====== Section 2 ======-->

          <jsp:include page="home/beans-dynamic.jsp"/>



      <!--====== Section 3 ======-->
          <%@include file="home/mugs-static.html"%>



      <!--====== Section 4 ======-->


          <jsp:include page="home/mugs-dynamic.jsp"/>




          <!--====== Section 5 ======-->

          <%@include file="home/machines-static.html"%>


          <!--====== Section 6 ======-->
          <jsp:include page="home/machines-dynamic.jsp"/>


          <!--====== Section 7 ======-->
          <%@include file="home/services.html"%>


          <!--====== Section 8 ======-->
          <%@include file="home/developers.html"%>




</div>
<!--====== End - White Container ======-->
</div>
<!--====== End - Anti Flash White Background ======-->
</div>
<!--====== End - App Content ======-->

</div>


<%@include file="commos/footer.html"%>


<jsp:include page="commos/modals.jsp"/>

<!--====== End - Main App ======-->


<%@include file="commos/script.html"%>







<!-- Test user data -->
<%@ page import="com.ecommerce.webapp.ecommercewebapp.model.User" %>
<%@ page import="java.util.Date" %>

<%
    User user = new User();
    user.setFirstName("kerellos");
    user.setLastName("samy");
    user.setEmail("kerolos@example.com");
    user.setPhone("1234567890");
    user.setGender("Male");
    user.setAddress("Smart village, 123 Coffee St.");
    user.setBirthDate(new Date());
    user.setBalance(300.59);

    session.setAttribute("currentUser", user);
%>

</body>
</html>
