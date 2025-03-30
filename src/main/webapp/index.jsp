<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Test user data -->
<%@ page import="com.ecommerce.webapp.model.User" %>
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

          <!--====== Product Tab ======-->
          <div class="u-s-p-b-60" id="electronic-01">
            <!--====== Section Intro ======-->
            <div class="section__intro u-s-m-b-46">
              <div class="container">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="block">

                      <span class="block__title">Beans Products</span>

                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--====== End - Section Intro ======-->

            <jsp:include page="commos/product-card.jsp"/>
          </div>
          <!--====== End - Product Tab ======-->



      <!--====== Section 3 ======-->
          <%@include file="home/mugs-static.html"%>



      <!--====== Section 4 ======-->


          <!--====== Product Tab ======-->
          <div class="u-s-p-b-60" id="female-02">
            <!--====== Section Intro ======-->
            <div class="section__intro u-s-m-b-46">
              <div class="container">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="block">

                      <span class="block__title">Mugs Products</span>

                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--====== End - Section Intro ======-->

            <jsp:include page="commos/product-card.jsp"/>
          </div>
          <!--====== End - Product Tab ======-->




          <!--====== Section 5 ======-->

          <%@include file="home/machines-static.html"%>


          <!--====== Section 6 ======-->
          <!--====== Product Tab ======-->
          <div class="u-s-p-b-60" id="male-03">
            <!--====== Section Intro ======-->
            <div class="section__intro u-s-m-b-46">
              <div class="container">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="block">

                      <span class="block__title">Machines Products</span>

                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--====== End - Section Intro ======-->

            <jsp:include page="commos/product-card.jsp"/>
          </div>
          <!--====== End - Product Tab ======-->


          <!--====== Section 7 ======-->
          <%@include file="home/services.html"%>






</div>
<!--====== End - White Container ======-->
</div>
<!--====== End - Anti Flash White Background ======-->
</div>
<!--====== End - App Content ======-->

</div>
    <!--====== End - Main App ======-->

    <%@include file="commos/footer.html"%>



  <jsp:include page="commos/modals.jsp"/>



<%@include file="commos/script.html"%>

</body>
</html>
