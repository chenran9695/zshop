package com.cr.zshop.params;

/**
 * @author ：cr
 * @date ：Created in 2019/3/21 20:27
 * @description：商品传入后台查询的参数集$
 * @modified By：cr
 * @version: $
 */
public class ProductParams {
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Integer productTypeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
}
