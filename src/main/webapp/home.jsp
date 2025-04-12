<%--------Mock data to test on it -----------------------%>
<%@ page import="com.ecommerce.webapp.entities.User" %>
<%@ page import="com.ecommerce.webapp.entities.Order" %>
<%@ page import="com.ecommerce.webapp.entities.OrderItem" %>
<%@ page import="com.ecommerce.webapp.entities.OrderStatus" %>
<%@ page import="com.ecommerce.webapp.entities.Product" %>
<%@ page import="com.ecommerce.webapp.entities.ProductCategory" %>
<%@ page import="com.ecommerce.webapp.entities.ProductStatus" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    User user = new User();
    user.setName("kerellos");
    user.setEmail("kerellos@example.com");
    user.setAddress("smart village");
    user.setCreditLimit(200.7);
    user.setPhone("01234567890");
    user.setPassword("123456");

    Product product1 = new Product("Ethiopian Yirgacheffe", "Light roast with floral notes", 14.99, ProductCategory.BEANS, "ethiopian.jpg", 100, ProductStatus.ACTIVE);
    Product product2 = new Product("Colombian Supremo", "Medium roast with caramel notes", 12.99, ProductCategory.BEANS, "colombian.jpg", 200, ProductStatus.ACTIVE);
    Product product3 = new Product("Ceramic Mug", "12oz ceramic mug", 9.99, ProductCategory.MUGS, "mug.jpg", 50, ProductStatus.ACTIVE);
    Product product4 = new Product("Espresso Machine", "Professional-grade machine", 299.99, ProductCategory.MACHINES, "espresso.jpg", 10, ProductStatus.ACTIVE);

    // Create list of orders
    List<Order> orderList = new ArrayList<>();

    // Order 1
    Order order1 = new Order(user, 0, OrderStatus.DELIVERED);
    order1.setOrderId(1);

    // Create OrderItems
    OrderItem item1_1 = new OrderItem(order1, product1, 2, product1.getPrice() * 2);
    OrderItem item1_2 = new OrderItem(order1, product3, 1, product3.getPrice());

    // Manually create the list and add items
    List<OrderItem> items1 = new ArrayList<>();
    items1.add(item1_1);
    items1.add(item1_2);
    order1.setOrderItems(items1);

    // Calculate total price
    order1.setTotalPrice(item1_1.getItemPrice() + item1_2.getItemPrice());
    orderList.add(order1);

    // Order 2
    Order order2 = new Order(user, 0, OrderStatus.PENDING);
    order2.setOrderId(2);

    // Create OrderItems
    OrderItem item2_1 = new OrderItem(order2, product2, 1, product2.getPrice());
    OrderItem item2_2 = new OrderItem(order2, product4, 1, product4.getPrice());
    OrderItem item2_3 = new OrderItem(order2, product3, 2, product3.getPrice() * 2);

    // Manually create the list and add items
    List<OrderItem> items2 = new ArrayList<>();
    items2.add(item2_1);
    items2.add(item2_2);
    items2.add(item2_3);
    order2.setOrderItems(items2);

    // Calculate total price
    order2.setTotalPrice(item2_1.getItemPrice() + item2_2.getItemPrice() + item2_3.getItemPrice());
    orderList.add(order2);

    // Set the orders list to the user
    user.setOrders(orderList);

    session = request.getSession();
    session.setAttribute("currentUser", user);
