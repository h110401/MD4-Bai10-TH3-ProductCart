package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Cart;
import rikkei.academy.model.Product;
import rikkei.academy.service.product.IProductService;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {

    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setUpCart() {
        return new Cart();
    }


    @GetMapping("/shop")
    public String getHome(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/index";
    }

    @GetMapping("/add/{id}")
    public String addToCart(
            @PathVariable("id") Optional<Product> product,
            @ModelAttribute Cart cart,
            @RequestParam String action
    ) {
        if (!product.isPresent()) {
            return "/error.404";
        }
        if (action.equals("increase")) {
            cart.addProduct(product.get());
            return "redirect:/shopping-cart";
        } else if (action.equals("decrease")) {
            cart.removeProduct(product.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(product.get());
        return "redirect:/shop";
    }
}
