package cn.com.hellowood.dynamicdatasource.controller;

import cn.com.hellowood.dynamicdatasource.configuration.DynamicDataSourceContextHolder;
import cn.com.hellowood.dynamicdatasource.configuration.TargetDataSource;
import cn.com.hellowood.dynamicdatasource.modal.Product;
import cn.com.hellowood.dynamicdatasource.modal.RoleDO;
import cn.com.hellowood.dynamicdatasource.modal.TbDO;
import cn.com.hellowood.dynamicdatasource.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Product controller
 *
 * @author HelloWood
 * @date 2017-07-11 11:38
 * @Email hellowoodes@gmail.com
 */

@RestController
@RequestMapping("/product")
public class ProduceController {

    @Autowired
    private ProductService productService;

    /**
     * Get product by id
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId) throws Exception {
        return productService.select(productId);
    }

    /**
     * Get all product
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/master")
    @TargetDataSource("master")
    public List<Product> getAllMasterProduct() {
        return productService.selectAll();
    }

    /**
     * Get all product
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/slave")
    @TargetDataSource("slave")
    public List<Product> getAllSlaveProduct() {
        return productService.selectAll();
    }

    @GetMapping("/salveRole")
    @TargetDataSource("slave")
    public Map<String, Object> getAllRole() {
        Map<String,Object> map=new HashMap<>();
        map.put("a",productService.listAllRoles());
        DynamicDataSourceContextHolder.containDataSourceKey("master");
        map.put("b",productService.selectAll());
        return map;
    }

    @GetMapping("/salveThird")
    public Map<String, Object> getThird() {
        Map<String,Object> map=new HashMap<>();
        DynamicDataSourceContextHolder.setDataSourceKey("third");
        map.put("third",productService.selectTbAll());
        DynamicDataSourceContextHolder.setDataSourceKey("master");
        map.put("master",productService.selectAll());
        DynamicDataSourceContextHolder.setDataSourceKey("slave");
        map.put("slave",productService.listAllRoles());
        return map;
    }

    /**
     * Update product by id
     *
     * @param productId
     * @param newProduct
     * @return
     * @throws Exception
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product newProduct) throws Exception {
        return productService.update(productId, newProduct);
    }

    /**
     * Delete product by id
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable("id") long productId) throws Exception {
        return productService.delete(productId);
    }

    /**
     * Save product
     *
     * @param newProduct
     * @return
     * @throws Exception
     */
    @PostMapping
    public boolean addProduct(@RequestBody Product newProduct) throws Exception {
        return productService.add(newProduct);
    }
}
