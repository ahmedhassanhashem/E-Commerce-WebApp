CREATE TABLE `User`(
    `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NULL,
    `email` VARCHAR(255) NOT NULL,
    `credit_limit` DECIMAL(8, 2) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(255) NOT NULL,
    `cart_Items` JSON NOT NULL COMMENT 'product_id, name, description, product_price, category, image ,quantity'
);
ALTER TABLE
    `User` ADD UNIQUE `user_email_unique`(`email`);
CREATE TABLE `Product`(
    `product_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` TEXT NOT NULL,
    `product_price` DECIMAL(8, 2) NOT NULL,
    `category` VARCHAR(255) NOT NULL,
    `stock_quantity` INT NOT NULL,
    `gallery` VARCHAR(255) NOT NULL
);
CREATE TABLE `Order`(
    `order_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT NOT NULL,
    `total_price` DECIMAL(8, 2) NOT NULL,
    `status` ENUM('pending', 'cancelled', 'delivered') NOT NULL DEFAULT 'pending',
    `created_at` DATE NOT NULL
);
CREATE TABLE `Order_Item`(
    `order_item_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `order_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `order_item_quantity` INT NOT NULL,
    `Item_price` DECIMAL(8, 2) NOT NULL
);
ALTER TABLE
    `Order_Item` ADD CONSTRAINT `order_item_order_id_foreign` FOREIGN KEY(`order_id`) REFERENCES `Order`(`order_id`);
ALTER TABLE
    `Order` ADD CONSTRAINT `order_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `User`(`user_id`);
ALTER TABLE
    `Order_Item` ADD CONSTRAINT `order_item_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `Product`(`product_id`);