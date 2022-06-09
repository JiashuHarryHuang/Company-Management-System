package com.web;

import com.alibaba.fastjson.JSON;
import com.pojo.Brand;
import com.pojo.PageBean;
import com.service.BrandService;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/selectByConditionServlet")
public class SelectByConditionServlet extends HttpServlet {
    private final BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Get current page number and page size from front end
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        //Get the brand data from front end
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
        System.out.println(brand);

        //Use Service to store query result into an object
        PageBean<Brand> pageBean = service.selectByPageAndCondition(currentPage, pageSize, brand);
        System.out.println(pageBean);

        //Turn pageBean into JSON and send back to front end
        String JSONString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }
}
