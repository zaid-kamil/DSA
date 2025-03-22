class Product{
    String name;
    int productId;
    int price;
}

interface ProductDao{
    int addProduct(Product product);
    int removeProduct(Product product);
    int updateProduct(Product product);
    Product getProduct(int productId);
}

class ProductDoaImpl implements ProductDao{

    @Override
    public int addProduct(Product product) {
        System.out.println("Product added successfully");
        return 1;
    }

    @Override
    public int removeProduct(Product product) {
        System.out.println("Product removed successfully");
        return 1;
    }

    @Override
    public int updateProduct(Product product) {
        System.out.println("Product updated successfully");
        return 1;
    }

    @Override
    public Product getProduct(int productId) {
        return new Product();
    }
}

// proxy class
class ProductDaoProxy implements ProductDao{

    ProductDao productDao = new ProductDoaImpl();

    @Override
    public int addProduct(Product product) {
        if (product.price > 1000) {
            System.out.println("Product price is too high");
            return 0;
        }
        return productDao.addProduct(product);
    }

    @Override
    public int removeProduct(Product product) {
        if (product.productId < 0) {
            System.out.println("Invalid product id");
            return 0;
        }
        return productDao.removeProduct(product);

    }

    @Override
    public int updateProduct(Product product) {
        if (product.productId < 0) {
            System.out.println("Invalid product id");
            return 0;
        }
        return productDao.updateProduct(product);
    }

    @Override
    public Product getProduct(int productId) {
        if (productId < 0) {
            System.out.println("Invalid product id");
            return null;
        }
        return productDao.getProduct(productId);
    }    
}

public class ProxyPattern {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDaoProxy();
        Product product = new Product();
        product.productId = -1;
        product.name = "Laptop";
        product.price = 10000;
        productDao.addProduct(product);
        productDao.removeProduct(product);
        productDao.updateProduct(product);
        System.out.println(productDao.getProduct(1));
    }
}