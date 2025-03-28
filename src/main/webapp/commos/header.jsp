<%--
  Created by IntelliJ IDEA.
  User: AHMED
  Date: 2025-03-28
  Time: 05:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>











<!-- HTML SECTION -->

<!--====== Main Header ======-->
<header class="header--style-2">

  <!--====== Nav 1 ======-->
  <nav class="primary-nav-wrapper">
    <div class="container">

      <!--====== Primary Nav ======-->
      <div class="primary-nav">


        <!--====== Main Logo ======-->

        <a class="main-logo" href="index.jsp">

          <img src="images/logo/logo-2.png" alt=""></a>
        <!--====== End - Main Logo ======-->



        <!--====== Dropdown Main plugin ======-->

        <div class="menu-init" id="navigation">

          <button class="btn btn--icon toggle-button fas fa-user-cog u-c-white" type="button"></button>

          <!--====== Menu ======-->
          <div class="ah-lg-mode">

            <span class="ah-close">✕ Close</span>

            <!--====== List ======-->
            <ul class="ah-list ah-list--design1 ah-list--link-color-white">
              <li>

                <a href="signup.html"><i class="fas fa-search-plus u-s-m-r-4"></i>

                  <span>About Us</span></a>
              </li>
              <li>

                <a href="signup.html"><i class="fas fa-envelope u-s-m-r-4"></i>

                  <span>Contact Us</span></a>
              </li>

              <li>

                <a href="signup.html"><i class="fas fa-user-plus u-s-m-r-4"></i>

                  <span>Register</span></a>
              </li>

              <li>

                <a href="signin.html"><i class="fas fa-lock u-s-m-r-4"></i>

                  <span>Login</span></a>
              </li>
              <li>
                <a href="dashboard.jsp"><i class="fas fa-user-cog u-s-m-r-4"></i>

                  <span>Account</span></a>
              </li>
              <li>

                <a href="signup.html"><i class="fas fa-lock-open u-s-m-r-4"></i>

                  <span>Logout</span></a></li>
              </li>
            </ul>
            <!--====== End - List ======-->
          </div>
          <!--====== End - Menu ======-->
        </div>
        <!--====== End - Dropdown Main plugin ======-->

      </div>
      <!--====== End - Primary Nav ======-->
    </div>
  </nav>
  <!--====== End - Nav 1 ======-->


  <!--====== Nav 2 ======-->
  <nav class="secondary-nav-wrapper">
    <div class="container">

      <!--====== Secondary Nav ======-->
      <div class="secondary-nav">


        <!--====== Dropdown Main plugin ======-->
        <div class="menu-init" id="navigation2">

          <button class="btn btn--icon toggle-button toggle-button--white fas fa-truck u-c-white" type="button"></button>

          <!--====== Menu ======-->
          <div class="ah-lg-mode">

            <span class="ah-close">✕ Close</span>

            <!--====== List ======-->
            <ul class="ah-list ah-list--design1 ah-list--link-color-white">
              <li>

                <a href="shop-side-version-2.html">Coffee Beans</a></li>

              </li>
              <li>

                <a href="shop-side-version-2.html">Coffee Mugs</a>
              </li>
              <li>

                <a href="shop-side-version-2.html">Coffee Machines</a></li>
            </ul>
            <!--====== End - List ======-->
          </div>
          <!--====== End - Menu ======-->
        </div>
        <!--====== End - Dropdown Main plugin ======-->

        <!--====== Search Form ======-->
        <form class="main-form">

          <label for="main-search"></label>

          <input class="input-text input-text--border-radius input-text--style-2" type="text" id="main-search" placeholder="Search">

          <button class="btn btn--icon fas fa-search main-search-button" type="submit"></button></form>
        <!--====== End - Search Form ======-->



        <!--====== Dropdown Main plugin ======-->
        <div class="menu-init" id="navigation3">

          <button class="btn btn--icon toggle-button toggle-button--white fas fa-shopping-bag toggle-button-shop u-c-white delte" type="button"></button>



          <!--====== Menu ======-->
          <div class="ah-lg-mode">

            <span class="ah-close">✕ Close</span>

            <!--====== List ======-->
            <ul class="ah-list ah-list--design1 ah-list--link-color-white">
              <li class="has-dropdown">


                <a class="mini-cart-shop-link"><i class="far fa-heart"></i>
                  <span class="total-item-round">2</span></a>

                <!--====== Dropdown ======-->

                <span class="js-menu-toggle"></span>
                <div class="mini-cart">

                  <!--====== Mini Product Container ======-->
                  <div class="mini-product-container gl-scroll u-s-m-b-15">

                    <!--====== Card for mini wishlist ======-->
                    <div class="card-mini-product">
                      <div class="mini-product">
                        <div class="mini-product__image-wrapper">

                          <a class="mini-product__link" href="product-detail.html">

                            <img class="u-img-fluid" src="images/product/men/product8.jpg" alt=""></a></div>
                        <div class="mini-product__info-wrapper">

                                                            <span class="mini-product__category">

                                                                <a href="shop-side-version-2.html">Men Clothing</a></span>

                          <span class="mini-product__name">

                                                                <a href="product-detail.html">New Fashion D Nice Elegant</a></span>

                          <span class="mini-product__quantity">1 x</span>

                          <span class="mini-product__price">$8</span></div>
                      </div>

                      <a class="mini-product__delete-link far fa-trash-alt"></a>
                    </div>
                    <!--====== End - Card for mini wishlist ======-->
                  </div>
                  <!--====== End - Mini Product Container ======-->

                  <!--====== Mini Product Statistics ======-->
                  <div class="mini-product-stat">
                    <div class="mini-action">

                      <a class="mini-link btn--e-brand-b-2" href="">PROCEED TO CART</a>

                      <a class="mini-link btn--e-transparent-secondary-b-2" href="wishlist.html">VIEW WISHLIST</a></div>
                  </div>
                  <!--====== End - Mini Product Statistics ======-->

                </div>
                <!--====== End - Dropdown ======-->


                </li>
              <li class="has-dropdown">

                <a class="mini-cart-shop-link"><i class="fas fa-shopping-bag"></i>

                  <span class="total-item-round">2</span></a>

                <!--====== Dropdown ======-->

                <span class="js-menu-toggle"></span>
                <div class="mini-cart">

                  <!--====== Mini Product Container ======-->
                  <div class="mini-product-container gl-scroll u-s-m-b-15">

                    <!--====== Card for mini cart ======-->
                    <div class="card-mini-product">
                      <div class="mini-product">
                        <div class="mini-product__image-wrapper">

                          <a class="mini-product__link" href="product-detail.html">

                            <img class="u-img-fluid" src="images/product/men/product8.jpg" alt=""></a></div>
                        <div class="mini-product__info-wrapper">

                                                            <span class="mini-product__category">

                                                                <a href="shop-side-version-2.html">Men Clothing</a></span>

                          <span class="mini-product__name">

                                                                <a href="product-detail.html">New Fashion D Nice Elegant</a></span>

                          <span class="mini-product__quantity">1 x</span>

                          <span class="mini-product__price">$8</span></div>
                      </div>

                      <a class="mini-product__delete-link far fa-trash-alt"></a>
                    </div>
                    <!--====== End - Card for mini cart ======-->
                  </div>
                  <!--====== End - Mini Product Container ======-->


                  <!--====== Mini Product Statistics ======-->
                  <div class="mini-product-stat">
                    <div class="mini-total">

                      <span class="subtotal-text">SUBTOTAL</span>

                      <span class="subtotal-value">$16</span></div>
                    <div class="mini-action">

                      <a class="mini-link btn--e-brand-b-2" href="checkout.html">PROCEED TO CHECKOUT</a>

                      <a class="mini-link btn--e-transparent-secondary-b-2" href="cart.html">VIEW CART</a></div>
                  </div>
                  <!--====== End - Mini Product Statistics ======-->
                </div>
                <!--====== End - Dropdown ======-->
              </li>
            </ul>
            <!--====== End - List ======-->
          </div>
          <!--====== End - Menu ======-->
        </div>
        <!--====== End - Dropdown Main plugin ======-->
      </div>
      <!--====== End - Secondary Nav ======-->
    </div>
  </nav>
  <!--====== End - Nav 2 ======-->
</header>
<!--====== End - Main Header ======-->