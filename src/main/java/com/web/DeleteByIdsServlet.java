package com.web;

import com.alibaba.fastjson.JSON;
import com.pojo.Brand;
import com.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/deleteByIdsServlet")
public class DeleteByIdsServlet extends HttpServlet {
    private final BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求体数据 前端送过来的是JSON数据，所以不能用getParameter()
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //Turn JSON object into java object
        int[] ids = JSON.parseObject(params, int[].class);
        System.out.println(Arrays.toString(ids));

        //Call sql method
        service.deleteByIds(ids);

        //Write result to front end
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
