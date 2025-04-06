<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


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

            

















<!--====== Section Content ======-->
<div class="section__content">
    <div class="container">
        <div class="tab-content">
            <!--======  Tab ======-->
            <div class="tab-pane fade show active" id="item-bean">
                <div class="slider-fouc">
                    <div class="owl-carousel tab-slider" data-item="4">
                        <c:forEach var="product" items="${requestScope.beans}">
                            <div class="u-s-m-b-30">
                                <div class="product-o product-o--hover-on">
                                    <div class="product-o__wrap">

                                        <a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-details.jsp">

                                            <img class="aspect__img" src="images/product/electronic/${product.image}.jpg" alt=""></a>
                                        <div class="product-o__action-wrap">
                                            <ul class="product-o__action-list">
                                                <li>

                                                    <a data-modal="modal"
                                                       data-modal-id="#quick-look"
                                                       data-tooltip="tooltip"
                                                       data-placement="top"
                                                       title="Quick View"
                                                       data-image="${product.image}"
                                                       data-name="${product.name}"
                                                       data-description="${product.description}"
                                                       data-stock="${product.stock}"><i class="fas fa-search-plus"></i></a></li>
                                                <li>

                                                    <a data-modal="modal" data-modal-id="#add-to-cart" data-tooltip="tooltip" data-placement="top" title="Add to Cart"><i class="fas fa-shopping-cart"></i></a>
                                                </li>

                                                <li>

                                                    <a data-modal="modal" data-modal-id="#add-to-wishlist" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist"><i class="fas fa-heart"></i></a>
                                                </li>


                                                <li>

                                                    <a data-modal="modal" data-modal-id="checkout" data-tooltip="tooltip" data-placement="top" title="Checkout"><i class="fas fa-plus"></i></a></li>

                                            </ul>
                                        </div>
                                    </div>

                                    <span class="product-o__category">

                                                            <a href="product-list.jsp">${product.category.name()}</a></span>

                                    <span class="product-o__name">

                                                            <a href="product-details.jsp">${product.name}</a></span>


                                    <span class="product-o__price">$${product.price}</span>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
        <!--====== End -  Tab ======-->
    </div>
</div>




















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

            

















<!--====== Section Content ======-->
<div class="section__content">
    <div class="container">
        <div class="tab-content">
            <!--======  Tab ======-->
            <div class="tab-pane fade show active" id="item-mug">
                <div class="slider-fouc">
                    <div class="owl-carousel tab-slider" data-item="4">

                       <c:forEach var="product" items="${requestScope.mugs}">
                           <div class="u-s-m-b-30">
                               <div class="product-o product-o--hover-on">
                                   <div class="product-o__wrap">

                                       <a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-details.jsp">

                                           <img class="aspect__img" src="images/product/electronic/${product.image}.jpg" alt=""></a>
                                       <div class="product-o__action-wrap">
                                           <ul class="product-o__action-list">
                                               <li>

                                                   <a data-modal="modal" data-modal-id="#quick-look" data-tooltip="tooltip" data-placement="top" title="Quick View"><i class="fas fa-search-plus"></i></a></li>
                                               <li>

                                                   <a data-modal="modal" data-modal-id="#add-to-cart" data-tooltip="tooltip" data-placement="top" title="Add to Cart"><i class="fas fa-shopping-cart"></i></a>
                                               </li>

                                               <li>

                                                   <a data-modal="modal" data-modal-id="#add-to-wishlist" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist"><i class="fas fa-heart"></i></a>
                                               </li>


                                               <li>

                                                   <a data-modal="modal" data-modal-id="checkout" data-tooltip="tooltip" data-placement="top" title="Checkout"><i class="fas fa-plus"></i></a></li>

                                           </ul>
                                       </div>
                                   </div>

                                   <span class="product-o__category">

                                                            <a href="product-list.jsp">${product.category.name()}</a></span>

                                   <span class="product-o__name">

                                                            <a href="product-details.jsp">${product.name}</a></span>


                                   <span class="product-o__price">$${product.price}</span>
                               </div>
                           </div>
                       </c:forEach>

                    </div>
                </div>
            </div>
        </div>
        <!--====== End -  Tab ======-->
    </div>
</div>




















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

            

















<!--====== Section Content ======-->
<div class="section__content">
    <div class="container">
        <div class="tab-content">
            <!--======  Tab ======-->
            <div class="tab-pane fade show active" id="item-machine">
                <div class="slider-fouc">
                    <div class="owl-carousel tab-slider" data-item="4">



                        <c:forEach var="product" items="${requestScope.machines}">
                            <div class="u-s-m-b-30">
                                <div class="product-o product-o--hover-on">
                                    <div class="product-o__wrap">

                                        <a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-list.jsp">

                                            <img class="aspect__img" src="images/product/electronic/${product.image}.jpg" alt=""></a>
                                        <div class="product-o__action-wrap">
                                            <ul class="product-o__action-list">
                                                <li>

                                                    <a data-modal="modal" data-modal-id="#quick-look" data-tooltip="tooltip" data-placement="top" title="Quick View"><i class="fas fa-search-plus"></i></a></li>
                                                <li>

                                                    <a data-modal="modal" data-modal-id="#add-to-cart" data-tooltip="tooltip" data-placement="top" title="Add to Cart"><i class="fas fa-shopping-cart"></i></a>
                                                </li>

                                                <li>

                                                    <a data-modal="modal" data-modal-id="#add-to-wishlist" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist"><i class="fas fa-heart"></i></a>
                                                </li>


                                                <li>

                                                    <a data-modal="modal" data-modal-id="checkout" data-tooltip="tooltip" data-placement="top" title="Checkout"><i class="fas fa-plus"></i></a></li>

                                            </ul>
                                        </div>
                                    </div>

                                    <span class="product-o__category">

                                                            <a href="product-list.jsp">${product.category.name()}</a></span>

                                    <span class="product-o__name">

                                                            <a href="product-details.jsp">${product.name}</a></span>


                                    <span class="product-o__price">$${product.price}</span>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
        <!--====== End -  Tab ======-->
    </div>
</div>




















          </div>
          <!--====== End - Product Tab ======-->







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
