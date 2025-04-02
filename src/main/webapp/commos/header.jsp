<%--
  Created by IntelliJ IDEA.
  User: AHMED
  Date: 2025-03-28
  Time: 05:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--====== Main Header ======-->
<header class="header--style-2">

  <!--====== Nav 1 ======-->
  <nav class="primary-nav-wrapper">
    <div class="container">

      <!--====== Primary Nav ======-->
      <div class="primary-nav">

        <!--====== Main Logo ======-->
        <a class="main-logo" href="index.jsp">
          <img src="images/logo/logo-2.png" alt="">
        </a>
        <!--====== End - Main Logo ======-->

        <!--====== Dropdown Main plugin ======-->
        <div class="menu-init" id="navigation">

          <button class="btn btn--icon toggle-button fas fa-user-alt u-c-white" type="button"></button>

          <!--====== Menu ======-->
          <div class="ah-lg-mode">

            <span class="ah-close">✕ Close</span>

            <!--====== List ======-->
            <ul class="ah-list ah-list--design1 ah-list--link-color-white">
              <li>

                <a href="about.jsp"><i class="fas fa-book-reader u-s-m-r-4"></i>

                  <span>About Us</span></a>
              </li>
              <li>

                <a href="faq.jsp"><i class="fas fa-clipboard u-s-m-r-4"></i>

                  <span>FAQ</span></a>
              </li>
              <li>

                <a href="contact.jsp"><i class="fas fa-envelope u-s-m-r-4"></i>

                  <span>Contact Us</span></a>
              </li>

              <% if(session.getAttribute("user") == null) { %>
              <!-- Show only when NOT logged in -->
              <li>

                <a href="register.jsp"><i class="fas fa-user-plus u-s-m-r-4"></i>

                  <span>Register</span></a>
              </li>
              <li>

                <a href="login.jsp"><i class="fas fa-sign-in-alt u-s-m-r-4"></i>

                  <span>Login</span></a>
              </li>
              <% } else { %>
              <!-- Show only when logged in -->
              <li>
                <a href="my-account.jsp"><i class="fas fa-user-cog u-s-m-r-4"></i>

                  <span>Account</span></a>
              </li>
              <li>

                <a href=""><i class="fas fa-sign-out-alt u-s-m-r-4"></i>

                  <span>Logout</span></a>
              </li>
              <% } %>
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

          <button class="btn btn--icon toggle-button toggle-button--white fas fa-shopping-bag u-c-white" type="button"></button>

          <!--====== Menu ======-->
          <div class="ah-lg-mode">

            <span class="ah-close">✕ Close</span>

            <!--====== List ======-->
            <ul class="ah-list ah-list--design1 ah-list--link-color-white">
              <li>

                <a href="product-list.jsp">Coffee Beans</a>
              </li>
              <li>

                <a href="product-list.jsp">Coffee Mugs</a>
              </li>
              <li>

                <a href="product-list.jsp">Coffee Machines</a></li>
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
          <button class="btn btn--icon fas fa-search main-search-button" type="submit"></button>
        </form>
        <!--====== End - Search Form ======-->

        <!--====== Dropdown Main plugin ======-->
        <div class="menu-init" id="navigation3">

          <button class="btn btn--icon toggle-button toggle-button--white fas fa-shopping-cart toggle-button-shop u-c-white" type="button"></button>

          <!--====== Menu ======-->
          <div class="ah-lg-mode">

            <span class="ah-close">✕ Close</span>

            <!--====== List ======-->
            <ul class="ah-list ah-list--design1 ah-list--link-color-white">
              <li>
                <a href="index.jsp"><i class="fas fa-home"></i></a>
              </li>
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

                          <a class="mini-product__link" href="product-details.jsp">

                            <img class="u-img-fluid" src="images/product/men/product8.jpg" alt=""></a></div>
                        <div class="mini-product__info-wrapper">

                                                            <span class="mini-product__category">

                                                                <a href="product-list.jsp">Men Clothing</a></span>

                          <span class="mini-product__name">

                                                                <a href="product-details.jsp">New Fashion D Nice Elegant</a></span>

                          <span class="mini-product__quantity">1 x</span>
                          <span class="mini-product__price">$8</span>
                        </div>
                      </div>
                      <a class="mini-product__delete-link far fa-trash-alt"></a>
                    </div>
                    <!--====== End - Card for mini wishlist ======-->
                  </div>
                  <!--====== End - Mini Product Container ======-->

                  <!--====== Mini Product Statistics ======-->
                  <div class="mini-product-stat">
                    <div class="mini-action">

                      <a class="mini-link btn--e-brand-b-2" href="cart.jsp">PROCEED TO CART</a>

                      <a class="mini-link btn--e-transparent-secondary-b-2" href="wishlist.jsp">VIEW WISHLIST</a></div>
                  </div>
                  <!--====== End - Mini Product Statistics ======-->

                </div>
                <!--====== End - Dropdown ======-->
              </li>
              <li class="has-dropdown">
                <a class="mini-cart-shop-link"><i class="fas fa-shopping-cart"></i>
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

                          <a class="mini-product__link" href="product-details.jsp">

                            <img class="u-img-fluid" src="images/product/men/product8.jpg" alt=""></a></div>
                        <div class="mini-product__info-wrapper">

                                                            <span class="mini-product__category">

                                                                <a href="product-list.jsp">Men Clothing</a></span>

                          <span class="mini-product__name">

                                                                <a href="product-details.jsp">New Fashion D Nice Elegant</a></span>

                          <span class="mini-product__quantity">1 x</span>
                          <span class="mini-product__price">$8</span>
                        </div>
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
                      <span class="subtotal-value">$16</span>
                    </div>
                    <div class="mini-action">

                      <a class="mini-link btn--e-brand-b-2" href="checkout.jsp">PROCEED TO CHECKOUT</a>

                      <a class="mini-link btn--e-transparent-secondary-b-2" href="cart.jsp">VIEW CART</a></div>
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
