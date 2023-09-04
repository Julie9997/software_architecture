package org.julie.architecture.hw10;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class OrderRepositoryImpl implements OrderRepository {

    private final Cache<Integer, Order> orderCache;
    private static final String DB_URL = "jdbc:sqlite:orders.db";

    public OrderRepositoryImpl() {
        this.orderCache = Caffeine.newBuilder().build();
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS orders (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "total_cost REAL" +
                    ")";
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS order_items (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "order_id INTEGER," +
                    "product_id INTEGER," +
                    "quantity INTEGER," +
                    "FOREIGN KEY (order_id) REFERENCES orders(id)," +
                    "FOREIGN KEY (product_id) REFERENCES products(id)" +
                    ")";
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "price REAL" +
                    ")";
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Order order) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO orders (total_cost) VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDouble(1, order.calculateTotalCost());
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                order.setId(orderId);

                List<OrderItem> orderItems = order.getItems();
                for (OrderItem orderItem : orderItems) {
                    saveOrderItem(orderItem, orderId);
                }

                // Сохранение заказа в кэше
                orderCache.put(order.getId(), order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сохраняет позицию заказа в базу данных.
     *
     * @param orderItem позиция заказа для сохранения
     * @param orderId   идентификатор заказа
     */
    private void saveOrderItem(OrderItem orderItem, int orderId) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)")) {

            pstmt.setInt(1, orderId);
            pstmt.setInt(2, orderItem.getProduct().getId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.executeUpdate();

            // Инвалидация соответствующего заказа в кэше
            orderCache.invalidate(orderId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает заказ по id.
     *
     * @param id идентификатор заказа
     * @return заказ с указанным идентификатором или null, если его нет
     */
    @Override
    public Order getById(int id) {

        Order order = orderCache.getIfPresent(id);

        if (order == null) {
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmt = conn.prepareStatement(
                         "SELECT * FROM orders WHERE id = ?")) {

                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    double totalCost = rs.getDouble("total_cost");
                    order = new Order(id, new ArrayList<OrderItem>());

                    List<OrderItem> orderItems = getOrderItemsByOrderId(id);
                    order.getItems().addAll(orderItems);
                    // Сохранение заказа в кэше
                    orderCache.put(id, order);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return order;
    }

    /**
     * Получает все заказы из базы данных.
     *
     * @return список объектов Order, представляющих все заказы
     */
    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM orders")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                double totalCost = rs.getDouble("total_cost");

                // Попытка получить заказ из кэша
                Order order = orderCache.getIfPresent(id);

                if (order == null) {
                    order = new Order(id, new ArrayList<OrderItem>());
                    List<OrderItem> orderItems = getOrderItemsByOrderId(id);
                    order.getItems().addAll(orderItems);

                    // Сохранение заказа в кэше
                    orderCache.put(id, order);
                }

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM order_items WHERE order_id = ?")) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int orderItemId = rs.getInt("id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");

                Product product = getProductById(productId);
                if (product != null) {
                    OrderItem orderItem = new OrderItem(orderItemId, product, quantity);
                    orderItems.add(orderItem);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }
    /**
     * Возвращает продукт из базы данных на основе заданного идентификатора продукта.
     *
     * @param  productId  идентификатор продукта для получения
     * @return            объект Product, представляющий полученный продукт, или null, если продукт не найден
     */
    public Product getProductById(int productId) {
        Product product = null;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM products WHERE id = ?")) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");

                product = new Product(productId, name, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

}

