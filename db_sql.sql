CREATE DATABASE webapp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

use webapp;

-- Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL,
    credit_balance DOUBLE NOT NULL,
    phone VARCHAR(15) NOT NULL UNIQUE,
    INDEX (email),
    INDEX (phone)
) ENGINE=InnoDB;

-- Products Table
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL,
    category ENUM('BEANS','MUGS','MACHINES','OTHER') NOT NULL,
    image VARCHAR(255) NOT NULL,
    stock INT NOT NULL
) ENGINE=InnoDB;

-- Cart Table (1:1 with User)
CREATE TABLE cart (
    cart_id INT PRIMARY KEY,
    CONSTRAINT fk_cart_user
        FOREIGN KEY (cart_id) 
        REFERENCES users(user_id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

-- Cart Items Table
CREATE TABLE cart_items (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    cart_id INT NOT NULL,
    CONSTRAINT fk_cart_item_product
        FOREIGN KEY (product_id)
        REFERENCES products(product_id),
    CONSTRAINT fk_cart_item_cart
        FOREIGN KEY (cart_id)
        REFERENCES cart(cart_id)
) ENGINE=InnoDB;

-- Wishlist Table (1:1 with User)
CREATE TABLE wishlist (
    wishlist_id INT PRIMARY KEY,
    CONSTRAINT fk_wishlist_user
        FOREIGN KEY (wishlist_id) 
        REFERENCES users(user_id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

-- Wishlist Items Table
CREATE TABLE wishlist_items (
    wishlist_item_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    wishlist_id INT NOT NULL,
    CONSTRAINT fk_wishlist_item_product
        FOREIGN KEY (product_id)
        REFERENCES products(product_id),
    CONSTRAINT fk_wishlistitem_wishlist
        FOREIGN KEY (wishlist_id)
        REFERENCES wishlist(wishlist_id)
) ENGINE=InnoDB;

-- Orders Table
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DOUBLE NOT NULL,
    status ENUM('PENDING','CANCELLED','ACCEPTED') NOT NULL,
    CONSTRAINT fk_order_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
) ENGINE=InnoDB;

-- Order Items Table
CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    item_price DOUBLE NOT NULL,
    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
        REFERENCES orders(order_id),
    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id)
        REFERENCES products(product_id)
) ENGINE=InnoDB;