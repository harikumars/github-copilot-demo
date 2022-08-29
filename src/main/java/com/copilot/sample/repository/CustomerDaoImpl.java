package com.copilot.sample.repository;

public class CustomerDaoImpl implements CustomerDao {

    //Autowire jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    @Override
    public Customer findById(String customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{customerId}, new CustomerRowMapper());
    }

    @Override
    public Customer create(Customer customer) {
        String sql = "INSERT INTO customer (customer_id, customer_name, customer_address, customer_phone, customer_email) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerPhone(), customer.getCustomerEmail());
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        String sql = "UPDATE customer SET customer_name = ?, customer_address = ?, customer_phone = ?, customer_email = ? WHERE customer_id = ?";
        jdbcTemplate.update(sql, customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerPhone(), customer.getCustomerEmail(), customer.getCustomerId());
        return customer;
    }

    @Override
    public void delete(Customer customer) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        jdbcTemplate.update(sql, customer.getCustomerId());
    }

    @Override
    public void deleteById(String customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        jdbcTemplate.update(sql, customerId);
    }

    @Override
    public List<Customer> findByName(String customerName) {
        String sql = "SELECT * FROM customer WHERE customer_name = ?";
        return jdbcTemplate.query(sql, new Object[]{customerName}, new CustomerRowMapper());
    }

    @Override
    public List<Customer> findByAddress(String customerAddress) {
        String sql = "SELECT * FROM customer WHERE customer_address = ?";
        return jdbcTemplate.query(sql, new Object[]{customerAddress}, new CustomerRowMapper());
    }

    @Override
    public List<Customer> findByPhone(String customerPhone) {
        String sql = "SELECT * FROM customer WHERE customer_phone = ?";
        return jdbcTemplate.query(sql, new Object[]{customerPhone}, new CustomerRowMapper());
    }

    @Override
    public List<Customer> findByEmail(String customerEmail) {
        String sql = "SELECT * FROM customer WHERE customer_email = ?";
        return jdbcTemplate.query(sql, new Object[]{customerEmail}, new CustomerRowMapper());
    }

}
