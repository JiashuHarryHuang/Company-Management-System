package com.web;

import com.alibaba.fastjson.JSON;
import com.pojo.Brand;
import com.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    private final BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get the id sent from brand.jsp
        String id = request.getParameter("id");
        //Call mysql method
        Brand brand = service.selectById(Integer.parseInt(id));
        //Turn into JSON
        String jsonString = JSON.toJSONString(brand);
        // 响应数据 application/json text/json
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