%>
<%---------------------------------------------------%>


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

                                        <a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-details?name=${product.name}">

                                            <img class="aspect__img" src="images/product/electronic/${product.image}.jpg" alt=""></a>
                                        <div class="product-o__action-wrap">
                                            <ul class="product-o__action-list">
                                                <li>

                                                    <a data-modal="modal"
                                                       data-modal-id="#quick-look"
                                                       data-tooltip="tooltip"
                                                       data-placement="top"
                                                       title="Quick View"
                                                       class="quick-look-trigger"
                                                       data-image="${product.image}"
                                                       data-name="${product.name}"
                                                       data-description="${product.description}"
                                                       data-stock="${product.stock}"
                                                       data-price="${product.price}">
                                                        <i class="fas fa-search-plus"></i>
                                                    </a>
                                                </li>
                                                <li>

                                                    <a data-modal="modal"
                                                       data-modal-id="#add-to-cart"
                                                       data-tooltip="tooltip"
                                                       data-placement="top"
                                                       title="Add to Cart"
                                                       class="add-to-cart-trigger"
                                                       data-image="${product.image}"
                                                       data-name="${product.name}"
                                                       data-stock="${product.stock}"
                                                       data-price="${product.price}">
                                                        <i class="fas fa-shopping-cart"></i>
                                                    </a>
                                                </li>

                                                <li>

                                                    <a data-modal="modal"
                                                       data-modal-id="#add-to-wishlist"
                                                       data-tooltip="tooltip"
                                                       data-placement="top"
                                                       title="Add to Wishlist"
                                                       class="add-to-wishlist-trigger"
                                                       data-image="${product.image}"
                                                       data-name="${product.name}"
                                                       data-price="${product.price}"><i class="fas fa-heart"></i></a>
                                                </li>


                                                <li>

                                                    <a data-modal="modal"
                                                       data-modal-id="checkout"
                                                       data-tooltip="tooltip"
                                                       data-placement="top"
                                                       title="Checkout"><i class="fas fa-plus"></i></a></li>

                                            </ul>
                                        </div>
                                    </div>

                                    <span class="product-o__category">

                                                            <a href="product-list?category=${product.category.name().toLowerCase()}">${product.category.name()}</a></span>

                                    <span class="product-o__name">

                                                            <a href="product-details?name=${product.name}">${product.name}</a></span>


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

                                       <a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-details?name=${product.name}">

                                           <img class="aspect__img" src="images/product/electronic/${product.image}.jpg" alt=""></a>
                                       <div class="product-o__action-wrap">
                                           <ul class="product-o__action-list">
                                               <li>

                                                   <a  class="quick-look-trigger"
                                                       data-image="${product.image}"
                                                       data-name="${product.name}"
                                                       data-description="${product.description}"
                                                       data-stock="${product.stock}"
                                                       data-price="${product.price}" data-modal="modal" data-modal-id="#quick-look" data-tooltip="tooltip" data-placement="top" title="Quick View"><i class="fas fa-search-plus"></i></a></li>
                                               <li>

                                                   <a class="add-to-cart-trigger"
                                                      data-image="${product.image}"
                                                      data-name="${product.name}"
                                                      data-stock="${product.stock}"
                                                      data-price="${product.price}" data-modal="modal" data-modal-id="#add-to-cart" data-tooltip="tooltip" data-placement="top" title="Add to Cart"><i class="fas fa-shopping-cart"></i></a>
                                               </li>

                                               <li>

                                                   <a class="add-to-wishlist-trigger"
                                                      data-image="${product.image}"
                                                      data-name="${product.name}"
                                                      data-price="${product.price}" data-modal="modal" data-modal-id="#add-to-wishlist" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist"><i class="fas fa-heart"></i></a>
                                               </li>


                                               <li>

                                                   <a data-modal="modal" data-modal-id="checkout" data-tooltip="tooltip" data-placement="top" title="Checkout"><i class="fas fa-plus"></i></a></li>

                                           </ul>
                                       </div>
                                   </div>

                                   <span class="product-o__category">

                                                            <a href="product-list?category=${product.category.name().toLowerCase()}">${product.category.name()}</a></span>

                                   <span class="product-o__name">

                                                            <a href="product-details?name=${product.name}">${product.name}</a></span>


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

                                        <a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-details?name=${product.name}">

                                            <img class="aspect__img" src="images/product/electronic/${product.image}.jpg" alt=""></a>
                                        <div class="product-o__action-wrap">
                                            <ul class="product-o__action-list">
                                                <li>

                                                    <a class="quick-look-trigger"
                                                       data-image="${product.image}"
                                                       data-name="${product.name}"
                                                       data-description="${product.description}"
                                                       data-stock="${product.stock}"
                                                       data-price="${product.price}" data-modal="modal" data-modal-id="#quick-look" data-tooltip="tooltip" data-placement="top" title="Quick View"><i class="fas fa-search-plus"></i></a></li>
                                                <li>

                                                    <a class="add-to-cart-trigger"
                                                       data-image="${product.image}"
                                                       data-name="${product.name}"
                                                       data-stock="${product.stock}"
                                                       data-price="${product.price}" data-modal="modal" data-modal-id="#add-to-cart" data-tooltip="tooltip" data-placement="top" title="Add to Cart"><i class="fas fa-shopping-cart"></i></a>
                                                </li>

                                                <li>

                                                    <a class="add-to-wishlist-trigger"
                                                       data-image="${product.image}"
                                                       data-name="${product.name}"
                                                       data-price="${product.price}" data-modal="modal" data-modal-id="#add-to-wishlist" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist"><i class="fas fa-heart"></i></a>
                                                </li>


                                                <li>

                                                    <a data-modal="modal" data-modal-id="checkout" data-tooltip="tooltip" data-placement="top" title="Checkout"><i class="fas fa-plus"></i></a></li>

                                            </ul>
                                        </div>
                                    </div>

                                    <span class="product-o__category">

                                                            <a href="product-list?category=${product.category.name().toLowerCase()}">${product.category.name()}</a></span>

                                    <span class="product-o__name">

                                                            <a href="product-details?name=${product.name}">${product.name}</a></span>


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

    <%@include file="commos/footer.jsp"%>



  <jsp:include page="commos/modals.jsp"/>



<%@include file="commos/script.html"%>
      <script src="js/custom-js/modals.js"></script>


</body>
</html>
