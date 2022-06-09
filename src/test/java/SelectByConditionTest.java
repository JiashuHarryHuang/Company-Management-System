import com.pojo.Brand;
import com.pojo.PageBean;
import com.service.BrandService;

public class SelectByConditionTest {
    public static void main(String[] args) {
        BrandService service = new BrandService();

        Brand brand = new Brand();
        brand.setCompanyName("华");
        brand.setBrandName("华");
        PageBean<Brand> brands = service.selectByPageAndCondition(1, 5, brand);
        System.out.println(brands);

    }
}
