package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductType;
import com.codegym.service.ProductService;
import com.codegym.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    public ProductService productService;

    @Autowired
    public ProductTypeService productTypeService;

    @ModelAttribute("productTypes")
    public Iterable<ProductType> productType(){
        return productTypeService.findAll();
    }

    @GetMapping("product")
    public ModelAndView showList(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Product> products;

        if (s.isPresent()){
            products = productService.findAllByNameContaining(s.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateProduct(){
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView saveProduct(@Validated @ModelAttribute("product") Product product, BindingResult result){
        if (result.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("product/create");
            return modelAndView;
        }
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "Create product successfully");
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Product product = productService.findById(id);
        ModelAndView modelAndView = null;
        if (product != null){
            modelAndView = new ModelAndView("product/edit");
            modelAndView.addObject("product", product);
        } else {
            modelAndView = new ModelAndView("error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit-product")
    public ModelAndView updateProduct(@Validated @ModelAttribute("product") Product product, BindingResult result){
        if (result.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("product/edit");
            return modelAndView;
        }
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Edit product successfully");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id){
        productService.delete(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        return modelAndView;
    }
}
